package com.recursia.yandextranslate.domain.dictionary;

import com.recursia.yandextranslate.domain.dictionary.models.WordPair;

import io.reactivex.Observable;

public class AddToDictionaryInteractorImpl implements AddToDictionaryInteractor {
    private final WordPairsRepository wordsRepository;
    private final TranslateRepository translateRepository;

    //TODO implement dagger
    public AddToDictionaryInteractorImpl(WordPairsRepository wordsRepository, TranslateRepository translateRepository) {
        this.wordsRepository = wordsRepository;
        this.translateRepository = translateRepository;
    }

    @Override
    public Observable<WordPair> addWord(String word, String fromLang, String toLang) {
        return translateRepository.getTranslate(word, fromLang, toLang)
                .doOnNext(pair -> wordsRepository.addWordPair(pair));
    }

}
