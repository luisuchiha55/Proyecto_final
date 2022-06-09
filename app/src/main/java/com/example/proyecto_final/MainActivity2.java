package com.example.proyecto_final;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto_final.database.DbManager;
import com.example.proyecto_final.database.sqlOpenHelper;
import com.example.proyecto_final.models.Users;

import java.util.Calendar;

public class MainActivity2 extends AppCompatActivity {

    private final String TAG = "MainActivity2";
    private Users users;
    private TextView txtview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        if (getIntent()!= null && getIntent().getExtras()!= null &&
                getIntent().getExtras().getSerializable("user")!= null) {

            users = (Users) getIntent().getExtras().getSerializable("user");
            txtview=findViewById(R.id.txtview);
            getLogTime();
        }


    }


    @SuppressLint("Range")
    public void getLogTime(){

        try {



            String loginQuery = "Select * from " + sqlOpenHelper.TBL_USER_LOG_TIME+ "where " +
                    sqlOpenHelper.USER_ID + " = '" + users.getUserId()+ "'";
            Log.e(TAG,"loginquery: "+ loginQuery);

            DbManager.getInstance().openDatabase();

            Cursor cursor=DbManager.getInstance().getDetails(loginQuery);



            Calendar calendar = Calendar.getInstance();
            StringBuilder stringBuilder = new StringBuilder();

            if (cursor != null && cursor.getCount() > 0 ) {
                cursor.moveToFirst();

                while (!cursor.isAfterLast()) {

                    calendar.setTimeInMillis(cursor.getLong(cursor.getColumnIndex(sqlOpenHelper.USER_LOG_TIME)));
                    stringBuilder.append(calendar.getTime().toString());
                    stringBuilder.append("\n");


                    cursor.moveToNext();
                }

                txtview.setText(stringBuilder.toString());

                cursor.close();


            }

        }catch (Exception e){
            e.printStackTrace();
        }DbManager.getInstance().closeDatabase();
    }
    }


