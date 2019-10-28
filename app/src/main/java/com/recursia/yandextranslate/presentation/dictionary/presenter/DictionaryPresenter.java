package com.recursia.yandextranslate.presentation.dictionary.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.recursia.yandextranslate.domain.dictionary.AddToDictionaryInteractor;
import com.recursia.yandextranslate.domain.dictionary.GetAllWordsInDictionaryInteractor;
import com.recursia.yandextranslate.domain.dictionary.SearchInDictionaryInteractor;
import com.recursia.yandextranslate.presentation.dictionary.mapper.WordPairToViewModelMapper;
import com.recursia.yandextranslate.presentation.dictionary.models.WordPairViewModel;
import com.recursia.yandextranslate.presentation.dictionary.view.DictionaryView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

@InjectViewState
public class DictionaryPresenter extends MvpPresenter<DictionaryView> {
    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private final AddToDictionaryInteractor mAddToDictionaryInteractor;
    private final SearchInDictionaryInteractor mSearchInDictionaryInteractor;
    private final GetAllWordsInDictionaryInteractor mGetAllWordsInDictionaryInteractor;
    private final WordPairToViewModelMapper mMapper;

    private final Subject<String> mTermSubject = BehaviorSubject.create();

    @Inject
    public DictionaryPresenter(
            AddToDictionaryInteractor mAddToDictionaryInteractor,
            SearchInDictionaryInteractor mSearchInDictionaryInteractor,
            GetAllWordsInDictionaryInteractor mGetAllWordsInDictionaryInteractor,
            WordPairToViewModelMapper mMapper) {
        this.mAddToDictionaryInteractor = mAddToDictionaryInteractor;
        this.mGetAllWordsInDictionaryInteractor = mGetAllWordsInDictionaryInteractor;
        this.mSearchInDictionaryInteractor = mSearchInDictionaryInteractor;
        this.mMapper = mMapper;
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
                .map(mMapper::transform)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleWordPairs, this::handleError);
        mCompositeDisposable.add(d);
    }

    private void initLiveSearch() {
        Disposable d = mTermSubject
                .debounce(300, TimeUnit.MILLISECONDS, Schedulers.computation())
                .distinctUntilChanged()
                .subscribe(this::updateDisplayedList);
        mCompositeDisposable.add(d);
    }

    private void updateDisplayedList(String term) {
        Log.i("TESTING1", term);
        Disposable d = mSearchInDictionaryInteractor.searchWords(term)
                .doOnSubscribe(disposable -> getViewState().showLoading())
                .map(mMapper::transform)
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
                .map(mMapper::transform)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(wordPairViewModel -> getViewState().addWord(wordPairViewModel),
                        throwable -> getViewState().showErrorMessage(throwable.getLocalizedMessage()));
        mCompositeDisposable.add(d);
    }

    public void onTextSubmitted(String text) {
        Disposable d = mSearchInDictionaryInteractor.searchWords(text)
                .doOnSubscribe(disposable -> getViewState().showLoading())
                .map(mMapper::transform)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleWordPairs, this::handleError);
        mCompositeDisposable.add(d);
    }

    public void onSwapButtonClicked() {
        getViewState().swapLanguages();
    }

    public void onTextChanged(String text) {
        Log.i("TESTING", text);
        mTermSubject.onNext(text);
    }
}
