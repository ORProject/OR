package com.project.or.main.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.or.R;
import com.project.or.main.CustomItemDecoration;
import com.project.or.main.adapter.NoticeAdapter;
import com.project.or.main.adapter.NoticeAdapterDataView;
import com.project.or.main.component.DaggerNoticeComponent;
import com.project.or.main.module.NoticeModule;
import com.project.or.main.presenter.NoticePresenter;
import com.trello.rxlifecycle.components.support.RxFragment;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;


public class NoticeFragment extends RxFragment implements NoticePresenter.View {


    @Inject
    NoticePresenter noticePresenter;

    @Inject
    NoticeAdapterDataView noticeAdapterDataView;

    @Bind(R.id.noticeRecyclerView)
    RecyclerView listNotice;

    NoticeAdapter mAdapter;

    public static NoticeFragment newInstance() {
        NoticeFragment fragment = new NoticeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_notice, container, false);
        mAdapter = new NoticeAdapter(getContext());

        DaggerNoticeComponent.builder()
                .noticeModule(new NoticeModule(this, mAdapter))
                .build()
                .inject(this);

        ButterKnife.bind(this, rootView);

        listNotice.setLayoutManager(new LinearLayoutManager(getContext()));
        listNotice.addItemDecoration(new CustomItemDecoration(10));
        listNotice.setAdapter(mAdapter);

        noticePresenter.getNoticeData();

        return rootView;
    }


    @Override
    public void refresh() {
        noticeAdapterDataView.refresh();
    }
}
