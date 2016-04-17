package com.project.or.main.module;

import com.project.or.main.adapter.NoticeAdapter;
import com.project.or.main.adapter.NoticeAdapterDataModel;
import com.project.or.main.adapter.NoticeAdapterDataView;
import com.project.or.main.presenter.NoticePresenter;
import com.project.or.main.presenter.NoticePresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by INNO-09 on 2016-04-07.
 */
@Module
public class NoticeModule {

    private NoticePresenter.View view;
    private NoticeAdapter adapter;

    public NoticeModule(NoticePresenter.View view, NoticeAdapter adapter) {
        this.view = view;
        this.adapter = adapter;
    }

    @Provides
    NoticeAdapterDataModel provideNoticeAdapterDataModel() {
        return adapter;
    }

    @Provides
    NoticeAdapterDataView provideNoticeAdapterDataView() {
        return adapter;
    }

    @Provides
    NoticePresenter provideNoticePresenter(NoticePresenterImpl noticePresenter) { return noticePresenter; }

    @Provides
    NoticePresenter.View provideView() {
        return view;
    }

}
