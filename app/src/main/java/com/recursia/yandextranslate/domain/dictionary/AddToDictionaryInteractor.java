package com.recursia.yandextranslate.domain.dictionary;

import com.recursia.yandextranslate.domain.dictionary.models.WordPair;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface AddToDictionaryInteractor {
    Single<WordPair> addWord(String word, String fromLang, String toLang);
}