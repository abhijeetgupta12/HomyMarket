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
public class Fragment_Men_Apparels extends Fragment {

    View v;
    RecyclerView recyclerView;
    ArrayList<DataFetch> data = new ArrayList<>();
    ProgressBar progressBar;
    Animation animation;

    public Fragment_Men_Apparels() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_women__apparels_, container, false);

        progressBar=v.findViewById(R.id.progress);
        animation= AnimationUtils.loadAnimation(getActivity(),R.anim.rotate);
        progressBar.startAnimation(animation);



        recyclerView=v.findViewById(R.id.recycler);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView

        data.clear();
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

                        String ID=jo1.getString("ID");
                        String NAME=jo1.getString("NAME");
                        String BRAND=jo1.getString("BRAND");
                        String GENDER=jo1.getString("GENDER");
                        String DISCOUNT=jo1.getString("DISCOUNT");
                        String DESC=jo1.getString("DESCRIPTION");
                        String SELL_PRICE=jo1.getString("SELLPRICE");
                        String MARK_PRICE=jo1.getString("MARKPRICE");
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

                        DataFetch ob = new DataFetch(ID,NAME,BRAND,GENDER,DISCOUNT,DESC,SELL_PRICE,MARK_PRICE
                                ,RATING,TYPE,SIZE,CATEGORY,LENGTH,IMAGE1,IMAGE2,IMAGE3,IMAGE4,IMAGE5,SHOP,COLOR,STOCK,MATERIAL);

                        data.add(ob);



                    }

                } catch (JSONException e) {
                    e.printStackTrace();

                    Toast.makeText(getActivity(),"Unable to Fetch Data",Toast.LENGTH_LONG).show();
                }
                progressBar.clearAnimation();
                progressBar.setVisibility(View.INVISIBLE);
                recyclerView.setAdapter(new RecyclerAdapter(getActivity(),data));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                progressBar.clearAnimation();
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

        sr.setShouldCache(false);
        rq.add(sr);

        return v;
    }

}
