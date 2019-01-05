package com.example.common.refrence.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.common.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SimpleFragment extends Fragment {

    private String mTiitle;

    public static SimpleFragment getInstance(String title) {
        SimpleFragment simpleFragment=new SimpleFragment();
        simpleFragment.mTiitle=title;
        return simpleFragment;
    }

    public SimpleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_simple, container, false);
        TextView textView=view.findViewById(R.id.card_title_tv);
        textView.setText(mTiitle);
        return view;
    }

}
