package com.recursia.yandextranslate.domain.favorite;

import com.recursia.yandextranslate.domain.dictionary.models.WordPair;

import io.reactivex.Completable;

public interface RemoveFavoriteWordPairInteractor {
    /**
     * @param wordPair word pair that we want to make not favorite
     *
     * Updating word pair value in database with false favorite flag <br>
     * execution on Schedulers.io()
     *
     * @return Completable
     */
    Completable removeFavorite(WordPair wordPair);
}
