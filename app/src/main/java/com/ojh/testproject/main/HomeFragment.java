package com.ojh.testproject.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ojh.testproject.R;
import com.ojh.testproject.main.adapter.TimeLineAdapter;
import com.ojh.testproject.main.data.TimeLineItem;
import com.ojh.testproject.main.view.TimeLineDecoration;
import com.ojh.testproject.manager.NetworkManager;
import com.ojh.testproject.network.TimeLineApi;
import com.ojh.testproject.test.SearchItem;
import com.ojh.testproject.test.SearchResult;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    @Bind(R.id.homeRecyclerView)
    RecyclerView recyclerView;

    TimeLineAdapter mAdapter;

    private String res_img[] = {"http://imgnews.naver.com/image/138/2011/02/16/20110216150008__BXS3J.jpg"
            , "http://imgnews.naver.com/image/011/2011/02/10/alba04201102101655230.jpg"};

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
        ButterKnife.bind(this,rootView);
        init();
        return rootView;
    }

    private void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new TimeLineDecoration(10));
        mAdapter = new TimeLineAdapter(getContext());
        recyclerView.setAdapter(mAdapter);

        getData();
    }

    private void getData() {

        TimeLineApi apiClient = NetworkManager.getInstance().getApi(TimeLineApi.class);

        Call<SearchResult> call = apiClient.searchImageList("636df8b114f0cc206273eacf348eb45a",
                "치킨", 10, 1, "json");
        call.enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                Log.d("message", response.message());

                mAdapter.clear();
                for (SearchItem item : response.body().channel.item) {
                    TimeLineItem mItem = new TimeLineItem();
                    mItem.iconUrl=item.thumbnail;
                    mItem.name=item.title;
                    mItem.desc=item.link;
                    mAdapter.add(mItem);
                }
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {
                Log.e("error", t.getMessage());
            }
        });

    }

}
