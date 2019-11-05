package com.recursia.yandextranslate.presentation.favorite.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.recursia.yandextranslate.R;
import com.recursia.yandextranslate.di.dictionary.DaggerFavoriteComponent;
import com.recursia.yandextranslate.di.dictionary.InteractorModule;
import com.recursia.yandextranslate.di.dictionary.MapperModule;
import com.recursia.yandextranslate.di.dictionary.RoomModule;
import com.recursia.yandextranslate.domain.dictionary.models.WordPair;
import com.recursia.yandextranslate.presentation.dictionary.view.adapter.WordPairsAdapter;
import com.recursia.yandextranslate.presentation.favorite.presenter.FavoritePresenter;
import com.recursia.yandextranslate.presentation.favorite.view.FavoriteView;

import java.util.List;

public class FavoriteActivity extends MvpAppCompatActivity implements FavoriteView {
    @InjectPresenter
    FavoritePresenter presenter;
    private RecyclerView recyclerView;
    private WordPairsAdapter adapter;

    @ProvidePresenter
    FavoritePresenter providePresenter() {
        return DaggerFavoriteComponent.builder()
                .interactorModule(new InteractorModule())
                .mapperModule(new MapperModule())
                .roomModule(new RoomModule(getApplication()))
                .build()
                .getFavoritePresenter();
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        bindViews();
        initActionBar();
        initAdapter();
        initRecyclerView();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    private void initActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(getString(R.string.favorite_title));
        }
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void deleteWord(int position) {
        adapter.deleteWord(position);
    }

    private void initAdapter() {
        adapter = new WordPairsAdapter();
        adapter.setOnClickListener((wordPair, position) -> presenter.onWordPairClicked(wordPair, position));
        recyclerView.setAdapter(adapter);
    }

    private void bindViews() {
        recyclerView = findViewById(R.id.favoriteWordPairsRecyclerView);
    }

    @Override
    public void setWords(List<WordPair> pairs) {
        adapter.setWordPairs(pairs);
    }
}