package com.recursia.yandextranslate.domain.dictionary;

import com.recursia.yandextranslate.domain.dictionary.models.WordPair;

import java.util.List;

import io.reactivex.Single;

public interface SearchInDictionaryInteractor {
    Single<List<WordPair>> searchWords(String word);
}
