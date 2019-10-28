package com.recursia.yandextranslate.di.dictionary;

import com.recursia.yandextranslate.data.mapper.NetworkTranslateModelToWordPairMapper;
import com.recursia.yandextranslate.data.network.dictionary.TranslateApi;
import com.recursia.yandextranslate.data.repositories.dictionary.TranslateRepositoryImpl;
import com.recursia.yandextranslate.domain.dictionary.TranslateRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = {MapperModule.class})
public class RetrofitModule {

    private static final String BASE_URL = "https://translate.yandex.net/api/v1.5/tr.json/";

    @Provides
    @Singleton
    public TranslateApi translateApi(Retrofit retrofit) {
        return retrofit.create(TranslateApi.class);
    }

    @Provides
    @Singleton
    public RxJava2CallAdapterFactory rxJava2CallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Singleton
    public GsonConverterFactory gsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    public Retrofit retrofit(GsonConverterFactory gsonConverterFactory, RxJava2CallAdapterFactory rxJava2CallAdapterFactory) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .build();
    }

    @Provides
    @Singleton
    public TranslateRepository translateRepository(TranslateApi api, NetworkTranslateModelToWordPairMapper mapper) {
        return new TranslateRepositoryImpl(api, mapper);
    }
}
