package com.recursia.yandextranslate.domain.dictionary;

import com.recursia.yandextranslate.domain.dictionary.models.WordPair;

import java.util.List;

import io.reactivex.Observable;

public class GetAllWordsInDictionaryInteractorImpl implements GetAllWordsInDictionaryInteractor {
    private final WordPairsRepository mRepository;

    public GetAllWordsInDictionaryInteractorImpl(WordPairsRepository mRepository) {
        this.mRepository = mRepository;
    }

    @Override
    public Observable<List<WordPair>> getAllWords() {
        return mRepository.getAllWordPairs();
    }

}
