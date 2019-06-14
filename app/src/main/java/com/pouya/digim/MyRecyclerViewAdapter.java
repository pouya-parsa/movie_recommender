package com.pouya.digim;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyRecyclerViewAdapter
    extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<Product> Products;
    private Context context;

    public MyRecyclerViewAdapter(List<Product> products, Context context) {
        Products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Product product = Products.get(i);
        viewHolder.txtname.setText(String.valueOf(product.getTitle()));
//        viewHolder.txtprice.setText(String.valueOf(product.getPrice()));
        //viewHolder.short_dis.setText(product.getShort_dis());
        viewHolder.ratingBar.setRating(product.getRating());
//        Glide.with(context).load(product.getImage()).into(viewHolder.productImage);
    }

    @Override
    public int getItemCount() {
        return Products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtname;
//        public TextView txtprice;
//        public TextView short_dis;
//        public ImageView productImage;
        public RatingBar ratingBar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtname = itemView.findViewById(R.id.productName);
//            txtprice = itemView.findViewById(R.id.productPrice);
//            productImage = itemView.findViewById(R.id.productImage);
            //short_dis = itemView.findViewById(R.id.short_dis);
            ratingBar=itemView.findViewById(R.id.RatingBar);
        }
    }
}