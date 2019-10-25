package com.recursia.yandextranslate.domain.dictionary;

import com.recursia.yandextranslate.models.dictionary.domain.WordPair;

import java.util.List;

import io.reactivex.Observable;

public class SearchInDictionaryInteractorImpl implements SearchInDictionaryInteractor {
    @Override
    public Observable<List<WordPair>> searchWords(String word) {
        return null;
    }
}
