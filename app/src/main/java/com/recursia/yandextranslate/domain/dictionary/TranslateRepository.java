package com.recursia.yandextranslate.domain.dictionary;

import com.recursia.yandextranslate.domain.dictionary.models.WordPair;

import io.reactivex.Single;

public interface TranslateRepository {
    /**
     * @param text text to translate
     * @param fromLang what language do we translate from
     * @param toLang what language do we translate to
     *
     * Try to get translated word <br>
     * execution on Schedulers.io()
     *
     * @return WordPair with plain and translated words or onError if there was an unexpected exception
     */
    Single<WordPair> getTranslate(String text, String fromLang, String toLang);
}
