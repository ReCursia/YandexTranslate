package com.recursia.yandextranslate.presentation.dictionary.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.recursia.yandextranslate.R;
import com.recursia.yandextranslate.di.dictionary.DaggerDictionaryComponent;
import com.recursia.yandextranslate.di.dictionary.InteractorModule;
import com.recursia.yandextranslate.di.dictionary.MapperModule;
import com.recursia.yandextranslate.di.dictionary.RetrofitModule;
import com.recursia.yandextranslate.di.dictionary.RoomModule;
import com.recursia.yandextranslate.presentation.dictionary.models.WordPairViewModel;
import com.recursia.yandextranslate.presentation.dictionary.presenter.DictionaryPresenter;
import com.recursia.yandextranslate.presentation.dictionary.view.DictionaryView;
import com.recursia.yandextranslate.presentation.dictionary.view.adapter.WordPairsAdapter;

import java.util.List;


public class DictionaryActivity extends MvpAppCompatActivity implements DictionaryView {

    private static final boolean REVERSE_LAYOUT = false;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dictionary_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.favoriteItem) {
            presenter.onItemFavoriteClicked();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void openFavoriteScreen() {
        Intent intent = new Intent(this, FavoriteActivity.class);
        startActivity(intent);
    }

    @Override
    public void updateWord(WordPairViewModel pair, int position) {
        adapter.updateWord(pair, position);
    }

    @ProvidePresenter
    DictionaryPresenter providePresenter() {
        return DaggerDictionaryComponent.builder().interactorModule(new InteractorModule())
                .mapperModule(new MapperModule())
                .roomModule(new RoomModule(getApplication()))
                .retrofitModule(new RetrofitModule()).build().getDictionaryPresenter();
    }

    @Override
    public void swapLanguages() {
        int fromIndex = translateFromSpinner.getSelectedItemPosition();
        int toIndex = translateToSpinner.getSelectedItemPosition();
        translateFromSpinner.setSelection(toIndex);
        translateToSpinner.setSelection(fromIndex);

        Toast.makeText(this, "Swapped successfully", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        setOnClickButtonsListeners();
        setEditTextSubmitListener();
        initAdapter();
        initRecyclerView();
    }

    private void bindViews() {
        addButton = findViewById(R.id.addButton);
        swapButton = findViewById(R.id.swapButton);
        editText = findViewById(R.id.editText);
        translateFromSpinner = findViewById(R.id.translateFromSpinner);
        translateToSpinner = findViewById(R.id.translateToSpinner);
        recyclerView = findViewById(R.id.wordPairsRecyclerView);
        progressBar = findViewById(R.id.progressBar);
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
                presenter.onTextChanged(s.toString());
            }
        });
    }

    private void initAdapter() {
        adapter = new WordPairsAdapter();
        adapter.setOnClickListener((wordPair, position) -> presenter.onWordPairClicked(wordPair, position));
        recyclerView.setAdapter(adapter);
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, REVERSE_LAYOUT));
    }

    @Override
    public void setWords(List<WordPairViewModel> pairs) {
        adapter.setWordPairs(pairs);
    }

    @Override
    public void addWord(WordPairViewModel pair) {
        adapter.addWord(pair);
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
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
