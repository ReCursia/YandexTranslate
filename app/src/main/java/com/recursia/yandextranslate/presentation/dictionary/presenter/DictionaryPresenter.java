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

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


@InjectViewState
public class DictionaryPresenter extends MvpPresenter<DictionaryView> {
    private CompositeDisposable compositeDisposable;
    private AddToDictionaryInteractor addToDictionaryInteractor;
    private SearchInDictionaryInteractor searchInDictionaryInteractor;
    private GetAllWordsInDictionaryInteractor getAllWordsInDictionaryInteractor;
    private WordPairToViewModelMapper mapper;

    //TODO add dagger injection
    public DictionaryPresenter(CompositeDisposable compositeDisposable,
                               AddToDictionaryInteractor addToDictionaryInteractor,
                               SearchInDictionaryInteractor searchInDictionaryInteractor,
                               GetAllWordsInDictionaryInteractor getAllWordsInDictionaryInteractor,
                               WordPairToViewModelMapper mapper) {
        this.compositeDisposable = compositeDisposable;
        this.addToDictionaryInteractor = addToDictionaryInteractor;
        this.getAllWordsInDictionaryInteractor = getAllWordsInDictionaryInteractor;
        this.searchInDictionaryInteractor = searchInDictionaryInteractor;
        this.mapper = mapper;
    }

    @Override
    public void onDestroy() {
        compositeDisposable.dispose();
    }

    @Override
    protected void onFirstViewAttach() {
        getViewState().showLoading();
        Disposable d = getAllWordsInDictionaryInteractor.getAllWords()
                .map(mapper::transform)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleWordPairs, this::handleError);
        compositeDisposable.add(d);
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
        Disposable d = addToDictionaryInteractor.addWord(text, translatedFrom, translatedTo)
                .map(mapper::transform)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(wordPairViewModel -> getViewState().addWord(wordPairViewModel),
                        throwable -> getViewState().showErrorMessage(throwable.getLocalizedMessage()));
        compositeDisposable.add(d);
    }

    public void onTextSubmitted(String text) {
        getViewState().showLoading();
        Disposable d = searchInDictionaryInteractor.searchWords(text)
                .map(mapper::transform)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleWordPairs, this::handleError);
        compositeDisposable.add(d);
    }

    public void onSwapButtonClicked() {
        getViewState().swapLanguages();
    }

}
