package com.recursia.yandextranslate.data.db.dictionary;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.recursia.yandextranslate.data.models.dictionary.WordPairDatabaseModel;

import java.util.List;

import io.reactivex.Observable;

@Dao
public interface WordPairDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertWordPair(WordPairDatabaseModel model);

    @Query("SELECT * FROM word_pairs")
    Observable<List<WordPairDatabaseModel>> getAllWordPairs();

    @Query("SELECT * FROM word_pairs WHERE plainWord == :query OR translatedWord == :query")
    Observable<List<WordPairDatabaseModel>> getAllQueryWordPairs(String query);
}
