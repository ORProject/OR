package com.ojh.testproject.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


interface Api {
    @GET("api")
    Call<String> res(@Query("q") String query);

}