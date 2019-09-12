package com.example.abhi.homymarket;


import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Home extends Fragment {

    private View v;
    private LinearLayout txt;
    SliderView sliderView;
    LinearLayout all;



    public Fragment_Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        v=inflater.inflate(R.layout.fragment_home_, container, false);



        //fragment testing........................................................

        /*all=v.findViewById(R.id.all);

        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = Objects.requireNonNull(getActivity()).getSupportFragmentManager();//Actual was---------FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.frame,new Fragment_Women_Apparels()).commit();//by default this fragment is used
            }
        });*/

        //........................................................................


        txt=v.findViewById(R.id.todaysdeal);  //Today's deal......
        startcoloranimation(txt);

        //ImageSlider.....

        sliderView=v.findViewById(R.id.imageSlider);
        final ImageSliderAdapter sliderAdapterExample=new ImageSliderAdapter(getActivity());
        sliderAdapterExample.setCount(5);
        sliderView.setSliderAdapter(sliderAdapterExample);
        sliderView.setIndicatorAnimation(IndicatorAnimations.SLIDE); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.CUBEINROTATIONTRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.startAutoCycle();
        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                sliderView.setCurrentPagePosition(position);
            }
        });

        //ImageSlider Ends.....

        return v;
    }

    public void startcoloranimation(View v){

        int startcolor=0xffffffff;
        int colorEnd=0xFFE9910C;
        ValueAnimator coloranim= ObjectAnimator.ofInt(v,"backgroundColor",startcolor,colorEnd);
        coloranim.setDuration(1500);
        coloranim.setEvaluator(new ArgbEvaluator());
        coloranim.setRepeatCount(1000);
        coloranim.setRepeatMode(ValueAnimator.REVERSE);
        coloranim.start();
    }

}
