package com.stephany.potentiostat;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.Objects;

public class ImpedanceSpectroscopy extends AppCompatActivity {

    private Spinner waveTypeSpinner;
    private Button btnEISStartScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_impedance_spectroscopy);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        btnEISStartScan = findViewById(R.id.btnEISStartScan);

        btnEISStartScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ImpedanceSpectroscopy.this, Graphing.class);
                intent.putExtra("TYPE","ImpedanceSpectroscopy");
                startActivity(intent);
            }
        });

        waveTypeSpinner = findViewById(R.id.waveTypeSpinner);

        ArrayList<String> waveType = new ArrayList<>();
        waveType.add("sine wave");
        waveType.add("step signal");
        waveType.add("multi-sine signal");


        ArrayAdapter<String> waveTypeAdapter = new ArrayAdapter<>(
                this,android.R.layout.simple_spinner_dropdown_item, waveType);

        waveTypeSpinner.setAdapter(waveTypeAdapter);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }


    public void onBackPressed() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this, R.style.ThemeOverlay_App_MaterialAlertDialog)
                .setTitle(R.string.exit_alert_title)
                .setMessage(R.string.exit_alert_message)
                .setCancelable(false)   //allows to exit by pressing outside
                .setPositiveButton("Discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
    }
}