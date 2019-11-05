package com.recursia.yandextranslate;

import android.support.v4.app.Fragment;

import com.recursia.yandextranslate.presentation.dictionary.view.activity.DictionaryFragment;
import com.recursia.yandextranslate.presentation.favorite.view.activity.FavoriteFragment;

import ru.terrakok.cicerone.android.support.SupportAppScreen;

public class Screens {
    public static final class DictionaryScreen extends SupportAppScreen {
        @Override
        public Fragment getFragment() {
            return DictionaryFragment.getNewInstance();
        }
    }

    public static final class FavoriteScreen extends SupportAppScreen {
        @Override
        public Fragment getFragment() {
            return FavoriteFragment.getNewInstance();
        }
    }
}
