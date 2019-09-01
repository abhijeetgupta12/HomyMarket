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
public class Fragment_BumperOffer extends Fragment {

    View v;
    RecyclerView recyclerView;

    ArrayList<String> name;
    ArrayList<String> color;
    ArrayList<String> brand;
    ArrayList<String> size;
    ArrayList<String> img;
    ArrayList<String> gender;
    ArrayList<String> desc;
    ArrayList<String> price;
    ArrayList<String> sleeves;
    ArrayList<String> rating;
    ArrayList<String> discount;
    ArrayList<String> type;

    ProgressBar progressBar;
    Animation animation;


    public Fragment_BumperOffer() {
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
        color=new ArrayList<>();
        brand=new ArrayList<>();
        size=new ArrayList<>();
        img=new ArrayList<>();
        gender=new ArrayList<>();
        desc=new ArrayList<>();
        price=new ArrayList<>();
        sleeves=new ArrayList<>();
        discount=new ArrayList<>();
        rating=new ArrayList<>();
        type=new ArrayList<>();

        recyclerView=v.findViewById(R.id.recycler);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView


        RequestQueue rq = Volley.newRequestQueue(getActivity());
        String url = "https://homimarket.com/wp-content/Android/fetch_shirts.php?get=select*from SHIRTS";
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
                        String COLOR=jo1.getString("COLOUR");
                        String BRAND=jo1.getString("BRAND");
                        String SIZE=jo1.getString("SIZE");
                        String IMAGE=jo1.getString("IMAGE");
                        String GENDER=jo1.getString("GENDER");
                        String DESC=jo1.getString("DESCRIPTION");
                        String PRICE=jo1.getString("PRICE");
                        String SLEEVES=jo1.getString("SLEEVES");
                        String RATING=jo1.getString("RATING");
                        String DISCOUNT=jo1.getString("DISCOUNT");
                        String TYPE=jo1.getString("TYPE");

                        name.add(NAME);
                        color.add(COLOR);
                        brand.add(BRAND);
                        size.add(SIZE);
                        img.add(IMAGE);
                        gender.add(GENDER);
                        desc.add(DESC);
                        price.add(PRICE);
                        sleeves.add(SLEEVES);
                        rating.add(RATING);
                        discount.add(DISCOUNT);
                        type.add(TYPE);


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                progressBar.clearAnimation();
                progressBar.setVisibility(View.INVISIBLE);

                recyclerView.setAdapter(new RecyclerAdapter(getActivity(),name,color,brand,size,img,gender,desc,price
                ,sleeves,rating,discount,type));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressBar.clearAnimation();
                progressBar.setVisibility(View.INVISIBLE);

                Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
        rq.add(sr);






        return v;
    }

}
