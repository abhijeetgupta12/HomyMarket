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

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ProgrammingViewHolder>{


    Context ctx;
    ArrayList<String> name,brand,gender,discount,desc,sellprice,markprice,rating,type,size,category,length,
            image1,image2,image3,image4,image5,shop,color,stock,material;



    public RecyclerAdapter(Context ctx, ArrayList<String> name, ArrayList<String> brand, ArrayList<String> gender, ArrayList<String> discount
                           ,ArrayList<String> desc,ArrayList<String> sellprice,ArrayList<String> markprice,ArrayList<String> rating
                            ,ArrayList<String> type,ArrayList<String> size,ArrayList<String> category,ArrayList<String> length
                            ,ArrayList<String> image1,ArrayList<String> image2,ArrayList<String> image3,ArrayList<String> image4
                            ,ArrayList<String> image5,ArrayList<String> shop,ArrayList<String> color,ArrayList<String> stock,ArrayList<String> material)
    {

        this.ctx=ctx;

        this.name=name;
        this.brand=brand;
        this.gender=gender;
        this.discount=discount;
        this.desc=desc;
        this.sellprice=sellprice;
        this.markprice=markprice;
        this.rating=rating;
        this.type=type;
        this.size=size;
        this.category=category;
        this.length=length;
        this.image1=image1;
        this.image2=image2;
        this.image3=image3;
        this.image4=image4;
        this.image5=image5;
        this.shop=shop;
        this.color=color;
        this.stock=stock;
        this.material=material;

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
        holder.price1.setText(sellprice.get(position));
        holder.discount1.setText(discount.get(position));

        Picasso.get()
                .load(image1.get(position))
                .placeholder(R.drawable.loading)
                .error(R.drawable.placeholder_image)
                .fit()
                .into(holder.image1);



        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Fragment_Final_Product ldf = new Fragment_Final_Product();
                Bundle args = new Bundle();
                args.putString("NAME",name.get(position));
                args.putString("BRAND",brand.get(position));
                args.putString("GENDER",gender.get(position));
                args.putString("DISCOUNT",discount.get(position));
                args.putString("DESCRIPTION",desc.get(position));
                args.putString("SELL_PRICE",sellprice.get(position));
                args.putString("MARK_PRICE",markprice.get(position));
                args.putString("RATING",rating.get(position));
                args.putString("TYPE",type.get(position));
                args.putString("SIZE",size.get(position));
                args.putString("CATEGORY",category.get(position));
                args.putString("LENGTH",length.get(position));
                args.putString("IMAGE1",image1.get(position));
                args.putString("IMAGE2",image2.get(position));
                args.putString("IMAGE3",image3.get(position));
                args.putString("IMAGE4",image4.get(position));
                args.putString("IMAGE5",image5.get(position));
                args.putString("SHOP",shop.get(position));
                args.putString("COLOR",color.get(position));
                args.putString("STOCK",stock.get(position));
                args.putString("MATERIAL",material.get(position));
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
