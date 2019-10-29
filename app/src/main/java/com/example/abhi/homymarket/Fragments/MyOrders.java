package com.example.abhi.homymarket.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.abhi.homymarket.Adapters.MyOrdersAdapter;
import com.example.abhi.homymarket.Adapters.RecyclerAdapter;
import com.example.abhi.homymarket.Models.DataFetch;
import com.example.abhi.homymarket.Models.MyOrdersFetch;
import com.example.abhi.homymarket.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyOrders extends Fragment {

    private View v;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    private RecyclerView recyclerView;
    ArrayList<MyOrdersFetch> data = new ArrayList<>();
    private ProgressBar progressBar;
    private Animation animation;
    private RelativeLayout relativeLayout;


    public MyOrders() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_my_orders, container, false);


        progressBar=v.findViewById(R.id.progress);
        animation= AnimationUtils.loadAnimation(getActivity(),R.anim.rotate);
        progressBar.startAnimation(animation);


        mAuth= FirebaseAuth.getInstance();
        currentUser=mAuth.getCurrentUser();
        String id = currentUser.getUid().trim();

        Log.d("lllll",id);

        recyclerView=v.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        data.clear();
        RequestQueue rq = Volley.newRequestQueue(getActivity());
        String url = "https://homimarket.com/wp-content/Android/MyOrders.php?get=select*from Orders where customerid LIKE \""+id+"\"";
        StringRequest sr= new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jo = new JSONObject(response);
                    JSONArray ja = jo.getJSONArray("result");



                    if(ja.length()==0)
                    {
                        Toast.makeText(getActivity(),"No Orders",Toast.LENGTH_SHORT).show();
                    }

                    for(int i =0;i<ja.length();i++)
                    {

                        JSONObject jo1 = ja.getJSONObject(i);

                        String ORDER_NO=jo1.getString("ORDER_NO");
                        String TITLE= jo1.getString("TITLE");
                        String PRICE=jo1.getString("PRICE");
                        String STATUS=jo1.getString("STATUS");
                        String ADDRESS=jo1.getString("ADDRESS");
                        String PHONE=jo1.getString("PHONE");
                        String DELIVARY_SLOT=jo1.getString("DELIVARY_SLOT");



                        //Instead of creating many arrayList we can create model class and then create list of model
                        //we can assign values to model class by creating constructor and sending values like shown below
                        MyOrdersFetch ob = new MyOrdersFetch(ORDER_NO,TITLE,PRICE,STATUS,ADDRESS,PHONE,DELIVARY_SLOT);

                        data.add(ob);

                    }


                } catch (JSONException e) {
                    Toast.makeText(getActivity(),"Unable to Fetch Data",Toast.LENGTH_LONG).show();
                }

                progressBar.clearAnimation();
                progressBar.setVisibility(View.INVISIBLE);
                recyclerView.setAdapter(new MyOrdersAdapter(getActivity(),data));


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                progressBar.clearAnimation();
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getActivity(),"Please check your internet connection...",Toast.LENGTH_LONG).show();

            }
        });

        sr.setShouldCache(false);
        rq.add(sr);





        return v;
    }

}
