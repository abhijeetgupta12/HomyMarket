package com.example.abhi.homymarket;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Final_Product extends Fragment {

    TextView textView;


    public Final_Product() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_final__product, container, false);

        textView=v.findViewById(R.id.text);


        String strtext = getArguments().getString("NAME");

        textView.setText(strtext);




        return v;
    }



}
