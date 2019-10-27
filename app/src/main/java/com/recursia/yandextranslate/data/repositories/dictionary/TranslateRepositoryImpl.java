package com.recursia.yandextranslate.data.repositories.dictionary;

import com.recursia.yandextranslate.data.mapper.NetworkTranslateModelToWordPairMapper;
import com.recursia.yandextranslate.data.network.dictionary.TranslateApi;
import com.recursia.yandextranslate.domain.dictionary.TranslateRepository;
import com.recursia.yandextranslate.domain.dictionary.models.WordPair;
import com.recursia.yandextranslate.utils.GetTranslateCodeUtils;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class TranslateRepositoryImpl implements TranslateRepository {
    private TranslateApi api;
    private NetworkTranslateModelToWordPairMapper mapper;

    public TranslateRepositoryImpl(TranslateApi api, NetworkTranslateModelToWordPairMapper mapper) {
        this.api = api;
        this.mapper = mapper;
    }

    @Override
    public Observable<WordPair> getTranslate(String text, String fromLang, String toLang) {
        return api.getTranslate(text, GetTranslateCodeUtils.getCode(fromLang, toLang))
                .subscribeOn(Schedulers.io())
                .map(mapper::transform)
                .doOnNext(wordPair -> wordPair.setPlainWord(text));
    }
}
