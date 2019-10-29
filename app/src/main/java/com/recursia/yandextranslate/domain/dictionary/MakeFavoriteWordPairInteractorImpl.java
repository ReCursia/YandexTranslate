package com.recursia.yandextranslate.domain.dictionary;

import com.recursia.yandextranslate.domain.dictionary.models.WordPair;

import javax.inject.Inject;

public class MakeFavoriteWordPairInteractorImpl implements MakeFavoriteWordPairInteractor {
    private final WordPairsRepository repository;

    @Inject
    public MakeFavoriteWordPairInteractorImpl(WordPairsRepository repository) {
        this.repository = repository;
    }

    @Override
    public void makeFavorite(WordPair wordPair) {
        repository.makeFavoriteWordPair(wordPair);
    }
}
