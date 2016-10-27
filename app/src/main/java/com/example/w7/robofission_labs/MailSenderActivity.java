package com.example.w7.robofission_labs;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.net.URL;

public class MailSenderActivity extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_sender);
        SendMail Mail = new SendMail();
        Mail.execute();


    }

    private class SendMail extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                GMailSender sender = new GMailSender("ashik5119@gmail.com", "amigos13",MailSenderActivity.this);
                sender.sendMail("This is Subject",
                        "This is Body",
                        "ashik5119@gmail.com",
                        "ashik5119@gmail.com");
            } catch (Exception e) {
                Log.e("SendMail", e.getMessage(), e);

            }


            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            Toast.makeText(MailSenderActivity.this,"Mail Send", Toast.LENGTH_SHORT).show();


        }
    }

}