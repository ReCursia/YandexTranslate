package com.recursia.yandextranslate.di.dictionary;

import com.recursia.yandextranslate.presentation.dictionary.presenter.DictionaryPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {InteractorModule.class})
public interface DictionaryComponent {

    DictionaryPresenter getDictionaryPresenter();
}
