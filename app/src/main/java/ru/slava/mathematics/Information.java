package ru.slava.mathematics;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;
import com.github.ybq.android.spinkit.style.Wave;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Information extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    private TextView infoText, link;
    private AlertDialog alertDialog;
    private ProgressBar progressBar;
    private final String webLink = "https://matematicheskii-trena-0.flycricket.io/privacy.html";

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppThemeMenu);
        setContentView(R.layout.activity_information);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        infoText = (TextView) findViewById(R.id.infoText);
        link = (TextView) findViewById(R.id.link);
        bottomNavigationView.setSelectedItemId(R.id.information);
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
                        return true;
//                    case R.id.aboutApp:
//                        startActivity(new Intent(getApplicationContext(), AppInfo.class));
//                        overridePendingTransition(0, 0);
//                        finish();
//                        return true;
                }
                return false;
            }
        });

        final View viewLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.loading_dialog_layout, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(Information.this);
        progressBar = (ProgressBar) viewLayout.findViewById(R.id.spin_kit);
        Sprite circle = new Circle();
        progressBar.setIndeterminateDrawable(circle);
        builder.setView(viewLayout);
        builder.setCancelable(false);
        alertDialog = builder.create();

        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webLink));
                            startActivity(intent);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(Information.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        } finally {
                            alertDialog.dismiss();
                        }
                    }
                }, 1000);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        AlertDialog.Builder builder = new AlertDialog.Builder(Information.this);
//        builder.setTitle("Как, уже всё? \uD83D\uDE25");
//        builder.setCancelable(false);
//        builder.setMessage("Уверены, что хотите выйти?");
//        builder.setPositiveButton("да", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//                finish();
//            }
//        });
//        builder.setNegativeButton("нет", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
    }
}