package com.recursia.yandextranslate.presentation.view.translates;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.recursia.yandextranslate.presentation.view.translates.models.WordPair;

import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface TranslateView extends MvpView {
    void setWordPairs(List<WordPair> pairs);

    @StateStrategyType(SkipStrategy.class)
    void showErrorMessage(String message);

    void showLoading();

    void hideLoading();
}
