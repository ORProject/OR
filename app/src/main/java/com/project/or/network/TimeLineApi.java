package com.project.or.network;

import com.project.or.test.SearchResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


public interface TimeLineApi {

//    @GET("search/image")
//    Call<SearchResult> searchImageList(@Query("apikey") String apikey,
//                                       @Query("q") String q,
//                                       @Query("result") int result,
//                                       @Query("pageno") int pageno,
//                                       @Query("output") String output);

    @GET("search/image")
    Observable<SearchResult> searchImageList(@Query("apikey") String apikey,
                                       @Query("q") String q,
                                       @Query("result") int result,
                                       @Query("pageno") int pageno,
                                       @Query("output") String output);

}