package com.recursia.yandextranslate.presentation.dictionary.mapper;

import com.recursia.yandextranslate.domain.dictionary.models.WordPair;
import com.recursia.yandextranslate.presentation.dictionary.models.WordPairViewModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class WordPairToViewModelMapper {

    public List<WordPairViewModel> transform(List<WordPair> wordPairsCollection) {
        List<WordPairViewModel> viewModelsCollection;

        if (isValid(wordPairsCollection)) {
            viewModelsCollection = new ArrayList<>();
            for (WordPair pair : wordPairsCollection) {
                viewModelsCollection.add(transform(pair));
            }
        } else {
            viewModelsCollection = Collections.emptyList();
        }
        return viewModelsCollection;
    }

    private boolean isValid(Collection<WordPair> collection) {
        return (collection != null) && !(collection.isEmpty());
    }

    public WordPairViewModel transform(WordPair pair) {
        if (pair == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        WordPairViewModel viewModel = new WordPairViewModel();
        viewModel.setPlainWord(pair.getPlainWord());
        viewModel.setTranslatedWord(pair.getTranslatedWord());
        return viewModel;
    }

}
