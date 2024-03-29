package com.recursia.yandextranslate.domain.dictionary.models;

import android.arch.persistence.room.Entity;

@Entity
public class WordPair {
    private String plainWord;
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
