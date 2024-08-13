package com.lalita.bankargapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.lalita.bankargapp.Clases.Contacto;
import com.lalita.bankargapp.Clases.ContactosAdapter;

import java.util.ArrayList;
import java.util.List;

public class AgregarPersonasActivity extends AppCompatActivity {

    private EditText editTextCBU;
    //private EditText editTextAlias;
    private EditText nombrePers;
    private Button buttonAddPerson;

    UsuariosSQLiteHelper miBase;


    RecyclerView recyclerViewContactos;
    ContactosAdapter contactoAdapter;
    List<Contacto> contactoList;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        editTextCBU = findViewById(R.id.editTextCBU);
        //editTextAlias = findViewById(R.id.editTextAlias);
        nombrePers = findViewById(R.id.nombrePers);
        buttonAddPerson = findViewById(R.id.buttonAddPerson);

        recyclerViewContactos = findViewById(R.id.recyclerViewContactos);


        // Inicializar la base de datos
        miBase = new UsuariosSQLiteHelper(this);

        // Configurar el RecyclerView
        recyclerViewContactos.setLayoutManager(new LinearLayoutManager(this));
        contactoList = getAllContactos();
        contactoAdapter = new ContactosAdapter(contactoList, this::eliminarContacto);
        recyclerViewContactos.setAdapter(contactoAdapter);


        buttonAddPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPerson();
            }
        });


        /*--- Boton en el tool bar que lleva al perfil---*/

        View btnPerfil = findViewById(R.id.account_cir);
        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AgregarPersonasActivity.this, PerfilActivity.class);
                startActivity(intent);
            }
        });

        /*--- lleva al home ---*/
        View btnHome = findViewById(R.id.rectangle_2);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AgregarPersonasActivity.this, ProductActivity.class);
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

                AgregarPersonasActivity activity = AgregarPersonasActivity.this;
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


    // Aca arranca la funcion Para Agregar un contacto
    private void addPerson() {
        String CBU = editTextCBU.getText().toString().trim();
        String nombre = nombrePers.getText().toString().trim();

        if (TextUtils.isEmpty(CBU)) {
            editTextCBU.setError("CBU/CVU es requerido");
            return;
        }
        if (TextUtils.isEmpty(nombre)) {
            nombrePers.setError("Nombre es requerido");
            return;
        }

        if (validarCampos(CBU, nombre)) {
            agregarContacto(CBU, nombre);
        }


    }


    //Verifico los campos

    private boolean validarCampos(String cbu, String nombre) {
        // Validar que el nombre solo contenga letras
        if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
            Toast.makeText(this, "El nombre solo debe contener letras", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Valido que el CBU contenga exactamente 22 numeros
        if (!cbu.matches("\\d{22}")) {
            Toast.makeText(this, "El CBU debe contener 22 dígitos", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    //Agrego el contacto


    private void agregarContacto(String cbu, String nombreContacto) {
        boolean insertado = miBase.insertarContacto(cbu, nombreContacto);
        if (insertado) {
            contactoList.add(new Contacto(cbu, nombreContacto));
            contactoAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Contacto agregado", Toast.LENGTH_SHORT).show();
            //Con esto se limpian los campos del formulario
            editTextCBU.setText("");
            nombrePers.setText("");
        } else {
            Toast.makeText(this, "Error al agregar el contacto", Toast.LENGTH_SHORT).show();
        }
    }



    // Mostrar Contactos que esten Cargados
    private List<Contacto> getAllContactos() {
        List<Contacto> contactos = new ArrayList<>();
        SQLiteDatabase db = miBase.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT CBU, nombre FROM contacto", null);

        if (cursor.moveToFirst()) {
            do {
                String cbu = cursor.getString(0);
                String nombre = cursor.getString(1);
                contactos.add(new Contacto(cbu, nombre));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactos;
    }


    //Borrar contacto

    private void eliminarContacto(Contacto contacto) {
        boolean eliminado = miBase.eliminarContacto(contacto.getCbu());
        if (eliminado) {
            contactoList.remove(contacto);
            contactoAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Contacto eliminado", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al eliminar el contacto", Toast.LENGTH_SHORT).show();
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



