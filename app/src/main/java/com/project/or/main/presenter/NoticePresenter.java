package com.project.or.main.presenter;

/**
 * Created by INNO-09 on 2016-04-07.
 */
public interface NoticePresenter {

    void getNoticeData();

    interface View {
        void refresh();
    }
}
