package com.recursia.yandextranslate.data.db.dictionary;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.recursia.yandextranslate.data.models.dictionary.WordPairDatabaseModel;


/*
This class is singleton
 */
@Database(entities = {WordPairDatabaseModel.class}, version = 2, exportSchema = false)
public abstract class WordPairsDatabase extends RoomDatabase {
    private static final String DB_NAME = "word_pairs.db";
    private static WordPairsDatabase mInstance;

    public static synchronized WordPairsDatabase getInstance(Context context) {
        if (mInstance == null) {
            mInstance = Room.databaseBuilder(context, WordPairsDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration() //deletes all data after version increment
                    .build();
        }
        return mInstance;
    }


    public abstract WordPairDao wordPairDao();

}
