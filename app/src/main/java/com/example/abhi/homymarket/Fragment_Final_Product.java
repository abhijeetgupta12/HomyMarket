package com.example.abhi.homymarket;


import android.graphics.Paint;
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
public class Fragment_Final_Product extends Fragment {

    private ViewPager viewPager;
    LinearLayout linearLayout;
    Spinner spinner;
    TextView tvquantity;
    String []quantity={"1","2","3","4","5","6"};
    ArrayAdapter<String> adp;
    RelativeLayout relativeLayout;
    private int dots_count;
    private ImageView[] dots;
    private TextView brand,name,markPrice,sellPrice,discount,length,color,gender,type,rating,material,desc;
    int Price,Qty;

    public Fragment_Final_Product() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_final__product, container, false);

        //retrieving data from previous fragment...............
        String brand1 = getArguments().getString("BRAND");
        String name1 = getArguments().getString("NAME");
        String mark_price1 = getArguments().getString("MARK_PRICE");
        String sell_price1 = getArguments().getString("SELL_PRICE");
        String discount1 = getArguments().getString("DISCOUNT");
        String length1 = getArguments().getString("LENGTH");
        String color1 = getArguments().getString("COLOR");
        String gender1 = getArguments().getString("GENDER");
        String type1 = getArguments().getString("TYPE");
        String rating1 = getArguments().getString("RATING");
        String material1 = getArguments().getString("MATERIAL");
        String description1 = getArguments().getString("DESCRIPTION");

        Price = Integer.parseInt(sell_price1);



        brand = v.findViewById(R.id.company_name);
        name = v.findViewById(R.id.product_name);
        markPrice = v.findViewById(R.id.markPrice);
        sellPrice = v.findViewById(R.id.sellPrice);
        discount = v.findViewById(R.id.discount);
        length = v.findViewById(R.id.length);
        color = v.findViewById(R.id.color);
        gender = v.findViewById(R.id.gender);
        type = v.findViewById(R.id.type);
        rating = v.findViewById(R.id.rating);
        material = v.findViewById(R.id.material);
        desc = v.findViewById(R.id.description);

        //setting values to final page elements
        brand.setText(brand1);
        name.setText(name1);
        markPrice.setText(mark_price1);
        sellPrice.setText(sell_price1);
        discount.setText(discount1);
        length.setText(length1);
        color.setText(color1);
        gender.setText(gender1);
        type.setText(type1);
        rating.setText(rating1);
        material.setText(material1);
        desc.setText(description1);

        //make a cut in a textView
        strikeThroughText(markPrice);


        //spinner
        tvquantity=v.findViewById(R.id.setQuantitytext);
        spinner=v.findViewById(R.id.Qty);
        adp=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,quantity);
        spinner.setAdapter(adp);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                tvquantity.setText(quantity[i]);
                Qty = Integer.parseInt(quantity[i]);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                tvquantity.setText(quantity[0]);
                Qty = Integer.parseInt(quantity[0]);
            }
        });


        String img1 = getArguments().getString("IMAGE1");
        String img2 = getArguments().getString("IMAGE2");
        String img3 = getArguments().getString("IMAGE3");
        String img4 = getArguments().getString("IMAGE4");
        String img5 = getArguments().getString("IMAGE5");



        //viewPager........................................................................................

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

        viewPagerAdapter_FinalProduct viewPagerAdapterdone=new viewPagerAdapter_FinalProduct(getActivity(),img1,img2,img3,img4,img5);
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

        //viewPagerEnds..............................................................................................



        return v;
    }


    //function for textView Cut
    private void strikeThroughText(TextView textView)
    {
        textView.setPaintFlags(textView.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
    }



}

