package com.recursia.yandextranslate.domain.dictionary;

import com.recursia.yandextranslate.domain.dictionary.models.WordPair;

import java.util.List;

import io.reactivex.Observable;

public interface WordPairsRepository {

    Observable<List<WordPair>> getAllWordPairs();

    Observable<List<WordPair>> getQueryWordPairs(String query);

    void addWordPair(WordPair pair);

}