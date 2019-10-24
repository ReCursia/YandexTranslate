package com.recursia.yandextranslate.presentation.view.dictionary;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.recursia.yandextranslate.presentation.view.dictionary.models.WordPairUiModel;

import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface DictionaryView extends MvpView {
    void setWords(List<WordPairUiModel> pairs);

    @StateStrategyType(SkipStrategy.class)
    void showErrorMessage(String message);

    void showLoading();

    void hideLoading();

    void swapLanguages();
}
