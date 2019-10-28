package com.recursia.yandextranslate.presentation.dictionary.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.recursia.yandextranslate.domain.dictionary.AddToDictionaryInteractor;
import com.recursia.yandextranslate.domain.dictionary.GetAllWordsInDictionaryInteractor;
import com.recursia.yandextranslate.domain.dictionary.SearchInDictionaryInteractor;
import com.recursia.yandextranslate.presentation.dictionary.mapper.WordPairToViewModelMapper;
import com.recursia.yandextranslate.presentation.dictionary.models.WordPairViewModel;
import com.recursia.yandextranslate.presentation.dictionary.view.DictionaryView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

@InjectViewState
public class DictionaryPresenter extends MvpPresenter<DictionaryView> {
    private final CompositeDisposable mCompositeDisposable;
    private final AddToDictionaryInteractor mAddToDictionaryInteractor;
    private final SearchInDictionaryInteractor mSearchInDictionaryInteractor;
    private final GetAllWordsInDictionaryInteractor mGetAllWordsInDictionaryInteractor;
    private final WordPairToViewModelMapper mMapper;

    @Inject
    public DictionaryPresenter(
            AddToDictionaryInteractor mAddToDictionaryInteractor,
            SearchInDictionaryInteractor mSearchInDictionaryInteractor,
            GetAllWordsInDictionaryInteractor mGetAllWordsInDictionaryInteractor,
            WordPairToViewModelMapper mMapper) {
        this.mCompositeDisposable = new CompositeDisposable();
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
        Disposable d = mGetAllWordsInDictionaryInteractor.getAllWords()
                .doOnSubscribe(disposable -> getViewState().showLoading())
                .map(mMapper::transform)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleWordPairs, this::handleError);
        mCompositeDisposable.add(d);
    }

    private void handleWordPairs(List<WordPairViewModel> wordPairs) {
        getViewState().setWords(wordPairs);
        getViewState().hideLoading();
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
        getViewState().showLoading();
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

}
