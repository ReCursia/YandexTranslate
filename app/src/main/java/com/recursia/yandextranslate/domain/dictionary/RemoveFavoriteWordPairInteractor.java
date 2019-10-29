package com.recursia.yandextranslate.domain.dictionary;

import com.recursia.yandextranslate.domain.dictionary.models.WordPair;

public interface RemoveFavoriteWordPairInteractor {
    void removeFavorite(WordPair wordPair);
}
