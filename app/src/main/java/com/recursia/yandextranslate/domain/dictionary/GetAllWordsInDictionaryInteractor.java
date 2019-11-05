package com.recursia.yandextranslate.domain.dictionary;

import com.recursia.yandextranslate.domain.dictionary.models.WordPair;

import java.util.List;

import io.reactivex.Single;

public interface GetAllWordsInDictionaryInteractor {
    /**
     * Try get all word pairs from dictionary <br>
     * execution on Schedulers.io()
     * @return List of word pairs or onError if there was an unexpected exception
     */
    Single<List<WordPair>> getAllWords();
}
