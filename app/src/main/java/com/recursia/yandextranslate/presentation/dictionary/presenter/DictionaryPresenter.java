package com.recursia.yandextranslate.presentation.dictionary.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.recursia.yandextranslate.domain.dictionary.AddToDictionaryInteractor;
import com.recursia.yandextranslate.domain.dictionary.GetAllWordsInDictionaryInteractor;
import com.recursia.yandextranslate.domain.dictionary.SearchInDictionaryInteractor;
import com.recursia.yandextranslate.presentation.dictionary.mapper.WordPairToViewModelMapper;
import com.recursia.yandextranslate.presentation.dictionary.view.DictionaryView;

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
    /*
    public DictionaryPresenter(AddToDictionaryInteractor addToDictionaryInteractor, SearchInDictionaryInteractor searchInDictionaryInteractor) {
        this.addToDictionaryInteractor = addToDictionaryInteractor;
        this.searchInDictionaryInteractor = searchInDictionaryInteractor;
    }
     */

    @Override
    public void onDestroy() {
        compositeDisposable.dispose();
    }

    @Override
    protected void onFirstViewAttach() {
        getViewState().showLoading();
        Disposable d = getAllWordsInDictionaryInteractor.getAllWords()
                .map(mapper::transofrm)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(wordPairViewModels -> {
                    getViewState().setWords(wordPairViewModels);
                    getViewState().hideLoading();
                }, throwable -> {
                    getViewState().showErrorMessage(throwable.getLocalizedMessage());
                    getViewState().hideLoading();
                });
        compositeDisposable.add(d);
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
                .map(mapper::transofrm)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(wordPairViewModels -> {
                    getViewState().hideLoading();
                    getViewState().setWords(wordPairViewModels);
                }, throwable -> {
                    getViewState().hideLoading();
                    getViewState().showErrorMessage(throwable.getLocalizedMessage());
                });
        compositeDisposable.add(d);
    }

    public void onSwapButtonClicked() {
        getViewState().swapLanguages();
    }

}
