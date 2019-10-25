package com.recursia.yandextranslate.presentation.presenter.dictionary;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.recursia.yandextranslate.presentation.view.dictionary.DictionaryView;

@InjectViewState
public class DictionaryPresenter extends MvpPresenter<DictionaryView> {

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
