package com.recursia.yandextranslate.domain.dictionary;

import io.reactivex.Completable;

public interface AddToDictionaryInteractor {
    //TODO needs db interface and network interface

    Completable addWord(String word, String fromLang, String toLang);
}
