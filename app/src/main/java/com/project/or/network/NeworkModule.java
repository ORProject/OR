package com.project.or.network;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by INNO-09 on 2016-04-07.
 */
@Module
public class NeworkModule {

    @Provides
    public Retrofit provideRetrofit() {
        return RetrofitCreator.createRetrofit();
    }

    @Provides
    public TimeLineApi provideTimeLineApi(Retrofit retrofit) { return new TimeLineApi(retrofit); }
}
