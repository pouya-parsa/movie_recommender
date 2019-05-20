package com.pouya.digim;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

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

    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_layout);

        //
        Intent intent = getIntent();

        //get view
        productTitle = findViewById(R.id.product_name);
        productImage = findViewById(R.id.product_image);
        productDisc = findViewById(R.id.product_disc);


        //database
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = firebaseDatabase.getReference(intent.getStringExtra("category") + "/" + intent.getStringExtra("key"));

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Product product = dataSnapshot.getValue(Product.class);
                Glide.with(getApplicationContext()).load(product.getImage()).into(productImage);
                productTitle.setText(product.getTitle());
                productDisc.setText(product.getShort_dis());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
