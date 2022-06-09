package com.example.proyecto_final.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class sqlOpenHelper extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "DB.sqlite";
    private static final int DATABASE_VERSION = 1;

    public static final String TBL_USER = "user_table";
    public static final String USER_ID = "id";
    public static final String USER_LOGIN_NAME = "user_login_name";
    public static final String USER_NAME = "name";
    public static final String USER_PASSWORD = "password";
    public static final String USER_EMAIL = "email";
    public static final String USER_BIRTHDAY = "birthday";
    public static final String USER_GENDER = "gender";

    public static final String TBL_USER_LOG_TIME = "user_log_time_table";
    public static final String USER_LOG_TIME = "log_time";

    //private final String TABLE_USER_DROP = "DROP TABLE IF EXISTS " + TBL_USER;

    private String TBL_CREATE_USER_LOGS = "create table " + TBL_USER_LOG_TIME + " ("+
            USER_ID + "integer," +
            USER_LOG_TIME + "long,)";


    private String TBL_CREATE_USER = "create table " + TBL_USER + " ("+
            USER_ID + "integer primary key," +
            USER_LOGIN_NAME + "text UNIQUE," +
            USER_NAME + "text," +
            USER_PASSWORD + "text," +
            USER_EMAIL + "text UNIQUE," +
            USER_BIRTHDAY + "text," +
            USER_GENDER + "text)";


    public sqlOpenHelper( Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TBL_CREATE_USER);
        db.execSQL(TBL_CREATE_USER_LOGS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //public void dropDb(SQLiteDatabase db){
        //Drop table
       // db.execSQL(TABLE_USER_DROP);
        //Again create table
        //db.execSQL(TBL_CREATE_USER);
    //}
}
