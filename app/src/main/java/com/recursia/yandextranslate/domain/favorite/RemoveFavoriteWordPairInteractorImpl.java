package com.recursia.yandextranslate.domain.favorite;

import com.recursia.yandextranslate.domain.dictionary.WordPairsRepository;
import com.recursia.yandextranslate.domain.dictionary.models.WordPair;

import javax.inject.Inject;

import io.reactivex.Completable;

public class RemoveFavoriteWordPairInteractorImpl implements RemoveFavoriteWordPairInteractor {
    private final WordPairsRepository mRepository;

    @Inject
    public RemoveFavoriteWordPairInteractorImpl(WordPairsRepository repository) {
        this.mRepository = repository;
    }

    @Override
    public Completable removeFavorite(WordPair wordPair) {
        return mRepository.removeFavoriteWordPair(wordPair);
    }
}
