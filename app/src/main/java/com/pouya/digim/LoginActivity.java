package com.pouya.digim;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    loginDatasource loginDS;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login_layout);

        loginDS = new loginDatasource(this);
    }

    public void login(View view) {
        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);

        loginDS.validate(username.getText().toString(), password.getText().toString());
    }

    public void signup(View view) {
        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);

        loginDS.create(username.getText().toString(), password.getText().toString());
    }
}
