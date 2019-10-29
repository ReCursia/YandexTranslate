package com.recursia.yandextranslate.di.dictionary;


import com.recursia.yandextranslate.domain.dictionary.AddToDictionaryInteractor;
import com.recursia.yandextranslate.domain.dictionary.AddToDictionaryInteractorImpl;
import com.recursia.yandextranslate.domain.dictionary.GetAllWordsInDictionaryInteractor;
import com.recursia.yandextranslate.domain.dictionary.GetAllWordsInDictionaryInteractorImpl;
import com.recursia.yandextranslate.domain.dictionary.GetFavoriteWordPairsInteractor;
import com.recursia.yandextranslate.domain.dictionary.GetFavoriteWordPairsInteractorImpl;
import com.recursia.yandextranslate.domain.dictionary.MakeFavoriteWordPairInteractor;
import com.recursia.yandextranslate.domain.dictionary.MakeFavoriteWordPairInteractorImpl;
import com.recursia.yandextranslate.domain.dictionary.RemoveFavoriteWordPairInteractor;
import com.recursia.yandextranslate.domain.dictionary.RemoveFavoriteWordPairInteractorImpl;
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

    @Provides
    @Singleton
    MakeFavoriteWordPairInteractor makeFavoriteWordPairInteractor(WordPairsRepository wordPairsRepository) {
        return new MakeFavoriteWordPairInteractorImpl(wordPairsRepository);
    }

    @Provides
    @Singleton
    RemoveFavoriteWordPairInteractor removeFavoriteWordPairInteractor(WordPairsRepository wordPairsRepository) {
        return new RemoveFavoriteWordPairInteractorImpl(wordPairsRepository);
    }

    @Provides
    @Singleton
    GetFavoriteWordPairsInteractor getFavoriteWordPairsInteractor(WordPairsRepository wordPairsRepository) {
        return new GetFavoriteWordPairsInteractorImpl(wordPairsRepository);
    }
}
