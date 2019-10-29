package com.recursia.yandextranslate.data.db.dictionary;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.recursia.yandextranslate.data.models.dictionary.WordPairDatabaseModel;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface WordPairDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertWordPair(WordPairDatabaseModel model);

    @Query("SELECT * FROM word_pairs")
    Single<List<WordPairDatabaseModel>> getAllWordPairs();

    @Query("SELECT * FROM word_pairs WHERE plainWord LIKE :query OR translatedWord LIKE :query")
    Single<List<WordPairDatabaseModel>> getAllQueryWordPairs(String query);

    @Query("SELECT * FROM word_pairs WHERE isFavorite = 1")
    Single<List<WordPairDatabaseModel>> getAllFavoriteWordPairs();

    @Update
    void updateWordPair(WordPairDatabaseModel wordPair);
}
