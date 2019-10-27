package com.recursia.yandextranslate.data.network.dictionary;

import com.recursia.yandextranslate.data.models.dictionary.TranslateNetworkModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TranslateApi {

    String API_KEY =
            "trnsl.1.1.20191022T132422Z." +
                    "e2514f4cb9505689." +
                    "ad6cb58598cfcfc04e982319ce1558fa6f50c224";

    @GET("translate/?key=" + API_KEY)
    Observable<TranslateNetworkModel> getTranslate(@Query("text") String text, @Query("lang") String lang);
}
