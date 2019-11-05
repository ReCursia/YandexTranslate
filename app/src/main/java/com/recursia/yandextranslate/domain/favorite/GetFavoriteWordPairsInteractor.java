package com.recursia.yandextranslate.domain.favorite;

import com.recursia.yandextranslate.domain.dictionary.models.WordPair;

import java.util.List;

import io.reactivex.Single;

public interface GetFavoriteWordPairsInteractor {
    /**
     * Try to get all favorite word pairs <br>
     * execution on Schedulers.io()
     *
     * @return List of word pairs or onError if there was an unexpected exception
     */
    Single<List<WordPair>> getAllFavoriteWordPairs();
}
