package com.example.abhi.homymarket;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.collection.LLRBNode;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ProgrammingViewHolder>{


    Context ctx;
    ArrayList<String> img;
    ArrayList<String> name;
    ArrayList<String> brand;
    ArrayList<String> price;


    public RecyclerAdapter(Context ctx, ArrayList<String> img,ArrayList<String> name,ArrayList<String> brand,ArrayList<String> price)
    {

        this.ctx=ctx;
        this.img=img;
        this.name=name;
        this.brand=brand;
        this.price=price;

    }

    @NonNull
    @Override
    public ProgrammingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater =  LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycle_resource,parent,false);       //view created
        return new ProgrammingViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ProgrammingViewHolder holder, int position) {

        holder.name1.setText(name.get(position));
        holder.brand1.setText(brand.get(position));
        holder.price1.setText(price.get(position));
        Picasso.get().load("https://images-na.ssl-images-amazon.com/images/I/61OP28Q60VL._UX342_.jpg").into(holder.image1);

    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class ProgrammingViewHolder extends RecyclerView.ViewHolder{
        ImageView image1;
        TextView name1,brand1,price1;
        LinearLayout linearLayout;
        RatingBar ratingBar;
        public ProgrammingViewHolder(View itemView) {               //view sent to be kept in a viewholder

            super(itemView);

            image1 = itemView.findViewById(R.id.image);
            name1 = itemView.findViewById(R.id.name);
            brand1 = itemView.findViewById(R.id.brand);
            price1 = itemView.findViewById(R.id.price);
            linearLayout=itemView.findViewById(R.id.linear);
            ratingBar=itemView.findViewById(R.id.rating);



        }
    }
}
