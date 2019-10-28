package com.recursia.yandextranslate.domain.dictionary;

import com.recursia.yandextranslate.domain.dictionary.models.WordPair;

import io.reactivex.Observable;

public class AddToDictionaryInteractorImpl implements AddToDictionaryInteractor {
    private final WordPairsRepository mWordsRepository;
    private final TranslateRepository mTranslateRepository;

    //TODO implement dagger
    public AddToDictionaryInteractorImpl(WordPairsRepository mWordsRepository, TranslateRepository mTranslateRepository) {
        this.mWordsRepository = mWordsRepository;
        this.mTranslateRepository = mTranslateRepository;
    }

    @Override
    public Observable<WordPair> addWord(String word, String fromLang, String toLang) {
        return mTranslateRepository.getTranslate(word, fromLang, toLang)
                .doOnNext(mWordsRepository::addWordPair);
    }

}
