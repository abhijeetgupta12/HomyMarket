package com.example.abhi.homymarket;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {


    EditText name,email,phone,pass,conPass;
    Button reg;
    FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        mAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phoneNo);
        pass = findViewById(R.id.pass);
        conPass = findViewById(R.id.conPass);

        reg = findViewById(R.id.register);//button





        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String tname=name.getText().toString().trim();
                final String tmail=email.getText().toString().trim();
                final String tphone=phone.getText().toString().trim();
                final String tpass=pass.getText().toString().trim();
                final String tconpass=conPass.getText().toString().trim();

                if(tname.equals(""))
                {
                    name.setError("Enter the name");
                    name.requestFocus();
                    return;
                }

                if(tmail.equals(""))
                {
                    email.setError("Enter the email");
                    email.requestFocus();
                    return;
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(tmail).matches())
                {
                    email.setError("Please Enter a Valid Email");
                    email.requestFocus();
                    return;

                }

                if(tphone.equals(""))
                {
                    phone.setError("Enter phone no");
                    phone.requestFocus();
                    return;
                }

                if(tphone.length()<10 || tphone.length()>10)
                {
                    phone.setError("Enter valid phone no");
                    phone.requestFocus();
                    return;
                }


                if(tpass.equals(""))
                {
                    pass.setError("Enter Password");
                    pass.requestFocus();
                    return;
                }
                if(tpass.length()<7)
                {
                    pass.setError("min length is 7");
                    pass.requestFocus();
                    return;
                }

                if(tconpass.equals(""))
                {
                    pass.setError("Enter Confirm Password");
                    pass.requestFocus();
                    return;
                }
                if(!tconpass.equals(tpass))
                {
                    pass.setError("Password Mismatch");
                    pass.requestFocus();
                    return;
                }


                progressDialog.setTitle("Registering the User");
                progressDialog.setMessage("Please wait while we create your account");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                mAuth.createUserWithEmailAndPassword(tmail,tpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            sendDataToFirebase(tname,tmail,tphone,tpass);
                        }else{
                            progressDialog.hide();
                            Toast.makeText(Register.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    }
                });
            }
        });







    }

    private void sendDataToFirebase(String tname,String tmail,String tphone,String tpass){

        FirebaseUser user=mAuth.getCurrentUser();
        Map<String,String> map=new HashMap<>();
        map.put("Name",tname);
        map.put("Email",tmail);
        map.put("Phone",tphone);
        map.put("Password",tpass);
        mRef= FirebaseDatabase.getInstance().getReference().child(user.getUid());
        mRef.setValue(map);

        progressDialog.dismiss();
    }


}
