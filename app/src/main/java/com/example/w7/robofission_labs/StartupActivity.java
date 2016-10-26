package com.example.w7.robofission_labs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
    }
    public void startMainActivity(View v){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
