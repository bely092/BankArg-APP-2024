package com.lalita.bankargapp;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
public class TransferirActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferir);
    }
}
