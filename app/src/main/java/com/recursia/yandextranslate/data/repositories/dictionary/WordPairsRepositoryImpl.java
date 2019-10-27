package com.recursia.yandextranslate.data.repositories.dictionary;

import com.recursia.yandextranslate.data.db.dictionary.WordPairDao;
import com.recursia.yandextranslate.data.mapper.DatabaseWordPairModelToWordPairMapper;
import com.recursia.yandextranslate.data.mapper.WordPairToDatabaseWordPairModelMapper;
import com.recursia.yandextranslate.domain.dictionary.WordPairsRepository;
import com.recursia.yandextranslate.domain.dictionary.models.WordPair;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class WordPairsRepositoryImpl implements WordPairsRepository {
    private WordPairDao dao;
    private DatabaseWordPairModelToWordPairMapper databaseModelToWordPairMapper;
    private WordPairToDatabaseWordPairModelMapper wordPairToDatabaseModelMapper;

    public WordPairsRepositoryImpl(WordPairDao dao,
                                   DatabaseWordPairModelToWordPairMapper databaseModelToWordPairMapper,
                                   WordPairToDatabaseWordPairModelMapper wordPairToDatabaseModelMapper) {
        this.dao = dao;
        this.databaseModelToWordPairMapper = databaseModelToWordPairMapper;
        this.wordPairToDatabaseModelMapper = wordPairToDatabaseModelMapper;
    }

    @Override
    public Observable<List<WordPair>> getAllWordPairs() {
        return dao.getAllWordPairs()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .map(databaseModelToWordPairMapper::transform);
    }

    @Override
    public void addWordPair(WordPair pair) {
        dao.insertWordPair(wordPairToDatabaseModelMapper.transform(pair));
    }

    @Override
    public Observable<List<WordPair>> getQueryWordPairs(String query) {
        //TODO toObservable? for sure?
        return dao.getAllQueryWordPairs('%' + query + '%')
                .toObservable()
                .subscribeOn(Schedulers.io())
                .map(databaseModelToWordPairMapper::transform);
    }

}
