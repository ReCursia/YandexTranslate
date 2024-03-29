package com.recursia.yandextranslate.presentation.dictionary.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.recursia.yandextranslate.R;
import com.recursia.yandextranslate.domain.dictionary.models.WordPair;

import java.util.ArrayList;
import java.util.List;

public class WordPairsAdapter extends RecyclerView.Adapter<WordPairsAdapter.WordPairHolder> {

    private static final boolean ATTACH_TO_ROOT = false;
    private List<WordPair> mWordPairs;
    private OnWordPairClicked clickListener;

    public void setOnClickListener(OnWordPairClicked listener) {
        this.clickListener = listener;
    }

    @NonNull
    @Override
    public WordPairHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.word_pair_item,
                viewGroup,
                ATTACH_TO_ROOT);

        return new WordPairHolder(itemView);
    }

    public void setWordPairs(List<WordPair> wordPairs) {
        this.mWordPairs = new ArrayList<>(wordPairs);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull WordPairHolder wordPairHolder, int i) {
        WordPair item = mWordPairs.get(i);
        wordPairHolder.plainText.setText(item.getPlainWord());
        wordPairHolder.translatedText.setText(item.getTranslatedWord());

        int visibility = item.isFavorite() ? View.VISIBLE : View.GONE;
        wordPairHolder.favoriteIcon.setVisibility(visibility);

        wordPairHolder.itemView.setOnClickListener(
                v -> {
                    if (clickListener != null) {
                        clickListener.onClick(item, wordPairHolder.getAdapterPosition());
                    }
                }
        );
    }

    @Override
    public int getItemCount() {
        return (mWordPairs != null) ? mWordPairs.size() : 0;
    }

    public void addWord(WordPair pair) {
        int size = mWordPairs.size();
        mWordPairs.add(pair);
        notifyItemInserted(size);
    }

    public void deleteWord(int position) {
        mWordPairs.remove(position);
        notifyItemRemoved(position);
    }

    public void updateWord(WordPair pair, int position) {
        mWordPairs.set(position, pair);
        notifyItemChanged(position);
    }

    class WordPairHolder extends RecyclerView.ViewHolder {
        final TextView plainText;
        final TextView translatedText;
        final ImageView favoriteIcon;

        WordPairHolder(@NonNull View itemView) {
            super(itemView);
            plainText = itemView.findViewById(R.id.plainText);
            translatedText = itemView.findViewById(R.id.translatedText);
            favoriteIcon = itemView.findViewById(R.id.favoriteIcon);
        }
    }
}
