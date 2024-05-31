package com.lalita.bankargapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;

import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.lalita.bankargapp.Clases.ExpandableListAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class SupportActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;
    private ExpandableListView expLV;
    private ExpandableListAdapter adapter;
    private ArrayList<String> listCategorias;
    private Map<String, ArrayList<String>> mapChild;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);
        /*---------------------Hooks------------------------*/
        drawerLayout=findViewById(R.id.drawer_layout);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,
                R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        /*---------------------Acordeon------------------------*/
        expLV = (ExpandableListView) findViewById(R.id.expLV);
        listCategorias = new ArrayList<>();
        mapChild = new HashMap<>();

        cargarDatos();

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                SupportActivity activity = SupportActivity.this;
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


    public void abrirCalendario(View view) {
        Calendar cal = Calendar.getInstance();
        int anio = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(SupportActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                String fecha = i + "/" + i1 + "/" + i2;

            }
        }, anio, mes, dia);
        dpd.show();
    }

    private void cargarDatos(){

        ArrayList<String> listQueEs = new ArrayList<>();
        ArrayList<String> listComoSeUsa = new ArrayList<>();
        ArrayList<String> listOtrasPreguntas = new ArrayList<>();

        listCategorias.add("¿Que es BankArg?");
        listCategorias.add("¿Como se usa BankArg?");
        listCategorias.add("Otras Preguntas");

        listQueEs.add("BankArg es la solucion digital y de vanguardia para todos tus tramites y gestiones bancarias.");
        listQueEs.add("BankArg fue desarrollada por un grupo de estudiantes del ISPC... Aprende mas sobre nosotros aqui.");

        listComoSeUsa.add("Registrarte es facil, rapido y seguro...");
        listComoSeUsa.add("En BankArg creemos que: !La informacion es poder¡...");

        listOtrasPreguntas.add("¿No pudimos aclarar todas tus dudas? No dudes en contactarnos...");

        mapChild.put(listCategorias.get(0), listQueEs);
        mapChild.put(listCategorias.get(1), listComoSeUsa);
        mapChild.put(listCategorias.get(2), listOtrasPreguntas);

        adapter = new ExpandableListAdapter(listCategorias, mapChild, this);
        expLV.setAdapter(adapter);
    }
}