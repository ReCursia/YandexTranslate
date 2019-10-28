package com.recursia.yandextranslate.di.dictionary;


import com.recursia.yandextranslate.data.mapper.DatabaseWordPairModelToWordPairMapper;
import com.recursia.yandextranslate.data.mapper.NetworkTranslateModelToWordPairMapper;
import com.recursia.yandextranslate.data.mapper.WordPairToDatabaseWordPairModelMapper;
import com.recursia.yandextranslate.presentation.dictionary.mapper.WordPairToViewModelMapper;

import dagger.Module;
import dagger.Provides;

@Module
public class MapperModule {
    @Provides
    DatabaseWordPairModelToWordPairMapper provideDatabaseWordPairModelToWordPairMapper() {
        return new DatabaseWordPairModelToWordPairMapper();
    }

    @Provides
    NetworkTranslateModelToWordPairMapper provideNetworkTranslateModelToWordPairMapper() {
        return new NetworkTranslateModelToWordPairMapper();
    }

    @Provides
    WordPairToDatabaseWordPairModelMapper provideWordPairToDatabaseWordPairModelMapper() {
        return new WordPairToDatabaseWordPairModelMapper();
    }
    @Provides
    WordPairToViewModelMapper wordPairToViewModelMapper(){
        return new WordPairToViewModelMapper();
    }
}
