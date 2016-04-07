package com.project.or.main.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.or.R;
import com.project.or.main.TimeLineDecoration;
import com.project.or.main.adapter.TimeLineAdapter;
import com.project.or.main.adapter.TimeLineAdapterDataView;
import com.project.or.main.component.DaggerHomeComponent;
import com.project.or.main.module.HomeModule;
import com.project.or.main.presenter.HomePresenter;
import com.trello.rxlifecycle.components.support.RxFragment;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeFragment extends RxFragment implements HomePresenter.View {

    @Inject
    HomePresenter homePresenter;

    @Inject
    TimeLineAdapterDataView timeLineAdapterDataView;

    @Bind(R.id.homeRecyclerView)
    RecyclerView recyclerView;

    TimeLineAdapter mAdapter;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        mAdapter = new TimeLineAdapter(getContext());

        DaggerHomeComponent.builder()
                .homeModule(new HomeModule(this, mAdapter))
                .build()
                .inject(this);

        ButterKnife.bind(this, rootView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new TimeLineDecoration(10));
        recyclerView.setAdapter(mAdapter);

        homePresenter.getData();

        return rootView;
    }

    @Override
    public void refresh() {
        timeLineAdapterDataView.refresh();
    }

}
