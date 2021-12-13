package com.stephany.potentiostat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.Objects;

public class SignUp extends AppCompatActivity {

    private EditText edtTxtSignUpName, edtTxtSignUpEmail, edtTxtSignUpPassword, edtTxtPasswordConfirmation;
    private Button btnSignUp;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Sign up");

        mAuth = FirebaseAuth.getInstance();

        edtTxtSignUpName = findViewById(R.id.edtTxtSignUpName);
        edtTxtSignUpEmail = findViewById(R.id.edtTxtSignUpEmail);
        edtTxtSignUpPassword = findViewById(R.id.edtTxtSignUpPassword);
        edtTxtPasswordConfirmation = findViewById(R.id.edtTxtPasswordConfirmation);
        btnSignUp = findViewById(R.id.btnSignUp);

        edtTxtSignUpName.addTextChangedListener(loginTextWatcher);
        edtTxtSignUpEmail.addTextChangedListener(loginTextWatcher);
        edtTxtSignUpPassword.addTextChangedListener(loginTextWatcher);
        edtTxtPasswordConfirmation.addTextChangedListener(loginTextWatcher);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strPassword1 = edtTxtSignUpPassword.getText().toString();
                String strPassword2 = edtTxtPasswordConfirmation.getText().toString();
                if(strPassword1.equals(strPassword2)){
                    mAuth.createUserWithEmailAndPassword(edtTxtSignUpEmail.getText().toString().trim(), edtTxtSignUpPassword.getText().toString())
                            .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("TAG", "createUserWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                                .setDisplayName(edtTxtSignUpName.getText().toString())
                                                .build();

                                        user.updateProfile(profileUpdates)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            Log.d("TAG", "User profile updated.");
                                                            Toast.makeText(SignUp.this, "Account Created Successfully.",
                                                                    Toast.LENGTH_SHORT).show();
                                                            Intent intent = new Intent(SignUp.this, MainActivity.class);
                                                            startActivity(intent);
                                                        }
                                                    }
                                                });

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w("TAG", "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(SignUp.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else{
                    Toast.makeText(SignUp.this, "Error! The passwords do not match.", Toast.LENGTH_SHORT).show();
                }

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

    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String nameInput = edtTxtSignUpName.getText().toString().trim();
            String emailInput = edtTxtSignUpEmail.getText().toString().trim();
            String passwordInput = edtTxtSignUpPassword.getText().toString().trim();
            String confirmationInput = edtTxtPasswordConfirmation.getText().toString().trim();

            btnSignUp.setEnabled(!nameInput.isEmpty() && !passwordInput.isEmpty() && !emailInput.isEmpty() && !confirmationInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
