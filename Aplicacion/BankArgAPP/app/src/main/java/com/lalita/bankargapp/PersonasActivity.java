package com.lalita.bankargapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

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
    private int usuarioId = -1; // -1 means it's a new user

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

        nombreEditText = findViewById(R.id.nombre);
        apellidoEditText = findViewById(R.id.apellido);
        passwordEditText = findViewById(R.id.password);
        nroDocEditText = findViewById(R.id.nro_doc);
        nroCalleEditText = findViewById(R.id.nro_calle);
        calleEditText = findViewById(R.id.calle);
        fechaNacEditText = findViewById(R.id.fecha_nac);
        tipoDocSpinner = findViewById(R.id.tipo_doc);
        localidadSpinner = findViewById(R.id.Localidad);
        tipoSexoSpinner = findViewById(R.id.tipo_sexo);
        saveButton = findViewById(R.id.btn_agregar);
        updateButton = findViewById(R.id.btn_editar);

        usuariosSQLiteHelper = new UsuariosSQLiteHelper(this);

//        tipoDocSpinner = findViewById(R.id.tipo_doc);
//        usuariosSQLiteHelper = new UsuariosSQLiteHelper(this);
//        loadSpinnerData("tipo_doc", "Documentos", tipoDocSpinner);

//        localidadSpinner = findViewById(R.id.Localidad);
//        usuariosSQLiteHelper = new UsuariosSQLiteHelper(this);
//        loadSpinnerData("localidad", "localidades", localidadSpinner);

//        tipoSexoSpinner = findViewById(R.id.tipo_sexo);
//        usuariosSQLiteHelper = new UsuariosSQLiteHelper(this);
//        loadSpinnerData("tipo", "Sexos", tipoSexoSpinner);

        LoadSpinnerData("tipo_doc", "Documentos", tipoDocSpinner);
        LoadSpinnerData("localidad", "localidades", localidadSpinner);
        LoadSpinnerData("tipo", "Sexos", tipoSexoSpinner);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarUsuario();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarUsuario();
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

                PersonasActivity activity = PersonasActivity.this;
                if (itemId == R.id.nav_home) {
                    Intent intent = new Intent(activity, HomeActivity.class);
                    Log.i("MENU_DRAWER_TAG", "Home is selected");
                    startActivities(new Intent[]{intent});
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (itemId == R.id.nav_banking) {
                    Intent intent = new Intent(activity, BankingActivity.class);
                    Log.i("MENU_DRAWER_TAG", "Banking is selected");
                    startActivities(new Intent[]{intent});
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (itemId == R.id.nav_product) {
                    Intent intent = new Intent(activity, ProductActivity.class);
                    Log.i("MENU_DRAWER_TAG", "Product is selected");
                    startActivities(new Intent[]{intent});
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (itemId == R.id.nav_loan) {
                    Intent intent = new Intent(activity, LoanActivity.class);
                    Log.i("MENU_DRAWER_TAG", "Loan is selected");
                    startActivities(new Intent[]{intent});
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (itemId == R.id.nav_profile) {
                    Intent intent = new Intent(activity, PerfilActivity.class);
                    Log.i("MENU_DRAWER_TAG", "Perfil is selected");
                    startActivities(new Intent[]{intent});
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (itemId == R.id.nav_contact) {
                    Intent intent = new Intent(activity, ContactActivity.class);
                    Log.i("MENU_DRAWER_TAG", "Contact is selected");
                    startActivities(new Intent[]{intent});
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (itemId == R.id.nav_support) {
                    Intent intent = new Intent(activity, SupportActivity.class);
                    Log.i("MENU_DRAWER_TAG", "Support is selected");
                    startActivities(new Intent[]{intent});
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (itemId == R.id.nav_transfer) {
                    Intent intent = new Intent(activity, TransferActivity.class);
                    Log.i("MENU_DRAWER_TAG", "Transfer is selected");
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

    private void guardarUsuario() {
        String nombre = nombreEditText.getText().toString();
        String apellido = apellidoEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        int tipoDoc = getSpinnerId(tipoDocSpinner, "Documentos", "id_tipo_doc");
        String nroDoc = nroDocEditText.getText().toString();
        int localidad = getSpinnerId(localidadSpinner, "Localidades", "cod_localidad");
        int nroCalle = Integer.parseInt(nroCalleEditText.getText().toString());
        String calle = calleEditText.getText().toString();
        String fechaNac = fechaNacEditText.getText().toString();
        int tipoSexo = getSpinnerId(tipoSexoSpinner, "Sexos", "id_tipo_sexo");

        usuariosSQLiteHelper.insertarUsuario(nombre, apellido, password, tipoDoc, nroDoc, localidad, nroCalle, calle, fechaNac, tipoSexo);
        Toast.makeText(this, "Usuario guardado correctamente", Toast.LENGTH_SHORT).show();
    }

    private void actualizarUsuario() {
        if (usuarioId != -1) { // Ensure a user is selected for update
            String nombre = nombreEditText.getText().toString();
            String apellido = apellidoEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            int tipoDoc = getSpinnerId(tipoDocSpinner, "Documentos", "id_tipo_doc");
            String nroDoc = nroDocEditText.getText().toString();
            int localidad = getSpinnerId(localidadSpinner, "Localidades", "cod_localidad");
            int nroCalle = Integer.parseInt(nroCalleEditText.getText().toString());
            String calle = calleEditText.getText().toString();
            String fechaNac = fechaNacEditText.getText().toString();
            int tipoSexo = getSpinnerId(tipoSexoSpinner, "Sexos", "id_tipo_sexo");

            int id = usuariosSQLiteHelper.buscarUsuarioId(nombre, apellido, nroDoc);

            if (id != -1) {
                usuariosSQLiteHelper.actualizarUsuario(id, nombre, apellido, password, tipoDoc, nroDoc, localidad, nroCalle, calle, fechaNac, tipoSexo);
                Toast.makeText(this, "Usuario actualizado correctamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
            }

//          usuariosSQLiteHelper.actualizarUsuario(usuarioId, nombre, apellido, password, tipoDoc, nroDoc, localidad, nroCalle, calle, fechaNac, tipoSexo);
        }
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