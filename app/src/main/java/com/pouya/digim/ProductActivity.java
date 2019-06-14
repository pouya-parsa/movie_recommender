package com.pouya.digim;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ProductActivity extends AppCompatActivity {

    TextView productTitle;
    ImageView productImage;
    TextView productDisc;
    RatingBar ratingBar;

    String productKey;
    MovieModel movie;

    User user;

    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_layout);


        //get view
        productTitle = findViewById(R.id.product_title);
        productImage = findViewById(R.id.product_image);
        ratingBar = findViewById(R.id.rated);

        //set action bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.actionBar));
        setSupportActionBar(toolbar);


        //database

        movie = (MovieModel) getIntent().getSerializableExtra("movie");

        Glide.with(getApplicationContext()).load(movie.getImage()).into(productImage);
        productTitle.setText(movie.getName());
        ratingBar.setRating(movie.getRate());
        setTitle(movie.getName());

        user = (User) getIntent().getSerializableExtra("user");

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                                                   @Override
                                                   public void onRatingChanged(RatingBar ratingBar,
                                                                               float rating,
                                                                               boolean fromUser) {
                                                       ratingBar.setRating(rating);
                                                       changeRate(rating);
                                                   }
                                               }
        );

    }


    public void changeRate(float rate) {
        Toast.makeText(getApplicationContext(), "Your rate is" + String.valueOf(rate), Toast.LENGTH_LONG).show();
    }

//    public void saveToBasket(View v) {
//        DatabaseReference dbRef= firebaseDatabase.getReference("basket/");
//
//        String key = dbRef.push().getKey();
//
//        dbRef= firebaseDatabase.getReference("basket/" + key);
//
//        dbRef.setValue(product);
//
//        Intent intent = new Intent(ProductActivity.this, MainActivity.class);
//
//        Bundle bundle = new Bundle();
//        bundle.putString("user", user.username);
//
//        intent.putExtras(bundle);
//        startActivity(intent);
//
//    }
}
