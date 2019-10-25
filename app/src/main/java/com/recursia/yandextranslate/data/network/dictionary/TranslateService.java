package com.recursia.yandextranslate.data.network.dictionary;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/*
This class is singleton
 */
//TODO make it with dagger

public class TranslateService {
    private static final String BASE_URL = "https://translate.yandex.net/api/v1.5/tr.json/";
    private static TranslateService instance;
    private final Retrofit retrofit;

    private TranslateService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static synchronized TranslateService getInstance() {
        if (instance == null) {
            instance = new TranslateService();
        }
        return instance;
    }

    public TranslateApi getTranslateApi() {
        return retrofit.create(TranslateApi.class);
    }
}
