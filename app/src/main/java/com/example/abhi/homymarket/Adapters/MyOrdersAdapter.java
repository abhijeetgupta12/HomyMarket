package com.example.abhi.homymarket.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abhi.homymarket.Models.DataFetch;
import com.example.abhi.homymarket.Models.MyOrdersFetch;
import com.example.abhi.homymarket.R;

import java.util.ArrayList;

public class MyOrdersAdapter extends RecyclerView.Adapter<MyOrdersAdapter.ProgrammingViewHolder>{

    Context ctx;
    ArrayList<MyOrdersFetch> data;


    public MyOrdersAdapter(Context ctx, ArrayList<MyOrdersFetch> data)
    {

        this.ctx=ctx;
        this.data = data;

    }

    @NonNull
    @Override
    public ProgrammingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =  LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.myorders_resource,parent,false);       //view created
        return new ProgrammingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgrammingViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ProgrammingViewHolder extends RecyclerView.ViewHolder{
        TextView title,orderno,price,status,address,phone,delivary_slot;
        LinearLayout linearLayout;
        RatingBar ratingBar;
        public ProgrammingViewHolder(View itemView) {               //view sent to be kept in a viewholder

            super(itemView);

            title = itemView.findViewById(R.id.title);
            orderno = itemView.findViewById(R.id.orderNo);
            price = itemView.findViewById(R.id.price);
            status = itemView.findViewById(R.id.status);
            address = itemView.findViewById(R.id.address);
            phone = itemView.findViewById(R.id.phone);
            delivary_slot = itemView.findViewById(R.id.delivary_slot);




        }
    }
}
