package com.example.abhi.homymarket.Fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.text.Html;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
    private FirebaseUser currentUser;
    private DatabaseReference db;
    private TextView name1,area1,pin1,landmark1,tot,del_charge,pay_amt;
    private Button Order;
    private float sum = 0;
    private float dc = 0;
    private String Add = "";
    private String Title = "";
    private Spinner days, time;
    private ArrayAdapter<String> spinner_days,spinner_time;
    private String db_day,db_time;
    private ProgressDialog progressDialog;
    private List<DataFetch> data;
    private List<CartFetch> qty;
    private String phoneNo;




    public DeliveryAdress(List<DataFetch> data,List<CartFetch> qty) {

        this.data = data;
        this.qty = qty;
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


                phoneNo = dataSnapshot.child("Phone").getValue().toString().trim();


                for(int i = 0;i<data.size();i++)
                {
                    sum = sum + Integer.parseInt(qty.get(i).getPrice());
                    Title = Title + qty.get(i).getName() + "*" + qty.get(i).getQuantity()+" ";
                }



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
                    String url="https://homimarket.com/wp-content/Alok/customer_order.php?";
                    StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {



                            progressDialog.dismiss();
                            dc=0;
                            sum=0;

                            //as i clicked the order now button the screen is moved to MyOrders page but when from MyOrders page
                            //i click on back button the net amount of payment increases....so dc and sum is kept as 0

                            MyOrders ldf = new MyOrders();
                            FragmentManager fm = (getActivity()).getSupportFragmentManager();

                            //the below for loop is used to clear the addToBack
                            int count = fm.getBackStackEntryCount();
                            for(int i = 0; i < count; ++i) {
                                fm.popBackStack();
                            }

                            fm.beginTransaction().replace(R.id.frame,ldf).addToBackStack(null).commit();


                            //clearing the cart..............

                            DatabaseReference mRef;
                            mAuth = FirebaseAuth.getInstance();
                            FirebaseUser user=mAuth.getCurrentUser();
                            mRef= FirebaseDatabase.getInstance().getReference().child(user.getUid()).child("WishList");
                            mRef.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    //This is the process to call a fragment from any Adapter Class................


                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                }
                            });



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
                            hm.put("phone",phoneNo);
                            hm.put("address",Add);
                            hm.put("title",Title);
                            hm.put("status","Pending");
                            hm.put("price",String.valueOf(sum+dc));
                            hm.put("delivary_slot",db_day+" "+db_time);

                            Log.d("@@@@@",currentUser.getUid());
                            Log.d("@@@@@",phoneNo);
                            Log.d("@@@@@",Add);
                            Log.d("@@@@@",Title);
                            Log.d("@@@@@","Pending");
                            Log.d("@@@@@",String.valueOf(sum+dc));
                            Log.d("@@@@@",db_day+" "+db_time);

                            return hm;
                        }
                    };

                    sr.setShouldCache(false);
                    rq.add(sr);


                }



            }
        });


        return v;
    }

}
