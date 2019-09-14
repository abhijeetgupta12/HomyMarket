package com.example.abhi.homymarket;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ProgrammingViewHolder>{


    Context ctx;
    ArrayList<DataFetch> data;



    public RecyclerAdapter(Context ctx,ArrayList<DataFetch> data)
    {

        this.ctx=ctx;

        this.data = data;

    }

    @NonNull
    @Override
    public ProgrammingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater =  LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycle_resource,parent,false);       //view created
        return new ProgrammingViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ProgrammingViewHolder holder, final int position) {

        holder.name1.setText(data.get(position).getName());
        holder.brand1.setText(data.get(position).getBrand());
        holder.price1.setText(data.get(position).getSellprice());
        holder.discount1.setText(data.get(position).getDiscount());

        Picasso.get()
                .load(data.get(position).getImage1())
                .placeholder(R.drawable.loading)
                .error(R.drawable.placeholder_image)
                .fit()
                .into(holder.image1);



        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Fragment_Final_Product ldf = new Fragment_Final_Product(data.get(position));
                FragmentManager fm = ((FragmentActivity)ctx).getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.frame,ldf).addToBackStack(null).commit();

                //addToBackStack(null)---- enables you to come back to the previous fragment

            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ProgrammingViewHolder extends RecyclerView.ViewHolder{
        ImageView image1;
        TextView name1,brand1,price1,discount1;
        LinearLayout linearLayout;
        RatingBar ratingBar;
        public ProgrammingViewHolder(View itemView) {               //view sent to be kept in a viewholder

            super(itemView);

            image1 = itemView.findViewById(R.id.image);
            name1 = itemView.findViewById(R.id.name);
            brand1 = itemView.findViewById(R.id.brand);
            price1 = itemView.findViewById(R.id.price);
            discount1 = itemView.findViewById(R.id.discount);
            linearLayout=itemView.findViewById(R.id.linear);
            ratingBar=itemView.findViewById(R.id.rating);



        }
    }
}
