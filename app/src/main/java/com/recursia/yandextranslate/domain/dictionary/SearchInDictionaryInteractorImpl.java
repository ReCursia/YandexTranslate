package com.recursia.yandextranslate.domain.dictionary;

import com.recursia.yandextranslate.domain.dictionary.models.WordPair;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class SearchInDictionaryInteractorImpl implements SearchInDictionaryInteractor {
    private final WordPairsRepository mRepository;

    @Inject
    public SearchInDictionaryInteractorImpl(WordPairsRepository mRepository) {
        this.mRepository = mRepository;
    }

    @Override
    public Single<List<WordPair>> searchWords(String query) {
        return mRepository.getQueryWordPairs(query);
    }

}
