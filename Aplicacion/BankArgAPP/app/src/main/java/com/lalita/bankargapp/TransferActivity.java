package com.lalita.bankargapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.MenuItem;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class TransferActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;
    TextView saldo;
    SQLiteDatabase db;

    Button btnTransferir;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);


        // Botón en el tool bar que lleva al perfil
        View btnPerfil = findViewById(R.id.account_cir);
        btnPerfil.setOnClickListener(view -> {
            Intent intent = new Intent(TransferActivity.this, PerfilActivity.class);
            startActivity(intent);
        });

        saldo = findViewById(R.id.saldo);

        // Inicializar la base de datos
        UsuariosSQLiteHelper dbHelper = new UsuariosSQLiteHelper(this);
        db = dbHelper.getReadableDatabase();

        // Recuperar id_usuario de SharedPreferences
        SharedPreferences preferences = getSharedPreferences("user_session", MODE_PRIVATE);
        int idUsuario = preferences.getInt("id_usuario", -1);

        // Verificar si id_usuario es válido
        if (idUsuario != -1) {
            // Obtener los datos del usuario de la base de datos
            obtenerDatosUsuario(idUsuario);
        } else {
            Toast.makeText(this, "Error: Usuario no logueado", Toast.LENGTH_SHORT).show();
        }


        // Botones de acciones> transferir, agregar e historial



        btnTransferir = findViewById(R.id.button13);
        btnTransferir.setOnClickListener(v -> {
            Intent intent = new Intent(TransferActivity.this, TransferirActivity.class);
            startActivity(intent);
        });

        /*--- lleva al home ---*/
        View btnHome = findViewById(R.id.rectangle_2);
        btnHome.setOnClickListener(view -> {
            Intent intent = new Intent(TransferActivity.this, ProductActivity.class);
            startActivity(intent);
        });

        Button btnAgregar = findViewById(R.id.button11);
        btnAgregar.setOnClickListener(view -> {
            Intent intent = new Intent(TransferActivity.this, AgregarPersonasActivity.class);
            startActivity(intent);
        });

        Button btnHistorial = findViewById(R.id.button12);
        btnHistorial.setOnClickListener(view -> {
            Intent intent = new Intent(TransferActivity.this, BankingActivity.class);
            startActivity(intent);
        });

        // Hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                TransferActivity activity = TransferActivity.this;
                if (itemId == R.id.nav_product) {
                    Intent intent = new Intent(activity, ProductActivity.class);
                    Log.i("MENU_DRAWER_TAG", "Home is selected");
                    startActivities(new Intent[]{intent});
                    drawerLayout.closeDrawer(GravityCompat.START);

                } else if (itemId == R.id.nav_banking) {
                    Intent intent = new Intent(activity, BankingActivity.class);
                    Log.i("MENU_DRAWER_TAG", "Banking is selected");
                    startActivities(new Intent[]{intent});
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else if (itemId == R.id.nav_contact) {
                    Intent intent = new Intent(activity, ContactActivity.class);
                    Log.i("MENU_DRAWER_TAG", "Contact is selected");
                    startActivities(new Intent[]{intent});
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (itemId == R.id.nav_support) {
                    Intent intent = new Intent(activity, SupportActivity.class);
                    Log.i("MENU_DRAWER_TAG", "Support is selected");
                    startActivities(new Intent[]{intent});
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (itemId == R.id.nav_logout) {
                    Toast.makeText(activity, "Singing Out", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(activity, MainActivity.class);
                    Log.i("MENU_DRAWER_TAG", "Logout is selected");
                    startActivities(new Intent[]{intent});
                    drawerLayout.closeDrawer(GravityCompat.START);
                }

                return true;
            }
        });
    }

    private void obtenerDatosUsuario(int idUsuario) {
        // Consulta para obtener la información del usuario desde la base de datos
        String query = "SELECT c.saldo " +
                "FROM Usuarios2 u " +
                "LEFT JOIN Cuentas c ON u.id_usuario = c.id_usuario " +
                "WHERE u.id_usuario = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(idUsuario)});

        if (cursor.moveToFirst()) {
            // Obtener datos del cursor y actualizar las vistas
            double saldoValue = cursor.getDouble(0);

            // Actualizar los TextView con los datos del usuario
            saldo.setText("$" + String.format("%.2f", saldoValue));
        } else {
            Toast.makeText(this, "No se encontraron datos para el usuario", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
