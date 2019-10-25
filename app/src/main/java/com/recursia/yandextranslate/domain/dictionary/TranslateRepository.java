package com.recursia.yandextranslate.domain.dictionary;

import com.recursia.yandextranslate.domain.dictionary.models.WordPair;

import io.reactivex.Observable;

public interface TranslateRepository {
    Observable<WordPair> getTranslate(String text, String fromLang, String toLang);
}
