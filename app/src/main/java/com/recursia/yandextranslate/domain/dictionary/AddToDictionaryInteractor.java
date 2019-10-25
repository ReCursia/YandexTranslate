package com.recursia.yandextranslate.domain.dictionary;

import com.recursia.yandextranslate.domain.dictionary.models.WordPair;

import io.reactivex.Observable;

public interface AddToDictionaryInteractor {
    Observable<WordPair> addWord(String word, String fromLang, String toLang);
}
