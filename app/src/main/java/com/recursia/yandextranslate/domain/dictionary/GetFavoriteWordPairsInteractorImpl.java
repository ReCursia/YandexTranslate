package com.recursia.yandextranslate.domain.dictionary;

import com.recursia.yandextranslate.domain.dictionary.models.WordPair;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetFavoriteWordPairsInteractorImpl implements GetFavoriteWordPairsInteractor {
    private final WordPairsRepository repository;

    @Inject
    public GetFavoriteWordPairsInteractorImpl(WordPairsRepository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<List<WordPair>> getAllFavoriteWordPairs() {
        return repository.getAllFavoriteWordPairs();
    }
}
