package com.example.abhi.homymarket;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;


public class HomePage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    LinearLayout txt;
    ViewPager mpager;
    ImageView im1,im2,im3,im4,im5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbarcustom);
        setSupportActionBar(toolbar);
        txt=findViewById(R.id.todaysdeal);
        startcoloranimation(txt);
        im1=findViewById(R.id.slide1);
        im2=findViewById(R.id.slide2);
        im3=findViewById(R.id.slide3);
        im4=findViewById(R.id.slide4);
        im5=findViewById(R.id.slide5);
        Picasso.get().load("https://www.google.com/imgres?imgurl=http%3A%2F%2Fassets.trendin.com%2Fimg%2Fapp%2Fshopmedia%2Fproduction%2F1%2F1-5-1697.jpg&imgrefurl=https%3A%2F%2Fwww.mytokri.com%2Fdiscuss%2Fthreads%2Ftrendin-allen-solly-womens-clothing-at-flat-50-off-rs-750-cashback-on-rs-1699.45208%2F&docid=Vde9gBH3EbDpEM&tbnid=3Jaz2CRxNKp-lM%3A&vet=10ahUKEwi6zoXpwpvkAhV24XMBHXnUB9YQMwhPKAgwCA..i&w=530&h=365&bih=754&biw=1536&q=women%20clothes%2050%25offer%20banner&ved=0ahUKEwi6zoXpwpvkAhV24XMBHXnUB9YQMwhPKAgwCA&iact=mrc&uact=8").into(im1);
        Picasso.get().load("https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwipj_mGw5vkAhXZfH0KHWM-BzkQjRx6BAgBEAQ&url=%2Furl%3Fsa%3Di%26source%3Dimages%26cd%3D%26ved%3D%26url%3Dhttps%253A%252F%252Fcoupons.expertsanswer.in%252F2014%252F02%252Fjabong-com-axis-bank-offer-33-axisp33pjb%252F%26psig%3DAOvVaw0AJXRnTffQbKf5zqUnKl-R%26ust%3D1566736608690780&psig=AOvVaw0AJXRnTffQbKf5zqUnKl-R&ust=1566736608690780").into(im2);
        Picasso.get().load("https://www.google.com/imgres?imgurl=https%3A%2F%2Fwww.shopickr.com%2Fwp-content%2Fuploads%2F2015%2F12%2Fjabong-anniversary-sale-winter-2015-1.jpg&imgrefurl=https%3A%2F%2Fwww.shopickr.com%2Fcoupon%2Fjabong-anniversary-sale-winter-wear-minimum-40-off&docid=NEAL1XmcDMJLyM&tbnid=90fwZTzYpGuekM%3A&vet=1&w=555&h=250&bih=754&biw=1536&ved=2ahUKEwipj_mGw5vkAhXZfH0KHWM-BzkQxiAoAHoECAEQFw&iact=c&ictx=1").into(im3);
        Picasso.get().load("https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwjjssKxw5vkAhVSb30KHazFDgYQjRx6BAgBEAQ&url=http%3A%2F%2Fonlinesasta.com%2Fforum%2Fthreads%2Fbuy1-get-1-kurtis-clearance-sale-snapdeal.75750%2F&psig=AOvVaw0AJXRnTffQbKf5zqUnKl-R&ust=1566736608690780").into(im4);
        Picasso.get().load("https://www.google.com/imgres?imgurl=https%3A%2F%2Fcdn2.f-cdn.com%2Fcontestentries%2F50629%2F2972773%2F52ac4a6d03f91_thumb900.jpg&imgrefurl=https%3A%2F%2Fwww.freelancer.com.jm%2Fcontest%2FDesign-a-Banner-for-a-off-Sale-for-Designer-Kids-Clothing-50629-byentry-2157761&docid=sUblbI95Z-S9tM&tbnid=oF2zPSYspCSQaM%3A&vet=1&w=980&h=450&bih=754&biw=1536&ved=2ahUKEwjjssKxw5vkAhVSb30KHazFDgYQxiAoAnoECAEQGw&iact=c&ictx=1").into(im5);

        mpager=findViewById(R.id.viewpager);
        int[] layouts={R.layout.slide1,R.layout.slide2,R.layout.slide3,R.layout.slide4,R.layout.slide5};




        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.homeburgericon);
        navigationView.setNavigationItemSelectedListener(this);


    }
    public void startcoloranimation(View v){
        int startcolor=0xffffffff;
        int colorEnd=0xFFE9910C;
        ValueAnimator coloranim= ObjectAnimator.ofInt(v,"backgroundColor",startcolor,colorEnd);
        coloranim.setDuration(1500);
        coloranim.setEvaluator(new ArgbEvaluator());
        coloranim.setRepeatCount(1000);
        coloranim.setRepeatMode(ValueAnimator.REVERSE);
        coloranim.start();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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

     /*   if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
