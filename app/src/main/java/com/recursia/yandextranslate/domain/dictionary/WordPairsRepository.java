package com.recursia.yandextranslate.domain.dictionary;

import com.recursia.yandextranslate.domain.dictionary.models.WordPair;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface WordPairsRepository {
    /**
     * Try to get all word pairs from dictionary <br>
     * execution on Schedulers.io()
     *
     * @return List of word pairs or onError if there was an unexpected exception
     */
    Single<List<WordPair>> getAllWordPairs();

    /**
     * @param query query to search in database
     *
     * Try to find all pairs of words by query <br>
     * execution on Schedulers.io()
     *
     * @return List of word pairs or onError if there was an unexpected exception
     */
    Single<List<WordPair>> getQueryWordPairs(String query);

    /**
     * @param wordPair word pair that we want to make favorite
     *
     * Updating word pair value in database with true favorite flag <br>
     * execution on Schedulers.io()
     *
     * @return Completable
     */
    Completable makeFavoriteWordPair(WordPair wordPair);

    /**
     * @param wordPair word pair that we want to make not favorite
     *
     * Updating word pair value in database with false favorite flag <br>
     * execution on Schedulers.io()
     *
     * @return Completable
     */
    Completable removeFavoriteWordPair(WordPair wordPair);

    /**
     * @param pair word pair we want to add in dictionary
     *
     *  Try to add word pair in dictionary <br>
     *  execution on Schedulers.io()
     */
    void addWordPair(WordPair pair);

    /**
     * Try to get all favorite word pairs from dictionary <br>
     * execution on Schedulers.io()
     *
     * @return List of word pairs or onError if there was an unexpected exception
     */
    Single<List<WordPair>> getAllFavoriteWordPairs();

}
