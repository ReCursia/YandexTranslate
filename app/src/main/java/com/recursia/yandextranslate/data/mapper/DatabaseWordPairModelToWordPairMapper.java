package com.recursia.yandextranslate.data.mapper;

import com.recursia.yandextranslate.data.models.dictionary.WordPairDatabaseModel;
import com.recursia.yandextranslate.domain.dictionary.models.WordPair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DatabaseWordPairModelToWordPairMapper {

    public List<WordPair> transform(List<WordPairDatabaseModel> wordPairsDatabase) {
        List<WordPair> wordPairCollection;

        if (isValid(wordPairsDatabase)) {
            wordPairCollection = new ArrayList<>();
            for (WordPairDatabaseModel pair : wordPairsDatabase) {
                wordPairCollection.add(transform(pair));
            }
        } else {
            wordPairCollection = Collections.emptyList();
        }
        return wordPairCollection;
    }

    private boolean isValid(Collection<WordPairDatabaseModel> collection) {
        return (collection != null) && !(collection.isEmpty());
    }

    private WordPair transform(WordPairDatabaseModel wordPairDatabaseModel) {
        if (wordPairDatabaseModel == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        WordPair wordPair = new WordPair();
        wordPair.setPlainWord(wordPairDatabaseModel.getPlainWord());
        wordPair.setTranslatedWord(wordPairDatabaseModel.getTranslatedWord());
        wordPair.setFavorite(wordPairDatabaseModel.isFavorite());
        return wordPair;
    }

}
