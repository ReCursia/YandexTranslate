package com.recursia.yandextranslate.domain.dictionary;

import com.recursia.yandextranslate.domain.dictionary.models.WordPair;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface WordPairsRepository {

    Single<List<WordPair>> getAllWordPairs();

    Single<List<WordPair>> getQueryWordPairs(String query);

    Completable makeFavoriteWordPair(WordPair wordPair);

    Completable removeFavoriteWordPair(WordPair wordPair);

    void addWordPair(WordPair pair);

    Single<List<WordPair>> getAllFavoriteWordPairs();

}
