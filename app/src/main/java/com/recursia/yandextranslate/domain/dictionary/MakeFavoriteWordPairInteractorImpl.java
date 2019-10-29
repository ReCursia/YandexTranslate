package com.recursia.yandextranslate.domain.dictionary;

import com.recursia.yandextranslate.domain.dictionary.models.WordPair;

import javax.inject.Inject;

import io.reactivex.Completable;

public class MakeFavoriteWordPairInteractorImpl implements MakeFavoriteWordPairInteractor {
    private final WordPairsRepository mRepository;

    @Inject
    public MakeFavoriteWordPairInteractorImpl(WordPairsRepository mRepository) {
        this.mRepository = mRepository;
    }

    @Override
    public Completable makeFavorite(WordPair wordPair) {
        return mRepository.makeFavoriteWordPair(wordPair);
    }
}
