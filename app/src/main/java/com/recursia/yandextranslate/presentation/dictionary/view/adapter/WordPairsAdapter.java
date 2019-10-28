package com.recursia.yandextranslate.presentation.dictionary.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.recursia.yandextranslate.R;
import com.recursia.yandextranslate.presentation.dictionary.models.WordPairViewModel;

import java.util.List;

public class WordPairsAdapter extends RecyclerView.Adapter<WordPairsAdapter.WordPairHolder> {

    private static final boolean ATTACH_TO_ROOT = false;
    private List<WordPairViewModel> wordPairs;

    @NonNull
    @Override
    public WordPairHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.word_pair_item,
                viewGroup,
                ATTACH_TO_ROOT);

        return new WordPairHolder(itemView);
    }

    public void setWordPairs(List<WordPairViewModel> wordPairs) {
        this.wordPairs = wordPairs;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull WordPairHolder wordPairHolder, int i) {
        WordPairViewModel item = wordPairs.get(i);
        wordPairHolder.plainText.setText(item.getPlainWord());
        wordPairHolder.translatedText.setText(item.getTranslatedWord());
    }

    @Override
    public int getItemCount() {
        return (wordPairs != null) ? wordPairs.size() : 0;
    }

    public void addWord(WordPairViewModel pair) {
        int size = wordPairs.size();
        wordPairs.add(pair);
        notifyItemInserted(size);
    }

    static class WordPairHolder extends RecyclerView.ViewHolder {
        final TextView plainText;
        final TextView translatedText;

        WordPairHolder(@NonNull View itemView) {
            super(itemView);
            plainText = itemView.findViewById(R.id.plainText);
            translatedText = itemView.findViewById(R.id.translatedText);
        }
    }
}
