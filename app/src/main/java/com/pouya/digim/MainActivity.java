package com.pouya.digim;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private List<Product> Products;
    private  FirebaseDatabase firebaseDatabase;
    private String category = "laptops";
    private DrawerLayout drawer;

    private int total;

    private SwipeButton swipeButton;
    private FloatingActionButton fab;
    TextView total_txt;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_layout);


        //loading database
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.getReference("basket").setValue("1");


        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        Products = new ArrayList<>();

        this.loadProducts(this.category);

        //load views
        total_txt = findViewById(R.id.total);
        total_txt.setVisibility(View.GONE);

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setBackgroundColor(getResources().getColor(R.color.actionBar));


        setSupportActionBar(toolbar);

        setTitle("DigiOn");


        drawer = findViewById(R.id.draw_layout);


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(this,
                        drawer,
                        toolbar,
                        R.string.navigation_drawer_open,
                        R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();


        recyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(getApplicationContext(), recyclerView, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Product product = Products.get(position);

                Intent intent = new Intent(MainActivity.this, ProductActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("key", product.getKey());
                bundle.putString("category", product.getCategory());
                intent.putExtras(bundle);


                startActivity(intent);

                saveToBasket(Products.get(position));
                Toast.makeText(getApplicationContext(), Products.get(position).getTitle() + " is clicked!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getApplicationContext(), Products.get(position).getTitle() + " is long pressed!", Toast.LENGTH_SHORT).show();

            }
        }));

        swipeButton = (SwipeButton) findViewById(R.id.swipe);
        swipeButton.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(boolean active) {
                Toast.makeText(getApplicationContext(), "Active" + active, Toast.LENGTH_SHORT).show();
                total_txt.setVisibility(View.GONE);

            }
        });

        swipeButton.setVisibility(View.GONE);


        fab = findViewById(R.id.shopping_cart);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "در حال انتقال به سبد خرید", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                fab.setVisibility(View.GONE);
                swipeButton.setVisibility(View.VISIBLE);
                total_txt.setVisibility(View.VISIBLE);
                total_txt.setText(" "+ total + " تومان ");


                loadProducts("basket");
            }
        });




    }




//    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Log.d("eoo", "hello");
        switch (menuItem.getItemId()) {
            case R.id.laptops :
                Toast.makeText(getApplicationContext(), "laptops selected", Toast.LENGTH_SHORT).show();
                this.loadProducts("laptops");
                break;
            case R.id.shirts:
                this.loadProducts("shirts");
                break;
            case R.id.fruits:
                this.loadProducts("fruits");
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void loadProducts(final String category) {
        Products.clear();
        DatabaseReference dbRef = firebaseDatabase.getReference(category + "/");

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    Product product = dataSnapshot1.getValue(Product.class);
                    product.setKey(dataSnapshot1.getKey());
                    product.setCategory(category);
                    Products.add(product);

                }
                setAdapterWithData();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void saveToBasket(Product product) {
        DatabaseReference dbRef= firebaseDatabase.getReference("basket/");

        String key = dbRef.push().getKey();

        dbRef= firebaseDatabase.getReference("basket/" + key);

        dbRef.setValue(product);

        total += product.getPrice();
        Log.d("price", String.valueOf(total));
    }


    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void setAdapterWithData() {
        recyclerViewAdapter = new MyRecyclerViewAdapter(Products, MainActivity.this);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}
