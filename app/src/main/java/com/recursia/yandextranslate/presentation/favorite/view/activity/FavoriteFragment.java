package com.recursia.yandextranslate.presentation.favorite.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import com.arellomobile.mvp.MvpAppCompatFragment;
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

public class FavoriteFragment extends MvpAppCompatFragment implements FavoriteView {
    @InjectPresenter
    FavoritePresenter presenter;
    private RecyclerView recyclerView;
    private WordPairsAdapter adapter;
    private Toolbar toolbar;

    public static FavoriteFragment getNewInstance() {
        return new FavoriteFragment();
    }

    @ProvidePresenter
    FavoritePresenter providePresenter() {
        return DaggerFavoriteComponent.builder()
                .interactorModule(new InteractorModule())
                .mapperModule(new MapperModule())
                .roomModule(new RoomModule(getActivity().getApplication()))
                .build()
                .getFavoritePresenter();
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindViews();
        initToolbar();
        initAdapter();
        initRecyclerView();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    private void initToolbar() {
        toolbar.setTitle(R.string.favorite_title);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24px);
        toolbar.setNavigationOnClickListener(v -> presenter.onBackPressed());
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
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
        View view = getView();
        if (view != null) {
            recyclerView = view.findViewById(R.id.favoriteWordPairsRecyclerView);
            toolbar = view.findViewById(R.id.toolbar);
        }
    }

    @Override
    public void setWords(List<WordPair> pairs) {
        adapter.setWordPairs(pairs);
    }
}
