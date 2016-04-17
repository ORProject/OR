package com.project.or.main.module;

import com.project.or.main.adapter.TimeLineAdapter;
import com.project.or.main.adapter.TimeLineAdapterDataModel;
import com.project.or.main.adapter.TimeLineAdapterDataView;
import com.project.or.main.presenter.HomePresenter;
import com.project.or.main.presenter.HomePresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by INNO-09 on 2016-04-07.
 */
@Module
public class HomeModule {

    private HomePresenter.View view;
    private TimeLineAdapter adapter;

    public HomeModule(HomePresenter.View view, TimeLineAdapter adapter) {
        this.view = view;
        this.adapter = adapter;
    }

    @Provides
    TimeLineAdapterDataModel provideTimeLineAdapterDataModel() {
        return adapter;
    }

    @Provides
    TimeLineAdapterDataView provideTimeLineAdpaLineAdapterDataView() {
        return adapter;
    }

    @Provides
    HomePresenter provideHomePresenter(HomePresenterImpl homePresenter) {
        return homePresenter;
    }

    @Provides
    HomePresenter.View provideView() {
        return view;
    }

}
