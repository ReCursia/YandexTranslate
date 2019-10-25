package com.recursia.yandextranslate.data.repositories.dictionary;

import com.recursia.yandextranslate.domain.dictionary.TranslateRepository;
import com.recursia.yandextranslate.domain.dictionary.models.WordPair;

import io.reactivex.Observable;
//TODO implement repository
public class TranslateRepositoryImpl implements TranslateRepository {
    @Override
    public Observable<WordPair> getTranslate(String text, String fromLang, String toLang) {
        return null;
    }
}
