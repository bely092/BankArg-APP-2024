package com.lalita.bankargapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;

import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.content.ClipData;
import android.content.Context;
import android.content.ClipboardManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class PerfilActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;

    Button btnEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        btnEditar = findViewById(R.id.btn_editar);

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerfilActivity.this, PersonasActivity.class);
                startActivity(intent);
            }
        });

        /*--- boton copiar CVU ---*/

        ImageButton copyText = findViewById(R.id.imageButton5);
        copyText.setOnClickListener(view -> {
            // TextView que contiene el CVU
            TextView texto = findViewById(R.id.CVU);
            String textToCopy = texto.getText().toString();

            // portapapeles
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("copiado", textToCopy);
            clipboard.setPrimaryClip(clip);

            // mensaje salio ok
            //Toast.makeText(this, "CVU copiado", Toast.LENGTH_SHORT).show();
        });



        /*--- boton copiar Alias ---*/

        ImageButton copTexto = findViewById(R.id.imageButton7);
        copTexto.setOnClickListener(view -> {
            // TextView que contiene Alias
            TextView texto = findViewById(R.id.Alias);
            String textToCopy = texto.getText().toString();

            // portapapeles
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("copiado", textToCopy);
            clipboard.setPrimaryClip(clip);

            // mensaje salio ok
            //Toast.makeText(this, "Alias copiado", Toast.LENGTH_SHORT).show();
        });


        /*--- lleva al home ---*/
        View btnHome = findViewById(R.id.rectangle_2);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PerfilActivity.this, HomeActivity.class);
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

                PerfilActivity activity = PerfilActivity.this;
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

//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
//            navigationView.setCheckedItem(R.id.nav_home);
//        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        int itemId = item.getItemId();
//
//        if (itemId == R.id.nav_home) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
//        } else if (itemId == R.id.nav_banking) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new BankingFragment()).commit();
//        } else if (itemId == R.id.nav_product) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProductFragment()).commit();
//        } else if (itemId == R.id.nav_loan) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LoanFragment()).commit();
//        } else if (itemId == R.id.nav_profile) {
//            Intent intent = new Intent(PerfilActivity.this, PerfilActivity.class);
//            startActivities(new Intent[]{intent});
//        } else if (itemId == R.id.nav_contact) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ContactFragment()).commit();
//        } else if (itemId == R.id.nav_support) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SupportFragment()).commit();
//        } else if (itemId == R.id.nav_transfer) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TransferFragment()).commit();
//        } else if (itemId == R.id.nav_logout) {
//            Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show();
//        }
//
////        switch (itemId) {
////            case R.id.nav_home:
////                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
////                break;
////            case R.id.nav_banking:
////                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new BankingFragment()).commit();
////                break;
////            case R.id.nav_product:
////                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProductFragment()).commit();
////                break;
////            case R.id.nav_loan:
////                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LoanFragment()).commit();
////                break;
////            case R.id.nav_profile:
////                Intent intent = new Intent(PerfilActivity.this, PerfilActivity.class);
////                startActivities(new Intent[]{intent});
////                break;
////            case R.id.nav_contact:
////                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ContactFragment()).commit();
////                break;
////            case R.id.nav_support:
////                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SupportFragment()).commit();
////                break;
////            case R.id.nav_transfer:
////                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TransferFragment()).commit();
////                break;
////            case R.id.nav_logout:
////                Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show();
////                break;
////        }
//        drawerLayout.closeDrawer(GravityCompat.START);
//        return true;
//    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}
