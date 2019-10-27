package com.recursia.yandextranslate.data.mapper;

import com.recursia.yandextranslate.data.models.dictionary.WordPairDatabaseModel;
import com.recursia.yandextranslate.domain.dictionary.models.WordPair;

public class WordPairToDatabaseWordPairModelMapper {
    public WordPairDatabaseModel transform(WordPair wordPair) {
        if (wordPair == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        WordPairDatabaseModel wordPairDatabaseModel = new WordPairDatabaseModel();
        wordPairDatabaseModel.setPlainWord(wordPair.getPlainWord());
        wordPairDatabaseModel.setTranslatedWord(wordPair.getTranslatedWord());
        return wordPairDatabaseModel;
    }

}
