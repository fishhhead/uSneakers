package com.example.usneakers.loadingscreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.usneakers.R;
import com.example.usneakers.SigninActivity;

public class SplashDisplay extends AppCompatActivity {

    //Define the duration of waiting time for 3s
    private final int SPLASH_DISPLAY_LENGTH = 4000;

    //when activity is firstly created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Remove the status bar for just loading activity*/
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_display);

        /*New Handler to start the activity
        * and close this Splash Display activity with the duration defined before*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent mainIntent = new Intent(SplashDisplay.this, SigninActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
