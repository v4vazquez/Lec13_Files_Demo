package com.example.eric.lec13_files_demo;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText titleNote;
    private EditText bodyNote;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleNote = (EditText) findViewById(R.id.note_title_ma);
        bodyNote = (EditText) findViewById(R.id.note_body_ma);
        tv = (TextView) findViewById(R.id.note_tv_ma);

    }

    public void saveNote(View view) {

        try {
            FileOutputStream outputStream = openFileOutput(titleNote.getText().toString().replace(" ",""), MODE_APPEND);
            outputStream.write(bodyNote.getText().toString().getBytes());
            outputStream.close();
            Snackbar.make(view, "File Saved", Snackbar.LENGTH_INDEFINITE).show();

        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        }
    }

    public void loadNote(View view) {
        String tempStr = "";
        String lstOfFile_Str="";
        ArrayList<String> lstOfNotes=new ArrayList<>();

        File filesDir = getFilesDir();
        long x = filesDir.getFreeSpace()/1_000_000;//this is in bytes
        File[] filesList=filesDir.listFiles();
        for(File fl:filesList)
        lstOfFile_Str+=fl.getName()+"\n";
        try {
            FileInputStream inputStream = openFileInput(titleNote.getText().toString().replace(" ","")); //filesList[2].getName()); replace titlenote
            int c;
            while ((c = inputStream.read()) != 1) {
                tempStr += Character.toString((char) c);
            }
            inputStream.close();

        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        }
        tv.setText(tempStr+ "\n files stored are: \n"+lstOfFile_Str+ "\n Free space available is: \n" + x +"M");
    }
}