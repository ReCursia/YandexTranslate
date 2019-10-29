package com.recursia.yandextranslate.presentation.dictionary.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.recursia.yandextranslate.R;
import com.recursia.yandextranslate.di.dictionary.DaggerFavoriteComponent;
import com.recursia.yandextranslate.di.dictionary.InteractorModule;
import com.recursia.yandextranslate.di.dictionary.MapperModule;
import com.recursia.yandextranslate.di.dictionary.RoomModule;
import com.recursia.yandextranslate.presentation.dictionary.models.WordPairViewModel;
import com.recursia.yandextranslate.presentation.dictionary.presenter.FavoritePresenter;
import com.recursia.yandextranslate.presentation.dictionary.view.FavoriteView;
import com.recursia.yandextranslate.presentation.dictionary.view.adapter.WordPairsAdapter;

import java.util.List;

public class FavoriteActivity extends MvpAppCompatActivity implements FavoriteView {
    private static final boolean REVERSE_LAYOUT = false;
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
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, REVERSE_LAYOUT));
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
    public void setWords(List<WordPairViewModel> pairs) {
        adapter.setWordPairs(pairs);
    }
}
