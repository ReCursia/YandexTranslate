package com.recursia.yandextranslate.domain.dictionary;

import com.recursia.yandextranslate.domain.dictionary.models.WordPair;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface TranslateRepository {
    Single<WordPair> getTranslate(String text, String fromLang, String toLang);
}
