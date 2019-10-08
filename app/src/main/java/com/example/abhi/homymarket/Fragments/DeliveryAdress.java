package com.example.abhi.homymarket.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.abhi.homymarket.Models.CartFetch;
import com.example.abhi.homymarket.R;
import com.example.abhi.homymarket.Adapters.Cart_Adapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class DeliveryAdress extends Fragment {

    View v;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    DatabaseReference db;
    TextView name1,area1,pin1,landmark1,tot,del_charge,pay_amt;
    float sum = 0;
    float dc = 0;


    public DeliveryAdress() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_delivery_adress, container, false);


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

                }



                String name=dataSnapshot.child("Name").getValue().toString();
                String area=dataSnapshot.child("Area").getValue().toString();
                String pin=dataSnapshot.child("Pin").getValue().toString();
                String landmark=dataSnapshot.child("Landmark").getValue().toString();


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
                dc=0;
                sum=0;
                CartFetch.priceList.clear();

            }

            @Override
            public void onCancelled( DatabaseError databaseError) {

            }
        });




        return v;
    }

}
