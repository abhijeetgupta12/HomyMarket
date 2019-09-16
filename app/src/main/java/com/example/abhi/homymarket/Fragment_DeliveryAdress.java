package com.example.abhi.homymarket;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
public class Fragment_DeliveryAdress extends Fragment {

    View v;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    DatabaseReference db;
    TextView name1,area1,pin1,landmark1;

    public Fragment_DeliveryAdress() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_delivery_adress, container, false);


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

                String name=dataSnapshot.child("Name").getValue().toString();
                String area=dataSnapshot.child("Area").getValue().toString();
                String pin=dataSnapshot.child("Pin").getValue().toString();
                String landmark=dataSnapshot.child("Landmark").getValue().toString();


                name1.setText("Name : "+name);
                area1.setText("Area : "+area);
                pin1.setText("Pin : "+pin);
                landmark1.setText("Landmark : "+landmark);
            }

            @Override
            public void onCancelled( DatabaseError databaseError) {

            }
        });




        return v;
    }

}
