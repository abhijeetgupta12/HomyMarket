package com.example.abhi.homymarket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WishList_Adapter extends RecyclerView.Adapter<WishList_Adapter.ProgrammingViewHolder> {

    Context ctx;
    List<DataFetch> data;
    FirebaseAuth mAuth;
    DatabaseReference mRef;


    public WishList_Adapter(Context ctx,List<DataFetch> data)  // data required in view is recieved here
    {

        this.ctx=ctx;
        this.data=data;

    }


    @NonNull
    @Override
    public ProgrammingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =  LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.cart_resource,parent,false);       //view created
        return new ProgrammingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgrammingViewHolder holder, final int position) {

        holder.textView.setText(data.get(position).getName());
        Picasso.get()
                .load(data.get(position).getImage1())
                .placeholder(R.drawable.loading)
                .error(R.drawable.placeholder_image)
                .fit()
                .into(holder.imageView);

        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mAuth = FirebaseAuth.getInstance();
                FirebaseUser user=mAuth.getCurrentUser();
                String key="Product_"+data.get(position).getId1();
                mRef= FirebaseDatabase.getInstance().getReference().child(user.getUid()).child("WishList").child(key);
                mRef.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

            }
        });




    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ProgrammingViewHolder extends RecyclerView.ViewHolder{
        Button del;
        ImageView imageView;
        TextView textView;
        public ProgrammingViewHolder(View itemView) {               //view sent to be kept in a viewholder

            super(itemView);

            del = itemView.findViewById(R.id.delete);
            imageView = itemView.findViewById(R.id.imageview);
            textView = itemView.findViewById(R.id.textview);
        }
    }
}
