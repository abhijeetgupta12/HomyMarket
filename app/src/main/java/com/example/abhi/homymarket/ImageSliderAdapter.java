package com.example.abhi.homymarket;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

public class ImageSliderAdapter extends SliderViewAdapter<ImageSliderAdapter.SliderAdapterVH> {

    private Context context;
    private int mCount;

    public ImageSliderAdapter(Context context) {
        this.context = context;
    }

    public void setCount(int count) {
        this.mCount = count;
    }


    @Override
    public ImageSliderAdapter.SliderAdapterVH onCreateViewHolder(ViewGroup parent) {

        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout, null);
        return new SliderAdapterVH(inflate);

    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(context,NextActivity.class));
            }
        });


        switch (position) {
            case 0:
                Picasso.get().load("https://i.pinimg.com/originals/db/2a/4e/db2a4ed0b0a393fff2c55b0180053bbd.jpg").fit().into(viewHolder.imageViewBackground);
                break;

            case 1:
                Picasso.get().load("https://cached.imagescaler.hbpl.co.uk/resize/scaleToFit/1200/600/cached.offlinehbpl.hbpl.co.uk/galleries/WAC/445_53156_Johnlewis2.jpg").fit().into(viewHolder.imageViewBackground);
                break;

            case 2:
                Picasso.get().load("https://i.pinimg.com/originals/db/2a/4e/db2a4ed0b0a393fff2c55b0180053bbd.jpg").fit().into(viewHolder.imageViewBackground);
                break;

            case 3:
                Picasso.get().load("https://cached.imagescaler.hbpl.co.uk/resize/scaleToFit/1200/600/cached.offlinehbpl.hbpl.co.uk/galleries/WAC/445_53156_Johnlewis2.jpg").fit().into(viewHolder.imageViewBackground);
                break;


            case 4:
                Picasso.get().load("https://i.pinimg.com/originals/db/2a/4e/db2a4ed0b0a393fff2c55b0180053bbd.jpg").fit().into(viewHolder.imageViewBackground);
                break;
            default:

                Picasso.get().load("http://www.pixstudija.com/wp-content/uploads/2018/06/Screen-Shot-2018-03-05-at-19.56.05.png").fit().into(viewHolder.imageViewBackground);
                break;

        }


    }

    @Override
    public int getCount() {
        return mCount;
    }
    class SliderAdapterVH extends SliderViewAdapter.ViewHolder{

        View itemView;
        ImageView imageViewBackground;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            this.itemView=itemView;

        }
    }
}


