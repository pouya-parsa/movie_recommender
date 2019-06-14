package com.pouya.digim;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ebanx.swipebtn.SwipeButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pouya.digim.addedByMohh.SortArray;
import com.pouya.digim.svd.SvdHandler;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private List<MovieModel> movies;
    private List<MovieModel> SortedMovies;
    private  FirebaseDatabase firebaseDatabase;
    private DrawerLayout drawer;

    private int total;

    private SwipeButton swipeButton;
    private FloatingActionButton fab;
    TextView total_txt;


    private User user;
    private TextView username_txt;
    private TextView charge_txt;

    private int goalUser;

    double [][] usersData;
    double [][] goalUserData;

    String username = "pouya";

    NavigationView navigationView;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_layout);
        //loading database
        firebaseDatabase = FirebaseDatabase.getInstance();
//        filled_by_pouya.add(new Movie("freins",4));
//        filled_by_pouya.add(new Movie("titanic",2));
        //recyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));



        //user data to make prediction
        this.readData(username);


        //data in recyclerView
        movies = new ArrayList<>();

        //load recycler view
        //get user
//        String username = getIntent().getStringExtra("user");


        //load views
        total_txt = findViewById(R.id.total);
        total_txt.setVisibility(View.GONE);
        navigationView = findViewById(R.id.nav_view);
        username_txt =  navigationView.getHeaderView(0).findViewById(R.id.username);
        charge_txt = navigationView.getHeaderView(0).findViewById(R.id.charge);
        //added by mohh//


        //custom toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.actionBar));
        setSupportActionBar(toolbar);
        setTitle("Movie");
//
//        //drawer
//        drawer = findViewById(R.id.draw_layout);
//        NavigationView navigationView = findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
//
//        ActionBarDrawerToggle toggle =
//                new ActionBarDrawerToggle(this,
//                        drawer,
//                        toolbar,
//                        R.string.navigation_drawer_open,
//                        R.string.navigation_drawer_close);
//
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
//
        recyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(getApplicationContext(), recyclerView, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                MovieModel movie = movies.get(position);

                Intent intent = new Intent(MainActivity.this, ProductActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("user", user);
                bundle.putSerializable("movie", movie);
                intent.putExtras(bundle);
                startActivity(intent);

            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getApplicationContext(), movies.get(position).getName() + " is long pressed!", Toast.LENGTH_SHORT).show();

            }
        }));

//
//        //swipeButton
//        swipeButton = (SwipeButton) findViewById(R.id.swipe);
//        swipeButton.setOnStateChangeListener(new OnStateChangeListener() {
//            @Override
//            public void onStateChange(boolean active) {
//                Toast.makeText(getApplicationContext(), "Active" + active, Toast.LENGTH_SHORT).show();
//                total_txt.setVisibility(View.GONE);
//
//                Intent intent = new Intent(MainActivity.this, confirmActivity.class);
//
//                Bundle bundle = new Bundle();
//
//                bundle.putSerializable("user", user);
//                bundle.putInt("total", total);
//
//                intent.putExtras(bundle);
//
//                startActivity(intent);
//
//            }
//        });
//
//        swipeButton.setVisibility(View.GONE);
//
//        //fab button
//        fab = findViewById(R.id.shopping_cart);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Snackbar.make(view, "در حال انتقال به سبد خرید", Snackbar.LENGTH_SHORT)
////                        .setAction("Action", null).show();
//                fab.setVisibility(View.GONE);
//                swipeButton.setVisibility(View.VISIBLE);
//                total_txt.setVisibility(View.VISIBLE);
//
//                DatabaseReference dbRef = firebaseDatabase.getReference("basket");
//
//                total = 0;
//                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()) {
////                            total += dataSnapshot1.getValue(Product.class).getPrice();
//                        }
//
//                        total_txt.setText(" "+ total + " تومان ");
//                        loadProducts("basket");
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
//            }
//        });





//        DatabaseReference dbRef = firebaseDatabase.getReference("/users/" + username);
//        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                user = dataSnapshot.getValue(User.class);
//                username_txt.setText(user.getName());
//                charge_txt.setText(String.valueOf(user.getCharge()) + " تومان ");
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });


    }

//    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Log.d("eoo", "hello");
        switch (menuItem.getItemId()) {
            case R.id.nav_exit:
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void loadMovies(final String username) {
        movies.clear();
        DatabaseReference dbRef = firebaseDatabase.getReference("users/"+ username +"/");

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    MovieModel movie = dataSnapshot1.getValue(MovieModel.class);
                    movies.add(movie);
                    SortedMovies = SortArray.Sorting(movies);


                }

                setAdapterWithData();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void readData(final String username) {
        DatabaseReference dbRef = firebaseDatabase.getReference("users/");

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                int userCounter = 0;
                int movieCounter = 0;

                double [][] data = new double[(int) dataSnapshot.getChildrenCount()][5];

                for (DataSnapshot user: dataSnapshot.getChildren()) {
                    if(user.getKey().equals(username)) {
                        goalUser = userCounter;
                    }
                    movieCounter = 0;
                    for(DataSnapshot movie: user.getChildren()) {
                        data[userCounter][movieCounter] = movie.getValue(MovieModel.class).getRate();
                        movieCounter++;
                    }
                    userCounter++;

                }

                usersData = data;
                startloadMovies("pouya");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void startloadMovies(String username) {

        this.loadMovies(username);
        Log.d("usersData", String.valueOf(usersData[5][4]));
        Log.d("goalUser", String.valueOf(goalUser));

        double[] predicted =  SvdHandler.SvdHandler(usersData, goalUserData, goalUser);

        
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
        recyclerViewAdapter = new MyRecyclerViewAdapter(SortedMovies, MainActivity.this);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
    public void ConnectToSvd(){

    }
}
