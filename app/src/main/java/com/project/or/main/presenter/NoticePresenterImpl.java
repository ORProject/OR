package com.project.or.main.presenter;

import android.widget.Toast;

import com.project.or.common.MyApplication;
import com.project.or.main.adapter.NoticeAdapterDataModel;
import com.project.or.main.model.NoticeItem;
import com.project.or.main.view.NoticeFragment;
import com.project.or.network.MyApi;
import com.project.or.network.NetWorkManager;
import com.project.or.test.SearchItem;
import com.project.or.test.SearchResult;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 01071724654 on 2016-04-17.
 */
public class NoticePresenterImpl implements NoticePresenter {

    private View view;
    private MyApi myApi;
    private NoticeAdapterDataModel noticeAdapterDataModel;

    @Inject
    public NoticePresenterImpl(View view, NoticeAdapterDataModel noticeAdapterDataModel) {
        this.view=view;
        this.noticeAdapterDataModel=noticeAdapterDataModel;
    }

    @Override
    public void getNoticeData() {

        myApi = NetWorkManager.getInstance().getApi(MyApi.class);

        myApi.getImageList("636df8b114f0cc206273eacf348eb45a",
                "피자", 10, 1, "json").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(((NoticeFragment) view).<SearchResult>bindToLifecycle())
                .subscribe(searchResult -> {
                            for (SearchItem item : searchResult.channel.item) {
                                NoticeItem mItem = new NoticeItem();
                                mItem.imgUrl = item.thumbnail;
                                mItem.leftItem = item.title;
                                mItem.rightItem = item.title;
                                noticeAdapterDataModel.add(mItem);
                            }
                        }
                        , throwable -> Toast.makeText(MyApplication.getContext(),
                                throwable.getMessage(), Toast.LENGTH_SHORT).show()
                        , () -> view.refresh());

    }
}
