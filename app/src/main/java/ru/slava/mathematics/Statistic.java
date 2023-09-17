package ru.slava.mathematics;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.yandex.mobile.ads.common.AdError;
import com.yandex.mobile.ads.common.AdRequest;
import com.yandex.mobile.ads.common.AdRequestConfiguration;
import com.yandex.mobile.ads.common.AdRequestError;
import com.yandex.mobile.ads.common.ImpressionData;
import com.yandex.mobile.ads.rewarded.Reward;
import com.yandex.mobile.ads.rewarded.RewardedAd;
import com.yandex.mobile.ads.rewarded.RewardedAdEventListener;
import com.yandex.mobile.ads.rewarded.RewardedAdLoadListener;
import com.yandex.mobile.ads.rewarded.RewardedAdLoader;

import java.util.ArrayList;

public class Statistic extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    private float r1, nr1, r2, nr2, r3, nr3, r4, nr4, r5, nr5, r6, nr6, r7, nr7, r8, nr8, r9, nr9, r10, nr10, r11, nr11;
    private View view1, view2, view3, view4, view5, view6, view7, view8, view9, view10, view11;
    private PieChart pieChart;
    private CustomProgressDialog customProgressDialog;

    //ADS
    @Nullable
    private RewardedAd mRewardedAd = null;
    @Nullable
    private RewardedAdLoader mRewardedAdLoader = null;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppThemeMenu);
        setContentView(R.layout.activity_statistic);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.statistic);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.statistic:
                        return true;
                    case R.id.menu:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
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
        customProgressDialog = new CustomProgressDialog(Statistic.this);
//        final View viewLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.loading_dialog_layout, null);
//        AlertDialog.Builder builder = new AlertDialog.Builder(Statistic.this);
//        progressBar = (ProgressBar) viewLayout.findViewById(R.id.spin_kit);
//        Sprite circle = new Circle();
//        progressBar.setIndeterminateDrawable(circle);
//        builder.setView(viewLayout);
//        builder.setCancelable(false);
//        alertDialog = builder.create();
//        alertDialog.show();
        customProgressDialog.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setStatistic();
                customProgressDialog.dismiss();
            }
        }, 800);

        setClickers();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(Statistic.this, R.style.AlertDialogCustom));
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

    @Override
    protected void onResume() {
        super.onResume();
        loadResults();
    }

    private void setStatistic() {
        loadResults();
        FVI();

        //1
        ArrayList<BarEntry> results1 = new ArrayList<>();
        results1.add(new BarEntry(10, r1));
        results1.add(new BarEntry(15, nr1));
        BarDataSet barDataSet1 = new BarDataSet(results1, "Верные/Неверные ответы");
        barDataSet1.setColors(Color.rgb(151, 211, 98), Color.rgb(241, 66, 87));
        barDataSet1.setValueTextColor(Color.rgb(28, 28, 28));
        barDataSet1.setValueTextSize(18f);
        BarData barData1 = new BarData(barDataSet1);
        ((BarChart) view1).setFitBars(true);
        ((BarChart) view1).setDrawValueAboveBar(true);
        ((BarChart) view1).setData(barData1);
        ((BarChart) view1).getDescription().setText("");
        ((BarChart) view1).animateY(2000);
        view1.setVisibility(View.VISIBLE);

        //2
        int t2 = (int) r2;
        r2 = (float) t2;
        ArrayList<BarEntry> results2 = new ArrayList<>();
        results2.add(new BarEntry(10, r2));
        results2.add(new BarEntry(15, nr2));
        BarDataSet barDataSet2 = new BarDataSet(results2, "Верные/Неверные ответы");
        barDataSet2.setColors(Color.rgb(151, 211, 98), Color.rgb(241, 66, 87));
        barDataSet2.setValueTextColor(Color.rgb(28, 28, 28));
        barDataSet2.setValueTextSize(18f);
        BarData barData2 = new BarData(barDataSet2);
        ((BarChart) view2).setFitBars(true);
        ((BarChart) view2).setDrawValueAboveBar(true);
        ((BarChart) view2).setData(barData2);
        ((BarChart) view2).getDescription().setText("");
        ((BarChart) view2).animateY(2000);
        view2.setVisibility(View.VISIBLE);

        //3
        ArrayList<BarEntry> results3 = new ArrayList<>();
        results3.add(new BarEntry(10, r3));
        results3.add(new BarEntry(15, nr3));
        BarDataSet barDataSet3 = new BarDataSet(results3, "Верные/Неверные ответы");
        barDataSet3.setColors(Color.rgb(151, 211, 98), Color.rgb(241, 66, 87));
        barDataSet3.setValueTextColor(Color.rgb(28, 28, 28));
        barDataSet3.setValueTextSize(18f);
        BarData barData3 = new BarData(barDataSet3);
        ((BarChart) view3).setFitBars(true);
        ((BarChart) view3).setDrawValueAboveBar(true);
        ((BarChart) view3).setData(barData3);
        ((BarChart) view3).getDescription().setText("");
        ((BarChart) view3).animateY(2000);
        view3.setVisibility(View.VISIBLE);

        //4
        ArrayList<BarEntry> results4 = new ArrayList<>();
        results4.add(new BarEntry(10, r4));
        results4.add(new BarEntry(15, nr4));
        BarDataSet barDataSet4 = new BarDataSet(results4, "Верные/Неверные ответы");
        barDataSet4.setColors(Color.rgb(151, 211, 98), Color.rgb(241, 66, 87));
        barDataSet4.setValueTextColor(Color.rgb(28, 28, 28));
        barDataSet4.setValueTextSize(18f);
        BarData barData4 = new BarData(barDataSet4);
        ((BarChart) view4).setFitBars(true);
        ((BarChart) view4).setDrawValueAboveBar(true);
        ((BarChart) view4).setData(barData4);
        ((BarChart) view4).getDescription().setText("");
        ((BarChart) view4).animateY(2000);
        view4.setVisibility(View.VISIBLE);

        //5
        ArrayList<BarEntry> results5 = new ArrayList<>();
        results5.add(new BarEntry(10, r5));
        results5.add(new BarEntry(15, nr5));
        BarDataSet barDataSet5 = new BarDataSet(results5, "Верные/Неверные ответы");
        barDataSet5.setColors(Color.rgb(151, 211, 98), Color.rgb(241, 66, 87));
        barDataSet5.setValueTextColor(Color.rgb(28, 28, 28));
        barDataSet5.setValueTextSize(18f);
        BarData barData5 = new BarData(barDataSet5);
        ((BarChart) view5).setFitBars(true);
        ((BarChart) view5).setDrawValueAboveBar(true);
        ((BarChart) view5).setData(barData5);
        ((BarChart) view5).getDescription().setText("");
        ((BarChart) view5).animateY(2000);
        view5.setVisibility(View.VISIBLE);

        //6
        ArrayList<BarEntry> results6 = new ArrayList<>();
        results6.add(new BarEntry(10, r6));
        results6.add(new BarEntry(15, nr6));
        BarDataSet barDataSet6 = new BarDataSet(results6, "Верные/Неверные ответы");
        barDataSet6.setColors(Color.rgb(151, 211, 98), Color.rgb(241, 66, 87));
        barDataSet6.setValueTextColor(Color.rgb(28, 28, 28));
        barDataSet6.setValueTextSize(18f);
        BarData barData6 = new BarData(barDataSet6);
        ((BarChart) view6).setFitBars(true);
        ((BarChart) view6).setDrawValueAboveBar(true);
        ((BarChart) view6).setData(barData6);
        ((BarChart) view6).getDescription().setText("");
        ((BarChart) view6).animateY(2000);
        view6.setVisibility(View.VISIBLE);

        //7
        ArrayList<BarEntry> results7 = new ArrayList<>();
        results7.add(new BarEntry(10, r7));
        results7.add(new BarEntry(15, nr7));
        BarDataSet barDataSet7 = new BarDataSet(results7, "Верные/Неверные ответы");
        barDataSet7.setColors(Color.rgb(151, 211, 98), Color.rgb(241, 66, 87));
        barDataSet7.setValueTextColor(Color.rgb(28, 28, 28));
        barDataSet7.setValueTextSize(18f);
        BarData barData7 = new BarData(barDataSet7);
        ((BarChart) view7).setFitBars(true);
        ((BarChart) view7).setDrawValueAboveBar(true);
        ((BarChart) view7).setData(barData7);
        ((BarChart) view7).getDescription().setText("");
        ((BarChart) view7).animateY(2000);
        view7.setVisibility(View.VISIBLE);

        //8
        ArrayList<BarEntry> results8 = new ArrayList<>();
        results8.add(new BarEntry(10, r8));
        results8.add(new BarEntry(15, nr8));
        BarDataSet barDataSet8 = new BarDataSet(results8, "Верные/Неверные ответы");
        barDataSet8.setColors(Color.rgb(151, 211, 98), Color.rgb(241, 66, 87));
        barDataSet8.setValueTextColor(Color.rgb(28, 28, 28));
        barDataSet8.setValueTextSize(18f);
        BarData barData8 = new BarData(barDataSet8);
        ((BarChart) view8).setFitBars(true);
        ((BarChart) view8).setDrawValueAboveBar(true);
        ((BarChart) view8).setData(barData8);
        ((BarChart) view8).getDescription().setText("");
        ((BarChart) view8).animateY(2000);
        view8.setVisibility(View.VISIBLE);

        //9
        ArrayList<BarEntry> results9 = new ArrayList<>();
        results9.add(new BarEntry(10, r9));
        results9.add(new BarEntry(15, nr9));
        BarDataSet barDataSet9 = new BarDataSet(results9, "Верные/Неверные ответы");
        barDataSet9.setColors(Color.rgb(151, 211, 98), Color.rgb(241, 66, 87));
        barDataSet9.setValueTextColor(Color.rgb(28, 28, 28));
        barDataSet9.setValueTextSize(18f);
        BarData barData9 = new BarData(barDataSet9);
        ((BarChart) view9).setFitBars(true);
        ((BarChart) view9).setDrawValueAboveBar(true);
        ((BarChart) view9).setData(barData9);
        ((BarChart) view9).getDescription().setText("");
        ((BarChart) view9).animateY(2000);
        view9.setVisibility(View.VISIBLE);

        //10
        ArrayList<BarEntry> results10 = new ArrayList<>();
        results10.add(new BarEntry(10, r10));
        results10.add(new BarEntry(15, nr10));
        BarDataSet barDataSet10 = new BarDataSet(results10, "Верные/Неверные ответы");
        barDataSet10.setColors(Color.rgb(151, 211, 98), Color.rgb(241, 66, 87));
        barDataSet10.setValueTextColor(Color.rgb(28, 28, 28));
        barDataSet10.setValueTextSize(18f);
        BarData barData10 = new BarData(barDataSet10);
        ((BarChart) view10).setFitBars(true);
        ((BarChart) view10).setDrawValueAboveBar(true);
        ((BarChart) view10).setData(barData10);
        ((BarChart) view10).getDescription().setText("");
        ((BarChart) view10).animateY(2000);
        view10.setVisibility(View.VISIBLE);

        //11
        ArrayList<BarEntry> results11 = new ArrayList<>();
        results11.add(new BarEntry(10, r11));
        results11.add(new BarEntry(15, nr11));
        BarDataSet barDataSet11 = new BarDataSet(results11, "Верные/Неверные ответы");
        barDataSet11.setColors(Color.rgb(151, 211, 98), Color.rgb(241, 66, 87));
        barDataSet11.setValueTextColor(Color.rgb(28, 28, 28));
        barDataSet11.setValueTextSize(18f);
        BarData barData11 = new BarData(barDataSet11);
        ((BarChart) view11).setFitBars(true);
        ((BarChart) view11).setDrawValueAboveBar(true);
        ((BarChart) view11).setData(barData11);
        ((BarChart) view11).getDescription().setText("");
        ((BarChart) view11).animateY(2000);
        view11.setVisibility(View.VISIBLE);

        //SUM ALL
        ArrayList<PieEntry> sumResults = new ArrayList<>();
        float[] results = loadResults();
        sumResults.add(new PieEntry(results[0], ""));
        sumResults.add(new PieEntry(results[1], ""));
        PieDataSet pieDataSet = new PieDataSet(sumResults, "");
        pieDataSet.setSliceSpace(2f);
        pieDataSet.setDrawValues(true);
        pieDataSet.setValueLineColor(Color.rgb(28, 28, 28));
        pieDataSet.setColors(Color.rgb(151, 211, 98), Color.rgb(241, 66, 87));
        pieDataSet.setValueTextColor(Color.rgb(28, 28, 28));
        pieDataSet.setValueTextSize(18f);
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.getDescription().setText("");
        pieChart.getDescription().setTextSize(18f);
        pieChart.setCenterText("Статистика для всех классов");
        pieChart.setCenterTextSize(14f);
        pieChart.animate();
        pieChart.animateXY(3000, 3000);
        pieChart.setVisibility(View.VISIBLE);
    }

    private float[] loadResults() {
        SharedPreferences sharedPreferences = getSharedPreferences("statistic", MODE_PRIVATE);
        long a1 = sharedPreferences.getLong("1-right", 0);
        long b1 = sharedPreferences.getLong("1-not_right", 0);
        r1 = (float) a1;
        nr1 = (float) b1;

        long a2 = sharedPreferences.getLong("2-right", 0);
        long b2 = sharedPreferences.getLong("2-not_right", 0);
        r2 = (float) a2;
        nr2 = (float) b2;

        long a3 = sharedPreferences.getLong("3-right", 0);
        long b3 = sharedPreferences.getLong("3-not_right", 0);
        r3 = (float) a3;
        nr3 = (float) b3;

        long a4 = sharedPreferences.getLong("4-right", 0);
        long b4 = sharedPreferences.getLong("4-not_right", 0);
        r4 = (float) a4;
        nr4 = (float) b4;

        long a5 = sharedPreferences.getLong("5-right", 0);
        long b5 = sharedPreferences.getLong("5-not_right", 0);
        r5 = (float) a5;
        nr5 = (float) b5;

        long a6 = sharedPreferences.getLong("6-right", 0);
        long b6 = sharedPreferences.getLong("6-not_right", 0);
        r6 = (float) a6;
        nr6 = (float) b6;

        long a7 = sharedPreferences.getLong("7-right", 0);
        long b7 = sharedPreferences.getLong("7-not_right", 0);
        r7 = (float) a7;
        nr7 = (float) b7;

        long a8 = sharedPreferences.getLong("8-right", 0);
        long b8 = sharedPreferences.getLong("8-not_right", 0);
        r8 = (float) a8;
        nr8 = (float) b8;

        long a9 = sharedPreferences.getLong("9-right", 0);
        long b9 = sharedPreferences.getLong("9-not_right", 0);
        r9 = (float) a9;
        nr9 = (float) b9;

        long a10 = sharedPreferences.getLong("10-right", 0);
        long b10 = sharedPreferences.getLong("10-not_right", 0);
        r10 = (float) a10;
        nr10 = (float) b10;

        long a11 = sharedPreferences.getLong("11-right", 0);
        long b11 = sharedPreferences.getLong("11-not_right", 0);
        r11 = (float) a11;
        nr11 = (float) b11;

        float sumResultsR = r1 + r2 + r3 + r4 + r5 + r6 + r7 + r8 + r9 + r10 + r11;
        float sumResultsNR = nr1 + nr2 + nr3 + nr4 + nr5 + nr6 + nr7 + nr8 + nr9 + nr10 + nr11;
        float[] sumPie = new float[2];
        sumPie[0] = sumResultsR;
        sumPie[1] = sumResultsNR;
        return sumPie;
    }

    private void FVI() {
        view1 = findViewById(R.id.graph1);
        view1 = (BarChart) view1;
        view2 = findViewById(R.id.graph2);
        view2 = (BarChart) view2;
        view3 = findViewById(R.id.graph3);
        view3 = (BarChart) view3;
        view4 = findViewById(R.id.graph4);
        view4 = (BarChart) view4;
        view5 = findViewById(R.id.graph5);
        view5 = (BarChart) view5;
        view6 = findViewById(R.id.graph6);
        view6 = (BarChart) view6;
        view7 = findViewById(R.id.graph7);
        view7 = (BarChart) view7;
        view8 = findViewById(R.id.graph8);
        view8 = (BarChart) view8;
        view9 = findViewById(R.id.graph9);
        view9 = (BarChart) view9;
        view10 = findViewById(R.id.graph10);
        view10 = (BarChart) view10;
        view11 = findViewById(R.id.graph11);
        view11 = (BarChart) view11;
        pieChart = (PieChart) findViewById(R.id.pieChart);
    }

    private void setViewVisibility(String viewVisibility) {
        if (viewVisibility.equals("Visible")) {
            view1.setVisibility(View.VISIBLE);
            view2.setVisibility(View.VISIBLE);
            view3.setVisibility(View.VISIBLE);
            view4.setVisibility(View.VISIBLE);
            view5.setVisibility(View.VISIBLE);
            view6.setVisibility(View.VISIBLE);
            view7.setVisibility(View.VISIBLE);
            view8.setVisibility(View.VISIBLE);
            view9.setVisibility(View.VISIBLE);
            view10.setVisibility(View.VISIBLE);
            view11.setVisibility(View.VISIBLE);
            pieChart.setVisibility(View.VISIBLE);
        }
        if (viewVisibility.equals("Invisible")) {
            view1.setVisibility(View.INVISIBLE);
            view2.setVisibility(View.INVISIBLE);
            view3.setVisibility(View.INVISIBLE);
            view4.setVisibility(View.INVISIBLE);
            view5.setVisibility(View.INVISIBLE);
            view6.setVisibility(View.INVISIBLE);
            view7.setVisibility(View.INVISIBLE);
            view8.setVisibility(View.INVISIBLE);
            view9.setVisibility(View.INVISIBLE);
            view10.setVisibility(View.INVISIBLE);
            view11.setVisibility(View.INVISIBLE);
            pieChart.setVisibility(View.INVISIBLE);
        }
    }

    private void setClickers() {
        CustomProgressDialog customProgressDialog = new CustomProgressDialog(Statistic.this);
        ImageButton clear_1 = (ImageButton) findViewById(R.id.clear1);
        ImageButton clear_2 = (ImageButton) findViewById(R.id.clear2);
        ImageButton clear_3 = (ImageButton) findViewById(R.id.clear3);
        ImageButton clear_4 = (ImageButton) findViewById(R.id.clear4);
        ImageButton clear_5 = (ImageButton) findViewById(R.id.clear5);
        ImageButton clear_6 = (ImageButton) findViewById(R.id.clear6);
        ImageButton clear_7 = (ImageButton) findViewById(R.id.clear7);
        ImageButton clear_8 = (ImageButton) findViewById(R.id.clear8);
        ImageButton clear_9 = (ImageButton) findViewById(R.id.clear9);
        ImageButton clear_10 = (ImageButton) findViewById(R.id.clear10);
        ImageButton clear_11 = (ImageButton) findViewById(R.id.clear11);

        //ADS
        mRewardedAdLoader = new RewardedAdLoader(this);
        mRewardedAdLoader.setAdLoadListener(new RewardedAdLoadListener() {
            @Override
            public void onAdLoaded(@NonNull final RewardedAd rewardedAd) {
                mRewardedAd = rewardedAd;
                // The ad was loaded successfully. Now you can show loaded ad.
            }

            @Override
            public void onAdFailedToLoad(@NonNull final AdRequestError adRequestError) {
                // Ad failed to load with AdRequestError.
                // Attempting to load a new ad from the onAdFailedToLoad() method is strongly discouraged.
            }
        });
        loadRewardedAd();

        // Создание объекта таргетирования рекламы.
        final AdRequest adRequest = new AdRequest.Builder().build();

        //1
        clear_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(Statistic.this);
//                builder.setTitle("Вы уверены, что хотите очистить результаты?");
//                builder.setCancelable(false);
//                builder.setMessage("Ваши верные/неверные ответы будут обнулены.");
//                builder.setPositiveButton("да", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {

                customProgressDialog.show();

                mRewardedAdLoader = new RewardedAdLoader(Statistic.this);
                mRewardedAdLoader.setAdLoadListener(new RewardedAdLoadListener() {
                    @Override
                    public void onAdLoaded(@NonNull final RewardedAd rewardedAd) {
                        customProgressDialog.dismiss();
                        mRewardedAd = rewardedAd;
                        mRewardedAd.setAdEventListener(new RewardedAdEventListener() {
                            @Override
                            public void onAdShown() {
                                // Called when ad is shown.
                            }

                            @Override
                            public void onAdFailedToShow(@NonNull final AdError adError) {
                                // Called when an InterstitialAd failed to show.
                            }

                            @Override
                            public void onAdDismissed() {
                                // Called when ad is dismissed.
                                // Clean resources after Ad dismissed
                                if (mRewardedAd != null) {
                                    mRewardedAd.setAdEventListener(null);
                                    mRewardedAd = null;
                                }

                                // Now you can preload the next interstitial ad.

                            }

                            @Override
                            public void onAdClicked() {
                                // Called when a click is recorded for an ad.
                            }

                            @Override
                            public void onAdImpression(@Nullable final ImpressionData impressionData) {
                                // Called when an impression is recorded for an ad.
                            }

                            @Override
                            public void onRewarded(@NonNull final Reward reward) {
                                setViewVisibility("Invisible");
                                final SharedPreferences sharedPreferences = getSharedPreferences("statistic", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putLong("1-right", 0);
                                editor.putLong("1-not_right", 0);
                                editor.apply();
                                Toast.makeText(getApplicationContext(), "Ваши результаты очищены.", Toast.LENGTH_SHORT).show();
                                setStatistic();
                                setViewVisibility("Visible");
                            }
                        });
                        mRewardedAd.show(Statistic.this);
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull final AdRequestError adRequestError) {
                        customProgressDialog.dismiss();
                        // Ad failed to load with AdRequestError.
                        // Attempting to load a new ad from the onAdFailedToLoad() method is strongly discouraged.
                    }
                });
                loadRewardedAd();
//                    }
//                });
//                builder.setNegativeButton("нет", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//                AlertDialog alertDialog = builder.create();
//                alertDialog.show();
            }
        });

        //2
        clear_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(Statistic.this);
//                builder.setTitle("Вы уверены, что хотите очистить результаты?");
//                builder.setCancelable(false);
//                builder.setMessage("Ваши верные/неверные ответы будут обнулены.");
//                builder.setPositiveButton("да", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {

                customProgressDialog.show();
                mRewardedAdLoader = new RewardedAdLoader(Statistic.this);
                mRewardedAdLoader.setAdLoadListener(new RewardedAdLoadListener() {
                    @Override
                    public void onAdLoaded(@NonNull final RewardedAd rewardedAd) {
                        customProgressDialog.dismiss();
                        mRewardedAd = rewardedAd;
                        mRewardedAd.setAdEventListener(new RewardedAdEventListener() {
                            @Override
                            public void onAdShown() {
                                // Called when ad is shown.
                            }

                            @Override
                            public void onAdFailedToShow(@NonNull final AdError adError) {
                                // Called when an InterstitialAd failed to show.
                            }

                            @Override
                            public void onAdDismissed() {
                                // Called when ad is dismissed.
                                // Clean resources after Ad dismissed
                                if (mRewardedAd != null) {
                                    mRewardedAd.setAdEventListener(null);
                                    mRewardedAd = null;
                                }

                                // Now you can preload the next interstitial ad.
                            }

                            @Override
                            public void onAdClicked() {
                                // Called when a click is recorded for an ad.
                            }

                            @Override
                            public void onAdImpression(@Nullable final ImpressionData impressionData) {
                                // Called when an impression is recorded for an ad.
                            }

                            @Override
                            public void onRewarded(@NonNull final Reward reward) {
                                setViewVisibility("Invisible");
                                final SharedPreferences sharedPreferences = getSharedPreferences("statistic", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putLong("2-right", 0);
                                editor.putLong("2-not_right", 0);
                                editor.apply();
                                Toast.makeText(getApplicationContext(), "Ваши результаты очищены.", Toast.LENGTH_SHORT).show();
                                setStatistic();
                                setViewVisibility("Visible");
                            }
                        });
                        mRewardedAd.show(Statistic.this);
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull final AdRequestError adRequestError) {
                        customProgressDialog.dismiss();
                        // Ad failed to load with AdRequestError.
                        // Attempting to load a new ad from the onAdFailedToLoad() method is strongly discouraged.
                    }
                });
                loadRewardedAd();
//                    }
//                });
//                builder.setNegativeButton("нет", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//                AlertDialog alertDialog = builder.create();
//                alertDialog.show();
            }
        });

        //3
        clear_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customProgressDialog.show();
//                AlertDialog.Builder builder = new AlertDialog.Builder(Statistic.this);
//                builder.setTitle("Вы уверены, что хотите очистить результаты?");
//                builder.setCancelable(false);
//                builder.setMessage("Ваши верные/неверные ответы будут обнулены.");
//                builder.setPositiveButton("да", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {

                // Регистрация слушателя для отслеживания событий, происходящих в рекламе.
                mRewardedAdLoader = new RewardedAdLoader(Statistic.this);
                mRewardedAdLoader.setAdLoadListener(new RewardedAdLoadListener() {
                    @Override
                    public void onAdLoaded(@NonNull final RewardedAd rewardedAd) {
                        customProgressDialog.dismiss();
                        mRewardedAd = rewardedAd;
                        mRewardedAd.setAdEventListener(new RewardedAdEventListener() {
                            @Override
                            public void onAdShown() {
                                // Called when ad is shown.
                            }

                            @Override
                            public void onAdFailedToShow(@NonNull final AdError adError) {
                                // Called when an InterstitialAd failed to show.
                            }

                            @Override
                            public void onAdDismissed() {
                                // Called when ad is dismissed.
                                // Clean resources after Ad dismissed
                                if (mRewardedAd != null) {
                                    mRewardedAd.setAdEventListener(null);
                                    mRewardedAd = null;
                                }

                                // Now you can preload the next interstitial ad.
                            }

                            @Override
                            public void onAdClicked() {
                                // Called when a click is recorded for an ad.
                            }

                            @Override
                            public void onAdImpression(@Nullable final ImpressionData impressionData) {
                                // Called when an impression is recorded for an ad.
                            }

                            @Override
                            public void onRewarded(@NonNull final Reward reward) {
                                setViewVisibility("Invisible");
                                final SharedPreferences sharedPreferences = getSharedPreferences("statistic", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putLong("3-right", 0);
                                editor.putLong("3-not_right", 0);
                                editor.apply();
                                Toast.makeText(getApplicationContext(), "Ваши результаты очищены.", Toast.LENGTH_SHORT).show();
                                setStatistic();
                                setViewVisibility("Visible");
                            }
                        });
                        mRewardedAd.show(Statistic.this);
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull final AdRequestError adRequestError) {
                        customProgressDialog.dismiss();
                        // Ad failed to load with AdRequestError.
                        // Attempting to load a new ad from the onAdFailedToLoad() method is strongly discouraged.
                    }
                });
                loadRewardedAd();
//                    }
//                });
//                builder.setNegativeButton("нет", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//                AlertDialog alertDialog = builder.create();
//                alertDialog.show();
            }
        });

        //4
        clear_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customProgressDialog.show();
//                AlertDialog.Builder builder = new AlertDialog.Builder(Statistic.this);
//                builder.setTitle("Вы уверены, что хотите очистить результаты?");
//                builder.setCancelable(false);
//                builder.setMessage("Ваши верные/неверные ответы будут обнулены.");
//                builder.setPositiveButton("да", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {

                // Регистрация слушателя для отслеживания событий, происходящих в рекламе.
                mRewardedAdLoader = new RewardedAdLoader(Statistic.this);
                mRewardedAdLoader.setAdLoadListener(new RewardedAdLoadListener() {
                    @Override
                    public void onAdLoaded(@NonNull final RewardedAd rewardedAd) {
                        customProgressDialog.dismiss();
                        mRewardedAd = rewardedAd;
                        mRewardedAd.setAdEventListener(new RewardedAdEventListener() {
                            @Override
                            public void onAdShown() {
                                // Called when ad is shown.
                            }

                            @Override
                            public void onAdFailedToShow(@NonNull final AdError adError) {
                                // Called when an InterstitialAd failed to show.
                            }

                            @Override
                            public void onAdDismissed() {
                                // Called when ad is dismissed.
                                // Clean resources after Ad dismissed
                                if (mRewardedAd != null) {
                                    mRewardedAd.setAdEventListener(null);
                                    mRewardedAd = null;
                                }

                                // Now you can preload the next interstitial ad.

                            }

                            @Override
                            public void onAdClicked() {
                                // Called when a click is recorded for an ad.
                            }

                            @Override
                            public void onAdImpression(@Nullable final ImpressionData impressionData) {
                                // Called when an impression is recorded for an ad.
                            }

                            @Override
                            public void onRewarded(@NonNull final Reward reward) {
                                setViewVisibility("Invisible");
                                final SharedPreferences sharedPreferences = getSharedPreferences("statistic", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putLong("4-right", 0);
                                editor.putLong("4-not_right", 0);
                                editor.apply();
                                Toast.makeText(getApplicationContext(), "Ваши результаты очищены.", Toast.LENGTH_SHORT).show();
                                setStatistic();
                                setViewVisibility("Visible");
                            }
                        });
                        mRewardedAd.show(Statistic.this);
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull final AdRequestError adRequestError) {
                        customProgressDialog.dismiss();
                        // Ad failed to load with AdRequestError.
                        // Attempting to load a new ad from the onAdFailedToLoad() method is strongly discouraged.
                    }
                });
                loadRewardedAd();
//                    }
//                });
//                builder.setNegativeButton("нет", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//                AlertDialog alertDialog = builder.create();
//                alertDialog.show();
            }
        });

        //5
        clear_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(Statistic.this);
//                builder.setTitle("Вы уверены, что хотите очистить результаты?");
//                builder.setCancelable(false);
//                builder.setMessage("Ваши верные/неверные ответы будут обнулены.");
//                builder.setPositiveButton("да", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {

                customProgressDialog.show();
                mRewardedAdLoader = new RewardedAdLoader(Statistic.this);
                mRewardedAdLoader.setAdLoadListener(new RewardedAdLoadListener() {
                    @Override
                    public void onAdLoaded(@NonNull final RewardedAd rewardedAd) {
                        customProgressDialog.dismiss();
                        mRewardedAd = rewardedAd;
                        mRewardedAd.setAdEventListener(new RewardedAdEventListener() {
                            @Override
                            public void onAdShown() {
                                // Called when ad is shown.
                            }

                            @Override
                            public void onAdFailedToShow(@NonNull final AdError adError) {
                                // Called when an InterstitialAd failed to show.
                            }

                            @Override
                            public void onAdDismissed() {
                                // Called when ad is dismissed.
                                // Clean resources after Ad dismissed
                                if (mRewardedAd != null) {
                                    mRewardedAd.setAdEventListener(null);
                                    mRewardedAd = null;
                                }

                                // Now you can preload the next interstitial ad.

                            }

                            @Override
                            public void onAdClicked() {
                                // Called when a click is recorded for an ad.
                            }

                            @Override
                            public void onAdImpression(@Nullable final ImpressionData impressionData) {
                                // Called when an impression is recorded for an ad.
                            }

                            @Override
                            public void onRewarded(@NonNull final Reward reward) {
                                setViewVisibility("Invisible");
                                final SharedPreferences sharedPreferences = getSharedPreferences("statistic", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putLong("5-right", 0);
                                editor.putLong("5-not_right", 0);
                                editor.apply();
                                Toast.makeText(getApplicationContext(), "Ваши результаты очищены.", Toast.LENGTH_SHORT).show();
                                setStatistic();
                                setViewVisibility("Visible");
                            }
                        });
                        mRewardedAd.show(Statistic.this);
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull final AdRequestError adRequestError) {
                        customProgressDialog.dismiss();
                        // Ad failed to load with AdRequestError.
                        // Attempting to load a new ad from the onAdFailedToLoad() method is strongly discouraged.
                    }
                });
                loadRewardedAd();
//                    }
//                });
//                builder.setNegativeButton("нет", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//                AlertDialog alertDialog = builder.create();
//                alertDialog.show();
            }
        });

        //6
        clear_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(Statistic.this);
//                builder.setTitle("Вы уверены, что хотите очистить результаты?");
//                builder.setCancelable(false);
//                builder.setMessage("Ваши верные/неверные ответы будут обнулены.");
//                builder.setPositiveButton("да", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {

                customProgressDialog.show();
                mRewardedAdLoader = new RewardedAdLoader(Statistic.this);
                mRewardedAdLoader.setAdLoadListener(new RewardedAdLoadListener() {
                    @Override
                    public void onAdLoaded(@NonNull final RewardedAd rewardedAd) {
                        customProgressDialog.dismiss();
                        mRewardedAd = rewardedAd;
                        mRewardedAd.setAdEventListener(new RewardedAdEventListener() {
                            @Override
                            public void onAdShown() {
                                // Called when ad is shown.
                            }

                            @Override
                            public void onAdFailedToShow(@NonNull final AdError adError) {
                                // Called when an InterstitialAd failed to show.
                            }

                            @Override
                            public void onAdDismissed() {
                                // Called when ad is dismissed.
                                // Clean resources after Ad dismissed
                                if (mRewardedAd != null) {
                                    mRewardedAd.setAdEventListener(null);
                                    mRewardedAd = null;
                                }

                                // Now you can preload the next interstitial ad.
                            }

                            @Override
                            public void onAdClicked() {
                                // Called when a click is recorded for an ad.
                            }

                            @Override
                            public void onAdImpression(@Nullable final ImpressionData impressionData) {
                                // Called when an impression is recorded for an ad.
                            }

                            @Override
                            public void onRewarded(@NonNull final Reward reward) {
                                setViewVisibility("Invisible");
                                final SharedPreferences sharedPreferences = getSharedPreferences("statistic", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putLong("6-right", 0);
                                editor.putLong("6-not_right", 0);
                                editor.apply();
                                Toast.makeText(getApplicationContext(), "Ваши результаты очищены.", Toast.LENGTH_SHORT).show();
                                setStatistic();
                                setViewVisibility("Visible");
                            }
                        });
                        mRewardedAd.show(Statistic.this);
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull final AdRequestError adRequestError) {
                        customProgressDialog.dismiss();
                        // Ad failed to load with AdRequestError.
                        // Attempting to load a new ad from the onAdFailedToLoad() method is strongly discouraged.
                    }
                });
                loadRewardedAd();
//                    }
//                });
//                builder.setNegativeButton("нет", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//                AlertDialog alertDialog = builder.create();
//                alertDialog.show();
            }
        });

        //7
        clear_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(Statistic.this);
//                builder.setTitle("Вы уверены, что хотите очистить результаты?");
//                builder.setCancelable(false);
//                builder.setMessage("Ваши верные/неверные ответы будут обнулены.");
//                builder.setPositiveButton("да", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {

                customProgressDialog.show();
                mRewardedAdLoader = new RewardedAdLoader(Statistic.this);
                mRewardedAdLoader.setAdLoadListener(new RewardedAdLoadListener() {
                    @Override
                    public void onAdLoaded(@NonNull final RewardedAd rewardedAd) {
                        customProgressDialog.dismiss();
                        mRewardedAd = rewardedAd;
                        mRewardedAd.setAdEventListener(new RewardedAdEventListener() {
                            @Override
                            public void onAdShown() {
                                // Called when ad is shown.
                            }

                            @Override
                            public void onAdFailedToShow(@NonNull final AdError adError) {
                                // Called when an InterstitialAd failed to show.
                            }

                            @Override
                            public void onAdDismissed() {
                                // Called when ad is dismissed.
                                // Clean resources after Ad dismissed
                                if (mRewardedAd != null) {
                                    mRewardedAd.setAdEventListener(null);
                                    mRewardedAd = null;
                                }

                                // Now you can preload the next interstitial ad.
                            }

                            @Override
                            public void onAdClicked() {
                                // Called when a click is recorded for an ad.
                            }

                            @Override
                            public void onAdImpression(@Nullable final ImpressionData impressionData) {
                                // Called when an impression is recorded for an ad.
                            }

                            @Override
                            public void onRewarded(@NonNull final Reward reward) {
                                setViewVisibility("Invisible");
                                final SharedPreferences sharedPreferences = getSharedPreferences("statistic", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putLong("7-right", 0);
                                editor.putLong("7-not_right", 0);
                                editor.apply();
                                Toast.makeText(getApplicationContext(), "Ваши результаты очищены.", Toast.LENGTH_SHORT).show();
                                setStatistic();
                                setViewVisibility("Visible");
                            }
                        });
                        mRewardedAd.show(Statistic.this);
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull final AdRequestError adRequestError) {
                        customProgressDialog.dismiss();
                        // Ad failed to load with AdRequestError.
                        // Attempting to load a new ad from the onAdFailedToLoad() method is strongly discouraged.
                    }
                });
                loadRewardedAd();
//                    }
//                });
//                builder.setNegativeButton("нет", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//                AlertDialog alertDialog = builder.create();
//                alertDialog.show();
            }
        });

        //8
        clear_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(Statistic.this, "скоро!", Toast.LENGTH_SHORT).show();
//                AlertDialog.Builder builder = new AlertDialog.Builder(Statistic.this);
//                builder.setTitle("Вы уверены, что хотите очистить результаты?");
//                builder.setCancelable(false);
//                builder.setMessage("Ваши верные/неверные ответы будут обнулены.");
//                builder.setPositiveButton("да", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        setViewVisibility("Invisible");
//                        final SharedPreferences sharedPreferences = getSharedPreferences("statistic", MODE_PRIVATE);
//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.putLong("8-right", 0);
//                        editor.putLong("8-not_right", 0);
//                        editor.apply();
//                        Toast.makeText(getApplicationContext(), "Ваши результаты очищены.", Toast.LENGTH_SHORT).show();
//                        setStatistic();
//                        setViewVisibility("Visible");
//                    }
//                });
//                builder.setNegativeButton("нет", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//                AlertDialog alertDialog = builder.create();
//                alertDialog.show();

                customProgressDialog.show();
                mRewardedAdLoader = new RewardedAdLoader(Statistic.this);
                mRewardedAdLoader.setAdLoadListener(new RewardedAdLoadListener() {
                    @Override
                    public void onAdLoaded(@NonNull final RewardedAd rewardedAd) {
                        customProgressDialog.dismiss();
                        mRewardedAd = rewardedAd;
                        mRewardedAd.setAdEventListener(new RewardedAdEventListener() {
                            @Override
                            public void onAdShown() {
                                // Called when ad is shown.
                            }

                            @Override
                            public void onAdFailedToShow(@NonNull final AdError adError) {
                                // Called when an InterstitialAd failed to show.
                            }

                            @Override
                            public void onAdDismissed() {
                                // Called when ad is dismissed.
                                // Clean resources after Ad dismissed
                                if (mRewardedAd != null) {
                                    mRewardedAd.setAdEventListener(null);
                                    mRewardedAd = null;
                                }

                                // Now you can preload the next interstitial ad.
                            }

                            @Override
                            public void onAdClicked() {
                                // Called when a click is recorded for an ad.
                            }

                            @Override
                            public void onAdImpression(@Nullable final ImpressionData impressionData) {
                                // Called when an impression is recorded for an ad.
                            }

                            @Override
                            public void onRewarded(@NonNull final Reward reward) {
                                setViewVisibility("Invisible");
                                final SharedPreferences sharedPreferences = getSharedPreferences("statistic", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putLong("8-right", 0);
                                editor.putLong("8-not_right", 0);
                                editor.apply();
                                Toast.makeText(getApplicationContext(), "Ваши результаты очищены.", Toast.LENGTH_SHORT).show();
                                setStatistic();
                                setViewVisibility("Visible");
                            }
                        });
                        mRewardedAd.show(Statistic.this);
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull final AdRequestError adRequestError) {
                        customProgressDialog.dismiss();
                        // Ad failed to load with AdRequestError.
                        // Attempting to load a new ad from the onAdFailedToLoad() method is strongly discouraged.
                    }
                });
                loadRewardedAd();
            }
        });

        //9
        clear_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(Statistic.this, "скоро!", Toast.LENGTH_SHORT).show();
//                AlertDialog.Builder builder = new AlertDialog.Builder(Statistic.this);
//                builder.setTitle("Вы уверены, что хотите очистить результаты?");
//                builder.setCancelable(false);
//                builder.setMessage("Ваши верные/неверные ответы будут обнулены.");
//                builder.setPositiveButton("да", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        setViewVisibility("Invisible");
//                        final SharedPreferences sharedPreferences = getSharedPreferences("statistic", MODE_PRIVATE);
//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.putLong("9-right", 0);
//                        editor.putLong("9-not_right", 0);
//                        editor.apply();
//                        Toast.makeText(getApplicationContext(), "Ваши результаты очищены.", Toast.LENGTH_SHORT).show();
//                        setStatistic();
//                        setViewVisibility("Visible");
//                    }
//                });
//                builder.setNegativeButton("нет", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//                AlertDialog alertDialog = builder.create();
//                alertDialog.show();
            }
        });

        //10
        clear_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(Statistic.this, "скоро!", Toast.LENGTH_SHORT).show();
//                AlertDialog.Builder builder = new AlertDialog.Builder(Statistic.this);
//                builder.setTitle("Вы уверены, что хотите очистить результаты?");
//                builder.setCancelable(false);
//                builder.setMessage("Ваши верные/неверные ответы будут обнулены.");
//                builder.setPositiveButton("да", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        setViewVisibility("Invisible");
//                        final SharedPreferences sharedPreferences = getSharedPreferences("statistic", MODE_PRIVATE);
//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.putLong("10-right", 0);
//                        editor.putLong("10-not_right", 0);
//                        editor.apply();
//                        Toast.makeText(getApplicationContext(), "Ваши результаты очищены.", Toast.LENGTH_SHORT).show();
//                        setStatistic();
//                        setViewVisibility("Visible");
//                    }
//                });
//                builder.setNegativeButton("нет", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//                AlertDialog alertDialog = builder.create();
//                alertDialog.show();
            }
        });

        //11
        clear_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(Statistic.this, "скоро!", Toast.LENGTH_SHORT).show();
//                AlertDialog.Builder builder = new AlertDialog.Builder(Statistic.this);
//                builder.setTitle("Вы уверены, что хотите очистить результаты?");
//                builder.setCancelable(false);
//                builder.setMessage("Ваши верные/неверные ответы будут обнулены.");
//                builder.setPositiveButton("да", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        setViewVisibility("Invisible");
//                        final SharedPreferences sharedPreferences = getSharedPreferences("statistic", MODE_PRIVATE);
//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.putLong("11-right", 0);
//                        editor.putLong("11-not_right", 0);
//                        editor.apply();
//                        Toast.makeText(getApplicationContext(), "Ваши результаты очищены.", Toast.LENGTH_SHORT).show();
//                        setStatistic();
//                        setViewVisibility("Visible");
//                    }
//                });
//                builder.setNegativeButton("нет", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//                AlertDialog alertDialog = builder.create();
//                alertDialog.show();
            }
        });
    }

    private void loadRewardedAd() {
        if (mRewardedAdLoader != null ) {
            final AdRequestConfiguration adRequestConfiguration =
                    new AdRequestConfiguration.Builder(getResources().getString(R.string.Rewarded_id)).build();
            mRewardedAdLoader.loadAd(adRequestConfiguration);
        }
    }
}