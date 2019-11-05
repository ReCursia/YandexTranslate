package com.recursia.yandextranslate.domain.favorite;

import com.recursia.yandextranslate.domain.dictionary.WordPairsRepository;
import com.recursia.yandextranslate.domain.dictionary.models.WordPair;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class GetFavoriteWordPairsInteractorImpl implements GetFavoriteWordPairsInteractor {
    private final WordPairsRepository mRepository;

    @Inject
    public GetFavoriteWordPairsInteractorImpl(WordPairsRepository mRepository) {
        this.mRepository = mRepository;
    }

    @Override
    public Single<List<WordPair>> getAllFavoriteWordPairs() {
        return mRepository.getAllFavoriteWordPairs();
    }
}
