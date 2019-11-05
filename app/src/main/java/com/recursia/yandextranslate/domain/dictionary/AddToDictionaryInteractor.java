package com.recursia.yandextranslate.domain.dictionary;

import com.recursia.yandextranslate.domain.dictionary.models.WordPair;

import io.reactivex.Single;

public interface AddToDictionaryInteractor {
    /**
     *
     * @param word word to add in dictionary
     * @param fromLang what language do we translate from
     * @param toLang what language do we translate to
     *
     * Try to get word translate <br>
     * and add it to dictionary <br>
     * execution on Schedulers.io()
     * @return WordPair with plain and translated words or onError if there was an unexpected exception
     */
    Single<WordPair> addWord(String word, String fromLang, String toLang);
}