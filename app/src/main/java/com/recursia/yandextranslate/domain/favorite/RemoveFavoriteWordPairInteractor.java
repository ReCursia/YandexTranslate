package com.recursia.yandextranslate.domain.favorite;

import com.recursia.yandextranslate.domain.dictionary.models.WordPair;

import io.reactivex.Completable;

public interface RemoveFavoriteWordPairInteractor {
    Completable removeFavorite(WordPair wordPair);
}
