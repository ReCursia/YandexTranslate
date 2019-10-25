package com.recursia.yandextranslate.models.dictionary.domain;

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

    public WordPair(String plainWord, String translatedWord) {
        this.plainWord = plainWord;
        this.translatedWord = translatedWord;
    }
}
