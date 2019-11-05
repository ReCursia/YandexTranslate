package com.recursia.yandextranslate.domain.dictionary;

import com.recursia.yandextranslate.domain.dictionary.models.WordPair;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;

public class AddToDictionaryInteractorImpl implements AddToDictionaryInteractor {
    private final WordPairsRepository mWordsRepository;
    private final TranslateRepository mTranslateRepository;

    @Inject
    public AddToDictionaryInteractorImpl(WordPairsRepository mWordsRepository, TranslateRepository mTranslateRepository) {
        this.mWordsRepository = mWordsRepository;
        this.mTranslateRepository = mTranslateRepository;
    }

    @Override
    public Single<WordPair> addWord(String word, String fromLang, String toLang) {
        return mTranslateRepository.getTranslate(word, fromLang, toLang)
                .doOnSuccess(mWordsRepository::addWordPair);
    }

}
