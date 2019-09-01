package com.example.abhi.homymarket;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.collection.LLRBNode;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ProgrammingViewHolder>{


    Context ctx;
    ArrayList<String> name;
    ArrayList<String> color;
    ArrayList<String> brand;
    ArrayList<String> size;
    ArrayList<String> img;
    ArrayList<String> gender;
    ArrayList<String> desc;
    ArrayList<String> price;
    ArrayList<String> sleeves;
    ArrayList<String> rating;
    ArrayList<String> discount;
    ArrayList<String> type;



    public RecyclerAdapter(Context ctx, ArrayList<String> name, ArrayList<String> color, ArrayList<String> brand, ArrayList<String> size
                           ,ArrayList<String> img,ArrayList<String> gender,ArrayList<String> desc,ArrayList<String> price
                            ,ArrayList<String> sleeves,ArrayList<String> rating,ArrayList<String> discount,ArrayList<String> type)
    {

        this.ctx=ctx;

        this.name=name;
        this.color=color;
        this.brand=brand;
        this.size=size;
        this.img=img;
        this.gender=gender;
        this.desc=desc;
        this.price=price;
        this.sleeves=sleeves;
        this.rating=rating;
        this.discount=discount;
        this.type=type;

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

        holder.name1.setText(name.get(position));
        holder.brand1.setText(brand.get(position));
        holder.price1.setText(price.get(position));
        holder.discount1.setText(discount.get(position));

        Picasso.get()
                .load("https://upload.wikimedia.org/wikipedia/commons/thumb/2/24/Blue_Tshirt.jpg/220px-Blue_Tshirt.jpg")
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.placeholder_image)
                .fit()
                .into(holder.image1);



        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

        /*        Intent intent = new Intent(ctx,Final_Product.class);
                intent.putExtra("NAME",name.get(position));
                intent.putExtra("COLOR",color.get(position));
                intent.putExtra("BRAND",brand.get(position));
                intent.putExtra("SIZE",size.get(position));
                intent.putExtra("IMG",img.get(position));
                intent.putExtra("GENDER",gender.get(position));
                intent.putExtra("DESC",desc.get(position));
                intent.putExtra("PRICE",price.get(position));
                intent.putExtra("LENGTH",sleeves.get(position));
                intent.putExtra("RATING",rating.get(position));
                intent.putExtra("DISCOUNT",discount.get(position));
                intent.putExtra("TYPE",type.get(position));
                ctx.startActivity(intent);
                ((Activity) ctx).finish();

*/


                Final_Product ldf = new Final_Product ();
                Bundle args = new Bundle();
                args.putString("NAME",name.get(position));
                args.putString("COLOR",color.get(position));
                args.putString("BRAND",brand.get(position));
                args.putString("SIZE",size.get(position));
                args.putString("IMG",img.get(position));
                args.putString("GENDER",gender.get(position));
                args.putString("DESC",desc.get(position));
                args.putString("PRICE",price.get(position));
                args.putString("LENGTH",sleeves.get(position));
                args.putString("RATING",rating.get(position));
                args.putString("DISCOUNT",discount.get(position));
                args.putString("TYPE",type.get(position));
                ldf.setArguments(args);

                FragmentManager fm = ((FragmentActivity)ctx).getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.frame,ldf).commit();

            }
        });


    }

    @Override
    public int getItemCount() {
        return name.size();
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
