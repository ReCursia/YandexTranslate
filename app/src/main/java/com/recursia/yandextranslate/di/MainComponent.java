package com.recursia.yandextranslate.di;

import com.recursia.yandextranslate.di.dictionary.NavigationModule;

import javax.inject.Singleton;

import dagger.Component;
import ru.terrakok.cicerone.NavigatorHolder;

@Singleton
@Component(modules = {NavigationModule.class})
public interface MainComponent {
    NavigatorHolder getNavigationHolder();
}
