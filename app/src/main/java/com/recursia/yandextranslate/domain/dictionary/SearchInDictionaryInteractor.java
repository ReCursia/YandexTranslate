package com.recursia.yandextranslate.domain.dictionary;

import com.recursia.yandextranslate.models.dictionary.domain.WordPair;

import java.util.List;

import io.reactivex.Observable;

public interface SearchInDictionaryInteractor {
    Observable<List<WordPair>> searchWords(String word);
}
