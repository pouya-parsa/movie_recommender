package com.pouya.digim;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Datasource {
    FirebaseDatabase database;
    private DatabaseReference ref;

    public Datasource() {
        //get database instance
        System.out.println("hello");
        database = FirebaseDatabase.getInstance();
    }

    public void writeData() {
        ref  =  database.getReference("users");
//        ref.setValue("Hello, World!");
    }

    public void readData() {
        DatabaseReference myRef = database.getReference("passengers/pouya");



        myRef.child("balance").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Long value = dataSnapshot.getValue(Long.class);
                Log.d("Database", "value is " + value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Database", "fetch failed");
            }
        });
    }

}
