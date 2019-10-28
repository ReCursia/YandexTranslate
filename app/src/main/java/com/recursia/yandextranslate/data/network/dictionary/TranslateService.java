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
    private static TranslateService mInstance;
    private final Retrofit mRetrofit;

    private TranslateService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static synchronized TranslateService getInstance() {
        if (mInstance == null) {
            mInstance = new TranslateService();
        }
        return mInstance;
    }

    public TranslateApi getTranslateApi() {
        return mRetrofit.create(TranslateApi.class);
    }

}
