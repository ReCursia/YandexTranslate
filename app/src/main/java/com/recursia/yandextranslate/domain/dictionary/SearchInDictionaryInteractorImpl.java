package com.recursia.yandextranslate.domain.dictionary;

import com.recursia.yandextranslate.domain.dictionary.models.WordPair;

import java.util.List;

import io.reactivex.Observable;

public class SearchInDictionaryInteractorImpl implements SearchInDictionaryInteractor {
    private WordPairsRepository repository;

    public SearchInDictionaryInteractorImpl(WordPairsRepository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<List<WordPair>> searchWords(String word) {
        return repository.getQueryWordPairs(word);
    }

}
