package ru.slava.mathematics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AppInfo extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    private AlertDialog alertDialog;
    private final String webLink = "https://play.google.com/store/apps/details?id=ru.slava.mathematics";

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppThemeMenu);
        setContentView(R.layout.activity_app_info);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        //bottomNavigationView.setSelectedItemId(R.id.aboutApp);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;
                    case R.id.statistic:
                        startActivity(new Intent(getApplicationContext(), Statistic.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;
                    case R.id.information:
                        startActivity(new Intent(getApplicationContext(), Information.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AppInfo.this);
        builder.setTitle("Как, уже всё? \uD83D\uDE25");
        builder.setCancelable(false);
        builder.setMessage("Уверены, что хотите выйти?");
        builder.setPositiveButton("да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });
        builder.setNegativeButton("нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}