package com.recursia.yandextranslate.data.repositories.dictionary;

import com.recursia.yandextranslate.data.db.dictionary.WordPairDao;
import com.recursia.yandextranslate.data.mapper.DatabaseWordPairModelToWordPairMapper;
import com.recursia.yandextranslate.domain.dictionary.WordPairsRepository;
import com.recursia.yandextranslate.domain.dictionary.models.WordPair;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class WordPairsRepositoryImpl implements WordPairsRepository {
    private WordPairDao dao;
    private DatabaseWordPairModelToWordPairMapper mapper;

    public WordPairsRepositoryImpl(WordPairDao dao, DatabaseWordPairModelToWordPairMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @Override
    public Observable<List<WordPair>> getAllWordPairs() {
        return dao.getAllWordPairs()
                .subscribeOn(Schedulers.io())
                .map(mapper::transform); //TODO i'm here!!!!!!!!!!
    }

    @Override
    public Observable<List<WordPair>> getQueryWordPairs(String query) {
        return null;
    }

    @Override
    public Observable<Void> addWordPair(WordPair pair) {
        return null;
    }

    @Override
    public Observable<Void> deleteWordPair(WordPair pair) {
        return null;
    }
}
