package com.example.w7.robofission_labs;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

/**
 * Created by w7 on 8/13/2016.
 */
public class MainActivity1 extends Activity {
    Button button;
    ImageView imageView;
    static final int CAM_REQUEST = 1;
    String root = Environment.getExternalStorageDirectory().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        button = (Button) findViewById(R.id.button);
        imageView = (ImageView) findViewById(R.id.imageView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);


                startActivityForResult(camera_intent,CAM_REQUEST);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAM_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
            saveImgFile(photo);
        }

        String path = "sdcard/Robofission_Labs/image.jpg";
        //imageView.setImageDrawable(Drawable.createFromPath(path) );
    }
    void saveImgFile(Bitmap bm){
        File myDir = new File(root + "/Robofission");
        myDir.mkdirs();
        //Random generator = new Random();
        //int n = 10000;
        //n = generator.nextInt(n);
        String fname = "sampleimage.jpg";
        File file = new File(myDir, fname);
        //if (file.exists())
            //file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendimage(View view){
        /*Intent email = new Intent(Intent.ACTION_SEND);
        email.setType("image/jpeg");
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"suhas@robofissionlabs.in"});
        email.putExtra(Intent.EXTRA_SUBJECT, "Order Free Visiting Card");
        //email.putExtra(Intent.EXTRA_TEXT, "message");
        email.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + root + "/Robofission/sampleimage.jpg" ));
        startActivity(Intent.createChooser(email, "Choose Email App:"));*/
        Intent intent= new Intent(this,MailSenderActivity.class);
        startActivity(intent);

    }
}
