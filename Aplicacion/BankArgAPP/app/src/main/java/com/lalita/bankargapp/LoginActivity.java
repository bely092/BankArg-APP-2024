package com.lalita.bankargapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

public class LoginActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button loginButton;
    SQLiteDatabase db;
    private Cursor fila;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);

        // Instancia del helper de la base de datos
        UsuariosSQLiteHelper dbHelper = new UsuariosSQLiteHelper(this);
        db = dbHelper.getReadableDatabase();

        // Listener del botón de login
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputUsername = username.getText().toString();
                String inputPassword = password.getText().toString();

//                Usuarios user = new Usuarios(inputUsername, inputPassword);

//                Usuarios usuario = dbHelper.findUserByUsernameAndPassword(inputUsername, inputPassword);
//
//                if (usuario != null) {
//                    // User exists, login successful
//                    Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
//
//                    // Navigate to the profile activity after successful login
//                    Intent intent = new Intent(LoginActivity.this, PerfilActivity.class);
//                    startActivity(intent);
//                } else {
//                    // User doesn't exist or incorrect password
//                    Toast.makeText(LoginActivity.this, "Login Failed!", Toast.LENGTH_SHORT).show();
//                }

                // Validar que los campos no estén vacíos
                if (inputUsername.isEmpty() || inputPassword.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Query the database to check if the user exists
//                fila = db.rawQuery("SELECT username, password FROM User WHERE username='"+
//                        inputUsername+"' and password='"+inputPassword+"'",null);

                // Consulta para validar el login usando un rawQuery con argumentos
                fila = db.rawQuery("SELECT id_usuario, email FROM Usuarios2 WHERE email=? AND password=?",
                        new String[]{inputUsername, inputPassword});

                if (fila.moveToFirst()) {
                    // Obtener el id_usuario del usuario que ha iniciado sesión
                    int idUsuario = fila.getInt(0); // El id_usuario es el primer campo en la consulta

                    // Guardar id_usuario en SharedPreferences
                    SharedPreferences preferences = getSharedPreferences("user_session", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("id_usuario", idUsuario);
                    editor.apply();

                    Toast.makeText(LoginActivity.this, "¡Login exitoso!", Toast.LENGTH_SHORT).show();

                    // Navegar a la siguiente pantalla
                    Intent intent = new Intent(LoginActivity.this, ProductActivity.class);
                    startActivity(intent);
                } else {
                    // Fallo en el login
                    Toast.makeText(LoginActivity.this, "Credenciales incorrectas. Inténtalo de nuevo.", Toast.LENGTH_SHORT).show();
                }
                fila.close();

//                if (username.getText().toString().equals("user") && password.getText().toString().equals("1234")) {
//                    Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
//
//                    Intent intent = new Intent(LoginActivity.this, PerfilActivity.class);
//                    startActivities(new Intent[]{intent});
//                } else {
//                    Toast.makeText(LoginActivity.this, "Login Failed!", Toast.LENGTH_SHORT).show();
//                }
            }
        });

        // Listener para ir al registro
        TextView textViewRegister = findViewById(R.id.signupText);
        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform action on click, here an intent to move to RegisterActivity
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        // Listener para abrir una página web
        TextView textViewWeb = findViewById(R.id.webText);
        textViewWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, Web.class);
                startActivity(intent);
            }
        });
    }
}