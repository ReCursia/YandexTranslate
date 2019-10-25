package com.recursia.yandextranslate.domain.dictionary;

import com.recursia.yandextranslate.models.dictionary.domain.WordPair;

import io.reactivex.Observable;

public interface AddToDictionaryInteractor {
    Observable<WordPair> addWord(String word, String fromLang, String toLang);
}
