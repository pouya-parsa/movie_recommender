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

    String productKey;
    Product product;

    User user;

    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_layout);

        //
        Intent intent = getIntent();

        //get view
        productTitle = findViewById(R.id.product_title);
        productImage = findViewById(R.id.product_image);
        productDisc = findViewById(R.id.product_disc);

        //set action bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.actionBar));
        setSupportActionBar(toolbar);


        //get product info
        productKey = intent.getStringExtra("key");


        //database
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = firebaseDatabase.getReference(intent.getStringExtra("category") + "/" + productKey);

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                product = dataSnapshot.getValue(Product.class);
//                Log.d("product", product.getKey());
                Glide.with(getApplicationContext()).load(product.getImage()).into(productImage);
                productTitle.setText(product.getTitle());
                productDisc.setText(product.getShort_dis());
                setTitle(product.getTitle());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        user = (User) getIntent().getSerializableExtra("user");


    }


    public void saveToBasket(View v) {
        DatabaseReference dbRef= firebaseDatabase.getReference("basket/");

        String key = dbRef.push().getKey();

        dbRef= firebaseDatabase.getReference("basket/" + key);

        dbRef.setValue(product);

        Intent intent = new Intent(ProductActivity.this, MainActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString("user", user.username);

        intent.putExtras(bundle);
        startActivity(intent);

    }

}
