package com.recursia.yandextranslate.presentation.dictionary.view.adapter;

import com.recursia.yandextranslate.domain.dictionary.models.WordPair;

public interface OnWordPairClicked {
    void onClick(WordPair wordPair, int position);
}
