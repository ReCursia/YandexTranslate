package com.recursia.yandextranslate.presentation.view.dictionary.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
    EditText editText;
    Spinner translateFromSpinner;
    Spinner translateToSpinner;
    RecyclerView recyclerView;
    ProgressBar progressBar;

    @InjectPresenter
    DictionaryPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        setOnClickButtonListener();
        setEditTextSubmitListener();
        initAdapter();
        initRecyclerView();
    }

    private void setEditTextSubmitListener() {
        //editText.
    }

    private void setOnClickButtonListener() {
        addButton.setOnClickListener((v) -> presenter.onAddButtonClicked());
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, REVERSE_LAYOUT));
    }

    private void initAdapter() {
        WordPairsAdapter adapter = new WordPairsAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void bindViews() {
        addButton = findViewById(R.id.addButton);
        editText = findViewById(R.id.editText);
        translateFromSpinner = findViewById(R.id.translateFromSpinner);
        translateToSpinner = findViewById(R.id.translateToSpinner);
        recyclerView = findViewById(R.id.wordPairsRecyclerView);
        progressBar = findViewById(R.id.progressBar);
    }

    @Override
    public void setWords(List<WordPairUiModel> pairs) {
        //TODO implement
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
