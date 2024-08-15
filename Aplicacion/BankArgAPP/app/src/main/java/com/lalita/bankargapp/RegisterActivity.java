package com.lalita.bankargapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;



public class RegisterActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    EditText nombre;
    EditText apellido;
//    EditText CustomerNameV;
//    EditText LastNameV;
//    EditText tipoDocV;
//    EditText nroDocV;
//    EditText fecNacV;
//    EditText codLocV;
//    EditText tipoSexV;
//    EditText nroCalleV;
//    EditText CalleV;
    Button registerButton;
    SQLiteDatabase db;
    private Cursor fila;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        nombre = findViewById(R.id.nombre);
        apellido = findViewById(R.id.apellido);

        registerButton = findViewById(R.id.registerButton);
//
//        CustomerNameV = findViewById(R.id.CustomerName);
//        LastNameV = findViewById(R.id.LastName);
//        tipoDocV = findViewById(R.id.tipoDoc);
//        nroDocV = findViewById(R.id.nroDoc);
//        fecNacV = findViewById(R.id.fecNac);
//        codLocV = findViewById(R.id.codLoc);
//        tipoSexV = findViewById(R.id.tipoSex);
//        nroCalleV = findViewById(R.id.nroCalle);
//        CalleV = findViewById(R.id.Calle);

        // Inicializar la base de datos
        UsuariosSQLiteHelper dbHelper = new UsuariosSQLiteHelper(this);
        db = dbHelper.getWritableDatabase();

        // Listener del botón de registro
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputEmail = email.getText().toString().trim();
                String inputPassword = password.getText().toString().trim();

                String inputNombre = nombre.getText().toString().trim();
                String inputApellido = nombre.getText().toString().trim();
//
//                String inputName = CustomerNameV.getText().toString();
//                String inputLastName = LastNameV.getText().toString();
//                Integer inputTipoDoc = Integer.parseInt(tipoDocV.getText().toString());
//                String inputNroDoc = nroDocV.getText().toString();
//                String inputFechaNac = fecNacV.getText().toString();
//                Integer inputLocalidad = Integer.parseInt(codLocV.getText().toString());
//                Integer inputTipoSexo = Integer.parseInt(tipoSexV.getText().toString());
//                Integer inputNroCalle = Integer.parseInt(nroCalleV.getText().toString());
//                String inputCalle = CalleV.getText().toString();

//                boolean registrationSuccessful = dbHelper.insertData(inputName, inputLastName, inputUsername, inputPassword, inputTipoDoc, inputNroDoc, inputLocalidad, inputNroCalle, inputCalle, inputFechaNac, inputTipoSexo);

//                if (registrationSuccessful == true){
//                    Toast.makeText(RegisterActivity.this, "Signup Successfully!", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
//                    startActivity(intent);
//                }else{
//                    Toast.makeText(RegisterActivity.this, "Signup Failed!", Toast.LENGTH_SHORT).show();
//                }

                // Validación de campos
                if (inputEmail.isEmpty() || inputPassword.isEmpty() || inputNombre.isEmpty() || inputApellido.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Llamar al método de inserción de usuario desde el helper
                boolean registrationSuccessful = dbHelper.insertarUsuario2(db, inputNombre, inputApellido, inputEmail, inputPassword);

                if (registrationSuccessful) {
                    // Recuperar el id del nuevo usuario registrado
                    Cursor cursor = db.rawQuery("SELECT id_usuario FROM Usuarios2 WHERE email=?", new String[]{inputEmail});
                    if (cursor.moveToFirst()) {
                        int idUsuario = cursor.getInt(0); // Obtener id_usuario del nuevo usuario registrado

                        // Insertar una cuenta para el usuario recién registrado
                        boolean accountCreated = dbHelper.insertarCuenta(db, idUsuario, 1, 0.0); // Aquí 1 es el id_tipo_cuenta (Ahorros, por ejemplo) y 0.0 es el saldo inicial

                        if (accountCreated) {
                            // Guardar id_usuario en SharedPreferences
                            SharedPreferences preferences = getSharedPreferences("user_session", MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putInt("id_usuario", idUsuario);
                            editor.apply();

                            Toast.makeText(RegisterActivity.this, "¡Registro y cuenta creados exitosamente!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, ProductActivity.class);
                            startActivity(intent);
                        } else {
                            // Error al crear la cuenta
                            Toast.makeText(RegisterActivity.this, "Error al crear la cuenta. Inténtalo nuevamente.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    cursor.close();
                } else {
                    Toast.makeText(RegisterActivity.this, "Error en el registro. Inténtalo nuevamente.", Toast.LENGTH_SHORT).show();
                }


//                ContentValues values = new ContentValues();
//                values.put("nombre", inputNombre);
//                values.put("apellido", inputApellido);
//                values.put("email", inputEmail);
//                values.put("password", inputPassword);

//                long newRowId = db.insert("User", null, values);

//                if (newRowId != -1) {
//                    // Registration successful
//                    Toast.makeText(RegisterActivity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
//                    // After successful registration, you might navigate the user back to the login screen
//                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//                    startActivity(intent);
//                } else {
//                    // Registration failed
//                    Toast.makeText(RegisterActivity.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
//                }
            }
        });

        TextView textViewLogin = findViewById(R.id.signupText);

        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform action on click, here an intent to move to LoginActivity
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}