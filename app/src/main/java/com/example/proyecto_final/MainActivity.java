package com.example.proyecto_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Latigo(View view){
        Intent j= new Intent(this, Latigo.class);
        startActivity(j);
    }

    public void BD(View view){
        Intent j=new Intent(this, Login.class);
        startActivity(j);
    }

    public  void JSON(View view){
        Intent j =new Intent(this, ArchivoJSON.class);
        startActivity(j);
    }



    @Override
    public void onClick(View v) {

    }
}