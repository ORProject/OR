package com.project.or.intro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.project.or.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class IntroFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    public static final int IMG_INTRO[] = {
            android.R.drawable.ic_lock_idle_alarm,
            android.R.drawable.ic_delete,
            android.R.drawable.ic_dialog_info,
            android.R.mipmap.sym_def_app_icon
    };

    @Bind(R.id.imgIntro) ImageView imageView;
    @Bind(R.id.btn_skip) Button btnSkip;


    public static IntroFragment newInstance(int sectionNumber) {
        IntroFragment fragment = new IntroFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_intro, container, false);
        ButterKnife.bind(this, rootView);

        int index = getArguments().getInt(ARG_SECTION_NUMBER);
        imageView.setImageResource(IMG_INTRO[index]);
        if(index==IMG_INTRO.length-1) {
            btnSkip.setVisibility(View.VISIBLE);
        }

        return rootView;
    }

    @OnClick(R.id.btn_skip)
    public void moveToLogin() {
        startActivity(new Intent(getActivity(),LoginActivity.class));
        getActivity().finish();
    }
}
