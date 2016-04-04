package com.ojh.testproject.main;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ojh.testproject.R;
import com.ojh.testproject.manager.PropertyManager;

import butterknife.Bind;
import butterknife.ButterKnife;


public class HomeFragment extends Fragment {

    @Bind(R.id.txtInfo)
    TextView textView;

    public HomeFragment() {
        // Required empty public constructor
    }

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
        ButterKnife.bind(this, rootView);

        PropertyManager pm = PropertyManager.getInstance();

        textView.setText("email : "+pm.getEmail()+"\nid : "+pm.getId()+"\nname : "+pm.getName()+"\ntoken : "+pm.getToken());

        return rootView;
    }

}
