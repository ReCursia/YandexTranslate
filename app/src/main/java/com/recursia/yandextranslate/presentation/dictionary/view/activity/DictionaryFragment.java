package com.recursia.yandextranslate.presentation.dictionary.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.recursia.yandextranslate.R;
import com.recursia.yandextranslate.di.dictionary.DaggerDictionaryComponent;
import com.recursia.yandextranslate.di.dictionary.InteractorModule;
import com.recursia.yandextranslate.di.dictionary.MapperModule;
import com.recursia.yandextranslate.di.dictionary.RetrofitModule;
import com.recursia.yandextranslate.di.dictionary.RoomModule;
import com.recursia.yandextranslate.domain.dictionary.models.WordPair;
import com.recursia.yandextranslate.presentation.dictionary.presenter.DictionaryPresenter;
import com.recursia.yandextranslate.presentation.dictionary.view.DictionaryView;
import com.recursia.yandextranslate.presentation.dictionary.view.adapter.WordPairsAdapter;

import java.util.List;


public class DictionaryFragment extends MvpAppCompatFragment implements DictionaryView {
    @InjectPresenter
    DictionaryPresenter presenter;
    private Button addButton;
    private Button swapButton;
    private EditText editText;
    private Spinner translateFromSpinner;
    private Spinner translateToSpinner;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private WordPairsAdapter adapter;
    private Toolbar toolbar;

    public static DictionaryFragment getNewInstance() {
        return new DictionaryFragment();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void openFavoriteScreen() {
        //Intent intent = new Intent(this, FavoriteFragment.class);
        //startActivity(intent);
    }

    @Override
    public void updateWord(WordPair pair, int position) {
        adapter.updateWord(pair, position);
    }

    @ProvidePresenter
    DictionaryPresenter providePresenter() {
        return DaggerDictionaryComponent.builder().interactorModule(new InteractorModule())
                .mapperModule(new MapperModule())
                .roomModule(new RoomModule(getActivity().getApplication()))
                .retrofitModule(new RetrofitModule()).build().getDictionaryPresenter();
    }

    @Override
    public void swapLanguages() {
        int fromIndex = translateFromSpinner.getSelectedItemPosition();
        int toIndex = translateToSpinner.getSelectedItemPosition();
        translateFromSpinner.setSelection(toIndex);
        translateToSpinner.setSelection(fromIndex);
        Toast.makeText(getContext(), "Swapped successfully", Toast.LENGTH_LONG).show();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dictionary, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindViews();
        setOnClickButtonsListeners();
        setEditTextSubmitListener();
        initToolbar();
        initAdapter();
        initRecyclerView();
    }

    private void initToolbar() {
        toolbar.setTitle(R.string.app_name);
        toolbar.inflateMenu(R.menu.dictionary_menu);
        toolbar.setOnMenuItemClickListener(menuItem -> {
            if (menuItem.getItemId() == R.id.favoriteItem) {
                presenter.onItemFavoriteClicked();
                return true;
            }
            return false;
        });
    }


    private void bindViews() {
        View view = getView();
        if (view != null) {
            addButton = view.findViewById(R.id.addButton);
            swapButton = view.findViewById(R.id.swapButton);
            editText = view.findViewById(R.id.editText);
            translateFromSpinner = view.findViewById(R.id.translateFromSpinner);
            translateToSpinner = view.findViewById(R.id.translateToSpinner);
            recyclerView = view.findViewById(R.id.wordPairsRecyclerView);
            progressBar = view.findViewById(R.id.progressBar);
            toolbar = view.findViewById(R.id.toolbar);
        }
    }

    private void setOnClickButtonsListeners() {
        addButton.setOnClickListener((v) -> presenter.onAddButtonClicked(editText.getText().toString(),
                getTranslatedFromLang()
                , getTranslatedToLang()));

        swapButton.setOnClickListener(v -> presenter.onSwapButtonClicked());
    }

    private String getTranslatedFromLang() {
        return translateFromSpinner.getSelectedItem().toString();
    }

    private String getTranslatedToLang() {
        return translateToSpinner.getSelectedItem().toString();
    }

    private void setEditTextSubmitListener() {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                presenter.onQueryTextChanged(s.toString());
            }
        });
    }

    private void initAdapter() {
        adapter = new WordPairsAdapter();
        adapter.setOnClickListener((wordPair, position) -> presenter.onWordPairClicked(wordPair, position));
        recyclerView.setAdapter(adapter);
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void setWords(List<WordPair> pairs) {
        adapter.setWordPairs(pairs);
    }

    @Override
    public void addWord(WordPair pair) {
        adapter.addWord(pair);
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

}
