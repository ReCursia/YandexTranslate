package com.recursia.yandextranslate.domain.dictionary;

import com.recursia.yandextranslate.domain.dictionary.models.WordPair;

import java.util.List;

import io.reactivex.Observable;

public class GetAllWordsInDictionaryInteractorImpl implements GetAllWordsInDictionaryInteractor {
    private WordPairsRepository repository;

    public GetAllWordsInDictionaryInteractorImpl(WordPairsRepository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<List<WordPair>> getAllWords() {
        return repository.getAllWordPairs();
    }

}
