package com.example.abhi.homymarket;


import android.app.DownloadManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Women_Apparels extends Fragment {

    View v;
    RecyclerView recyclerView;

    ArrayList<String> img,name,brand,price;


    public Fragment_Women_Apparels() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_women__apparels_, container, false);

        img=new ArrayList<>();
        name=new ArrayList<>();
        brand=new ArrayList<>();
        price=new ArrayList<>();

        recyclerView=v.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


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

                        String IMAGE=jo1.getString("IMAGE");
                        String NAME=jo1.getString("NAME");
                        String BRAND=jo1.getString("BRAND");
                        String PRICE=jo1.getString("PRICE");
                        String RATING=jo1.getString("RATING");

                        Log.d("abcde",NAME);

                        img.add(IMAGE);
                        name.add(NAME);
                        brand.add(BRAND);
                        price.add(PRICE);


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                recyclerView.setAdapter(new RecyclerAdapter(getActivity(),img,name,brand,price));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("abcde","Sorry");

                Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
        rq.add(sr);






        return v;
    }

}
