package com.recursia.yandextranslate.presentation.dictionary.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.recursia.yandextranslate.presentation.dictionary.models.WordPairViewModel;

import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface DictionaryView extends MvpView {
    void setWords(List<WordPairViewModel> pairs);

    @StateStrategyType(AddToEndStrategy.class)
    void addWord(WordPairViewModel pair);

    @StateStrategyType(AddToEndStrategy.class)
    void updateWord(WordPairViewModel pair, int position);

    @StateStrategyType(SkipStrategy.class)
    void showErrorMessage(String message);

    void showLoading();

    void hideLoading();

    void swapLanguages();

    @StateStrategyType(SkipStrategy.class)
    void openFavoriteScreen();
}
