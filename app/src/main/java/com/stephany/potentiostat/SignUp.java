package com.stephany.potentiostat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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

public class SignUp extends AppCompatActivity {

    private EditText edtTxtSignUpName, edtTxtSignUpEmail, edtTxtSignUpPassword, edtTxtPasswordConfirmation;
    private Button btnSignUp;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        edtTxtSignUpName = findViewById(R.id.edtTxtSignUpName);
        edtTxtSignUpEmail = findViewById(R.id.edtTxtSignUpEmail);
        edtTxtSignUpPassword = findViewById(R.id.edtTxtSignUpPassword);
        edtTxtPasswordConfirmation = findViewById(R.id.edtTxtPasswordConfirmation);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.createUserWithEmailAndPassword(edtTxtSignUpEmail.getText().toString(), edtTxtSignUpPassword.getText().toString())
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
                                                        Intent intent = new Intent(SignUp.this, Login.class);
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
            }
        });
    }
}
