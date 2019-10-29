package com.recursia.yandextranslate.presentation.dictionary.view.adapter;

import com.recursia.yandextranslate.presentation.dictionary.models.WordPairViewModel;

public interface OnWordPairClicked {
    void onClick(WordPairViewModel wordPair, int position);
}
