package com.recursia.yandextranslate.presentation.dictionary.view;


import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.recursia.yandextranslate.presentation.dictionary.models.WordPairViewModel;

import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface FavoriteView extends MvpView {
    void setWords(List<WordPairViewModel> pairs);

    @StateStrategyType(SkipStrategy.class)
    void deleteWord(int position);
}
