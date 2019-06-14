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

    private List<MovieModel> movies;
    private Context context;

    public MyRecyclerViewAdapter(List<MovieModel> movies, Context context) {
        movies = movies;
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
        MovieModel movie = movies.get(i);
        viewHolder.txtname.setText(String.valueOf(movie.getName()));
        viewHolder.ratingBar.setRating(movie.getRate());
        Glide.with(context).load(movie.getImage()).into(viewHolder.movieImage);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtname;
        public ImageView movieImage;
        public RatingBar ratingBar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtname = itemView.findViewById(R.id.productName);
            movieImage = itemView.findViewById(R.id.productImage);
            ratingBar=itemView.findViewById(R.id.RatingBar);
        }
    }
}