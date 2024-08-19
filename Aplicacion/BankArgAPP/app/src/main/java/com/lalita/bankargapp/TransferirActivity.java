package com.lalita.bankargapp;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.MenuItem;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import android.app.AlertDialog;
import android.widget.Button;

public class TransferirActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;

    SQLiteDatabase db;
    UsuariosSQLiteHelper dbHelper;
    EditText inputCVU, inputMonto;
    Button transferButton;
    int idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferir);

        dbHelper = new UsuariosSQLiteHelper(this);
        db = dbHelper.getWritableDatabase();

        // Recuperar id_usuario de SharedPreferences
        SharedPreferences preferences = getSharedPreferences("user_session", MODE_PRIVATE);
        idUsuario = preferences.getInt("id_usuario", -1);

        inputCVU = findViewById(R.id.cbu);
        inputMonto = findViewById(R.id.Dinerotransferir);
        transferButton = findViewById(R.id.Transferir);


        transferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Validar campos y realizar la transferencia
                String cvu = inputCVU.getText().toString().trim();
                String montoStr = inputMonto.getText().toString().trim();

                if (cvu.isEmpty() || montoStr.isEmpty()) {
                    Toast.makeText(TransferirActivity.this, "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show();
                    return;
                }

                double monto = Double.parseDouble(montoStr);

                // Validar el saldo y realizar la transferencia
                if (validarSaldo(idUsuario, monto)) {
                    realizarTransferencia(idUsuario, cvu, monto);
                } else {
                    Toast.makeText(TransferirActivity.this, "Saldo insuficiente para la transferencia.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*--- Boton en el tool bar que lleva al perfil---*/

        View btnPerfil = findViewById(R.id.account_cir);
        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TransferirActivity.this, PerfilActivity.class);
                startActivity(intent);
            }
        });

        /*--- Ventana emergente de aviso ---*/
//        Button btnPagar = findViewById(R.id.Transferir);
//        btnPagar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(TransferirActivity.this, "Servicio no disponible en estos momentos", Toast.LENGTH_SHORT).show();
//
//            }
//        });


        /*--- lleva al home ---*/
        View btnHome = findViewById(R.id.rectangle_2);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TransferirActivity.this, ProductActivity.class);
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

                TransferirActivity activity = TransferirActivity.this;
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

    // Método para validar el saldo antes de realizar la transferencia
    private boolean validarSaldo(int idUsuario, double monto) {
        String query = "SELECT saldo FROM Cuentas WHERE id_usuario = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(idUsuario)});

        if (cursor.moveToFirst()) {
            double saldoActual = cursor.getDouble(0);
            cursor.close();

            // Verificar que el saldo sea mayor o igual al monto que se desea transferir
            return saldoActual >= monto;
        }

        cursor.close();
        return false;
    }

    // Método para realizar la transferencia
    private void realizarTransferencia(int idUsuario, String cvu, double monto) {
        db.beginTransaction();

        try {
            // Obtener el id_cuenta del usuario
            String queryCuenta = "SELECT id_cuenta, saldo FROM Cuentas WHERE id_usuario = ?";
            Cursor cursor = db.rawQuery(queryCuenta, new String[]{String.valueOf(idUsuario)});

            if (!cursor.moveToFirst()) {
                Toast.makeText(this, "Error: No se encontró la cuenta del usuario.", Toast.LENGTH_SHORT).show();
                cursor.close();
                return;
            }

            int idCuenta = cursor.getInt(0);
            double saldoActual = cursor.getDouble(1);
            cursor.close();

            // Paso 1: Actualizar el saldo de la cuenta del usuario (restar el monto)
            String queryActualizarSaldo = "UPDATE Cuentas SET saldo = saldo - ? WHERE id_usuario = ?";
            db.execSQL(queryActualizarSaldo, new Object[]{monto, idUsuario});

            // Paso 2: Insertar la transacción en la tabla Transacciones
            ContentValues transaccionValues = new ContentValues();
            transaccionValues.put("id_cuenta", idCuenta); // La cuenta desde la que se transfiere
            transaccionValues.put("id_tipo_transaccion", 3); // 3 es tipo 'transferencia'
            transaccionValues.put("monto", monto);
            transaccionValues.put("descripcion", "Transferencia realizada");
            db.insert("Transacciones", null, transaccionValues);

            // Finalizar la transacción
            db.setTransactionSuccessful();
            Toast.makeText(this, "Transferencia realizada con éxito.", Toast.LENGTH_SHORT).show();

//            // Paso 1: Verificar si el destino (cvu) existe en la tabla de cuentas
//            String queryCuentaDestino = "SELECT id_cuenta FROM Cuentas WHERE contacto = ?";
//            Cursor cursor = db.rawQuery(queryCuentaDestino, new String[]{cvu});
//
//            if (!cursor.moveToFirst()) {
//                Toast.makeText(this, "El destino no existe.", Toast.LENGTH_SHORT).show();
//                cursor.close();
//                return;
//            }
//
//            int idCuentaDestino = cursor.getInt(0);
//            cursor.close();
//
//            // Paso 2: Actualizar el saldo de la cuenta del usuario (restar el monto)
//            String queryActualizarSaldo = "UPDATE Cuentas SET saldo = saldo - ? WHERE id_usuario = ?";
//            db.execSQL(queryActualizarSaldo, new Object[]{monto, idUsuario});
//
//            // Paso 3: Actualizar el saldo de la cuenta destino (sumar el monto)
//            String queryActualizarSaldoDestino = "UPDATE Cuentas SET saldo = saldo + ? WHERE id_cuenta = ?";
//            db.execSQL(queryActualizarSaldoDestino, new Object[]{monto, idCuentaDestino});
//
//            // Paso 4: Insertar la transacción en la tabla Transacciones
//            ContentValues transaccionValues = new ContentValues();
//            transaccionValues.put("id_cuenta", idCuentaDestino); // La cuenta destino
//            transaccionValues.put("id_tipo_transaccion", 1); // Suponiendo 1 es tipo 'transferencia'
//            transaccionValues.put("monto", monto);
//            transaccionValues.put("descripcion", "Transferencia realizada");
//            db.insert("Transacciones", null, transaccionValues);
//
//            // Finalizar la transacción
//            db.setTransactionSuccessful();
//            Toast.makeText(this, "Transferencia realizada con éxito.", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al realizar la transferencia.", Toast.LENGTH_SHORT).show();
        } finally {
            db.endTransaction();
        }
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
