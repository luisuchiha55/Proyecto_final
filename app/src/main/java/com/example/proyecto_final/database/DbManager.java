package com.example.proyecto_final.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.concurrent.atomic.AtomicInteger;

public class DbManager {

    private static DbManager instance;
    private static SQLiteOpenHelper mDatabaseHelper;
    private AtomicInteger mOpenCounter = new AtomicInteger();
    private SQLiteDatabase mDb;

    public static synchronized void initializeInstance(SQLiteOpenHelper helper){
        if (instance == null){
            instance = new DbManager();
            mDatabaseHelper = helper;
        }
    }

    public static synchronized DbManager getInstance(){
        if (instance == null){
            throw new IllegalStateException(
                    DbManager.class.getSimpleName()
                            + " is not initialized, call initializeInstance(..) method first.");
        }
        return instance;
    }

    public synchronized void openDatabase() {
        if (mOpenCounter.incrementAndGet() == 1) {
            //opening new database
            mDb = mDatabaseHelper.getWritableDatabase();
        }
    }

    public synchronized void closeDatabase() {
        if (mOpenCounter.decrementAndGet() == 0) {
            //Closing database
            mDb.close();
        }
    }

    public Cursor getDetails(String query){
        //Db.Manager.getInstance().closeDatabase();
        return mDb.rawQuery(query, null);
    }

    public boolean inserts(String tableName, ContentValues values){
        long l = -1;
        try {
            l = mDb.insert(tableName, null, values);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return l !=-1;
    }

    public boolean delete(String tableName){
        try{
            mDb.delete(tableName, null, null);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
}
