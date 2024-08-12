package com.lalita.bankargapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;

import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class BankingActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;

    Button btnTransferencia, btnPagos, btnPerfil, btnLoan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banking);

        /*--- Boton en el tool bar que lleva al perfil---*/

        View btnPerfil = findViewById(R.id.account_cir);

        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BankingActivity.this, PerfilActivity.class);
                startActivity(intent);
            }
        });


        Button btn_transferir = findViewById(R.id.button13);
        btn_transferir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BankingActivity.this, TransferActivity.class);
                startActivity(intent);
            }
        });

        Button btn_pagos = findViewById(R.id.button12);
        btn_pagos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BankingActivity.this, PagosActivity.class);
                startActivity(intent);
            }
        });

        Button btn_prestamos = findViewById(R.id.button14);
        btn_prestamos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BankingActivity.this, LoanActivity.class);
                startActivity(intent);
            }
        });

        /*--- lleva al home ---*/
        View btnHome = findViewById(R.id.view10);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BankingActivity.this, ProductActivity.class);
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

                BankingActivity activity = BankingActivity.this;
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