package com.example.abhi.homymarket;


import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Final_Product extends Fragment {

    ViewPager viewPager;
    LinearLayout linearLayout;
    Spinner spinner;
    TextView tvquantity;
    String []quantity={"1","2","3","4","5","6"};
    ArrayAdapter<String> adp;
    RelativeLayout relativeLayout;
    private int dots_count;
    private ImageView[] dots;


    public Final_Product() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_final__product, container, false);

        tvquantity=v.findViewById(R.id.setQuantitytext);
        spinner=v.findViewById(R.id.Qty);
        adp=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,quantity);
        spinner.setAdapter(adp);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i)
                {
                    case 0:
                        tvquantity.setText(quantity[0]);
                        break;
                    case 1:
                        tvquantity.setText(quantity[1]);
                        break;
                    case 2:
                        tvquantity.setText(quantity[2]);
                        break;
                    case 3:
                        tvquantity.setText(quantity[3]);
                        break;
                    case 4:
                        tvquantity.setText(quantity[4]);
                        break;
                    case 5:
                        tvquantity.setText(quantity[5]);
                        break;
                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                tvquantity.setText("1");
            }
        });

        viewPager=v.findViewById(R.id.viewitemangle);
        linearLayout=v.findViewById(R.id.lineardots);
        relativeLayout=v.findViewById(R.id.viewpagerlinear);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
                relativeLayout.setLayoutParams(params);
            }
        });

        viewPagerAdapter_FinalProduct viewPagerAdapterdone=new viewPagerAdapter_FinalProduct(getActivity());
        viewPager.setAdapter(viewPagerAdapterdone);
        dots_count=viewPagerAdapterdone.getCount();
        dots=new ImageView[dots_count];

        for(int i=0;i<dots_count;i++)
        {
            dots[i]=new ImageView(getActivity());
            dots[i].setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.inactive_dots));
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8,0,8,0);
            linearLayout.addView(dots[i],params);
        }
        dots[0].setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.active_dots));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                for(int k=0;k<dots_count;k++){
                    dots[k].setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.inactive_dots));
                }
                dots[i].setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.active_dots));

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });




        //String strtext = getArguments().getString("NAME");





        return v;
    }



}

