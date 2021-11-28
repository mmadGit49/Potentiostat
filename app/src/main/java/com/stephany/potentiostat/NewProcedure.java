package com.stephany.potentiostat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Objects;

public class NewProcedure extends AppCompatActivity {

    private Button btnCV, btnAmperometry, btnDPV, btnEIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_procedure);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        btnCV = findViewById(R.id.btnCV);
        btnAmperometry = findViewById(R.id.btnAmperometry);
        btnDPV = findViewById(R.id.btnDPV);
        btnEIS = findViewById(R.id.btnEIS);

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
}