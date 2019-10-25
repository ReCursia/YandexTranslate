package com.recursia.yandextranslate.presentation.presenter.dictionary;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.recursia.yandextranslate.domain.dictionary.AddToDictionaryInteractor;
import com.recursia.yandextranslate.domain.dictionary.GetAllWordsInDictionaryInteractor;
import com.recursia.yandextranslate.domain.dictionary.SearchInDictionaryInteractor;
import com.recursia.yandextranslate.presentation.view.dictionary.DictionaryView;

@InjectViewState
public class DictionaryPresenter extends MvpPresenter<DictionaryView> {
    private AddToDictionaryInteractor addToDictionaryInteractor;
    private SearchInDictionaryInteractor searchInDictionaryInteractor;
    private GetAllWordsInDictionaryInteractor getAllWordsInDictionaryInteractor;

    //TODO add dagger injection
    public DictionaryPresenter(AddToDictionaryInteractor addToDictionaryInteractor, SearchInDictionaryInteractor searchInDictionaryInteractor) {
        this.addToDictionaryInteractor = addToDictionaryInteractor;
        this.searchInDictionaryInteractor = searchInDictionaryInteractor;
    }

    //TODO mapper?
    @Override
    protected void onFirstViewAttach() {
        getViewState().hideLoading();
        //TODO start download dict from database
    }

    public void onAddButtonClicked() {
        //TODO make query
    }

    public void onTextSubmitted(String text, String fromLang, String toLang) {
        //TODO implement interaction
        getViewState().showErrorMessage(text + " " + fromLang + " " + toLang);
    }

    public void onSwapButtonClicked() {
        getViewState().swapLanguages();
    }

}
