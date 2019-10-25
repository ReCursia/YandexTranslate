package com.recursia.yandextranslate.domain.dictionary;

import io.reactivex.Observable;

public interface TranslateRepository {
    Observable<Void> getTranslate(String text, String fromLang, String toLang);
}
