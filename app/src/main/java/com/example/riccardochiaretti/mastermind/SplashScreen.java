package com.example.riccardochiaretti.mastermind;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends Activity {

    //Time to wait
    private final int SPLASH_DISPLAY_LENGTH = 3000;
    @Override
    public void onCreate(Bundle cicle) {
        super.onCreate(cicle);
        setContentView(R.layout.activity_splash_screen);

        /* New Handler to start the MainMenu
         * and close this Splash-Screen after three seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                //Start MainMenu
                Intent mainIntent = new Intent(SplashScreen.this,MainMenu.class);
                SplashScreen.this.startActivity(mainIntent);
                SplashScreen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}

