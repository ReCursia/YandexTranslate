package com.recursia.yandextranslate.di.favorite;

import com.recursia.yandextranslate.di.dictionary.InteractorModule;
import com.recursia.yandextranslate.di.dictionary.NavigationModule;
import com.recursia.yandextranslate.presentation.favorite.presenter.FavoritePresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {InteractorModule.class, NavigationModule.class})
public interface FavoriteComponent {
    FavoritePresenter getFavoritePresenter();
}
