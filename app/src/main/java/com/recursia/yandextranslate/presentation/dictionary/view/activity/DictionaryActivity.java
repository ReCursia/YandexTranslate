package com.recursia.yandextranslate.presentation.dictionary.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.recursia.yandextranslate.R;
import com.recursia.yandextranslate.data.db.dictionary.WordPairDao;
import com.recursia.yandextranslate.data.db.dictionary.WordPairsDatabase;
import com.recursia.yandextranslate.data.mapper.DatabaseWordPairModelToWordPairMapper;
import com.recursia.yandextranslate.data.mapper.NetworkTranslateModelToWordPairMapper;
import com.recursia.yandextranslate.data.mapper.WordPairToDatabaseWordPairModelMapper;
import com.recursia.yandextranslate.data.network.dictionary.TranslateApi;
import com.recursia.yandextranslate.data.network.dictionary.TranslateService;
import com.recursia.yandextranslate.data.repositories.dictionary.TranslateRepositoryImpl;
import com.recursia.yandextranslate.data.repositories.dictionary.WordPairsRepositoryImpl;
import com.recursia.yandextranslate.domain.dictionary.AddToDictionaryInteractor;
import com.recursia.yandextranslate.domain.dictionary.AddToDictionaryInteractorImpl;
import com.recursia.yandextranslate.domain.dictionary.GetAllWordsInDictionaryInteractor;
import com.recursia.yandextranslate.domain.dictionary.GetAllWordsInDictionaryInteractorImpl;
import com.recursia.yandextranslate.domain.dictionary.SearchInDictionaryInteractor;
import com.recursia.yandextranslate.domain.dictionary.SearchInDictionaryInteractorImpl;
import com.recursia.yandextranslate.domain.dictionary.TranslateRepository;
import com.recursia.yandextranslate.domain.dictionary.WordPairsRepository;
import com.recursia.yandextranslate.presentation.dictionary.mapper.WordPairToViewModelMapper;
import com.recursia.yandextranslate.presentation.dictionary.models.WordPairViewModel;
import com.recursia.yandextranslate.presentation.dictionary.presenter.DictionaryPresenter;
import com.recursia.yandextranslate.presentation.dictionary.view.DictionaryView;
import com.recursia.yandextranslate.presentation.dictionary.view.adapter.WordPairsAdapter;
import com.recursia.yandextranslate.presentation.dictionary.view.decorator.MarginItemDecoration;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class DictionaryActivity extends MvpAppCompatActivity implements DictionaryView {

    private static final boolean REVERSE_LAYOUT = false;

    Button addButton;
    Button swapButton;
    EditText editText;
    Spinner translateFromSpinner;
    Spinner translateToSpinner;
    RecyclerView recyclerView;
    ProgressBar progressBar;

    @InjectPresenter
    DictionaryPresenter presenter;
    WordPairsAdapter adapter;

    //TODO implement dagger
    @ProvidePresenter
    DictionaryPresenter providePresenter() {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        TranslateApi api = TranslateService.getInstance().getTranslateApi();
        WordPairDao dao = WordPairsDatabase.getInstance(this).wordPairDao();
        TranslateRepository translateRepository = new TranslateRepositoryImpl(
                api, new NetworkTranslateModelToWordPairMapper()
        );
        WordPairsRepository wordPairsRepository = new WordPairsRepositoryImpl(
                dao, new DatabaseWordPairModelToWordPairMapper(), new WordPairToDatabaseWordPairModelMapper()
        );
        AddToDictionaryInteractor addToDictionaryInteractor = new AddToDictionaryInteractorImpl(
                wordPairsRepository, translateRepository
        );
        GetAllWordsInDictionaryInteractor getAllWordsInDictionaryInteractor = new GetAllWordsInDictionaryInteractorImpl(
                wordPairsRepository
        );
        SearchInDictionaryInteractor searchInDictionaryInteractor = new SearchInDictionaryInteractorImpl(
                wordPairsRepository
        );
        return new DictionaryPresenter(
                compositeDisposable,
                addToDictionaryInteractor,
                searchInDictionaryInteractor,
                getAllWordsInDictionaryInteractor,
                new WordPairToViewModelMapper()
        );
    }

    @Override
    public void swapLanguages() {
        int fromIndex = translateFromSpinner.getSelectedItemPosition();
        int toIndex = translateToSpinner.getSelectedItemPosition();
        translateFromSpinner.setSelection(toIndex);
        translateToSpinner.setSelection(fromIndex);

        Toast.makeText(this, "Swapped successfully", Toast.LENGTH_SHORT).show();
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
        editText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                presenter.onTextSubmitted(editText.getText().toString());
                return true;
            }
            return false;
        });
    }

    private void initAdapter() {
        adapter = new WordPairsAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, REVERSE_LAYOUT));
        recyclerView.addItemDecoration(new MarginItemDecoration(this, getResources().getDimensionPixelSize(R.dimen.word_pair_item_margin)));
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
