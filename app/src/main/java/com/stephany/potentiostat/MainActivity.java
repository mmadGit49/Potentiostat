package com.stephany.potentiostat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private Button btnNewProcedure, btnMyProcedures;
    private FirebaseAuth mAuth;
    private TextView txtUserWelcome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Home");

        mAuth = FirebaseAuth.getInstance();

        btnNewProcedure = findViewById(R.id.btnNewProcedure);
        btnMyProcedures = findViewById(R.id.btnMyProcedures);

        btnNewProcedure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,NewProcedure.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null){
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
        }else{
            txtUserWelcome = findViewById(R.id.txtWelcome);
            txtUserWelcome.setText("Hello "+ currentUser.getDisplayName() + "!");
        }
    }

    //Closes the App
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    //Creates menu on the action bar
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
                break;
            case R.id.option_2:
                mAuth.signOut();
                Intent intent = new Intent(MainActivity.this,Login.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}