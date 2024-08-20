package com.lalita.bankargapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class SupportActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;
    private Button BtnQueEs;
    private Button BtnComoSeUsa;
    private Button BtnOtrasPreguntas;
    private Button btnSolicitarTurno, btnEnviar;
    private EditText tuConsulta;
    private String fechaSeleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        /*--- Boton en el tool bar que lleva al perfil---*/

        View btnPerfil = findViewById(R.id.account_cir);
        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SupportActivity.this, PerfilActivity.class);
                startActivity(intent);
            }
        });


        /*--- lleva al home ---*/
        View btnHome = findViewById(R.id.rectangle_2);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SupportActivity.this, ProductActivity.class);
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

        /* FAQ */

        BtnQueEs = findViewById(R.id.que_es);

        BtnQueEs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertaQue = new AlertDialog.Builder(SupportActivity.this);
                alertaQue.setMessage("-Es la solucion digital y de vanguardia para todos tus tramites y gestiones bancarias \n -Fue desarrollada por un grupo de estudiantes del ISPC como proyecto integrador de los saberes adquiridos durante el cursado de la Tecnicatura Superior en Desarrollo Web y Aplicaciones Digitales.")
                        .setCancelable(false)
                        .setNegativeButton("Genial", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog titulo = alertaQue.create();
                titulo.setTitle("BankArg");
                titulo.show();
            }
        });

        /*--- Ventana emergente de aviso ---*/
        btnEnviar = findViewById(R.id.Enviar);
        tuConsulta = findViewById(R.id.tu_consulta);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {


                                             //Enviar correo
                                             Intent intent = new Intent(Intent.ACTION_SEND);
                                             intent.setType("message/rfc922");
                                             intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"contacto.bankarg@gmail.com"});
                                             intent.putExtra(Intent.EXTRA_SUBJECT, "Consulta hacia soporte");
                                             intent.putExtra(Intent.EXTRA_TEXT, tuConsulta.getText().toString().trim());

                                             try {
                                                 startActivity(Intent.createChooser(intent, "Enviar correo usando..."));
                                             } catch (android.content.ActivityNotFoundException ex) {
                                                 Toast.makeText(SupportActivity.this, "No hay aplicaciones de correo instaladas.", Toast.LENGTH_SHORT).show();
                                             }

                                             // Limpia el campo de texto
                                             tuConsulta.setText("");

                                             // Muestra la alerta con SweetAlert2
                                             AlertDialog.Builder alertaConsultaEnviada = new AlertDialog.Builder(SupportActivity.this);
                                             alertaConsultaEnviada.setMessage("Tu consulta ha sido enviada.")
                                                     .setCancelable(false)
                                                     .setNegativeButton("Gracias", new DialogInterface.OnClickListener() {
                                                         @Override
                                                         public void onClick(DialogInterface dialogInterface, int i) {
                                                             dialogInterface.cancel();
                                                         };
                                                     });
                                             AlertDialog titulo = alertaConsultaEnviada.create();
                                             titulo.setTitle("Enviado");
                                             titulo.show();


                                         }
        });




                BtnComoSeUsa = (Button) findViewById(R.id.como_se_usa);
                BtnComoSeUsa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder alertaComo = new AlertDialog.Builder(SupportActivity.this);
                        alertaComo.setMessage("-Registrarte es facil, rapido y seguro! Solo necesitas ingresar tus datos personales y estaras usando BankArg en unos instantes. \n -En BankArg creemos que: !La informacion es poder¡ Sobre todo aquella de una fuente segura y confiable.")
                                .setCancelable(false)
                                .setNegativeButton("Genial", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                });
                        AlertDialog titulo = alertaComo.create();
                        titulo.setTitle("BankArg");
                        titulo.show();
                    }
                });


                BtnOtrasPreguntas = (Button) findViewById(R.id.otras_preguntas);
                BtnOtrasPreguntas.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder alertaOtras = new AlertDialog.Builder(SupportActivity.this);
                        alertaOtras.setMessage("¿No pudimos aclarar todas tus dudas? No dudes en contactarnos, para que podamos seguir mejorando esta seccion")
                                .setCancelable(false)
                                .setNegativeButton("Gracias", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                });
                        AlertDialog titulo = alertaOtras.create();
                        titulo.setTitle("BankArg");
                        titulo.show();

                    }
                });


//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                navigationView = findViewById(R.id.nav_view);
                navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        int itemId = item.getItemId();

                        SupportActivity activity = SupportActivity.this;
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

    /*--- Ventana emergente de aviso ---*/
    //btnSolicitarTurno = findViewById(R.id.Solicitar_turno);
    //btnSolicitarTurno.setOnClickListener(new View.OnClickListener() {
    //    @Override
    //   public void onClick(View view) {
    //        Toast.makeText(SupportActivity.this, "Servicio no disponible en estos momentos", Toast.LENGTH_SHORT).show();

    //    }
    // });

    
    public void abrirCalendario(View view) {
        Calendar cal = Calendar.getInstance();
        int anioActual = cal.get(Calendar.YEAR);
        int mesActual = cal.get(Calendar.MONTH);
        int diaActual = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(SupportActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int anio, int mes, int dia) {
                // Crear un calendario con la fecha seleccionada
                Calendar fechaSeleccionadaCal = Calendar.getInstance();
                fechaSeleccionadaCal.set(anio, mes, dia);

                // Comparar la fecha seleccionada con la fecha actual
                if (fechaSeleccionadaCal.before(cal)) {
                    // Si la fecha seleccionada es anterior a la actual, mostrar advertencia
                    Toast.makeText(SupportActivity.this, "La fecha no puede ser anterior a hoy.", Toast.LENGTH_SHORT).show();
                } else {
                    // Guardar la fecha seleccionada si es válida
                    fechaSeleccionada = anio + "/" + (mes + 1) + "/" + dia;
                    //Toast.makeText(SupportActivity.this, "Fecha seleccionada: " + fechaSeleccionada, Toast.LENGTH_SHORT).show();
                }
            }
        }, anioActual, mesActual, diaActual);

        // Establecer la fecha mínima para que el usuario no pueda seleccionar fechas anteriores
        dpd.getDatePicker().setMinDate(cal.getTimeInMillis());

        dpd.show();
    }



    public void solicitarTurno(View view) {
        if (fechaSeleccionada != null) {
            // aca se sumarian las operaciones para agendar el turno
            // como guardar fecha en la base de datos o mostrar un mensaje de confirmación
            Toast.makeText(SupportActivity.this, "Turno agendado para la fecha: " + fechaSeleccionada, Toast.LENGTH_SHORT).show();

            // También puedes limpiar la fecha seleccionada después de agendar el turno si es necesario
            fechaSeleccionada = null;
        } else {
            Toast.makeText(SupportActivity.this, "Por favor, selecciona una fecha primero.", Toast.LENGTH_SHORT).show();
        }
    }




}