package com.recursia.yandextranslate.presentation.favorite.view;


import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.recursia.yandextranslate.domain.dictionary.models.WordPair;

import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface FavoriteView extends MvpView {
    void setWords(List<WordPair> pairs);

    @StateStrategyType(AddToEndStrategy.class)
    void deleteWord(int position);

    @StateStrategyType(SkipStrategy.class)
    void showErrorMessage(String message);
}
