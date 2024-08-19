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
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
public class PagoServicioActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;

    SQLiteDatabase db;
    UsuariosSQLiteHelper dbHelper;
    EditText inputMonto, inputCodigoEmpresa, inputCodigoPago;
    RadioGroup radioGroupServicio;
    int idUsuario, idCuenta;
    double saldoDisponible;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago_servicio);

        // Inicializamos la base de datos
        dbHelper = new UsuariosSQLiteHelper(this);
        db = dbHelper.getWritableDatabase();

        // Recuperar id_usuario de SharedPreferences
        SharedPreferences preferences = getSharedPreferences("user_session", MODE_PRIVATE);
        idUsuario = preferences.getInt("id_usuario", -1);

        // Obtener id_cuenta
        idCuenta = obtenerIdCuenta(idUsuario);

        // Hooks de la vista
        inputMonto = findViewById(R.id.numero_monto);
        inputCodigoEmpresa = findViewById(R.id.codigo_empresa);
        inputCodigoPago = findViewById(R.id.codigo_pago);
        radioGroupServicio = findViewById(R.id.select);
        Button btnPagar = findViewById(R.id.btn_pagar_servicio);

//        // Mostrar saldo disponible
        saldoDisponible = obtenerSaldoCuenta(idCuenta);
//        TextView textSaldoDisponible = findViewById(R.id.saldo_disponible);
//        textSaldoDisponible.setText("Saldo disponible: $" + saldoDisponible);

        // Listener del botón Pagar
        btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Validar campos
                if (!validarCampos()) return;

                // Obtener el monto y validar si hay saldo suficiente
                double monto = Double.parseDouble(inputMonto.getText().toString());
                if (monto > saldoDisponible) {
                    Toast.makeText(PagoServicioActivity.this, "Saldo insuficiente", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Mostrar alerta de confirmación
                mostrarAlertaConfirmacion(monto);
            }
        });

//        btnPagar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String montoStr = inputMonto.getText().toString().trim();
//                String empresa = inputEmpresa.getText().toString().trim();
//                String codigoPago = inputCodigoPago.getText().toString().trim();
//
//                // Validar que todos los campos estén llenos
//                if (montoStr.isEmpty() || empresa.isEmpty() || codigoPago.isEmpty()) {
//                    Toast.makeText(PagoServicioActivity.this, "Todos los campos son obligatorios.", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                // Obtener el monto ingresado
//                double monto = Double.parseDouble(montoStr);
//
//                // Verificar el saldo del usuario
//                if (!validarSaldo(idUsuario, monto)) {
//                    Toast.makeText(PagoServicioActivity.this, "Saldo insuficiente.", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                // Confirmación antes de realizar la transacción
//                new AlertDialog.Builder(PagoServicioActivity.this)
//                        .setTitle("Confirmación de pago")
//                        .setMessage("¿Está seguro de que desea realizar el pago?")
//                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                // Proceder con el pago
//                                realizarPago(idUsuario, monto, empresa, codigoPago);
//                            }
//                        })
//                        .setNegativeButton("No", null)
//                        .show();
//            }
//        });


        /*--- Boton en el tool bar que lleva al perfil---*/

        View btnPerfil = findViewById(R.id.account_cir);
        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PagoServicioActivity.this, PerfilActivity.class);
                startActivity(intent);
            }
        });

        /*--- Ventana emergente de aviso ---*/
//        Button btnPagar = findViewById(R.id.btn_pagar_servicio);
//        btnPagar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(PagoServicioActivity.this, "Servicio no disponible en estos momentos", Toast.LENGTH_SHORT).show();
//
//            }
//        });

        /*--- lleva al home ---*/
        View btnHome = findViewById(R.id.view_bot);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PagoServicioActivity.this, ProductActivity.class);
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

                PagoServicioActivity activity = PagoServicioActivity.this;
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

    private int obtenerIdCuenta(int idUsuario) {
        Cursor cursor = db.rawQuery("SELECT id_cuenta FROM Cuentas WHERE id_usuario = ?", new String[]{String.valueOf(idUsuario)});
        if (cursor.moveToFirst()) {
            return cursor.getInt(0);
        }
        return -1;
    }

    private double obtenerSaldoCuenta(int idCuenta) {
        Cursor cursor = db.rawQuery("SELECT saldo FROM Cuentas WHERE id_cuenta = ?", new String[]{String.valueOf(idCuenta)});
        if (cursor.moveToFirst()) {
            return cursor.getDouble(0);
        }
        return 0.0;
    }

    private boolean validarCampos() {
        if (inputMonto.getText().toString().isEmpty()) {
            Toast.makeText(this, "Ingrese el monto", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (inputCodigoEmpresa.getText().toString().isEmpty()) {
            Toast.makeText(this, "Ingrese el código de la empresa", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (inputCodigoPago.getText().toString().isEmpty()) {
            Toast.makeText(this, "Ingrese el código de pago", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (radioGroupServicio.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Seleccione un servicio", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void mostrarAlertaConfirmacion(final double monto) {
        new AlertDialog.Builder(this)
                .setTitle("Confirmar Pago")
                .setMessage("¿Está seguro de que desea realizar esta transacción?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        realizarPago(monto);
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void realizarPago(double monto) {
        // Insertar transacción
        ContentValues valoresTransaccion = new ContentValues();
        valoresTransaccion.put("id_cuenta", idCuenta);
        valoresTransaccion.put("id_tipo_transaccion", 3);
        valoresTransaccion.put("monto", monto);
        valoresTransaccion.put("descripcion", "Pago de servicio");

        db.insert("Transacciones", null, valoresTransaccion);

        // Actualizar saldo en la cuenta
        double nuevoSaldo = saldoDisponible - monto;
        ContentValues valoresCuenta = new ContentValues();
        valoresCuenta.put("saldo", nuevoSaldo);
        db.update("Cuentas", valoresCuenta, "id_cuenta = ?", new String[]{String.valueOf(idCuenta)});

        Toast.makeText(this, "Pago realizado con éxito", Toast.LENGTH_SHORT).show();
        finish(); // Cerrar actividad
    }

    // Función para validar si el usuario tiene saldo suficiente
    private boolean validarSaldo(int idUsuario, double monto) {
        Cursor cursor = db.rawQuery("SELECT saldo FROM Cuentas WHERE id_usuario = ?", new String[]{String.valueOf(idUsuario)});
        if (cursor.moveToFirst()) {
            double saldoActual = cursor.getDouble(0);
            return saldoActual >= monto;  // Verificar si el saldo es suficiente
        }
        return false;
    }

    // Función para realizar el pago
//    private void realizarPago(int idUsuario, double monto, String empresa, String codigoPago) {
//        // Restar el monto de la cuenta del usuario
//        db.execSQL("UPDATE Cuentas SET saldo = saldo - ? WHERE id_usuario = ?", new Object[]{monto, idUsuario});
//
//        // Registrar la transacción en la tabla Transacciones
//        ContentValues transaccionValues = new ContentValues();
//        transaccionValues.put("id_usuario", idUsuario);
//        transaccionValues.put("monto", monto);
//        transaccionValues.put("descripcion", "Pago a " + empresa + " Código: " + codigoPago);
//        transaccionValues.put("tipo_transaccion", 3);
//        transaccionValues.put("fecha_transaccion", System.currentTimeMillis());
//        db.insert("Transacciones", null, transaccionValues);
//
//        Toast.makeText(this, "Pago realizado exitosamente.", Toast.LENGTH_SHORT).show();
//        finish();  // Finalizamos la actividad después del pago
//    }
}

