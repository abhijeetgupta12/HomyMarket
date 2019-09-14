package com.example.abhi.homymarket;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WishList_Adapter extends RecyclerView.Adapter<WishList_Adapter.ProgrammingViewHolder> {




    public WishList_Adapter()  // data required in view is recieved here
    {

    }


    @NonNull
    @Override
    public ProgrammingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =  LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.cart_resource,parent,false);       //view created
        return new ProgrammingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgrammingViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ProgrammingViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        public ProgrammingViewHolder(View itemView) {               //view sent to be kept in a viewholder

            super(itemView);

            imageView = itemView.findViewById(R.id.imageview);
            textView = itemView.findViewById(R.id.textview);
        }
    }
}
