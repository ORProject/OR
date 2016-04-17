package com.project.or.network;

import com.project.or.test.SearchResult;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface MyApi {
    @GET("search/image")
    Observable<SearchResult> getImageList(@Query("apikey") String apikey,
                                          @Query("q") String q,
                                          @Query("result") int result,
                                          @Query("pageno") int pageno,
                                          @Query("output") String output);
}