package com.example.abhi.homymarket.Adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.abhi.homymarket.R;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.squareup.picasso.Picasso;

public class ViewPagerAdapter_FinalProduct extends PagerAdapter {

    Context context;
    String img[] = new String[5];
    LayoutInflater layoutInflater;

    // Integer []image={R.drawable.patiala1,R.drawable.patiala12,R.drawable.patiala13,R.drawable.patiala1,R.drawable.patiala12};


    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.custom_layout,null);
        PhotoView photoView=view.findViewById(R.id.photoview);
       // photoView.setImageURI(Uri.parse(img[position]));

        Picasso.get()
                .load(img[position])
                .placeholder(R.drawable.loading)
                .error(R.drawable.placeholder_image)
                .fit()
                .centerCrop()
                .into(photoView);

        ViewPager viewPager=(ViewPager)container;
        viewPager.addView(view,0);

        return view;
    }

    @Override
    public void destroyItem( ViewGroup container, int position,  Object object) {
        ViewPager vp=(ViewPager) container;
        View v=(View) object;
        vp.removeView(v);
    }

    public ViewPagerAdapter_FinalProduct(Context context, String img1, String img2, String img3, String img4, String img5) {
        this.context = context;
        img[0]=img1;
        img[1]=img2;
        img[2]=img3;
        img[3]=img4;
        img[4]=img5;
    }

    @Override
    public int getCount() {
        return img.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }
}
