package com.recursia.yandextranslate.presentation.dictionary.models;

public class WordPairViewModel {
    private String plainWord;
    private String translatedWord;

    public WordPairViewModel(String plainWord, String translatedWord) {
        this.plainWord = plainWord;
        this.translatedWord = translatedWord;
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