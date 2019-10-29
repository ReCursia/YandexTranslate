package com.recursia.yandextranslate.domain.dictionary;

import com.recursia.yandextranslate.domain.dictionary.models.WordPair;

import javax.inject.Inject;

public class RemoveFavoriteWordPairInteractorImpl implements RemoveFavoriteWordPairInteractor {
    private final WordPairsRepository mRepository;

    @Inject
    public RemoveFavoriteWordPairInteractorImpl(WordPairsRepository repository) {
        this.mRepository = repository;
    }

    @Override
    public void removeFavorite(WordPair wordPair) {
        mRepository.removeFavoriteWordPair(wordPair);
    }
}
