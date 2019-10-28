package com.recursia.yandextranslate.domain.dictionary;

import com.recursia.yandextranslate.domain.dictionary.models.WordPair;

import java.util.List;

import io.reactivex.Observable;

public class SearchInDictionaryInteractorImpl implements SearchInDictionaryInteractor {
    private final WordPairsRepository mRepository;

    public SearchInDictionaryInteractorImpl(WordPairsRepository mRepository) {
        this.mRepository = mRepository;
    }

    @Override
    public Observable<List<WordPair>> searchWords(String word) {
        return mRepository.getQueryWordPairs(word);
    }

}
