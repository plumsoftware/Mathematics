package ru.slava.mathematics;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;
import com.github.ybq.android.spinkit.style.Wave;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.yandex.mobile.ads.appopenad.AppOpenAd;
import com.yandex.mobile.ads.appopenad.AppOpenAdEventListener;
import com.yandex.mobile.ads.appopenad.AppOpenAdLoadListener;
import com.yandex.mobile.ads.appopenad.AppOpenAdLoader;
import com.yandex.mobile.ads.common.AdError;
import com.yandex.mobile.ads.common.AdRequestConfiguration;
import com.yandex.mobile.ads.common.AdRequestError;
import com.yandex.mobile.ads.common.ImpressionData;
import com.yandex.mobile.ads.common.MobileAds;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private Intent intent1, intent2, intent3, intent4, intent5, intent6, intent7, intent8;
    private GridView gridView;
    private final String[] numbers = new String[]{"1", "5", "9", "2", "6", "10", "3", "7", "11", "4", "8"};
    private Animation animation1;
//    private AlertDialog alertDialog;
//    private ProgressBar progressBar;
    private CustomProgressDialog customProgressDialog;

    private AppOpenAdLoader appOpenAdLoader = null;
    private final String AD_UNIT_ID = "R-M-1737730-4";
    private final AdRequestConfiguration adRequestConfiguration = new AdRequestConfiguration.Builder(AD_UNIT_ID).build();
    private AppOpenAd mAppOpenAd = null;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppThemeMenu);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        customProgressDialog = new CustomProgressDialog(MainActivity.this);

        customProgressDialog.show();
        MobileAds.initialize(this, () -> {

        });

        appOpenAdLoader = new AppOpenAdLoader(this);
        AppOpenAdLoadListener appOpenAdLoadListener = new AppOpenAdLoadListener() {
            @Override
            public void onAdLoaded(@NonNull final AppOpenAd appOpenAd) {
                // The ad was loaded successfully. Now you can show loaded ad.
                mAppOpenAd = appOpenAd;
                mAppOpenAd.show(MainActivity.this);
                customProgressDialog.dismiss();
            }

            @Override
            public void onAdFailedToLoad(@NonNull final AdRequestError adRequestError) {
                // Ad failed to load with AdRequestError.
                // Attempting to load a new ad from the onAdFailedToLoad() method is strongly discouraged.
                customProgressDialog.dismiss();
            }
        };
        appOpenAdLoader.setAdLoadListener(appOpenAdLoadListener);
        appOpenAdLoader.loadAd(adRequestConfiguration);

        AppOpenAdEventListener appOpenAdEventListener = new AppOpenAdEventListener() {
            @Override
            public void onAdShown() {
                // Called when ad is shown.
            }

            @Override
            public void onAdFailedToShow(@NonNull final AdError adError) {
                // Called when ad failed to show.
            }

            @Override
            public void onAdDismissed() {
                // Called when ad is dismissed.
                // Clean resources after dismiss and preload new ad.
            }

            @Override
            public void onAdClicked() {
                // Called when a click is recorded for an ad.
            }

            @Override
            public void onAdImpression(@Nullable final ImpressionData impressionData) {
                // Called when an impression is recorded for an ad.
            }
        };

        if (mAppOpenAd != null) {
            mAppOpenAd.setAdEventListener(appOpenAdEventListener);
        }

        if (mAppOpenAd != null) {
            mAppOpenAd.show(MainActivity.this);
        }

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        gridView = (GridView) findViewById(R.id.gridView);
        bottomNavigationView.setSelectedItemId(R.id.menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu:
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
//                    case R.id.aboutApp:
//                        startActivity(new Intent(getApplicationContext(), AppInfo.class));
//                        overridePendingTransition(0, 0);
//                        finish();
//                        return true;
                }
                return false;
            }
        });
        animation1 = AnimationUtils.loadAnimation(this, R.anim.click_anim);
//        final View viewLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.loading_dialog_layout, null);
//        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//        progressBar = (ProgressBar) viewLayout.findViewById(R.id.spin_kit);
//        Sprite circle = new Circle();
//        progressBar.setIndeterminateDrawable(circle);
//        builder.setView(viewLayout);
//        builder.setCancelable(false);
//        alertDialog = builder.create();
        //INTENTS
        intent1 = new Intent(getApplicationContext(), Class1.class);
        intent2 = new Intent(getApplicationContext(), Class2.class);
        intent3 = new Intent(getApplicationContext(), Class3.class);
        intent4 = new Intent(getApplicationContext(), Class4.class);
        intent5 = new Intent(getApplicationContext(), Class5.class);
        intent6 = new Intent(getApplicationContext(), Class6.class);
        intent7 = new Intent(getApplicationContext(), Class7.class);
        intent8 = new Intent(getApplicationContext(), Class8.class);
        //GRIDVIEW
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.grid, R.id.textView, numbers);
        gridView.setAdapter(arrayAdapter);
        gridView.setNumColumns(3);
        gridView.setColumnWidth(120);
        gridView.setVerticalSpacing(35);
        gridView.setStretchMode(GridView.STRETCH_SPACING_UNIFORM);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.startAnimation(animation1);
                Object object = gridView.getItemIdAtPosition(position);
                String string = object.toString();
                final int pos = Integer.parseInt(string);
                setEnabled();
                customProgressDialog.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        switch (pos) {
                            case 0:
                                startActivity(intent1);
                                overridePendingTransition(R.anim.anim_layout_top, R.anim.anim_layout_bottom);
                                break;
                            case 3:
                                startActivity(intent2);
                                overridePendingTransition(R.anim.anim_layout_top, R.anim.anim_layout_bottom);
                                break;
                            case 6:
                                startActivity(intent3);
                                overridePendingTransition(R.anim.anim_layout_top, R.anim.anim_layout_bottom);
                                break;
                            case 9:
                                startActivity(intent4);
                                overridePendingTransition(R.anim.anim_layout_top, R.anim.anim_layout_bottom);
                                break;
                            case 1:
                                startActivity(intent5);
                                overridePendingTransition(R.anim.anim_layout_top, R.anim.anim_layout_bottom);
                                break;
                            case 4:
                                startActivity(intent6);
                                overridePendingTransition(R.anim.anim_layout_top, R.anim.anim_layout_bottom);
                                break;
                            case 7:
                                startActivity(intent7);
                                overridePendingTransition(R.anim.anim_layout_top, R.anim.anim_layout_bottom);
                                break;
                            case 10:
                                customProgressDialog.dismiss();
                                //Toast.makeText(MainActivity.this, "В разработке!", Toast.LENGTH_SHORT).show();
                                startActivity(intent8);
                                overridePendingTransition(R.anim.anim_layout_top, R.anim.anim_layout_bottom);
                                break;
                            case 2:
                                customProgressDialog.dismiss();
                                Toast.makeText(MainActivity.this, "В разработке!", Toast.LENGTH_SHORT).show();
                                //startActivity(intent9);
                                break;
                            case 5:
                                customProgressDialog.dismiss();
                                Toast.makeText(MainActivity.this, "В разработке!", Toast.LENGTH_SHORT).show();
                                //startActivity(intent10);
                                break;
                            case 8:
                                customProgressDialog.dismiss();
                                Toast.makeText(MainActivity.this, "В разработке!", Toast.LENGTH_SHORT).show();
                                //startActivity(intent11);
                                break;
                        }
                        customProgressDialog.dismiss();
                    }
                }, 2000);

            }
        });
    }

    private class CountDown extends CountDownTimer {
        private CountDown(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            gridView.setEnabled(false);
        }

        @Override
        public void onFinish() {
            gridView.setEnabled(true);
        }
    }

    private void setEnabled() {
        CountDown countDown = new CountDown(100, 100);
        countDown.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//        builder.setTitle("Как, уже всё? \uD83D\uDE25");
//        builder.setCancelable(false);
//        builder.setMessage("Уверены, что хотите выйти?");
//        builder.setPositiveButton("да", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
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
