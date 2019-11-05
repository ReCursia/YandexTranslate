package com.recursia.yandextranslate.presentation.dictionary.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.recursia.yandextranslate.Screens;
import com.recursia.yandextranslate.domain.dictionary.AddToDictionaryInteractor;
import com.recursia.yandextranslate.domain.dictionary.GetAllWordsInDictionaryInteractor;
import com.recursia.yandextranslate.domain.dictionary.MakeFavoriteWordPairInteractor;
import com.recursia.yandextranslate.domain.dictionary.SearchInDictionaryInteractor;
import com.recursia.yandextranslate.domain.dictionary.models.WordPair;
import com.recursia.yandextranslate.presentation.dictionary.view.DictionaryView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class DictionaryPresenter extends MvpPresenter<DictionaryView> {
    private final static int TIMEOUT = 500;
    private final static TimeUnit TIME_UNIT = TimeUnit.MILLISECONDS;

    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private final AddToDictionaryInteractor mAddToDictionaryInteractor;
    private final SearchInDictionaryInteractor mSearchInDictionaryInteractor;
    private final GetAllWordsInDictionaryInteractor mGetAllWordsInDictionaryInteractor;
    private final MakeFavoriteWordPairInteractor mMakeFavoriteWordPairInteractor;
    private final Subject<String> mSubject = BehaviorSubject.create();

    private final Router mRouter;

    @Inject
    public DictionaryPresenter(AddToDictionaryInteractor mAddToDictionaryInteractor,
                               SearchInDictionaryInteractor mSearchInDictionaryInteractor,
                               GetAllWordsInDictionaryInteractor mGetAllWordsInDictionaryInteractor,
                               MakeFavoriteWordPairInteractor mMakeFavoriteWordPairInteractor,
                               Router router) {
        this.mAddToDictionaryInteractor = mAddToDictionaryInteractor;
        this.mSearchInDictionaryInteractor = mSearchInDictionaryInteractor;
        this.mGetAllWordsInDictionaryInteractor = mGetAllWordsInDictionaryInteractor;
        this.mMakeFavoriteWordPairInteractor = mMakeFavoriteWordPairInteractor;
        this.mRouter = router;
    }

    @Override
    public void onDestroy() {
        mCompositeDisposable.dispose();
    }

    @Override
    protected void onFirstViewAttach() {
        initLiveSearch();
        initScreen();
    }

    private void initScreen() {
        Disposable d = mGetAllWordsInDictionaryInteractor.getAllWords()
                .doOnSubscribe(disposable -> getViewState().showLoading())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleWordPairs, this::handleError);
        mCompositeDisposable.add(d);
    }

    private void initLiveSearch() {
        Disposable d = mSubject
                .debounce(TIMEOUT, TIME_UNIT, AndroidSchedulers.mainThread())
                .distinctUntilChanged()
                .subscribe(this::updateDisplayedList);
        mCompositeDisposable.add(d);
    }

    private void updateDisplayedList(String text) {
        Disposable d = mSearchInDictionaryInteractor.searchWords(text)
                .doOnSubscribe(disposable -> getViewState().showLoading())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleWordPairs, this::handleError);
        mCompositeDisposable.add(d);
    }

    private void handleWordPairs(List<WordPair> wordPairs) {
        getViewState().setWords(wordPairs);
        getViewState().hideLoading();
    }

    private void handleError(Throwable t) {
        getViewState().showErrorMessage(t.getLocalizedMessage());
        getViewState().hideLoading();
    }

    public void onAddButtonClicked(String text, String translatedFrom, String translatedTo) {
        Disposable d = mAddToDictionaryInteractor.addWord(text, translatedFrom, translatedTo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(wordPairViewModel -> getViewState().addWord(wordPairViewModel),
                        throwable -> getViewState().showErrorMessage(throwable.getLocalizedMessage()));
        mCompositeDisposable.add(d);
    }

    public void onSwapButtonClicked() {
        getViewState().swapLanguages();
    }

    public void onQueryTextChanged(String text) {
        mSubject.onNext(text);
    }

    public void onWordPairClicked(WordPair wordPair, int position) {
        if (!wordPair.isFavorite()) makeFavoriteWordPair(wordPair, position);
    }

    private void makeFavoriteWordPair(WordPair wordPair, int position) {
        Disposable d = mMakeFavoriteWordPairInteractor.makeFavorite(wordPair)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> {
                    wordPair.setFavorite(true);
                    getViewState().updateWord(wordPair, position);
                })
                .doOnError(throwable -> getViewState().showErrorMessage(throwable.getLocalizedMessage()))
                .subscribe();
        mCompositeDisposable.add(d);
    }

    public void onItemFavoriteClicked() {
        mRouter.navigateTo(new Screens.FavoriteScreen());
    }
}
