package com.example.abhi.homymarket;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_MyAccount extends Fragment {

    View v;
    private DatabaseReference db;
    private FirebaseAuth mAuth;
    private TextView name1,email1,phone1,area1,pin1,landmark1;
    private FirebaseUser currentUser;
    private EditText area,pin,landmark;
    private Button update;


    public Fragment_MyAccount() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment__my_account, container, false);

        //button
        update=v.findViewById(R.id.update);

        //textView
        name1=v.findViewById(R.id.name);
        email1=v.findViewById(R.id.email);
        phone1=v.findViewById(R.id.phone);
        area1=v.findViewById(R.id.area);
        pin1=v.findViewById(R.id.pin);
        landmark1=v.findViewById(R.id.landmark);

        //editText
        area=v.findViewById(R.id.address1);
        pin=v.findViewById(R.id.address2);
        landmark=v.findViewById(R.id.address3);


        //Retrieving Data
        mAuth=FirebaseAuth.getInstance();
        currentUser=mAuth.getCurrentUser();
        db= FirebaseDatabase.getInstance().getReference().child(currentUser.getUid());
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {

                String name=dataSnapshot.child("Name").getValue().toString();
                String area=dataSnapshot.child("Area").getValue().toString();
                String pin=dataSnapshot.child("Pin").getValue().toString();
                String landmark=dataSnapshot.child("Landmark").getValue().toString();
                String email=dataSnapshot.child("Email").getValue().toString();
                String phone=dataSnapshot.child("Phone").getValue().toString();


                name1.setText("Name : "+name);
                email1.setText("Email : "+email);
                phone1.setText("Phone No : "+phone);
                area1.setText("Area : "+area);
                pin1.setText("Pin : "+pin);
                landmark1.setText("Landmark : "+landmark);
            }

            @Override
            public void onCancelled( DatabaseError databaseError) {

            }
        });


        //Updating Data
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(area.getText().toString().trim().equals(""))
                {
                    area.setError("Can't be Empty");
                    area.requestFocus();
                    return;
                }

                if(pin.getText().toString().trim().equals(""))
                {
                    pin.setError("Can't be Empty");
                    pin.requestFocus();
                    return;
                }

                if(pin.getText().toString().trim().length()<6 && pin.getText().toString().trim().length()>6)
                {
                    pin.setError("Enter a valid pin");
                    pin.requestFocus();
                    return;
                }

            db.child("Area").setValue(area.getText().toString().trim());
            db.child("Pin").setValue(pin.getText().toString().trim());
            db.child("Landmark").setValue(landmark.getText().toString().trim());

            area.setText("");
            pin.setText("");
            landmark.setText("");


            Toast.makeText(getActivity(), "Details Updated", Toast.LENGTH_SHORT).show();

            }
        });



        return v;
    }

}
