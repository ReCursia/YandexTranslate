package com.recursia.yandextranslate.data.repositories.dictionary;

import com.recursia.yandextranslate.data.mapper.NetworkTranslateModelToWordPairMapper;
import com.recursia.yandextranslate.data.network.dictionary.TranslateApi;
import com.recursia.yandextranslate.domain.dictionary.TranslateRepository;
import com.recursia.yandextranslate.domain.dictionary.models.WordPair;
import com.recursia.yandextranslate.utils.GetTranslateCodeUtils;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class TranslateRepositoryImpl implements TranslateRepository {
    private final TranslateApi mApi;
    private final NetworkTranslateModelToWordPairMapper mMapper;

    public TranslateRepositoryImpl(TranslateApi mApi, NetworkTranslateModelToWordPairMapper mMapper) {
        this.mApi = mApi;
        this.mMapper = mMapper;
    }

    @Override
    public Observable<WordPair> getTranslate(String text, String fromLang, String toLang) {
        return mApi.getTranslate(text, GetTranslateCodeUtils.getCode(fromLang, toLang))
                .subscribeOn(Schedulers.io())
                .map(mMapper::transform)
                .doOnNext(wordPair -> wordPair.setPlainWord(text));
    }

}
