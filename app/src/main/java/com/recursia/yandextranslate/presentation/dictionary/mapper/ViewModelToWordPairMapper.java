package com.recursia.yandextranslate.presentation.dictionary.mapper;

import com.recursia.yandextranslate.domain.dictionary.models.WordPair;
import com.recursia.yandextranslate.presentation.dictionary.models.WordPairViewModel;

public class ViewModelToWordPairMapper {
    public WordPair transform(WordPairViewModel viewModel) {
        if (viewModel == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        WordPair wordPair = new WordPair();
        wordPair.setPlainWord(viewModel.getPlainWord());
        wordPair.setTranslatedWord(viewModel.getTranslatedWord());
        wordPair.setFavorite(viewModel.isFavorite());
        return wordPair;
    }
}
