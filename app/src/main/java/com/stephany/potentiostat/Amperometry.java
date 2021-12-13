package com.stephany.potentiostat;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Objects;

public class Amperometry extends AppCompatActivity {

    private EditText edtTxtAmpName, edtTxtAmpWait, edtTxtAmpPotential,edtTxtAmpDuration;
    private Button btnAmpStartScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amperometry);

        btnAmpStartScan = findViewById(R.id.btnAmpStartScan);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        btnAmpStartScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Amperometry.this, Graphing.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }

    //When back arrow is pressed
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