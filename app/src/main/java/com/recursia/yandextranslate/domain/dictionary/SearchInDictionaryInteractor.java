package com.recursia.yandextranslate.domain.dictionary;

import com.recursia.yandextranslate.domain.dictionary.models.WordPair;

import java.util.List;

import io.reactivex.Single;

public interface SearchInDictionaryInteractor {
    /**
     * @param query query to search in dictionary
     *
     * Try to find all pairs of words by query <br>
     * execution on Schedulers.io()
     * @return List of word pairs or onError if there was an unexpected exception
     */
    Single<List<WordPair>> searchWords(String query);
}
