package com.stephany.potentiostat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnNewProcedure, btnMyProcedures, btnSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNewProcedure = findViewById(R.id.btnNewProcedure);
        btnMyProcedures = findViewById(R.id.btnMyProcedures);
        btnSettings = findViewById(R.id.btnSettings);

        btnNewProcedure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,NewProcedure.class);
                startActivity(intent);
            }
        });

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Bluetooth.class);
                startActivity(intent);
            }
        });
    }


}