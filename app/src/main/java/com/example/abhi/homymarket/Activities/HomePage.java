package com.example.abhi.homymarket.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.example.abhi.homymarket.Fragments.BumperOffer;
import com.example.abhi.homymarket.Fragments.Chakhna;
import com.example.abhi.homymarket.Fragments.ContactUs;
import com.example.abhi.homymarket.Fragments.DairyProducts;
import com.example.abhi.homymarket.Fragments.Fruits;
import com.example.abhi.homymarket.Fragments.Home;
import com.example.abhi.homymarket.Fragments.Kids_Toys;
import com.example.abhi.homymarket.Fragments.Men_Apparels;
import com.example.abhi.homymarket.Fragments.MyAccount;
import com.example.abhi.homymarket.Fragments.Cart;
import com.example.abhi.homymarket.Fragments.MyOrders;
import com.example.abhi.homymarket.Fragments.Stationary;
import com.example.abhi.homymarket.Fragments.Women_Apparels;
import com.example.abhi.homymarket.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;


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

        //Messaging Service.....




        Home = findViewById(R.id.home1);
        fm.beginTransaction().replace(R.id.frame,new Home()).commit();//by default this fragment is used


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
        db = FirebaseDatabase.getInstance().getReference().child(currentUser.getUid());
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {


                //The code below is used to set the value in any of the view in nav_header_main.xml....
                String name="Hi "+dataSnapshot.child("Name").getValue().toString()+"!";
                NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                View headerView = navigationView.getHeaderView(0);
                TextView navUsername = (TextView) headerView.findViewById(R.id.home1);
                navUsername.setText(name);

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

                fm.beginTransaction().replace(R.id.frame,new Cart()).addToBackStack(null).commit();//by default this fragment is used



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

        } else if (id == R.id.stationary) {

             fm.beginTransaction().replace(R.id.frame,new Stationary()).addToBackStack(null).commit();

        } else if (id == R.id.fruits) {

            fm.beginTransaction().replace(R.id.frame,new Fruits()).addToBackStack(null).commit();

        }
        else if (id == R.id.dairy_products) {

            fm.beginTransaction().replace(R.id.frame,new DairyProducts()).addToBackStack(null).commit();

        }
        else if (id == R.id.home) {

            fm.beginTransaction().replace(R.id.frame,new Home()).commit();

        } else if (id == R.id.myaccount) {

            fm.beginTransaction().replace(R.id.frame,new MyAccount()).addToBackStack(null).commit();

        } else if (id == R.id.chakhna) {

            fm.beginTransaction().replace(R.id.frame,new Chakhna()).addToBackStack(null).commit();

        } else if (id == R.id.helpcenter) {

            fm.beginTransaction().replace(R.id.frame,new ContactUs()).addToBackStack(null).commit();

        }else if (id == R.id.myorder) {

            fm.beginTransaction().replace(R.id.frame,new MyOrders()).addToBackStack(null).commit();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }







}
