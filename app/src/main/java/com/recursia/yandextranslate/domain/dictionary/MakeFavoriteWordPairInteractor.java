package com.recursia.yandextranslate.domain.dictionary;

import com.recursia.yandextranslate.domain.dictionary.models.WordPair;

import io.reactivex.Completable;

public interface MakeFavoriteWordPairInteractor {
    /**
     * @param wordPair word pair that we want to make favorite
     *
     * Updating word pair value in database with true favorite flag <br>
     * execution on Schedulers.io()
     *
     * @return Completable
     */
    Completable makeFavorite(WordPair wordPair);
}
