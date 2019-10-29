package com.recursia.yandextranslate.domain.dictionary;

import com.recursia.yandextranslate.domain.dictionary.models.WordPair;

import java.util.List;

import io.reactivex.Observable;

public interface GetFavoriteWordPairsInteractor {
    Observable<List<WordPair>> getAllFavoriteWordPairs();
}
