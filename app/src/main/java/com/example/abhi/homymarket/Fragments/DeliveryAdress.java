package com.example.abhi.homymarket.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.abhi.homymarket.Models.CartFetch;
import com.example.abhi.homymarket.Models.DataFetch;
import com.example.abhi.homymarket.R;
import com.example.abhi.homymarket.Adapters.Cart_Adapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class DeliveryAdress extends Fragment {

    View v;
    private FirebaseAuth mAuth;
    FirebaseUser currentUser;
    DatabaseReference db;
    TextView name1,area1,pin1,landmark1,tot,del_charge,pay_amt;
    Button Order;
    float sum = 0;
    float dc = 0;
    String Add = "";
    String Title = "";
    Spinner days, time;
    ArrayAdapter<String> spinner_days,spinner_time;
    String db_day,db_time;
    ProgressDialog progressDialog;





    public DeliveryAdress() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_delivery_adress, container, false);

        final String DAYS[] = {"SELECT DAY","MONDAY","TUESDAY","WEDNESDAY","THURSDAY","FRIDAY","SATURDAY","SUNDAY"};
        final String TIME[] = {"SELECT TIME","8:30 P.M-9:30 P.M","11:00 P.M-01:00 A.M"};
        days = v.findViewById(R.id.days);
        time = v.findViewById(R.id.time);
        progressDialog=new ProgressDialog(getActivity());


        Order = v.findViewById(R.id.orderNow);
        del_charge= v.findViewById(R.id.delivary_charge);
        pay_amt = v.findViewById(R.id.PayingAmount);
        tot = v.findViewById(R.id.tot_price);
        name1=v.findViewById(R.id.name);
        area1=v.findViewById(R.id.area);
        pin1=v.findViewById(R.id.pin);
        landmark1=v.findViewById(R.id.landmark);


        mAuth= FirebaseAuth.getInstance();
        currentUser=mAuth.getCurrentUser();
        db= FirebaseDatabase.getInstance().getReference().child(currentUser.getUid());
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {



                for(int i = 0;i<CartFetch.priceList.size();i++)
                {
                    sum = sum + CartFetch.priceList.get(i);

                    Log.d("@@@@@",String.valueOf(CartFetch.priceList.get(i)));
                    Log.d("@@@@@",String.valueOf(CartFetch.qty.get(i)));

                    Title = Title + CartFetch.name.get(i) + "*" + CartFetch.qty.get(i)+" ";
                }
                Log.d("@@@@@","-----------------------------------------------------------");
                Log.d("@@@@@",Title);



                String name=dataSnapshot.child("Name").getValue().toString();
                String area=dataSnapshot.child("Area").getValue().toString();
                String pin=dataSnapshot.child("Pin").getValue().toString();
                String landmark=dataSnapshot.child("Landmark").getValue().toString();

                Add = "Name: "+name+" Area: "+area+" Landmark: "+landmark+" Pin: "+pin;

                name1.setText("Name : "+name);
                area1.setText("Area : "+area);
                pin1.setText("Pin : "+pin);
                landmark1.setText("Landmark : "+landmark);

                if(sum<30)
                {
                    dc = 10;
                }
                del_charge.setText(String.valueOf(dc));
                pay_amt.setText(String.valueOf(dc+sum));
                tot.setText(String.valueOf(sum));
                Title = "";
                CartFetch.priceList.clear();
                CartFetch.qty.clear();
                CartFetch.name.clear();


            }

            @Override
            public void onCancelled( DatabaseError databaseError) {

            }
        });


        spinner_days = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,DAYS);
        days.setAdapter(spinner_days);

        days.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                db_day = days.getItemAtPosition(i).toString().trim();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_time = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,TIME);
        time.setAdapter(spinner_time);


        time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                db_time = time.getItemAtPosition(i).toString().trim();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });





        Order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(db_day.equals("SELECT DAY") || db_time.equals("SELECT TIME"))
                {
                    Toast.makeText(getActivity(),"Enter Delivary Date and Time",Toast.LENGTH_SHORT).show();
                }

                else
                {

                    progressDialog.setTitle("Placing Order");
                    progressDialog.setMessage("Please wait ...");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    RequestQueue rq = Volley.newRequestQueue(getActivity());
                    String url="hjhjhgj";
                    StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            progressDialog.dismiss();
                            MyOrders ldf = new MyOrders();
                            FragmentManager fm = (getActivity()).getSupportFragmentManager();
                            fm.beginTransaction().replace(R.id.frame,ldf).addToBackStack(null).commit();



                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            progressDialog.dismiss();
                            Toast.makeText(getActivity(),"Plz..try again..",Toast.LENGTH_SHORT).show();

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            HashMap<String,String> hm = new HashMap<String, String>();
                            hm.put("customerid",currentUser.getUid());
                            hm.put("phone",currentUser.getPhoneNumber());
                            hm.put("address",Add);
                            hm.put("title",Title);
                            hm.put("status","Pending");
                            hm.put("price",String.valueOf(sum+dc));
                            hm.put("delivary_slot",db_day+" "+db_time);
                            return hm;
                        }
                    };
                    rq.add(sr);


                }



            }
        });


        return v;
    }

}
