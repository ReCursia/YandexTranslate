package com.recursia.yandextranslate.domain.favorite;

import com.recursia.yandextranslate.domain.dictionary.models.WordPair;

import java.util.List;

import io.reactivex.Single;

public interface GetFavoriteWordPairsInteractor {
    Single<List<WordPair>> getAllFavoriteWordPairs();
}
