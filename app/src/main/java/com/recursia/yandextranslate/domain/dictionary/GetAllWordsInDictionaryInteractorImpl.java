package com.recursia.yandextranslate.domain.dictionary;

import com.recursia.yandextranslate.domain.dictionary.models.WordPair;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class GetAllWordsInDictionaryInteractorImpl implements GetAllWordsInDictionaryInteractor {
    private final WordPairsRepository mRepository;

    @Inject
    public GetAllWordsInDictionaryInteractorImpl(WordPairsRepository mRepository) {
        this.mRepository = mRepository;
    }

    @Override
    public Single<List<WordPair>> getAllWords() {
        return mRepository.getAllWordPairs();
    }

}
