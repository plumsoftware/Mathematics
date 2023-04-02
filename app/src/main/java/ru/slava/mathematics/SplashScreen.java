package ru.slava.mathematics;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    private Intent intent;
    private final int SPLASH_DISPLAY_LENGTH = 900;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppSplashScreen);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        intent = new Intent(getApplicationContext(), MainActivity.class);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                startActivity(intent);
                SplashScreen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
