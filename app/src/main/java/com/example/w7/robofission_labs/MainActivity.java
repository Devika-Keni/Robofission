package com.example.w7.robofission_labs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void launchactivity1(View view) {

        String button_text;
        button_text = ((Button) view).getText().toString();

        if (button_text.equals("click me")) {
            Intent intent = new Intent(this,MainActivity1.class);
            startActivity(intent);
        }

    }

    public void launchactivity2(View view ){

        String button_text;
        button_text=((Button) view).getText().toString();

        if(button_text.equals("click Me")){
            Intent intent= new Intent(this,MainActivity2.class);
            startActivity(intent);
        }

    }
}
