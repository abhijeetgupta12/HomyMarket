package com.example.abhi.homymarket;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class HomePage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    FragmentManager fm = getSupportFragmentManager();


    ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    private DatabaseReference db;
    private ImageButton Cart;
    private TextView Notification,Home;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm.beginTransaction().replace(R.id.frame,new Fragment_Home()).commit();//by default this fragment is used


        //As soon as the app opens if the user is already logged in this page opens with user details and a
        //dialouge appears saying "Loading Please wait....

        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please wait");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();


        //The below coded lines are used to retrieve signed in user details ....

        mAuth=FirebaseAuth.getInstance();
        FirebaseUser currentUser=mAuth.getCurrentUser();//gets the current user
        db= FirebaseDatabase.getInstance().getReference().child(currentUser.getUid());
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {


                //here you retrieve user name or any thing related

                progressDialog.dismiss();
            }

            @Override
            public void onCancelled( DatabaseError databaseError) {
                progressDialog.dismiss();

            }
        });


        //Below is the code for Custom_toolbar and its related component.........

        Cart=findViewById(R.id.cart);
        Notification=findViewById(R.id.notification);
        Toolbar toolbar = findViewById(R.id.toolbarcustom);
        setSupportActionBar(toolbar);

        Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(HomePage.this,"Cart",Toast.LENGTH_LONG).show();

                fm.beginTransaction().replace(R.id.frame,new Fragment_WishList()).commit();//by default this fragment is used



            }
        });

        Notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Toast.makeText(HomePage.this,"Notification",Toast.LENGTH_LONG).show();

            }
        });









        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.homeburgericon);//orange colour


        navigationView.setNavigationItemSelectedListener(this);


    }//onCreate Ends............................






    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);  //if the drawer bar is open it first closes it else it closes the app
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.logout) {

            mAuth.signOut();
            startActivity(new Intent(HomePage.this,Login.class));
            finish();

        } else if (id == R.id.women) {

             fm.beginTransaction().replace(R.id.frame,new Fragment_Women_Apparels()).addToBackStack(null).commit();

        } else if (id == R.id.home) {

            fm.beginTransaction().replace(R.id.frame,new Fragment_Home()).addToBackStack(null).commit();

        } else if (id == R.id.myaccount) {

            fm.beginTransaction().replace(R.id.frame,new Fragment_MyAccount()).addToBackStack(null).commit();

        } else if (id == R.id.bumperoffer) {

            fm.beginTransaction().replace(R.id.frame,new Fragment_BumperOffer()).addToBackStack(null).commit();

        } else if (id == R.id.men) {

            fm.beginTransaction().replace(R.id.frame,new Fragment_Men_Apparels()).addToBackStack(null).commit();

        }else if (id == R.id.kids) {

            fm.beginTransaction().replace(R.id.frame,new Fragment_Kids_Toys()).addToBackStack(null).commit();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }







}
