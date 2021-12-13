package com.stephany.potentiostat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class NewProcedure extends AppCompatActivity {

    private Button btnCV, btnAmperometry, btnDPV, btnEIS;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_procedure);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setTitle("New Scan");

        btnCV = findViewById(R.id.btnCV);
        btnAmperometry = findViewById(R.id.btnAmperometry);
        btnDPV = findViewById(R.id.btnDPV);
        btnEIS = findViewById(R.id.btnEIS);
        mAuth = FirebaseAuth.getInstance();

        btnCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewProcedure.this, CyclicVoltammetry.class);
                startActivity(intent);
            }
        });

        btnAmperometry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewProcedure.this, Amperometry.class);
                startActivity(intent);
            }
        });

        btnDPV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewProcedure.this, DirectPulseVoltammetry.class);
                startActivity(intent);
            }
        });

        btnEIS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewProcedure.this, ImpedanceSpectroscopy.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.overflow_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.option_1:
                Intent intent = new Intent(NewProcedure.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.option_2:
                mAuth.signOut();
                Intent intent_2 = new Intent(NewProcedure.this,Login.class);
                startActivity(intent_2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}