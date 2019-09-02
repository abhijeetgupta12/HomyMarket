package com.example.abhi.homymarket;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Kids_Toys extends Fragment {


    View v;
    RecyclerView recyclerView;


    ArrayList<String> name,brand,gender,discount,desc,sellprice,markprice,rating,type,size,category,length,
            image1,image2,image3,image4,image5,shop,color,stock,material;

    ProgressBar progressBar;
    Animation animation;



    public Fragment_Kids_Toys() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        v = inflater.inflate(R.layout.fragment_women__apparels_, container, false);


        progressBar=v.findViewById(R.id.progress);
        animation= AnimationUtils.loadAnimation(getActivity(),R.anim.rotate);
        progressBar.startAnimation(animation);

        name=new ArrayList<>();
        brand=new ArrayList<>();
        gender=new ArrayList<>();
        discount=new ArrayList<>();
        desc=new ArrayList<>();
        sellprice=new ArrayList<>();
        markprice=new ArrayList<>();
        rating=new ArrayList<>();
        type=new ArrayList<>();
        size=new ArrayList<>();
        category=new ArrayList<>();
        length=new ArrayList<>();
        image1=new ArrayList<>();
        image2=new ArrayList<>();
        image3=new ArrayList<>();
        image4=new ArrayList<>();
        image5=new ArrayList<>();
        shop=new ArrayList<>();
        color=new ArrayList<>();
        stock=new ArrayList<>();
        material=new ArrayList<>();

        recyclerView=v.findViewById(R.id.recycler);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView


        RequestQueue rq = Volley.newRequestQueue(getActivity());
        String url = "https://homimarket.com/wp-content/Android/products.php?get=select*from PRODUCTS";
        StringRequest sr= new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONObject jo = new JSONObject(response);
                    JSONArray ja = jo.getJSONArray("result");

                    for(int i =0;i<ja.length();i++)
                    {

                        JSONObject jo1 = ja.getJSONObject(i);

                        String NAME=jo1.getString("NAME");
                        String BRAND=jo1.getString("BRAND");
                        String GENDER=jo1.getString("GENDER");
                        String DISCOUNT=jo1.getString("DISCOUNT");
                        String DESC=jo1.getString("DESCRIPTION");
                        String SELLPRICE=jo1.getString("SELLPRICE");
                        String MARKPRICE=jo1.getString("MARKPRICE");
                        String RATING=jo1.getString("RATING");
                        String TYPE=jo1.getString("TYPE");
                        String SIZE=jo1.getString("SIZE");
                        String CATEGORY=jo1.getString("CATEGORY");
                        String LENGTH=jo1.getString("LENGTH");
                        String IMAGE1=jo1.getString("IMAGE1");
                        String IMAGE2=jo1.getString("IMAGE2");
                        String IMAGE3=jo1.getString("IMAGE3");
                        String IMAGE4=jo1.getString("IMAGE4");
                        String IMAGE5=jo1.getString("IMAGE5");
                        String SHOP=jo1.getString("SHOP");
                        String COLOR=jo1.getString("COLOR");
                        String STOCK=jo1.getString("STOCK");
                        String MATERIAL=jo1.getString("MATERIAL");





                        name.add(NAME);
                        brand.add(BRAND);
                        gender.add(GENDER);
                        discount.add(DISCOUNT);
                        desc.add(DESC);
                        sellprice.add(SELLPRICE);
                        markprice.add(MARKPRICE);
                        rating.add(RATING);
                        type.add(TYPE);
                        size.add(SIZE);
                        category.add(CATEGORY);
                        length.add(LENGTH);
                        image1.add(IMAGE1);
                        image2.add(IMAGE2);
                        image3.add(IMAGE3);
                        image4.add(IMAGE4);
                        image5.add(IMAGE5);
                        shop.add(SHOP);
                        color.add(COLOR);
                        stock.add(STOCK);
                        material.add(MATERIAL);


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                progressBar.clearAnimation();
                progressBar.setVisibility(View.INVISIBLE);



                recyclerView.setAdapter(new RecyclerAdapter(getActivity(),name,brand,gender,discount,desc,sellprice,markprice,
                        rating,type,size,category,length,image1,image2,image3,image4,image5,shop,color,stock,material));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("abcde","Sorry");

                progressBar.clearAnimation();
                progressBar.setVisibility(View.INVISIBLE);


                Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
        rq.add(sr);






        return v;

    }

}
