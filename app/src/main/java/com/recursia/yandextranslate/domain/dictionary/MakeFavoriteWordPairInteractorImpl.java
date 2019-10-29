package com.recursia.yandextranslate.domain.dictionary;

import com.recursia.yandextranslate.domain.dictionary.models.WordPair;

import javax.inject.Inject;

public class MakeFavoriteWordPairInteractorImpl implements MakeFavoriteWordPairInteractor {
    private final WordPairsRepository mRepository;

    @Inject
    public MakeFavoriteWordPairInteractorImpl(WordPairsRepository mRepository) {
        this.mRepository = mRepository;
    }

    @Override
    public void makeFavorite(WordPair wordPair) {
        mRepository.makeFavoriteWordPair(wordPair);
    }
}
