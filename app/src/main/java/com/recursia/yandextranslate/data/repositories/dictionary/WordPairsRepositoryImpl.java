package com.recursia.yandextranslate.data.repositories.dictionary;

import com.recursia.yandextranslate.data.db.dictionary.WordPairDao;
import com.recursia.yandextranslate.data.mapper.DatabaseWordPairModelToWordPairMapper;
import com.recursia.yandextranslate.data.mapper.WordPairToDatabaseWordPairModelMapper;
import com.recursia.yandextranslate.domain.dictionary.WordPairsRepository;
import com.recursia.yandextranslate.domain.dictionary.models.WordPair;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class WordPairsRepositoryImpl implements WordPairsRepository {
    private final WordPairDao mDao;
    private final DatabaseWordPairModelToWordPairMapper mDatabaseModelToWordPairMapper;
    private final WordPairToDatabaseWordPairModelMapper mWordPairToDatabaseModelMapper;

    @Inject
    public WordPairsRepositoryImpl(WordPairDao mDao,
                                   DatabaseWordPairModelToWordPairMapper mDatabaseModelToWordPairMapper,
                                   WordPairToDatabaseWordPairModelMapper mWordPairToDatabaseModelMapper) {
        this.mDao = mDao;
        this.mDatabaseModelToWordPairMapper = mDatabaseModelToWordPairMapper;
        this.mWordPairToDatabaseModelMapper = mWordPairToDatabaseModelMapper;
    }

    @Override
    public Observable<List<WordPair>> getAllWordPairs() {
        return mDao.getAllWordPairs()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .map(mDatabaseModelToWordPairMapper::transform);
    }

    @Override
    public void addWordPair(WordPair pair) {
        mDao.insertWordPair(mWordPairToDatabaseModelMapper.transform(pair));
    }

    @Override
    public Observable<List<WordPair>> getQueryWordPairs(String query) {
        //TODO toObservable? for sure?
        return mDao.getAllQueryWordPairs('%' + query + '%')
                .toObservable()
                .subscribeOn(Schedulers.io())
                .map(mDatabaseModelToWordPairMapper::transform);
    }

}
