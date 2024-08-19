package com.lalita.bankargapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

public class PagoCuentasActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;

    EditText editTextMonto, editTextCodigoPago;
    RadioGroup radioGroupSelect;
    Button btnPagar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago_cuentas);

        // Obtener el id_usuario desde SharedPreferences
        SharedPreferences preferences = getSharedPreferences("user_session", MODE_PRIVATE);
        int idUsuario = preferences.getInt("id_usuario", -1); // Valor por defecto -1 si no existe

        if (idUsuario == -1) {
            Toast.makeText(this, "No se encontró el usuario logueado", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Implementación del botón de pago
        Button btnPagar = findViewById(R.id.btn_pagar_cuentas);
        btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtener los campos del layout
                EditText montoEditText = findViewById(R.id.numero_monto);
                EditText codigoPagoEditText = findViewById(R.id.codigo_pago);
                RadioGroup selectRadioGroup = findViewById(R.id.select);
                int selectedId = selectRadioGroup.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedId);

                // Verificar que los campos no estén vacíos
                if (montoEditText.getText().toString().isEmpty() || codigoPagoEditText.getText().toString().isEmpty()) {
                    Toast.makeText(PagoCuentasActivity.this, "Debe completar todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Obtener el monto
                double monto = Double.parseDouble(montoEditText.getText().toString());

                // Mostrar un AlertDialog de confirmación
                new AlertDialog.Builder(PagoCuentasActivity.this)
                        .setTitle("Confirmar pago")
                        .setMessage("¿Está seguro que desea realizar el pago de " + monto + " para " + selectedRadioButton.getText() + "?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Verificar saldo suficiente y realizar el pago
                                realizarPago(idUsuario, monto, selectedRadioButton.getText().toString());
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .show();
            }
        });


        /*--- Boton en el tool bar que lleva al perfil---*/

        View btnPerfil = findViewById(R.id.account_cir);
        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PagoCuentasActivity.this, PerfilActivity.class);
                startActivity(intent);
            }
        });

        /*--- Ventana emergente de aviso ---*/
//        Button btnPagar = findViewById(R.id.btn_pagar_cuentas);
//        btnPagar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(PagoCuentasActivity.this, "Servicio no disponible en estos momentos", Toast.LENGTH_SHORT).show();
//
//            }
//        });

        /*--- lleva al home ---*/
        View btnHome = findViewById(R.id.view_bot);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PagoCuentasActivity.this, ProductActivity.class);
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

                PagoCuentasActivity activity = PagoCuentasActivity.this;
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

    private void realizarPago(int idUsuario, double monto, String tipoPago) {
        // Aquí debes acceder a la base de datos y verificar el saldo del usuario
        UsuariosSQLiteHelper dbHelper = new UsuariosSQLiteHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT saldo FROM Cuentas WHERE id_usuario = ?", new String[]{String.valueOf(idUsuario)});
        if (cursor.moveToFirst()) {
            double saldoActual = cursor.getDouble(0);

            if (saldoActual >= monto) {
                // Actualizar saldo
                db.execSQL("UPDATE Cuentas SET saldo = saldo - ? WHERE id_usuario = ?", new Object[]{monto, idUsuario});

                // Insertar transacción
                ContentValues valores = new ContentValues();
                valores.put("id_cuenta", obtenerIdCuenta(idUsuario));
                valores.put("id_tipo_transaccion", 3); // Asumimos que tienes un método para esto
                valores.put("monto", monto);
                valores.put("descripcion", "Pago de " + tipoPago);

                db.insert("Transacciones", null, valores);

                Toast.makeText(this, "Pago realizado exitosamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Saldo insuficiente", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Error al obtener la cuenta", Toast.LENGTH_SHORT).show();
        }

        cursor.close();
        db.close();
    }

    private int obtenerIdCuenta(int idUsuario) {
        // Método para obtener id_cuenta basado en id_usuario
        // Realiza una consulta a la tabla Cuentas
        UsuariosSQLiteHelper dbHelper = new UsuariosSQLiteHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT id_cuenta FROM Cuentas WHERE id_usuario = ?", new String[]{String.valueOf(idUsuario)});
        if (cursor.moveToFirst()) {
            int idCuenta = cursor.getInt(0);
            cursor.close();
            db.close();
            return idCuenta;
        } else {
            cursor.close();
            db.close();
            return -1; // Si no se encontró la cuenta
        }
    }


}






