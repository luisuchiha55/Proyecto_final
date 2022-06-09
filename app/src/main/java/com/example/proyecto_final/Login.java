package com.example.proyecto_final;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyecto_final.database.DbManager;
import com.example.proyecto_final.database.sqlOpenHelper;
import com.example.proyecto_final.models.Users;

public class Login extends AppCompatActivity {

    private final String TAG = "Login";
    private EditText edtUserName, edtPassword;
    private Button btnLogin, btnRegistrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegistrar = findViewById(R.id.btnRegistrar);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View view) {

                try {



                String loginQuery = "Select * from " + sqlOpenHelper.TBL_USER + "where " +
                        sqlOpenHelper.USER_LOGIN_NAME + " = '" + edtUserName.getText().toString()+
                        "' AND" + sqlOpenHelper.USER_PASSWORD + "= '"+ edtPassword.getText().toString()
                        + "'";

                Log.e(TAG,"loginquery: "+ loginQuery);

                DbManager.getInstance().openDatabase();

                Cursor cursor=DbManager.getInstance().getDetails(loginQuery);


                    Users users = new Users();

                if (cursor != null && cursor.getCount() > 0 ){
                    cursor.moveToFirst();

                    while (!cursor.isAfterLast()){


                        users.setUserId(cursor.getInt(cursor.getColumnIndex(sqlOpenHelper.USER_ID)));
                        users.setName(cursor.getString(cursor.getColumnIndex(sqlOpenHelper.USER_NAME)));
                        users.setEmail(cursor.getString(cursor.getColumnIndex(sqlOpenHelper.USER_EMAIL)));
                        users.setBirthday(cursor.getString(cursor.getColumnIndex(sqlOpenHelper.USER_BIRTHDAY)));
                        users.setGender(cursor.getString(cursor.getColumnIndex(sqlOpenHelper.USER_GENDER)));
                        users.setLoginName(cursor.getString(cursor.getColumnIndex(sqlOpenHelper.USER_LOGIN_NAME)));

                        Log.e(TAG, "user: " + users.toString());

                        cursor.moveToNext();
                    }

                    cursor.close();

                    Toast.makeText(Login.this, "Loggin Successful", Toast.LENGTH_LONG).show();

                    try {


                        ContentValues contentValues= new ContentValues();



                        contentValues.put(sqlOpenHelper.USER_ID, users.getUserId());
                        contentValues.put(sqlOpenHelper.USER_LOG_TIME, System.currentTimeMillis());

                        DbManager.getInstance().openDatabase();
                        DbManager.getInstance().inserts(sqlOpenHelper.TBL_USER_LOG_TIME
                                , contentValues);


                        Intent intent = new Intent(Login.this, MainActivity2.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("user", users);
                        intent.putExtras(bundle);
                        startActivity(intent);

                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }else Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_LONG).show();

                }catch (Exception e){
                    e.printStackTrace();
                }DbManager.getInstance().closeDatabase();
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Registro.class));
            }
        });



    }
}