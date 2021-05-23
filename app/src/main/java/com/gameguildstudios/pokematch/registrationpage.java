package com.gameguildstudios.pokematch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class registrationpage extends AppCompatActivity {
    EditText usernameinp,passwordinp,renter;
    Button register;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrationpage);
        mAuth = FirebaseAuth.getInstance();
        usernameinp=(EditText)findViewById(R.id.editTextTextEmailAddress);
        passwordinp=(EditText)findViewById(R.id.editTextTextPassword);
        renter=(EditText)findViewById(R.id.editTextTextPassword5);
        register=(Button)findViewById(R.id.buttonregister);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerNewUser();

            }
        });

    }
    private void registerNewUser()
    {


        // Take the value of two edit texts in Strings
        String email, password,rentered;
        email = usernameinp.getText().toString();
        password = passwordinp.getText().toString();
        rentered = renter.getText().toString();

        // Validations for input email and password
        if(!rentered.equals(password))
        {
            Toast.makeText(getApplicationContext(),
                    "Passwords Do not match",
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(),
                    "Please enter email!!",
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(),
                    "Please enter password!!",
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }
        mAuth
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),
                                    "Registration successful!",
                                    Toast.LENGTH_LONG)
                                    .show();

                            // hide the progress bar


                            // if the user created intent to login activity
                            Intent intent
                                    = new Intent(getApplicationContext(),
                                    MainActivity.class);
                            startActivity(intent);
                        }
                        else {

                            // Registration failed
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Registration failed!!"
                                            + " Please try again later",
                                    Toast.LENGTH_LONG)
                                    .show();

                            // hide the progress bar

                        }
                    }
                });
    }
}