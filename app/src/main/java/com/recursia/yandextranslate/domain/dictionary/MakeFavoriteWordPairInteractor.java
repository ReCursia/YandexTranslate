package com.recursia.yandextranslate.domain.dictionary;

import com.recursia.yandextranslate.domain.dictionary.models.WordPair;

public interface MakeFavoriteWordPairInteractor {
    void makeFavorite(WordPair wordPair);
}
