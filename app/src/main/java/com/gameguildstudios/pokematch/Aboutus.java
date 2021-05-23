package com.gameguildstudios.pokematch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Aboutus extends AppCompatActivity {
    Toolbar toolbar;
    FloatingActionButton call,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);

        /*toolbar = (Toolbar)findViewById(R.id.toolbarsms);
        toolbar.setTitle("About us");*/
        ImageView image = (ImageView)findViewById(R.id.logo_aboutus);
        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        image.startAnimation(animation1);
        TextView text=(TextView)findViewById(R.id.textView7);
        text.startAnimation(animation1);
        text=(TextView)findViewById(R.id.textView8);
        text.startAnimation(animation1);
        String number="9019533135";
        call=findViewById(R.id.CallUs);
        email=findViewById(R.id.emailbutton);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+number));//change the number
                if (ContextCompat.checkSelfPermission(Aboutus.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Aboutus.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                }
                else
                {
                    startActivity(callIntent);
                }

            }
        });
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String to="leeshandsouza@gmail.com";
                String subject="feedback- Pokematch user";
                String message="";


                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
                email.putExtra(Intent.EXTRA_SUBJECT, subject);
                email.putExtra(Intent.EXTRA_TEXT, message);

                //need this to prompts email client only
                email.setType("message/rfc822");

                startActivity(Intent.createChooser(email, "Choose an Email client :"));
            }
        });

    }
}