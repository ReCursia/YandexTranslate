package com.recursia.yandextranslate.di.dictionary;


import com.recursia.yandextranslate.domain.dictionary.AddToDictionaryInteractor;
import com.recursia.yandextranslate.domain.dictionary.AddToDictionaryInteractorImpl;
import com.recursia.yandextranslate.domain.dictionary.GetAllWordsInDictionaryInteractor;
import com.recursia.yandextranslate.domain.dictionary.GetAllWordsInDictionaryInteractorImpl;
import com.recursia.yandextranslate.domain.dictionary.SearchInDictionaryInteractor;
import com.recursia.yandextranslate.domain.dictionary.SearchInDictionaryInteractorImpl;
import com.recursia.yandextranslate.domain.dictionary.TranslateRepository;
import com.recursia.yandextranslate.domain.dictionary.WordPairsRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {RoomModule.class, RetrofitModule.class})
public class InteractorModule {
    @Provides
    @Singleton
    AddToDictionaryInteractor addToDictionaryInteractor(WordPairsRepository wordPairsRepository, TranslateRepository translateRepository) {
        return new AddToDictionaryInteractorImpl(wordPairsRepository, translateRepository);
    }

    @Provides
    @Singleton
    SearchInDictionaryInteractor searchInDictionaryInteractor(WordPairsRepository wordPairsRepository) {
        return new SearchInDictionaryInteractorImpl(wordPairsRepository);
    }

    @Provides
    @Singleton
    GetAllWordsInDictionaryInteractor getAllWordsInDictionaryInteractor(WordPairsRepository wordPairsRepository) {
        return new GetAllWordsInDictionaryInteractorImpl(wordPairsRepository);
    }
}
