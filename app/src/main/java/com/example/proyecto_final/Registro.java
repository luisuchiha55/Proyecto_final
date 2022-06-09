package com.example.proyecto_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.proyecto_final.database.DbManager;
import com.example.proyecto_final.database.sqlOpenHelper;

public class Registro extends AppCompatActivity {

    private EditText edtUserName, edtPassword, edtName, edtEmail, edtBithday;
    private RadioButton radiobtnMale;
    private Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
        edtEmail = findViewById(R.id.edtEmail);
        edtName = findViewById(R.id.edtName);
        edtBithday = findViewById(R.id.edtBirthday);

        radiobtnMale = findViewById(R.id.radiobtnMale);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener((view) -> {



             try {


                ContentValues contentValues= new ContentValues();

                ContentValues contentValues1 = new ContentValues();

                contentValues.put(sqlOpenHelper.USER_LOGIN_NAME, edtUserName.getText().toString());
                contentValues.put(sqlOpenHelper.USER_PASSWORD, edtPassword.getText().toString());
                contentValues.put(sqlOpenHelper.USER_GENDER, radiobtnMale.isChecked() ? "M" : "F");
                contentValues.put(sqlOpenHelper.USER_BIRTHDAY, edtBithday.getText().toString());
                contentValues.put(sqlOpenHelper.USER_EMAIL, edtEmail.getText().toString());
                contentValues.put(sqlOpenHelper.USER_NAME, edtName.getText().toString());

                DbManager.getInstance().openDatabase();
                boolean inserted = DbManager.getInstance().inserts(sqlOpenHelper.TBL_USER
                        , contentValues);




                if (inserted){
                    Toast.makeText(Registro.this, "Account Created", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(Registro.this, "This user is already Exists", Toast.LENGTH_LONG).show();
                }
             }catch (Exception e){
                 e.printStackTrace();
             }


        });


    }

}