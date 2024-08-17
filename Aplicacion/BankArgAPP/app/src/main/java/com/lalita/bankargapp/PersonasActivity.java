package com.lalita.bankargapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.EditText;
import android.text.InputType;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.lalita.bankargapp.Clases.Usuario;
import com.lalita.bankargapp.Clases.UsuarioAdapter;

import java.util.ArrayList;
import java.util.List;


public class PersonasActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;
    private EditText nombreEditText, apellidoEditText, passwordEditText, nroDocEditText, nroCalleEditText, calleEditText, fechaNacEditText;
    private Spinner tipoDocSpinner, localidadSpinner, tipoSexoSpinner;
    private Button saveButton, updateButton;
    private UsuariosSQLiteHelper usuariosSQLiteHelper;
    private ListView listViewUsuarios;
    private RecyclerView recyclerViewUsuarios;
    private int usuarioId = -1; // -1 means it's a new user

    EditText nombre, apellido, password, email;
    Button btnEditar, btnAgregar;
    SQLiteDatabase db;
    UsuariosSQLiteHelper dbHelper;
    int idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personas);

        /*--- Boton en el tool bar que lleva al perfil---*/

        View btnPerfil = findViewById(R.id.account_cir);
        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PersonasActivity.this, PerfilActivity.class);
                startActivity(intent);
            }
        });

        /*--- lleva al home ---*/
        View btnHome = findViewById(R.id.view8);
        btnHome.setOnClickListener(view -> {
            Intent intent = new Intent(PersonasActivity.this, ProductActivity.class);
            startActivity(intent);
        });

        // Inicializar los EditText y botones
        nombre = findViewById(R.id.nombre);
        apellido = findViewById(R.id.apellido);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        btnEditar = findViewById(R.id.btn_editar);
//        btnAgregar = findViewById(R.id.btn_agregar);

        // Inicializar el helper de la base de datos
        dbHelper = new UsuariosSQLiteHelper(this);

        // Recuperar id_usuario de SharedPreferences
        SharedPreferences preferences = getSharedPreferences("user_session", MODE_PRIVATE);
        idUsuario = preferences.getInt("id_usuario", -1);

        if (idUsuario != -1) {
            // Si el usuario está logueado, cargar los datos
            cargarDatosUsuario(idUsuario);
        } else {
            Toast.makeText(this, "Error: Usuario no logueado", Toast.LENGTH_SHORT).show();
            finish(); // Finalizar la actividad si no hay usuario logueado
        }

        // Configurar el botón de edición para mostrar el anuncio de confirmación
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogoConfirmacion();
            }
        });

        // Si es necesario agregar un nuevo usuario (esto no es necesario si sólo se edita el usuario actual)
//        btnAgregar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                agregarNuevoUsuario();
//            }
//        });

//        nombreEditText = findViewById(R.id.nombre);
//        apellidoEditText = findViewById(R.id.apellido);
//        passwordEditText = findViewById(R.id.password);
//        nroDocEditText = findViewById(R.id.nro_doc);
//        nroCalleEditText = findViewById(R.id.nro_calle);
//        calleEditText = findViewById(R.id.calle);
//        fechaNacEditText = findViewById(R.id.fecha_nac);
//        tipoDocSpinner = findViewById(R.id.tipo_doc);
//        localidadSpinner = findViewById(R.id.Localidad);
//        tipoSexoSpinner = findViewById(R.id.tipo_sexo);
//        saveButton = findViewById(R.id.btn_agregar);
//        updateButton = findViewById(R.id.btn_editar);
//        listViewUsuarios = findViewById(R.id.listViewUsuarios);
//        recyclerViewUsuarios = findViewById(R.id.recyclerUsuarios);

//        usuariosSQLiteHelper = new UsuariosSQLiteHelper(this);

//        tipoDocSpinner = findViewById(R.id.tipo_doc);
//        usuariosSQLiteHelper = new UsuariosSQLiteHelper(this);
//        loadSpinnerData("tipo_doc", "Documentos", tipoDocSpinner);

//        localidadSpinner = findViewById(R.id.Localidad);
//        usuariosSQLiteHelper = new UsuariosSQLiteHelper(this);
//        loadSpinnerData("localidad", "localidades", localidadSpinner);

//        tipoSexoSpinner = findViewById(R.id.tipo_sexo);
//        usuariosSQLiteHelper = new UsuariosSQLiteHelper(this);
//        loadSpinnerData("tipo", "Sexos", tipoSexoSpinner);

//        LoadSpinnerData("tipo_doc", "Documentos", tipoDocSpinner);
//        LoadSpinnerData("localidad", "localidades", localidadSpinner);
//        LoadSpinnerData("tipo", "Sexos", tipoSexoSpinner);

//        saveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                guardarUsuario();
//            }
//        });
//
//        updateButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                actualizarUsuario();
//            }
//        });

//        recyclerViewUsuarios.setLayoutManager(new LinearLayoutManager(this));

//        loadUserData();

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

                PersonasActivity activity = PersonasActivity.this;
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

    // Método para cargar los datos del usuario en los EditText
    private void cargarDatosUsuario(int idUsuario) {
        // Consultar la base de datos usando el método de tu helper
        Cursor cursor = dbHelper.getReadableDatabase().rawQuery(
                "SELECT nombre, apellido, email FROM Usuarios2 WHERE id_usuario = ?",
                new String[]{String.valueOf(idUsuario)}
        );

        if (cursor.moveToFirst()) {
            // Rellenar los campos del formulario con los datos del usuario
            nombre.setText(cursor.getString(0));
            apellido.setText(cursor.getString(1));
            email.setText(cursor.getString(2));
        }
        cursor.close();
    }

    // Método para mostrar un diálogo de confirmación antes de editar
    private void mostrarDialogoConfirmacion() {
        AlertDialog.Builder builder = new AlertDialog.Builder(PersonasActivity.this);
        builder.setTitle("Confirmar edición");
        builder.setMessage("¿Deseas editar los datos?");

        // Si la contraseña se va a cambiar, muestra un campo para la contraseña anterior
        if (!password.getText().toString().isEmpty()) {
            // Inflar un layout que tenga un campo para la contraseña anterior
            final EditText inputPasswordAnterior = new EditText(this);
            inputPasswordAnterior.setHint("Contraseña anterior");
            inputPasswordAnterior.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

            // Añadir el campo de texto al diálogo
            builder.setView(inputPasswordAnterior);

            builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Validar la contraseña anterior antes de proceder con la edición
                    String passwordAnterior = inputPasswordAnterior.getText().toString();
                    validarYEditarDatos(passwordAnterior);
                }
            });
        } else {
            builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Editar sin cambiar la contraseña
                    editarDatosUsuario();
                }
            });
        }

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Cerrar el diálogo si no quiere editar
                dialog.dismiss();
            }
        });

        // Mostrar el diálogo
        builder.show();
    }

    // Método para editar y actualizar los datos del usuario
    private void editarDatosUsuario() {
        // Obtener los valores de los EditText
        String nuevoNombre = nombre.getText().toString();
        String nuevoApellido = apellido.getText().toString();
        String nuevoEmail = email.getText().toString();
        String nuevaPassword = password.getText().toString();  // Campo opcional

        // Validar que los campos de nombre, apellido, y email no estén vacíos
        if (nuevoNombre.isEmpty() || nuevoApellido.isEmpty() || nuevoEmail.isEmpty()) {
            Toast.makeText(this, "Por favor complete todos los campos obligatorios.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Si el campo de contraseña está vacío, mantener la contraseña actual
        if (nuevaPassword.isEmpty()) {
            Cursor cursor = dbHelper.getReadableDatabase().rawQuery(
                    "SELECT password FROM Usuarios2 WHERE id_usuario = ?",
                    new String[]{String.valueOf(idUsuario)}
            );
            if (cursor.moveToFirst()) {
                nuevaPassword = cursor.getString(0);  // Usar la contraseña actual
            }
            cursor.close();
        }

        // Usar el método actualizarUsuario2 del helper
        boolean actualizado = dbHelper.actualizarUsuario2(idUsuario, nuevoNombre, nuevoApellido, nuevoEmail, nuevaPassword);

        if (actualizado) {
            Toast.makeText(this, "Datos actualizados correctamente.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al actualizar los datos.", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para validar la contraseña anterior y editar los datos
    private void validarYEditarDatos(String passwordAnterior) {
        // Validar la contraseña anterior
        Cursor cursor = dbHelper.getReadableDatabase().rawQuery(
                "SELECT password FROM Usuarios2 WHERE id_usuario = ?",
                new String[]{String.valueOf(idUsuario)}
        );

        if (cursor.moveToFirst()) {
            String passwordActual = cursor.getString(0);
            if (passwordActual.equals(passwordAnterior)) {
                // La contraseña anterior coincide, proceder con la edición
                editarDatosUsuario();
            } else {
                Toast.makeText(this, "Contraseña anterior incorrecta.", Toast.LENGTH_SHORT).show();
            }
        }
        cursor.close();
    }

    // Método opcional para agregar un nuevo usuario (si es necesario)
    private void agregarNuevoUsuario() {
        String nuevoNombre = nombre.getText().toString();
        String nuevoApellido = apellido.getText().toString();
        String nuevoEmail = email.getText().toString();
        String nuevaPassword = password.getText().toString();

        // Validar que los campos no estén vacíos
        if (nuevoNombre.isEmpty() || nuevoApellido.isEmpty() || nuevoEmail.isEmpty() || nuevaPassword.isEmpty()) {
            Toast.makeText(this, "Por favor complete todos los campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Usar el método insertarUsuario2 del helper
        boolean agregado = dbHelper.insertarUsuario2(dbHelper.getWritableDatabase(), nuevoNombre, nuevoApellido, nuevoEmail, nuevaPassword);

        if (agregado) {
            Toast.makeText(this, "Usuario agregado exitosamente.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al agregar el usuario.", Toast.LENGTH_SHORT).show();
        }
    }

//    Antiguo ->

    private void guardarUsuario() {
        String nombre = nombreEditText.getText().toString();
        String apellido = apellidoEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        int tipoDoc = getSpinnerId(tipoDocSpinner, "Documentos", "id_tipo_doc");
        String nroDoc = nroDocEditText.getText().toString();
        int localidad = getSpinnerId(localidadSpinner, "Localidades", "cod_localidad");
        String nroCalleStr = nroCalleEditText.getText().toString();
        String calle = calleEditText.getText().toString();
        String fechaNac = fechaNacEditText.getText().toString();
        int tipoSexo = getSpinnerId(tipoSexoSpinner, "Sexos", "id_tipo_sexo");

        if (nombre.isEmpty() || apellido.isEmpty() || password.isEmpty() || nroDoc.isEmpty() ||
                nroCalleStr.isEmpty() || calle.isEmpty() || fechaNac.isEmpty() || tipoDoc == -1 ||
                localidad == -1 || tipoSexo == -1) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        int nroCalle;
        try {
            nroCalle = Integer.parseInt(nroCalleStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Número de calle inválido", Toast.LENGTH_SHORT).show();
            return;
        }

        usuariosSQLiteHelper.insertarUsuario(nombre, apellido, password, tipoDoc, nroDoc, localidad, nroCalle, calle, fechaNac, tipoSexo);
        Toast.makeText(this, "Usuario guardado correctamente", Toast.LENGTH_SHORT).show();
        loadUserData();
    }

    private void actualizarUsuario() {
            String nombre = nombreEditText.getText().toString();
            String apellido = apellidoEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            int tipoDoc = getSpinnerId(tipoDocSpinner, "Documentos", "id_tipo_doc");
            String nroDoc = nroDocEditText.getText().toString();
            int localidad = getSpinnerId(localidadSpinner, "Localidades", "cod_localidad");
            String nroCalleStr = nroCalleEditText.getText().toString();
            String calle = calleEditText.getText().toString();
            String fechaNac = fechaNacEditText.getText().toString();
            int tipoSexo = getSpinnerId(tipoSexoSpinner, "Sexos", "id_tipo_sexo");

            if (nombre.isEmpty() || apellido.isEmpty() || password.isEmpty() || nroDoc.isEmpty() ||
                    nroCalleStr.isEmpty() || calle.isEmpty() || fechaNac.isEmpty() || tipoDoc == -1 ||
                    localidad == -1 || tipoSexo == -1) {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

        int nroCalle;
        try {
            nroCalle = Integer.parseInt(nroCalleStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Número de calle inválido", Toast.LENGTH_SHORT).show();
            return;
        }

            int id = usuariosSQLiteHelper.buscarUsuarioId(nombre, apellido, nroDoc);

            if (id != -1) {
                usuariosSQLiteHelper.actualizarUsuario(id, nombre, apellido, password, tipoDoc, nroDoc, localidad, nroCalle, calle, fechaNac, tipoSexo);
                Toast.makeText(this, "Usuario actualizado correctamente", Toast.LENGTH_SHORT).show();
                loadUserData();
            } else {
                Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
            }

//          usuariosSQLiteHelper.actualizarUsuario(usuarioId, nombre, apellido, password, tipoDoc, nroDoc, localidad, nroCalle, calle, fechaNac, tipoSexo);
    }

    private void LoadSpinnerData(String columnName, String tableName, Spinner spinner) {
        List<String> labels = usuariosSQLiteHelper.getAllLabels(columnName, tableName);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, labels);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    private int getSpinnerId(Spinner spinner, String tableName, String COLUMN_ID) {
        int position = spinner.getSelectedItemPosition();
        List<Integer> ids = usuariosSQLiteHelper.getAllIds(COLUMN_ID, tableName);
        return ids.get(position);
    }

    private void loadUserData() {
        List<Usuario> usuarios = usuariosSQLiteHelper.getAllUsuarios();
        UsuarioAdapter adapter = new UsuarioAdapter(usuarios);
        recyclerViewUsuarios.setAdapter(adapter);
    }

//    private void loadUserData() {
//        List<Usuario> usuarios = usuariosSQLiteHelper.getAllUsuarios();
//        UsuarioAdapter adapter = new UsuarioAdapter(this, usuarios);
//        listViewUsuarios.setAdapter(adapter);
//    }

    private void loadSpinnerData(String COLUMN_NAME, String TABLE_NAME, Spinner SPINNER) {
        List<String> labels = getAllLabels(COLUMN_NAME, TABLE_NAME);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, labels);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        SPINNER.setAdapter(dataAdapter);
    }

    private List<String> getAllLabels(String COLUMN_NAME, String TABLE_NAME) {
        List<String> labels = new ArrayList<>();

        SQLiteDatabase db = usuariosSQLiteHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_NAME + " FROM " + TABLE_NAME + " ORDER BY " + COLUMN_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return labels;
    }
}