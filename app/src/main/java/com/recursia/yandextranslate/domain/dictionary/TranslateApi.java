package com.recursia.yandextranslate.domain.dictionary;

import com.recursia.yandextranslate.models.data.TranslateNetworkResponseModel;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TranslateApi {

    //TODO implement Retrofit with base url = https://translate.yandex.net/api/v1.5/tr.json/

    String API_KEY =
            "trnsl.1.1.20191022T132422Z." +
                    "e2514f4cb9505689." +
                    "ad6cb58598cfcfc04e982319ce1558fa6f50c224";

    @GET("translate/?key=" + API_KEY)
    Single<TranslateNetworkResponseModel> getTranslate(@Query("text") String text, @Query("lang") String lang);
}
