package com.project.or.network;

import com.project.or.test.SearchResult;

import javax.inject.Inject;

import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


public class TimeLineApi {

    private Retrofit retrofit;

    @Inject
    public TimeLineApi(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public Observable<SearchResult> searchImage(String apikey, String q, int result, int pageno, String output) {
        return retrofit.create(Api.class).getImageList(apikey, q, result, pageno, output);
    }

    interface Api {
        @GET("search/image")
        Observable<SearchResult> getImageList(@Query("apikey") String apikey,
                                              @Query("q") String q,
                                              @Query("result") int result,
                                              @Query("pageno") int pageno,
                                              @Query("output") String output);
    }

}