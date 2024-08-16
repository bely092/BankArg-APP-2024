package com.lalita.bankargapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.content.ClipData;
import android.content.Context;
import android.content.ClipboardManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class PerfilActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;

    TextView nombreCompleto, email, telefono, cvu, alias, dni, saldo;
    Button btnEditar;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        // Referencias a los TextView en el layout
        nombreCompleto = findViewById(R.id.nombreCompleto);
        email = findViewById(R.id.Email);
        telefono = findViewById(R.id.Telefono);
        cvu = findViewById(R.id.CVU);
        alias = findViewById(R.id.Alias);
        dni = findViewById(R.id.DNI);
        saldo = findViewById(R.id.saldo);

        btnEditar = findViewById(R.id.btn_editar);

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

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerfilActivity.this, PersonasActivity.class);
                startActivity(intent);
            }
        });

        /*--- boton copiar CVU ---*/
        ImageButton copyText = findViewById(R.id.imageButton5);
        copyText.setOnClickListener(view -> {
            String textToCopy = cvu.getText().toString();
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("copiado", textToCopy);
            clipboard.setPrimaryClip(clip);
        });



        /*--- boton copiar Alias ---*/
        ImageButton copTexto = findViewById(R.id.imageButton7);
        copTexto.setOnClickListener(view -> {
            String textToCopy = alias.getText().toString();
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("copiado", textToCopy);
            clipboard.setPrimaryClip(clip);
        });


        /*--- lleva al home ---*/
        View btnHome = findViewById(R.id.rectangle_2);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PerfilActivity.this, ProductActivity.class);
                startActivity(intent);
            }
        });

        /*---------------------Hooks------------------------*/
        drawerLayout=findViewById(R.id.drawer_layout);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,
                R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                PerfilActivity activity = PerfilActivity.this;
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

        //               else if (itemId == R.id.nav_loan) {
                //                    Intent intent = new Intent(activity, LoanActivity.class);
//                     Log.i("MENU_DRAWER_TAG", "Loan is selected");
                //                    startActivities(new Intent[]{intent});
//                     drawerLayout.closeDrawer(GravityCompat.START);
                //                } else if (itemId == R.id.nav_profile) {
                //                    Intent intent = new Intent(activity, PerfilActivity.class);
                //                    Log.i("MENU_DRAWER_TAG", "Perfil is selected");
//                     startActivities(new Intent[]{intent});
                //                    drawerLayout.closeDrawer(GravityCompat.START);
                //                }
//                 else if (itemId == R.id.nav_transfer) {
//                     Intent intent = new Intent(activity, TransferActivity.class);
                //                    Log.i("MENU_DRAWER_TAG", "Transfer is selected");
                //                    startActivities(new Intent[]{intent});
                //                    drawerLayout.closeDrawer(GravityCompat.START);
                //                }
//    else if (itemId == R.id.nav_product) {
//    Intent intent = new Intent(activity, ProductActivity.class);
 //       Log.i("MENU_DRAWER_TAG", "Product is selected");
 //       startActivities(new Intent[]{intent});
   //     drawerLayout.closeDrawer(GravityCompat.START);
 //   }

//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
//            navigationView.setCheckedItem(R.id.nav_home);
//        }


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

    private void obtenerDatosUsuario(int idUsuario) {
        // Consulta para obtener la información del usuario desde la base de datos
        String query = "SELECT u.nombre, u.apellido, u.email, c.saldo, c.id_cuenta " +
                "FROM Usuarios2 u " +
                "LEFT JOIN Cuentas c ON u.id_usuario = c.id_usuario " +
                "WHERE u.id_usuario = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(idUsuario)});

        if (cursor.moveToFirst()) {
            // Obtener datos del cursor y actualizar las vistas
            String nombre = cursor.getString(0);
            String apellido = cursor.getString(1);
            String emailStr = cursor.getString(2);
            double saldoValue = cursor.getDouble(3);
            String aliasStr = null;
            String cvuStr = null;
            String dniStr = null;
            String telefonoStr = null;
            int idCuenta = cursor.getInt(4); // ID de la cuenta

            // Actualizar los TextView con los datos del usuario
            nombreCompleto.setText(nombre + " " + apellido);
            email.setText(emailStr);
            cvu.setText(cvuStr != null ? cvuStr : "Sin CVU");
            alias.setText(aliasStr != null ? aliasStr : "Sin alias");
            dni.setText(dniStr != null ? dniStr : "Sin DNI");
            telefono.setText(telefonoStr != null ? telefonoStr : "Sin teléfono");
            saldo.setText("$" + String.format("%.2f", saldoValue));
        } else {
            Toast.makeText(this, "No se encontraron datos para el usuario", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
    }

}
