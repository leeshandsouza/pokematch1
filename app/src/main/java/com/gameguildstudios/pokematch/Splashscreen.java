package com.gameguildstudios.pokematch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Splashscreen extends AppCompatActivity {

    private static int SPLASH_SCREEN_TIME_OUT=3000;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //This method is used so that your splash activity
        //can cover the entire screen.
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_splashscreen);
        //this will bind your MainActivity.class file with activity_main.
        ImageView image = (ImageView)findViewById(R.id.logo_id);
        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(), R.anim.side_slide);
        image.startAnimation(animation1);
        TextView text=(TextView)findViewById(R.id.welcome_text);
        text.startAnimation(animation1);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               String uid=FirebaseAuth.getInstance().getUid();
               if(uid==null){
                Intent i=new Intent(getApplicationContext(),
                       MainActivity.class);
                //Intent is used to switch from one activity to another.

                startActivity(i);
                //invoke the SecondActivity.

                finish();}
               else
               {
                   Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
                   intent.putExtra("email",FirebaseAuth.getInstance().getCurrentUser().getEmail().toString());
                   startActivity(intent);
                   
               }
                //the current activity will get finished.
            }
        }, SPLASH_SCREEN_TIME_OUT);
    }
}