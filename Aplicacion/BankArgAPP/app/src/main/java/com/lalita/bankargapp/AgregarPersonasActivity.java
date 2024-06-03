package com.lalita.bankargapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AgregarPersonasActivity extends AppCompatActivity {

    private EditText editTextCBU;
    private EditText editTextAlias;
    private Button buttonAddPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        editTextCBU = findViewById(R.id.editTextCBU);
        editTextAlias = findViewById(R.id.editTextAlias);
        buttonAddPerson = findViewById(R.id.buttonAddPerson);

        buttonAddPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPerson();
            }
        });
    }

    private void addPerson() {
        String cbu = editTextCBU.getText().toString().trim();
        String alias = editTextAlias.getText().toString().trim();

        if (TextUtils.isEmpty(cbu)) {
            editTextCBU.setError("CBU/CVU es requerido");
            return;
        }

        if (TextUtils.isEmpty(alias)) {
            editTextAlias.setError("Alias es requerido");
            return;
        }

        Toast.makeText(this, "Persona agregada: " + alias, Toast.LENGTH_LONG).show();
    }
}

