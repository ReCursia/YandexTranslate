package com.recursia.yandextranslate.domain.dictionary;

import com.recursia.yandextranslate.domain.dictionary.models.WordPair;

import javax.inject.Inject;

public class RemoveFavoriteWordPairInteractorImpl implements RemoveFavoriteWordPairInteractor {
    private final WordPairsRepository repository;

    @Inject
    public RemoveFavoriteWordPairInteractorImpl(WordPairsRepository repository) {
        this.repository = repository;
    }

    @Override
    public void removeFavorite(WordPair wordPair) {
        repository.removeFavoriteWordPair(wordPair);
    }
}
