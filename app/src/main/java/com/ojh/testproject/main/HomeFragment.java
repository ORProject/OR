package com.ojh.testproject.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ojh.testproject.R;
import com.ojh.testproject.main.adapter.TimeLineAdapter;
import com.ojh.testproject.main.data.TimeLineItem;
import com.ojh.testproject.main.view.TimeLineDecoration;

import butterknife.Bind;
import butterknife.ButterKnife;


public class HomeFragment extends Fragment {

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
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        init(rootView);
        return rootView;
    }

    private void init(View v) {
        recyclerView = (RecyclerView)v.findViewById(R.id.homeRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new TimeLineDecoration(10));
        mAdapter = new TimeLineAdapter(getContext());
        recyclerView.setAdapter(mAdapter);

        getData();
    }

    private void getData() {
        mAdapter.clear();
        for(int i=0; i<10; i++) {
            TimeLineItem item = new TimeLineItem();
            item.name = "이름"+i;
            item.desc = i+"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
            item.iconUrl = res_img[i%2];
            mAdapter.add(item);
        }
    }

}
