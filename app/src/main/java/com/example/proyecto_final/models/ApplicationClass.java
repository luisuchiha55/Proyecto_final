package com.example.proyecto_final.models;

import android.app.Application;

import com.example.proyecto_final.database.DbManager;
import com.example.proyecto_final.database.sqlOpenHelper;

public class ApplicationClass extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        DbManager.initializeInstance(new sqlOpenHelper(getApplicationContext()));
    }
}
