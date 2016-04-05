package com.project.or.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.or.R;
import com.project.or.manager.PropertyManager;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ExtraFragment extends Fragment {

    @Bind(R.id.txtInfo)
    TextView textView;

    public ExtraFragment() {
        // Required empty public constructor
    }


    public static ExtraFragment newInstance() {
        ExtraFragment fragment = new ExtraFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_extra, container, false);
        ButterKnife.bind(this, rootView);

        PropertyManager pm = PropertyManager.getInstance();

        textView.setText("email : "+pm.getEmail()+"\nid : "+pm.getId()+"\nname : "+pm.getName()+"\ntoken : "+pm.getToken());

        return rootView;
    }

}
