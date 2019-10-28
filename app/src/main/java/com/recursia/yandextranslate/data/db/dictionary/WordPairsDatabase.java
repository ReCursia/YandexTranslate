package com.recursia.yandextranslate.data.db.dictionary;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.recursia.yandextranslate.data.models.dictionary.WordPairDatabaseModel;

@Database(entities = {WordPairDatabaseModel.class}, version = 2, exportSchema = false)
public abstract class WordPairsDatabase extends RoomDatabase {

    public abstract WordPairDao wordPairDao();

}
