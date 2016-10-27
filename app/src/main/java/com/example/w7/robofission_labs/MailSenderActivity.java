package com.example.w7.robofission_labs;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;

public class MailSenderActivity extends Activity {
    TextView tv1;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_sender);
        tv1 =(TextView)findViewById(R.id.textView1);
        Intent intent = getIntent();
        String fname = intent.getStringExtra("filename");
        SendMailTask Mail = new SendMailTask();
        Mail.execute(fname);


    }

    private class SendMailTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... filename) {
            try {
                GMailSender sender = new GMailSender();
                sender.sendMail(filename[0]);
            } catch (Exception e) {
                Log.e("SendMail", e.getMessage(), e);

            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            //Toast.makeText(MailSenderActivity.this,"Mail Send", Toast.LENGTH_SHORT).show();
            tv1.setText("Order Requested");


        }
    }

}