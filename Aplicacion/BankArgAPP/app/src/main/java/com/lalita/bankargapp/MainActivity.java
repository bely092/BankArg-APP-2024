package com.lalita.bankargapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    //variable de tipo Button
    Button btnInicio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Acceso al login
        btnInicio = findViewById(R.id.btn_main_in);
        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivities(new Intent[]{intent});
            }
        });

        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();

        // Ejemplo: Insertar un documento
//        db.execSQL("INSERT INTO Documentos (tipo_doc) VALUES ('DNI');");
    }

    // Sobrescribe el comportamiento del botón "atrás"
    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        // No se llama a super.onBackPressed(), por lo tanto el botón "atrás" no hará nada
    }

}