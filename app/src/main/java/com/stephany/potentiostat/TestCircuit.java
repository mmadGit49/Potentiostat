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
    public static final String PARAMETERS = "PROCEDURE_PARAMS";
    private ProcedureParams procedureParams;

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

                String name = edtTxtTestName.getText().toString();
                int wait = Integer.parseInt(edtTxtTestWait.getText().toString());
                int duration = Integer.parseInt(edtTxtTestDuration.getText().toString());
                int scanRate = Integer.parseInt(edtTxtTestScanRate.getText().toString());

                procedureParams = new ProcedureParams(name, wait, duration, scanRate);

                Intent intent = new Intent(getApplicationContext(),Bluetooth.class);
                intent.putExtra(PARAMETERS, procedureParams);
                startActivity(intent);
            }
        });
    }

}
