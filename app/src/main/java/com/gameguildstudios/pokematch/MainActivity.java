package com.gameguildstudios.pokematch;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    EditText username,password1;
    Button login,register,forgot;
    ProgressBar pb;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        username=(EditText)findViewById(R.id.editTextTextPersonName2);
        password1=(EditText)findViewById(R.id.editTextTextPassword2);
        login=(Button)findViewById(R.id.loginbut);
        register=(Button)findViewById(R.id.buttonregis);
        forgot=(Button)findViewById(R.id.forgotbutton);
        pb=findViewById(R.id.progressBar);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginUserAccount(v);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent j=new Intent(getApplicationContext(), registrationpage.class);
                startActivity(j);
            }
        });
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = username.getText().toString();
                if(!email.isEmpty()){
                mAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener() {
                            @Override
                            public void onComplete(@NonNull Task task) {
                                if (task.isSuccessful()) {
                                    View view = findViewById(R.id.forgotbutton);
                                    String message = "Email to reset password has been sent";
                                    int duration = Snackbar.LENGTH_SHORT;

                                    showSnackbar(view, message, duration);
                                } else {
                                    View view = findViewById(R.id.forgotbutton);
                                    String message = "Password reset failed";
                                    int duration = Snackbar.LENGTH_SHORT;

                                    showSnackbar(view, message, duration);
                                }
                            }
                        });}
                else {
                    View view = findViewById(R.id.forgotbutton);
                    String message = "Enter a valid email";
                    int duration = Snackbar.LENGTH_SHORT;

                    showSnackbar(view, message, duration);
                }
                }

        });


    }

    private void loginUserAccount(View v)
    {

        // Take the value of two edit texts in Strings
        String email, password;
        email = username.getText().toString();
        password = password1.getText().toString();

        // validations for input email and password
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
        // signin existing user
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(
                                    @NonNull Task<AuthResult> task)
                            {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(),
                                            "Login successful!!",
                                            Toast.LENGTH_LONG)
                                            .show();




                                    // if sign-in is successful
                                    // intent to home activity

                                    Button b=(Button)findViewById(R.id.loginbut);
                                    b.setVisibility(v.INVISIBLE);
                                    pb.setVisibility(v.VISIBLE);
                                    Intent intent
                                            = new Intent(getApplicationContext(),
                                            MainActivity3.class);
                                    intent.putExtra("email",email);
                                    startActivity(intent);
                                }

                                else {

                                    // sign-in failed
                                    Toast.makeText(getApplicationContext(),
                                            "Wrong password/email",
                                            Toast.LENGTH_LONG)
                                            .show();


                                }
                            }
                        });
    }
    public void showSnackbar(View view, String message, int duration)
    {
        Snackbar.make(view, message, duration).show();
    }

}
