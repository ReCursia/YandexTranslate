package com.recursia.yandextranslate.presentation.dictionary.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.recursia.yandextranslate.domain.dictionary.AddToDictionaryInteractor;
import com.recursia.yandextranslate.domain.dictionary.GetAllWordsInDictionaryInteractor;
import com.recursia.yandextranslate.domain.dictionary.MakeFavoriteWordPairInteractor;
import com.recursia.yandextranslate.domain.dictionary.SearchInDictionaryInteractor;
import com.recursia.yandextranslate.domain.dictionary.models.WordPair;
import com.recursia.yandextranslate.presentation.dictionary.mapper.ViewModelToWordPairMapper;
import com.recursia.yandextranslate.presentation.dictionary.mapper.WordPairToViewModelMapper;
import com.recursia.yandextranslate.presentation.dictionary.models.WordPairViewModel;
import com.recursia.yandextranslate.presentation.dictionary.view.DictionaryView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

@InjectViewState
public class DictionaryPresenter extends MvpPresenter<DictionaryView> {
    private final static int TIMEOUT = 500;
    private final static TimeUnit TIME_UNIT = TimeUnit.MILLISECONDS;

    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private final AddToDictionaryInteractor mAddToDictionaryInteractor;
    private final SearchInDictionaryInteractor mSearchInDictionaryInteractor;
    private final GetAllWordsInDictionaryInteractor mGetAllWordsInDictionaryInteractor;
    private final MakeFavoriteWordPairInteractor mMakeFavoriteWordPairInteractor;
    private final WordPairToViewModelMapper mWordPairToViewModelMapper;
    private final ViewModelToWordPairMapper mViewModelToWordPairMapper;
    private final Subject<String> mTermSubject = BehaviorSubject.create();

    @Inject
    public DictionaryPresenter(AddToDictionaryInteractor mAddToDictionaryInteractor,
                               SearchInDictionaryInteractor mSearchInDictionaryInteractor,
                               GetAllWordsInDictionaryInteractor mGetAllWordsInDictionaryInteractor,
                               MakeFavoriteWordPairInteractor mMakeFavoriteWordPairInteractor,
                               WordPairToViewModelMapper mWordPairToViewModelMapper,
                               ViewModelToWordPairMapper mViewModelToWordPairMapper) {
        this.mAddToDictionaryInteractor = mAddToDictionaryInteractor;
        this.mSearchInDictionaryInteractor = mSearchInDictionaryInteractor;
        this.mGetAllWordsInDictionaryInteractor = mGetAllWordsInDictionaryInteractor;
        this.mMakeFavoriteWordPairInteractor = mMakeFavoriteWordPairInteractor;
        this.mWordPairToViewModelMapper = mWordPairToViewModelMapper;
        this.mViewModelToWordPairMapper = mViewModelToWordPairMapper;
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
                .map(mWordPairToViewModelMapper::transform)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleWordPairs, this::handleError);
        mCompositeDisposable.add(d);
    }

    private void initLiveSearch() {
        Disposable d = mTermSubject
                .debounce(TIMEOUT, TIME_UNIT, AndroidSchedulers.mainThread())
                .distinctUntilChanged()
                .subscribe(this::updateDisplayedList);
        mCompositeDisposable.add(d);
    }

    private void updateDisplayedList(String term) {
        Disposable d = mSearchInDictionaryInteractor.searchWords(term)
                .doOnSubscribe(disposable -> getViewState().showLoading())
                .map(mWordPairToViewModelMapper::transform)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleWordPairs, this::handleError);
        mCompositeDisposable.add(d);
    }

    private void handleWordPairs(List<WordPairViewModel> wordPairs) {
        getViewState().setWords(wordPairs);
        getViewState().hideLoading(); //TODO doOnFinally
    }

    private void handleError(Throwable t) {
        getViewState().showErrorMessage(t.getLocalizedMessage());
        getViewState().hideLoading();
    }

    public void onAddButtonClicked(String text, String translatedFrom, String translatedTo) {
        Disposable d = mAddToDictionaryInteractor.addWord(text, translatedFrom, translatedTo)
                .map(mWordPairToViewModelMapper::transform)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(wordPairViewModel -> getViewState().addWord(wordPairViewModel),
                        throwable -> getViewState().showErrorMessage(throwable.getLocalizedMessage()));
        mCompositeDisposable.add(d);
    }

    public void onSwapButtonClicked() {
        getViewState().swapLanguages();
    }

    public void onTextChanged(String text) {
        mTermSubject.onNext(text);
    }

    public void onWordPairClicked(WordPairViewModel viewModel, int position) {
        WordPair wordPair = mViewModelToWordPairMapper.transform(viewModel);
        if (!wordPair.isFavorite()) makeFavoriteWordPair(wordPair, position);
    }

    private void makeFavoriteWordPair(WordPair wordPair, int position) {
        Disposable d = Completable.fromAction(() -> mMakeFavoriteWordPairInteractor.makeFavorite(wordPair))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    wordPair.setFavorite(true);
                    getViewState().updateWord(mWordPairToViewModelMapper.transform(wordPair), position);
                })
                .subscribe();
        mCompositeDisposable.add(d);
    }

    public void onItemFavoriteClicked() {
        getViewState().openFavoriteScreen();
    }
}
