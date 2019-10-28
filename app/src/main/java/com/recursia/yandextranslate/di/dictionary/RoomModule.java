package com.recursia.yandextranslate.di.dictionary;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.recursia.yandextranslate.data.db.dictionary.WordPairDao;
import com.recursia.yandextranslate.data.db.dictionary.WordPairsDatabase;
import com.recursia.yandextranslate.data.mapper.DatabaseWordPairModelToWordPairMapper;
import com.recursia.yandextranslate.data.mapper.WordPairToDatabaseWordPairModelMapper;
import com.recursia.yandextranslate.data.repositories.dictionary.WordPairsRepositoryImpl;
import com.recursia.yandextranslate.domain.dictionary.WordPairsRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {MapperModule.class})
public class RoomModule {
    private static final String DB_NAME = "word_pairs.db";
    private WordPairsDatabase database;

    public RoomModule(Application application) {
        database = Room.databaseBuilder(application, WordPairsDatabase.class, DB_NAME).build();
    }

    @Singleton
    @Provides
    WordPairsDatabase providesRoomDatabase() {
        return database;
    }

    @Singleton
    @Provides
    WordPairDao providesWordPairDao(WordPairsDatabase database) {
        return database.wordPairDao();
    }

    @Singleton
    @Provides
    WordPairsRepository providesWordPairsRepository(WordPairDao dao,
                                                    DatabaseWordPairModelToWordPairMapper databaseWordPairModelToWordPairMapper,
                                                    WordPairToDatabaseWordPairModelMapper wordPairToViewModelMapper
    ) {
        return new WordPairsRepositoryImpl(dao, databaseWordPairModelToWordPairMapper, wordPairToViewModelMapper);
    }
}
