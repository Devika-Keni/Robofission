package com.example.w7.robofission_labs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;


/**
 * Created by w7 on 8/16/2016.
 */
public class MainActivity2 extends Activity {
    EditText editText,editText2,editText3,editText4,editText5,editText6,editText7,editText8,editText9,editText10,editText11,editText12;
    Button button,button1;
    TextView textView;

    private String filename = "SampleFile.txt";
    private String filepath = "MyFileStorage";
    File myExternalFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        button=(Button)findViewById(R.id.button);
        button1=(Button)findViewById(R.id.button1);
        editText=(EditText)findViewById(R.id.editText);
        editText2=(EditText)findViewById(R.id.editText2);
        editText3=(EditText)findViewById(R.id.editText3);
        editText4=(EditText)findViewById(R.id.editText4);
        editText5=(EditText)findViewById(R.id.editText5);
        editText6=(EditText)findViewById(R.id.editText6);
        editText7=(EditText)findViewById(R.id.editText7);
        editText8=(EditText)findViewById(R.id.editText8);
        editText9=(EditText)findViewById(R.id.editText9);
        editText10=(EditText)findViewById(R.id.editText10);
        editText11=(EditText)findViewById(R.id.editText11);
        editText12=(EditText)findViewById(R.id.editText12);
        textView=(TextView)findViewById(R.id.textView);
    }

    public void savedata(View view){
        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            button1.setEnabled(false);
        }
        else {
            Toast.makeText(MainActivity2.this,"creating file", Toast.LENGTH_SHORT).show();

            String root = Environment.getExternalStorageDirectory().toString();
            myExternalFile = new File(root + "/Robofission" , filename);
        }

        if (editText.getText().toString().trim().equalsIgnoreCase(""))
        {
            editText.setError("your full name is Empty");
        }
        else if (editText2.getText().toString().trim().equalsIgnoreCase(""))
        {
            editText2.setError("Organization/Company's Name is Empty");
        }
        else if (editText3.getText().toString().trim().equalsIgnoreCase(""))
        {
            editText3.setError("Designation is Empty");
        }
        else if (editText4.getText().toString().trim().equalsIgnoreCase(""))
        {
            editText4.setError("Email id is Empty");
        }
        else if (editText5.getText().toString().trim().equalsIgnoreCase(""))
        {
            editText5.setError("Contact No or Mobile No is Empty");
        }
        else if (editText6.getText().toString().trim().equalsIgnoreCase(""))
        {
            editText6.setError("Complete Postal Address is Empty");
        }
        else if (editText7.getText().toString().trim().equalsIgnoreCase(""))
        {
            editText7.setError("City is Empty");
        }
        else if (editText8.getText().toString().trim().equalsIgnoreCase(""))
        {
            editText8.setError("State is Empty");
        }
        else if (editText9.getText().toString().trim().equalsIgnoreCase(""))
        {
            editText9.setError("Pin Code is Empty");
        }
        else if (editText11.getText().toString().trim().equalsIgnoreCase(""))
        {
            editText11.setError("Should we put your organization's logo on card ? is Empty");
        }
        else if (editText12.getText().toString().trim().equalsIgnoreCase(""))
        {
            editText12.setError("Visiting card design is Empty");
        }
        else {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(myExternalFile);
            OutputStreamWriter outputStreamWriter =new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.write("Your full Name =>");
            outputStreamWriter.write(editText.getText().toString());
            outputStreamWriter.write("\nOrganization/Company's Name =>");
            outputStreamWriter.write(editText2.getText().toString());
            outputStreamWriter.write("\nDesignation =>");
            outputStreamWriter.write(editText3.getText().toString());
            outputStreamWriter.write("\nEmail id =>");
            outputStreamWriter.write(editText4.getText().toString());
            outputStreamWriter.write("\nContact No or Mobile No =>");
            outputStreamWriter.write(editText5.getText().toString());
            outputStreamWriter.write("\nCOMPLETE POSTAL ADDRESS =>");
            outputStreamWriter.write(editText6.getText().toString());
            outputStreamWriter.write("\nCITY =>");
            outputStreamWriter.write(editText7.getText().toString());
            outputStreamWriter.write("\nSTATE =>");
            outputStreamWriter.write(editText8.getText().toString());
            outputStreamWriter.write("\nPINCODE =>");
            outputStreamWriter.write(editText9.getText().toString());
            outputStreamWriter.write("\nOrganization or company website if any =>");
            outputStreamWriter.write(editText10.getText().toString());
            outputStreamWriter.write("\nShould we put your company's logo on your card ? =>");
            outputStreamWriter.write(editText11.getText().toString());
            outputStreamWriter.write("\nVisiting card design =>");
            outputStreamWriter.write(editText12.getText().toString());
            outputStreamWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
            textView.setText("Data Saved to memory...");

    }
    }
    public void shownextpage(View view){
        Intent intent = new Intent(this,DetailsActivity.class);
        startActivity(intent);
    }



    private static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    private static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }


}
