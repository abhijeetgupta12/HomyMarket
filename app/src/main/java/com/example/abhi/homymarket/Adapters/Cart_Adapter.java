package com.example.abhi.homymarket.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abhi.homymarket.Models.CartFetch;
import com.example.abhi.homymarket.Models.DataFetch;
import com.example.abhi.homymarket.Fragments.Final_Product;
import com.example.abhi.homymarket.Fragments.Cart;
import com.example.abhi.homymarket.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Cart_Adapter extends RecyclerView.Adapter<Cart_Adapter.ProgrammingViewHolder> {

    Context ctx;
    List<DataFetch> data;
    FirebaseAuth mAuth;
    DatabaseReference mRef;
    String size;
    float PriceFloat;

    // data required in view is recieved here
    public Cart_Adapter(Context ctx, List<DataFetch> data)
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
    public void onBindViewHolder(@NonNull final ProgrammingViewHolder holder, final int position) {

        holder.textView.setText(data.get(position).getName());
        Picasso.get()
                .load(data.get(position).getImage1())
                .placeholder(R.drawable.loading)
                .error(R.drawable.placeholder_image)
                .fit()
                .into(holder.imageView);

        PriceFloat = Float.parseFloat(data.get(position).getSellprice());
        holder.Price.setText(String.valueOf(PriceFloat));

        CartFetch.priceList.add(PriceFloat);
        CartFetch.qty.add(1);
        CartFetch.name.add(data.get(position).getName());



        String key="Product_"+data.get(position).getId1();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user=mAuth.getCurrentUser();
        mRef= FirebaseDatabase.getInstance().getReference().child(user.getUid()).child("WishList").child(key);
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                size=dataSnapshot.child("spinner_size").getValue().toString();
                holder.SIZE.setText(size);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                //This is a process to delete a node in firebase .................

                mAuth = FirebaseAuth.getInstance();
                FirebaseUser user=mAuth.getCurrentUser();
                String key="Product_"+data.get(position).getId1();
                mRef= FirebaseDatabase.getInstance().getReference().child(user.getUid()).child("WishList").child(key);
                mRef.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        //This is the process to call a fragment from any Adapter Class................

                        Cart ldf = new Cart();
                        FragmentManager fm = ((FragmentActivity)ctx).getSupportFragmentManager();
                        fm.beginTransaction().replace(R.id.frame,ldf).addToBackStack(null).commit();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

                CartFetch.priceList.remove(position);
                CartFetch.qty.remove(position);
                CartFetch.name.remove(position);


            }
        });


        holder.Qty.setText(String.valueOf(1));

        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String get = holder.Qty.getText().toString().trim();
                int k = Integer.parseInt(get);
                k++;
                Float price = Float.parseFloat(data.get(position).getSellprice());
                Float newPrice = price * k;
                holder.Price.setText(String.valueOf(newPrice));
                holder.Qty.setText(String.valueOf(k));


                CartFetch.priceList.set(position,newPrice);
                CartFetch.qty.set(position,k);


            }
        });

        holder.sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String get = holder.Qty.getText().toString().trim();
                int k = Integer.parseInt(get);
                if(k!=1)
                    k--;

                Float price = Float.parseFloat(data.get(position).getSellprice());
                Float newPrice = price * k;
                holder.Price.setText(String.valueOf(newPrice));
                holder.Qty.setText(String.valueOf(k));

                CartFetch.priceList.set(position,newPrice);
                CartFetch.qty.set(position,k);

            }
        });


        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Final_Product ldf = new Final_Product(data.get(position));
                FragmentManager fm = ((FragmentActivity)ctx).getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.frame,ldf).addToBackStack(null).commit();
                data.clear();

            }
        });




    }







    @Override
    public int getItemCount() {
        return data.size();
    }








    public class ProgrammingViewHolder extends RecyclerView.ViewHolder{
        ImageButton del,add,sub;
        ImageView imageView;
        TextView textView,SIZE,Qty,Price;
        public ProgrammingViewHolder(View itemView) {               //view sent to be kept in a viewholder

            super(itemView);


            del = itemView.findViewById(R.id.delete);
            imageView = itemView.findViewById(R.id.imageview);
            textView = itemView.findViewById(R.id.textview);
            SIZE = itemView.findViewById(R.id.size_selected);
            add = itemView.findViewById(R.id.add);
            sub = itemView.findViewById(R.id.sub);
            Qty = itemView.findViewById(R.id.Qty);
            Price = itemView.findViewById(R.id.price);



        }
    }
}
