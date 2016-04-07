package com.project.or.main.presenter;

import android.widget.Toast;

import com.project.or.common.MyApplication;
import com.project.or.main.adapter.TimeLineAdapterDataModel;
import com.project.or.main.model.TimeLineItem;
import com.project.or.main.view.HomeFragment;
import com.project.or.network.TimeLineApi;
import com.project.or.test.SearchItem;
import com.project.or.test.SearchResult;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by INNO-09 on 2016-04-07.
 */
public class HomePresenterImpl implements HomePresenter {

    private View view;
    private TimeLineApi timeLineApi;
    private TimeLineAdapterDataModel timeLineAdapterDataModel;

    @Inject
    public HomePresenterImpl(View view, TimeLineApi timeLineApi, TimeLineAdapterDataModel timeLineAdapterDataModel) {
        this.view = view;
        this.timeLineApi = timeLineApi;
        this.timeLineAdapterDataModel = timeLineAdapterDataModel;
    }

    @Override
    public void getData() {

        timeLineApi.searchImage("636df8b114f0cc206273eacf348eb45a",
                "치킨", 10, 1, "json").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(((HomeFragment) view).<SearchResult>bindToLifecycle())
                .subscribe(searchResult -> {
                    for (SearchItem item : searchResult.channel.item) {
                        TimeLineItem mItem = new TimeLineItem();
                        mItem.iconUrl = item.thumbnail;
                        mItem.name = item.title;
                        mItem.desc = item.link;
                        timeLineAdapterDataModel.add(mItem);
                    }
                }
                 , throwable -> Toast.makeText(MyApplication.getContext(),
                                throwable.getMessage(), Toast.LENGTH_SHORT).show()
                 , () -> view.refresh());
    }
}
