package com.example.w7.robofission_labs;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DetailsActivity extends AppCompatActivity {
    private String filename = "samplefile.txt";
    TextView output1;
    String root = Environment.getExternalStorageDirectory().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        output1=(TextView) findViewById(R.id.output);
        displayDetails();
    }
    void displayDetails(){
        String root = Environment.getExternalStorageDirectory().toString();
        File file = new File(root + "/Robofission" , filename);
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
        }
        catch (IOException e) {
            Toast.makeText(getApplicationContext(),"Error reading file!",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }


        output1.setText(text);
    }
    public void sendemail(View view){
        if(isNetworkAvailable()){
            Intent email = new Intent(Intent.ACTION_SEND);
            email.setType("text/plain");
            email.putExtra(Intent.EXTRA_EMAIL, new String[]{"suhas@robofissionlabs.in"});
            email.putExtra(Intent.EXTRA_SUBJECT, "Order Free Visiting Card");
            //email.putExtra(Intent.EXTRA_TEXT, "message");
            email.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + root + "/Robofission/samplefile.txt"));
            startActivity(Intent.createChooser(email, "Choose Email App:"));
        }

    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
