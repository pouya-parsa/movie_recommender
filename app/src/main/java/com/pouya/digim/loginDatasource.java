package com.pouya.digim;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class loginDatasource extends Datasource {
    AppCompatActivity activity;

    String user_name;

    public loginDatasource(AppCompatActivity activity) {
        this.activity = activity;
    }

    public void redirect(String user_name) {
        Intent intent;

        intent = new Intent(activity, MainActivity.class);


        Bundle bundle = new Bundle();
        bundle.putString("user", user_name);

        intent.putExtras(bundle);

        activity.startActivity(intent);

    }

    public void validate(final String username, final String password) {


        DatabaseReference ref = database.getReference("users/" + username);


        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.hasChild("password") && dataSnapshot.child("password").getValue().toString().equals(password)) {
                    Toast.makeText(activity.getApplicationContext(), "با موفقیت وارد شدید " + dataSnapshot.child("name").getValue(), Toast.LENGTH_SHORT).show();
                    user_name = username;
                    redirect(user_name);
                } else {
                    Toast.makeText(activity.getApplicationContext(), "نام کاربری یا رمز عبور اشتباه است", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void create(final String username, final String password) {
        DatabaseReference ref = database.getReference("users/" + username);

        ref.setValue(new User(username, password));
        Toast.makeText(activity.getApplicationContext(), "حساب کاربری شما ایجاد شد", Toast.LENGTH_LONG).show();

        this.user_name = username;
        this.redirect(username);
    }

}
