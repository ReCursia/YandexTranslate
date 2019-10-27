package com.recursia.yandextranslate.data.mapper;

import com.recursia.yandextranslate.data.models.dictionary.TranslateNetworkModel;
import com.recursia.yandextranslate.domain.dictionary.models.WordPair;

public class NetworkTranslateModelToWordPairMapper {

    private final static int TRANSLATE_INDEX = 0;

    public WordPair transform(TranslateNetworkModel translateNetworkModel) {
        if (translateNetworkModel == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        WordPair wordPair = new WordPair();
        wordPair.setTranslatedWord(translateNetworkModel.getText().get(TRANSLATE_INDEX));
        return wordPair;
    }
}
