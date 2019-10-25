package com.recursia.yandextranslate.presentation.view.dictionary.activity;

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
import com.recursia.yandextranslate.R;
import com.recursia.yandextranslate.presentation.presenter.dictionary.DictionaryPresenter;
import com.recursia.yandextranslate.presentation.view.dictionary.DictionaryView;
import com.recursia.yandextranslate.presentation.view.dictionary.adapter.WordPairsAdapter;
import com.recursia.yandextranslate.presentation.view.dictionary.models.WordPairUiModel;

import java.util.List;

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
        addButton.setOnClickListener((v) -> presenter.onAddButtonClicked());
        swapButton.setOnClickListener(v -> presenter.onSwapButtonClicked());
    }

    private void setEditTextSubmitListener() {
        editText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                presenter.onTextSubmitted(editText.getText().toString(), getTranslatedFromLang(), getTranslatedToLang());
                return true;
            }
            return false;
        });
    }

    private String getTranslatedToLang() {
        return translateToSpinner.getSelectedItem().toString();
    }

    private String getTranslatedFromLang() {
        return translateFromSpinner.getSelectedItem().toString();
    }

    private void initAdapter() {
        adapter = new WordPairsAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, REVERSE_LAYOUT));
    }

    @Override
    public void setWords(List<WordPairUiModel> pairs) {
        adapter.setWordPairs(pairs);
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
