package com.stephany.potentiostat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;

public class TestCircuit extends AppCompatActivity {

    private EditText edtTxtTestName, edtTxtTestWait, edtTxtTestScanRate, edtTxtTestDuration;
    private Button btnTestStartScan;
    public static final String PARAMETERS = "DURATION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_circuit);

        edtTxtTestName = findViewById(R.id.edtTxtTestName);
        edtTxtTestWait = findViewById(R.id.edtTxtTestWait);
        edtTxtTestScanRate= findViewById(R.id.edtTxtTestScanRate);
        edtTxtTestDuration = findViewById(R.id.edtTxtTestDuration);
        btnTestStartScan=findViewById(R.id.btnTestStartScan);

        btnTestStartScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),Bluetooth.class);
                intent.putExtra(PARAMETERS, edtTxtTestDuration.getText().toString());
                startActivity(intent);
            }
        });
    }
}
