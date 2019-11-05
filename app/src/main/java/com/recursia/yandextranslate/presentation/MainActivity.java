package com.recursia.yandextranslate.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.recursia.yandextranslate.R;
import com.recursia.yandextranslate.Screens;
import com.recursia.yandextranslate.di.DaggerMainComponent;
import com.recursia.yandextranslate.di.dictionary.NavigationModule;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.android.support.SupportAppNavigator;
import ru.terrakok.cicerone.commands.Command;
import ru.terrakok.cicerone.commands.Replace;

public class MainActivity extends AppCompatActivity {

    private NavigatorHolder navigatorHolder;

    private final Navigator navigator = new SupportAppNavigator(this, R.id.main_container) {
        @Override
        public void applyCommands(Command[] commands) {
            super.applyCommands(commands);
            getSupportFragmentManager().executePendingTransactions(); //animations
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigatorHolder = DaggerMainComponent.builder()
                .navigationModule(new NavigationModule())
                .build()
                .getNavigationHolder();

        if (savedInstanceState == null) {
            navigator.applyCommands(new Command[]{new Replace(new Screens.DictionaryScreen())});
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigatorHolder.setNavigator(navigator);
    }

    @Override
    protected void onPause() {
        navigatorHolder.removeNavigator();
        super.onPause();
    }
}
