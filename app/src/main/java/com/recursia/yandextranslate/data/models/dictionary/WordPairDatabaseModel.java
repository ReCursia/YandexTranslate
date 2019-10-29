package com.recursia.yandextranslate.data.models.dictionary;

import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

@Entity(tableName = "word_pairs", primaryKeys = {"plainWord", "translatedWord"})
public class WordPairDatabaseModel {
    @NonNull
    private String plainWord;
    @NonNull
    private String translatedWord;

    private boolean isFavorite;

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getPlainWord() {
        return plainWord;
    }

    public void setPlainWord(String plainWord) {
        this.plainWord = plainWord;
    }

    public String getTranslatedWord() {
        return translatedWord;
    }

    public void setTranslatedWord(String translatedWord) {
        this.translatedWord = translatedWord;
    }

}
