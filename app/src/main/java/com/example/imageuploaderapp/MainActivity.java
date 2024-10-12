package com.example.imageuploaderapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.provider.MediaStore;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.imageuploaderapp.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity
{

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private final int GALLERY_REQ_CODE = 1000;
    ImageView img;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img =findViewById(R.id.imgGallery);
        Button  btn  = findViewById(R.id.bttn);

        Button mybtn = findViewById(R.id.subBtn);
        EditText name = findViewById(R.id.name);
        EditText email = findViewById(R.id.email);
        EditText phone = findViewById(R.id.phone);
        EditText addrs = findViewById(R.id.location);
        EditText comp = findViewById(R.id.complaint);

        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                Intent iGallery  = new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery,GALLERY_REQ_CODE);
            }
        });

        mybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mname = name.getText().toString();
                String memail= email.getText().toString();
                String mphone = phone.getText().toString();
                String maddrs = addrs.getText().toString();
                String mcomp = comp.getText().toString();

                String title = "Complaint submitted !";
                String msg = "Complaint by  :"+mname+"\n"+"contact number :"+mphone+"\n"+"Email :"+memail+"\n"+"location of debris :"+maddrs;
                //Toast.makeText(MainActivity.this, "button clicked !", Toast.LENGTH_SHORT).show();

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle(title)
                        .setMessage(msg)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener(){

                            @Override
                            public void onClick(DialogInterface dialog, int i) {

                                dialog.dismiss();
                            }
                        }) . create().show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK)
        {
            if(requestCode ==GALLERY_REQ_CODE)
            {    // FOR GALLERY

                img.setImageURI(data.getData());
                // codinf finish
            }
        }
    }
}