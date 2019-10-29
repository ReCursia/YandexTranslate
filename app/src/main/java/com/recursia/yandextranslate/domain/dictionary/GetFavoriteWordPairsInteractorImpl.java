package com.recursia.yandextranslate.domain.dictionary;

import com.recursia.yandextranslate.domain.dictionary.models.WordPair;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetFavoriteWordPairsInteractorImpl implements GetFavoriteWordPairsInteractor {
    private final WordPairsRepository mRepository;

    @Inject
    public GetFavoriteWordPairsInteractorImpl(WordPairsRepository mRepository) {
        this.mRepository = mRepository;
    }

    @Override
    public Observable<List<WordPair>> getAllFavoriteWordPairs() {
        return mRepository.getAllFavoriteWordPairs();
    }
}
