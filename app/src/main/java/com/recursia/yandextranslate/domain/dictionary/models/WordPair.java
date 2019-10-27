package com.recursia.yandextranslate.domain.dictionary.models;

import android.arch.persistence.room.Entity;

@Entity
public class WordPair {
    private String plainWord;
    private String translatedWord;

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
