package com.recursia.yandextranslate.domain.dictionary;

import com.recursia.yandextranslate.domain.dictionary.models.WordPair;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface WordPairsRepository {

    Observable<List<WordPair>> getAllWordPairs();

    Observable<List<WordPair>> getQueryWordPairs(String query);

    Completable makeFavoriteWordPair(WordPair wordPair);

    Completable removeFavoriteWordPair(WordPair wordPair);

    void addWordPair(WordPair pair);

    Observable<List<WordPair>> getAllFavoriteWordPairs();

}
