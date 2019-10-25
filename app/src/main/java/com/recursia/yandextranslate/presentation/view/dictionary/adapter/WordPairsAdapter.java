package com.recursia.yandextranslate.presentation.view.dictionary.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.recursia.yandextranslate.R;
import com.recursia.yandextranslate.models.presentation.WordPairUiModel;

import java.util.List;

public class WordPairsAdapter extends RecyclerView.Adapter<WordPairsAdapter.WordPairHolder> {

    private List<WordPairUiModel> wordPairs;

    @NonNull
    @Override
    public WordPairHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.word_pair_item,
                viewGroup,
                false);

        return new WordPairHolder(itemView);
    }

    public void setWordPairs(List<WordPairUiModel> wordPairs) {
        this.wordPairs = wordPairs;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull WordPairHolder wordPairHolder, int i) {
        WordPairUiModel item = wordPairs.get(i);
        wordPairHolder.plainText.setText(item.getPlainWord());
        wordPairHolder.translatedText.setText(item.getTranslatedWord());
    }

    @Override
    public int getItemCount() {
        return (wordPairs != null) ? wordPairs.size() : 0;
    }

    static class WordPairHolder extends RecyclerView.ViewHolder {
        TextView plainText;
        TextView translatedText;

        WordPairHolder(@NonNull View itemView) {
            super(itemView);
            plainText = itemView.findViewById(R.id.plainText);
            translatedText = itemView.findViewById(R.id.translatedText);
        }
    }
}
