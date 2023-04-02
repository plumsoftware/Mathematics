package ru.slava.mathematics;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.Selection;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.yandex.mobile.ads.banner.AdSize;
import com.yandex.mobile.ads.banner.BannerAdEventListener;
import com.yandex.mobile.ads.banner.BannerAdView;
import com.yandex.mobile.ads.common.AdRequest;
import com.yandex.mobile.ads.common.AdRequestError;
import com.yandex.mobile.ads.common.ImpressionData;
import com.yandex.mobile.ads.common.InitializationListener;
import com.yandex.mobile.ads.common.MobileAds;
import com.yandex.mobile.ads.interstitial.InterstitialAd;
import com.yandex.mobile.ads.interstitial.InterstitialAdEventListener;

import java.util.ArrayList;
import java.util.Random;

public class Class7 extends AppCompatActivity {
    private String string, se = "";
    private int countRight = 0;
    private int countNotRight = 0;
    private Animation animation;
    private long CountRightS;
    private long CountNotRightS;
    private ArrayList<String> bugs = new ArrayList<>();
    //ADS
    private InterstitialAd mInterstitialAd;

    protected StringBuilder userAnswer = new StringBuilder();
    protected TextView answerTV, rAnswer, right, notRight, task, notation, textViewBigTask, bigAnswer;
    protected LinearLayout l1, l2;

    @SuppressLint({"SetTextI18n", "SourceLockedOrientationActivity"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppThemeClasses);
        setContentView(R.layout.classes_layout);
        try {
            getSupportActionBar().setTitle("7 Класс");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayUseLogoEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // ADS CODE START
        MobileAds.initialize(this, new InitializationListener() {
            @Override
            public void onInitializationCompleted() {

            }
        });

        // Создание Interstitial ads
        final AdRequest adRequestI = new AdRequest.Builder().build();

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.Interstitial_id));
        mInterstitialAd.loadAd(adRequestI);

        mInterstitialAd.setInterstitialAdEventListener(new InterstitialAdEventListener() {
            @Override
            public void onAdLoaded() {

            }

            @Override
            public void onAdFailedToLoad(@NonNull AdRequestError error) {

            }

            @Override
            public void onAdDismissed() {
                finish();
                overridePendingTransition(R.anim.anim_layout_enter_bottom, R.anim.anim_layout_close_top);
            }

            @Override
            public void onAdShown() {

            }

            @Override
            public void onImpression(@Nullable final ImpressionData impressionData) {

            }

            @Override
            public void onAdClicked() {

            }

            @Override
            public void onReturnedToApplication() {

            }

            @Override
            public void onLeftApplication() {

            }
        });
        //ADS CODE END

        animation = AnimationUtils.loadAnimation(this, R.anim.click_anim);
        task = (TextView) findViewById(R.id.task);
        answerTV = (TextView) findViewById(R.id.answer);
        rAnswer = (TextView) findViewById(R.id.rAnswer);
        right = (TextView) findViewById(R.id.right);
        notRight = (TextView) findViewById(R.id.notRight);
        notation = (TextView) findViewById(R.id.notation);
        textViewBigTask = (TextView) findViewById(R.id.textViewBigTask);
        bigAnswer = (TextView) findViewById(R.id.bigAnswer);
        l1 = (LinearLayout) findViewById(R.id.l1);
        l2 = (LinearLayout) findViewById(R.id.l2);

        l1.setVisibility(View.GONE);
        l2.setVisibility(View.GONE);

        SpannableString content = new SpannableString("    ");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        answerTV.setText(content);
        bigAnswer.setText(content);
        userAnswer.delete(0, userAnswer.length());
        task.setScrollBarStyle(View.SCROLLBARS_INSIDE_INSET);

        Random random = new Random();
        int m = random.nextInt(17);
        // 0 - 7 SMALL
        // 8 - 14 BIG
        // 15 - 16 SMALL
        if ((m < 8) || (m > 14)) {
            l2.setVisibility(View.VISIBLE);
            l1.setVisibility(View.GONE);
        }
        if (m > 7 && m < 15) {
            l2.setVisibility(View.GONE);
            l1.setVisibility(View.VISIBLE);
        }
        if (m == 0) {
            int a = random.nextInt(900) + 99;
            float num1 = (float) ((float) a) / 10;

            int b = random.nextInt(900) + 99;
            float num2 = (float) ((float) b) / 10;

            int c = random.nextInt(900) + 99;
            float num3 = (float) ((float) c) / 10;

            int d = (int) (((num1 + num2) + num3) * 10);
            float result = (float) ((float) d) / 10;

            string = Float.toString(result);
            task.setText(Float.toString(num1) + " + " + Float.toString(num2) + " + " + Float.toString(num3) + " = ");
        }
        if (m == 1) {
            int a = random.nextInt(900) + 99;
            float num1 = (float) ((float) a) / 10;

            int b = random.nextInt(900) + 99;
            float num2 = (float) ((float) b) / 10;

            int c = random.nextInt(900) + 99;
            float num3 = (float) ((float) c) / 10;

            int d = (int) (((num1 - num2) + num3) * 10);
            float result = (float) ((float) d) / 10;

            string = Float.toString(result);
            task.setText(Float.toString(num1) + " - " + Float.toString(num2) + " + " + Float.toString(num3) + " = ");
        }
        if (m == 2) {
            int a = random.nextInt(900) + 99;
            float num1 = (float) ((float) a) / 10;

            int b = random.nextInt(900) + 99;
            float num2 = (float) ((float) b) / 10;

            int c = random.nextInt(900) + 99;
            float num3 = (float) ((float) c) / 10;

            int d = (int) (((num1 + num2) - num3) * 10);
            float result = (float) ((float) d) / 10;

            string = Float.toString(result);
            task.setText(Float.toString(num1) + " + " + Float.toString(num2) + " - " + Float.toString(num3) + " = ");
        }
        if (m == 3) {
            int a = random.nextInt(900) + 99;
            float num1 = (float) ((float) a) / 10;

            int b = random.nextInt(900) + 99;
            float num2 = (float) ((float) b) / 10;

            int c = random.nextInt(900) + 99;
            float num3 = (float) ((float) c) / 10;

            int d = (int) (((num1 + num2) * num3) * 100);
            float result = (float) ((float) d) / 100;

            string = Float.toString(result);
            task.setText("(" + Float.toString(num1) + " + " + Float.toString(num2) + ") × " + Float.toString(num3) + " = ");
        }
        if (m == 4) {
            int a = random.nextInt(900) + 99;
            float num1 = (float) ((float) a) / 10;

            int b = random.nextInt(900) + 99;
            float num2 = (float) ((float) b) / 10;

            int c = random.nextInt(900) + 99;
            float num3 = (float) ((float) c) / 10;

            int d = (int) (((num1 * num2) + num3) * 100);
            float result = (float) ((float) d) / 100;

            string = Float.toString(result);
            task.setText(Float.toString(num1) + " × " + Float.toString(num2) + " + " + Float.toString(num3) + " = ");
        }
        if (m == 5) {
            int a = random.nextInt(900) + 99;
            float num1 = ((float) a) / 10;

            int b = random.nextInt(900) + 99;
            float num2 = ((float) b) / 10;

            int c = random.nextInt(900) + 99;
            float num3 = ((float) c) / 10;

            int d = (int) ((num1 - num2 - num3) * 10);
            float result = ((float) d) / 10;

            string = Float.toString(result);
            task.setText(Float.toString(num1) + " - " + Float.toString(num2) + " - " + Float.toString(num3) + " = ");
        }
        if (m == 6) {
            int a = random.nextInt(900) + 99;
            float num1 = ((float) a) / 10;

            int b = random.nextInt(900) + 99;
            float num2 = ((float) b) / 10;

            int c = random.nextInt(900) + 99;
            float num3 = ((float) c) / 10;

            int d = (int) (((num1 - num2) * num3) * 100);
            float result = ((float) d) / 100;

            string = Float.toString(result);
            task.setText("(" + Float.toString(num1) + " - " + Float.toString(num2) + ") × " + Float.toString(num3) + " = ");
        }
        if (m == 7) {
            int a = random.nextInt(900) + 99;
            float num1 = ((float) a) / 10;

            int b = random.nextInt(900) + 99;
            float num2 = ((float) b) / 10;

            int d = (int) ((num1 * num2) * 100);
            float num3 = ((float) d) / 100;

            int e = (int) (((num3 / num1)) * 1000);
            float result = ((float) e) / 1000;


            string = Float.toString(result);
            task.setText(Float.toString(num3) + " : " + Float.toString(num1) + " = ");

        }
        if (m == 8) {
            int num1 = (random.nextInt(9) + 1) * 10;
            textViewBigTask.setText("Трактор едет по дороге, проезжая " + Integer.toString(num1) + " метров за каждую секунду. Выразите его скорость в км/ч.");
            int result = (int) (num1 * 3.6);
            string = Integer.toString(result);
        }
        if (m == 9) {
            int num1 = (random.nextInt(40) + 10) * 10;
            int num2 = (random.nextInt(5) + 1) * 5;
            textViewBigTask.setText("Ежемесячная плата за телефон составляет " + Integer.toString(num1) + " рублей  в месяц. Сколько рублей составит ежемесячная плата за телефон, если она вырастет на " + Integer.toString(num2) + " %.");
            float result = (float) num1 + (((float) num1 * (((float) num2) / 100)));
            if (result % 2 == 0 || result % 3 == 0 || result % 5 == 0 || result % 7 == 0 || result % 9 == 0) {
                result = (int) result;
                string = Integer.toString((int) result);
            } else {
                string = Float.toString(result);
            }
        }
        if (m == 10) {
            int num1 = random.nextInt(5) + 10;
            int x = random.nextInt(5) + 10;
            int result = x + num1;
            string = Integer.toString(x);
            textViewBigTask.setText("Найдите корень уравнения \n" + "X + " + Integer.toString(num1) + " = " + Integer.toString(result));
            //x + num1 = result
        }
        if (m == 11) {
            int num1 = random.nextInt(5) + 10;
            int num2 = random.nextInt(5) + 10;
            int x = random.nextInt(5) + 10;
            //(X + num1) x num2 = result
            int result = (x + num1) * num2;
            textViewBigTask.setText("Найдите корень уравнения \n" + "(X + " + Integer.toString(num1) + ") × " + Integer.toString(num2) + " = " + Integer.toString(result));
            string = Integer.toString(x);

        }
        if (m == 12) {
            int num1 = random.nextInt(10) + 5;
            int x = random.nextInt(10) + 5;
            //(x-num1) = (x - num1)^2
            int result = (int) Math.pow((x - num1), 2);

            //x-n=
            int a = (int) Math.pow(result, 0.5);
            int b = -(int) Math.pow(result, 0.5);

            int x1 = a + num1;
            int x2 = b + num1;
            textViewBigTask.setText("Найдите корень уравнения. Если уравнение имеет несколько корней, то запишите больший из них \n" + "(X - " + Integer.toString(num1) + ")^2 = " + Integer.toString(result));

            if (x1 > x2) {
                string = Integer.toString(x1);
            } else {
                string = Integer.toString(x2);
            }

        }
        if (m == 13) {
            int k = random.nextInt(5) + 1;
            int b = random.nextInt(5) + 1;
            int x = random.nextInt(4) + 1;
            textViewBigTask.setText("График задётся функцией y = " + Integer.toString(k) + "x + " + Integer.toString(b) + ". Найдите значение функции при x = " + Integer.toString(x));
            int result = (x * k) + b;
            string = Integer.toString(result);
        }
        if (m == 14) {
            int b1 = random.nextInt(9) + 2;
            int b2 = random.nextInt(9) - 15;
            //y = kx + b
            int k1 = random.nextInt(5) + 2;
            int k2 = k1 + 1;

            float x = ((float) b2 - (float) b1) / ((float) k1 - (float) k2);

            textViewBigTask.setText("Найдите координату x пересечения графиков y = " + Integer.toString(k1) + "x + " + Integer.toString(b1) + " и y = " + Integer.toString(k2) + "x " + Integer.toString(b2));
            if (((float) b2 - (float) b1) % ((float) k1 - (float) k2) == 0) {
                string = Integer.toString((int) x);
            } else {
                string = Float.toString(x);
            }

        }
        if (m == 15) {
            int num3 = random.nextInt(7) + 1;
            int num4 = num3 + 1;

            int num5 = random.nextInt(7) + 1;
            int num6 = num5 + 1;

            int a = (num3 * num6) - (num5 * num4);
            int b = num4 * num6;

            int c = 1;

            if ((num3 * num6) - (num5 * num4) == 0) {
                c = 0;
            } else {
                for (int i = 1; i <= a; i++) {
                    if (a % i == 0 && b % i == 0) {
                        a = a / i;
                        b = b / i;
                        for (int j = 1; j <= a; j++) {
                            if (a % j == 0 && b % j == 0) {
                                a = a / j;
                                b = b / j;
                            }
                        }
                    }
                }
            }
            // a/b

            int num1 = random.nextInt(7) + 1;
            int num2 = num1 + 1;
            // num1/num2

            if (num1 == b && num2 == a) {
                string = Integer.toString(1);
            }
            if (c == 0) {
                string = Integer.toString(0);
            } else {
                int d = num1 * a;
                int e = num2 * b;
                if (d / e == 1) {
                    string = Integer.toString(1);
                }
                if (d / e == -1) {
                    string = Integer.toString(-1);
                }
                for (int i = 1; i <= d; i++) {
                    if (d % i == 0 && e % i == 0) {
                        d = d / i;
                        e = e / i;
                        string = Integer.toString(d) + "/" + Integer.toString(e);
                        for (int j = 1; j <= a; j++) {
                            if (d % j == 0 && e % j == 0) {
                                d = d / j;
                                e = e / j;
                                string = Integer.toString(d) + "/" + Integer.toString(e);
                            }
                        }
                    }
                }
            }
            task.setText(Integer.toString(num1) + "/" + Integer.toString(num2) + " × (" + Integer.toString(num3) + "/" + Integer.toString(num4) + " - " + Integer.toString(num5) + "/" + Integer.toString(num6) + ") = ");
        }
        if (m == 16) {
            int num3 = random.nextInt(7) + 1;
            int num4 = num3 + 1;

            int num5 = random.nextInt(7) + 1;
            int num6 = num5 + 1;

            int a = (num3 * num6) + (num5 * num4);
            int b = num4 * num6;

            int c = 0;

            if (a == b) {
                c = 1;
            } else {
                for (int i = 1; i <= a; i++) {
                    if (a % i == 0 && b % i == 0) {
                        a = a / i;
                        b = b / i;
                        for (int j = 1; j <= a; j++) {
                            if (a % j == 0 && b % j == 0) {
                                a = a / j;
                                b = b / j;
                            }
                        }
                    }
                }
            }

            int num1 = random.nextInt(7) + 1;
            int num2 = num1 + 1;
            // num1/num2

            if (num1 == b && num2 == a) {
                string = Integer.toString(1);
            }
            if (c == 1) {
                string = Integer.toString(num1) + "/" + Integer.toString(num2);
            } else {
                int d = num1 * a;
                int e = num2 * b;
                if (d / e == 1) {
                    string = Integer.toString(1);
                }
                for (int i = 1; i <= d; i++) {
                    if (d % i == 0 && e % i == 0) {
                        d = d / i;
                        e = e / i;
                        string = Integer.toString(d) + "/" + Integer.toString(e);
                        for (int j = 1; j <= a; j++) {
                            if (d % j == 0 && e % j == 0) {
                                d = d / j;
                                e = e / j;
                                string = Integer.toString(d) + "/" + Integer.toString(e);
                            }
                        }
                    }
                }
            }
            task.setText(Integer.toString(num1) + "/" + Integer.toString(num2) + " × (" + Integer.toString(num3) + "/" + Integer.toString(num4) + " + " + Integer.toString(num5) + "/" + Integer.toString(num6) + ") = ");
        }

        if ((m < 8) || (m > 14)) {
            String send = bugs.add(task.getText().toString()) + string;
            bugs.add(send);
        }
        if (m > 7 && m < 15) {
            String send = bugs.add(textViewBigTask.getText().toString()) + string;
            bugs.add(send);
        }
    }

    @SuppressLint("SetTextI18n")
    public void check(View view) {
        view.startAnimation(animation);
        se = "";
        if (userAnswer.toString().equals(string)) {
            countRight++;
            right.setText(Integer.toString(countRight));
        }
        if (!userAnswer.toString().equals(string)) {
            countNotRight++;
            notRight.setText(Integer.toString(countNotRight));
        }

        rAnswer.setText(string);

        l1.setVisibility(View.GONE);
        l2.setVisibility(View.GONE);

        SpannableString content = new SpannableString("    ");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        answerTV.setText(content);
        bigAnswer.setText(content);
        userAnswer.delete(0, userAnswer.length());
        task.setScrollBarStyle(View.SCROLLBARS_INSIDE_INSET);

        Random random = new Random();
        int m = random.nextInt(17);
        if ((m < 8) || (m > 14)) {
            l2.setVisibility(View.VISIBLE);
            l1.setVisibility(View.GONE);
        }
        if (m > 7 && m < 15) {
            l2.setVisibility(View.GONE);
            l1.setVisibility(View.VISIBLE);
        }
        if (m == 0) {
            int a = random.nextInt(900) + 99;
            float num1 = (float) ((float) a) / 10;

            int b = random.nextInt(900) + 99;
            float num2 = (float) ((float) b) / 10;

            int c = random.nextInt(900) + 99;
            float num3 = (float) ((float) c) / 10;

            int d = (int) (((num1 + num2) + num3) * 10);
            float result = (float) ((float) d) / 10;

            string = Float.toString(result);
            task.setText(Float.toString(num1) + " + " + Float.toString(num2) + " + " + Float.toString(num3) + " = ");
        }
        if (m == 1) {
            int a = random.nextInt(900) + 99;
            float num1 = (float) ((float) a) / 10;

            int b = random.nextInt(900) + 99;
            float num2 = (float) ((float) b) / 10;

            int c = random.nextInt(900) + 99;
            float num3 = (float) ((float) c) / 10;

            int d = (int) (((num1 - num2) + num3) * 10);
            float result = (float) ((float) d) / 10;

            string = Float.toString(result);
            task.setText(Float.toString(num1) + " - " + Float.toString(num2) + " + " + Float.toString(num3) + " = ");
        }
        if (m == 2) {
            int a = random.nextInt(900) + 99;
            float num1 = (float) ((float) a) / 10;

            int b = random.nextInt(900) + 99;
            float num2 = (float) ((float) b) / 10;

            int c = random.nextInt(900) + 99;
            float num3 = (float) ((float) c) / 10;

            int d = (int) (((num1 + num2) - num3) * 10);
            float result = (float) ((float) d) / 10;

            string = Float.toString(result);
            task.setText(Float.toString(num1) + " + " + Float.toString(num2) + " - " + Float.toString(num3) + " = ");
        }
        if (m == 3) {
            int a = random.nextInt(900) + 99;
            float num1 = (float) ((float) a) / 10;

            int b = random.nextInt(900) + 99;
            float num2 = (float) ((float) b) / 10;

            int c = random.nextInt(900) + 99;
            float num3 = (float) ((float) c) / 10;

            int d = (int) (((num1 + num2) * num3) * 100);
            float result = (float) ((float) d) / 100;

            string = Float.toString(result);
            task.setText("(" + Float.toString(num1) + " + " + Float.toString(num2) + ") × " + Float.toString(num3) + " = ");
        }
        if (m == 4) {
            int a = random.nextInt(900) + 99;
            float num1 = (float) ((float) a) / 10;

            int b = random.nextInt(900) + 99;
            float num2 = (float) ((float) b) / 10;

            int c = random.nextInt(900) + 99;
            float num3 = (float) ((float) c) / 10;

            int d = (int) (((num1 * num2) + num3) * 100);
            float result = (float) ((float) d) / 100;

            string = Float.toString(result);
            task.setText(Float.toString(num1) + " × " + Float.toString(num2) + " + " + Float.toString(num3) + " = ");
        }
        if (m == 5) {
            int a = random.nextInt(900) + 99;
            float num1 = ((float) a) / 10;

            int b = random.nextInt(900) + 99;
            float num2 = ((float) b) / 10;

            int c = random.nextInt(900) + 99;
            float num3 = ((float) c) / 10;

            int d = (int) ((num1 - num2 - num3) * 10);
            float result = ((float) d) / 10;

            string = Float.toString(result);
            task.setText(Float.toString(num1) + " - " + Float.toString(num2) + " - " + Float.toString(num3) + " = ");
        }
        if (m == 6) {
            int a = random.nextInt(900) + 99;
            float num1 = ((float) a) / 10;

            int b = random.nextInt(900) + 99;
            float num2 = ((float) b) / 10;

            int c = random.nextInt(900) + 99;
            float num3 = ((float) c) / 10;

            int d = (int) (((num1 - num2) * num3) * 100);
            float result = ((float) d) / 100;

            string = Float.toString(result);
            task.setText("(" + Float.toString(num1) + " - " + Float.toString(num2) + ") × " + Float.toString(num3) + " = ");
        }
        if (m == 7) {
            int a = random.nextInt(900) + 99;
            float num1 = ((float) a) / 10;

            int b = random.nextInt(900) + 99;
            float num2 = ((float) b) / 10;

            int d = (int) ((num1 * num2) * 100);
            float num3 = ((float) d) / 100;

            int e = (int) (((num3 / num1)) * 1000);
            float result = ((float) e) / 1000;


            string = Float.toString(result);
            task.setText(Float.toString(num3) + " : " + Float.toString(num1) + " = ");

        }
        if (m == 8) {
            int num1 = (random.nextInt(9) + 1) * 10;
            textViewBigTask.setText("Трактор едет по дороге, проезжая " + Integer.toString(num1) + " метров за каждую секунду. Выразите его скорость в км/ч.");
            int result = (int) (num1 * 3.6);
            string = Integer.toString(result);
        }
        if (m == 9) {
            int num1 = (random.nextInt(40) + 10) * 10;
            int num2 = (random.nextInt(5) + 1) * 5;
            textViewBigTask.setText("Ежемесячная плата за телефон составляет " + Integer.toString(num1) + " рублей  в месяц. Сколько рублей составит ежемесячная плата за телефон, если она вырастет на " + Integer.toString(num2) + " %.");
            float result = (float) num1 + (((float) num1 * (((float) num2) / 100)));
            if (result % 2 == 0 || result % 3 == 0 || result % 5 == 0 || result % 7 == 0 || result % 9 == 0) {
                result = (int) result;
                string = Integer.toString((int) result);
            } else {
                string = Float.toString(result);
            }
        }
        if (m == 10) {
            int num1 = random.nextInt(5) + 10;
            int x = random.nextInt(5) + 10;
            int result = x + num1;
            string = Integer.toString(x);
            textViewBigTask.setText("Найдите корень уравнения \n" + "X + " + Integer.toString(num1) + " = " + Integer.toString(result));
            //x + num1 = result
        }
        if (m == 11) {
            int num1 = random.nextInt(5) + 10;
            int num2 = random.nextInt(5) + 10;
            int x = random.nextInt(5) + 10;
            //(X + num1) x num2 = result
            int result = (x + num1) * num2;
            textViewBigTask.setText("Найдите корень уравнения \n" + "(X + " + Integer.toString(num1) + ") × " + Integer.toString(num2) + " = " + Integer.toString(result));
            string = Integer.toString(x);

        }
        if (m == 12) {
            int num1 = random.nextInt(10) + 5;
            int x = random.nextInt(10) + 5;
            //(x-num1) = (x - num1)^2
            int result = (int) Math.pow((x - num1), 2);

            //x-n=
            int a = (int) Math.pow(result, 0.5);
            int b = -(int) Math.pow(result, 0.5);

            int x1 = a + num1;
            int x2 = b + num1;
            textViewBigTask.setText("Найдите корень уравнения. Если уравнение имеет несколько корней, то запишите больший из них \n" + "(X - " + Integer.toString(num1) + ")^2 = " + Integer.toString(result));

            if (x1 > x2) {
                string = Integer.toString(x1);
            } else {
                string = Integer.toString(x2);
            }

        }
        if (m == 13) {
            int k = random.nextInt(5) + 1;
            int b = random.nextInt(5) + 1;
            int x = random.nextInt(4) + 1;
            textViewBigTask.setText("График задётся функцией y = " + Integer.toString(k) + "x + " + Integer.toString(b) + ". Найдите значение функции при x = " + Integer.toString(x));
            int result = (x * k) + b;
            string = Integer.toString(result);
        }
        if (m == 14) {
            int b1 = random.nextInt(9) + 2;
            int b2 = random.nextInt(9) - 15;
            //y = kx + b
            int k1 = random.nextInt(5) + 2;
            int k2 = k1 + 1;

            float x = ((float) b2 - (float) b1) / ((float) k1 - (float) k2);

            textViewBigTask.setText("Найдите координату x пересечения графиков y = " + Integer.toString(k1) + "x + " + Integer.toString(b1) + " и y = " + Integer.toString(k2) + "x " + Integer.toString(b2));
            if (((float) b2 - (float) b1) % ((float) k1 - (float) k2) == 0) {
                string = Integer.toString((int) x);
            } else {
                string = Float.toString(x);
            }

        }
        if (m == 15) {
            int num3 = random.nextInt(7) + 1;
            int num4 = num3 + 1;

            int num5 = random.nextInt(7) + 1;
            int num6 = num5 + 1;

            int a = (num3 * num6) - (num5 * num4);
            int b = num4 * num6;

            int c = 1;

            if ((num3 * num6) - (num5 * num4) == 0) {
                c = 0;
            } else {
                for (int i = 1; i <= a; i++) {
                    if (a % i == 0 && b % i == 0) {
                        a = a / i;
                        b = b / i;
                        for (int j = 1; j <= a; j++) {
                            if (a % j == 0 && b % j == 0) {
                                a = a / j;
                                b = b / j;
                            }
                        }
                    }
                }
            }
            // a/b

            int num1 = random.nextInt(7) + 1;
            int num2 = num1 + 1;
            // num1/num2

            if (num1 == b && num2 == a) {
                string = Integer.toString(1);
            }
            if (c == 0) {
                string = Integer.toString(0);
            } else {
                int d = num1 * a;
                int e = num2 * b;
                if (d / e == 1) {
                    string = Integer.toString(1);
                }
                if (d / e == -1) {
                    string = Integer.toString(-1);
                }
                for (int i = 1; i <= d; i++) {
                    if (d % i == 0 && e % i == 0) {
                        d = d / i;
                        e = e / i;
                        string = Integer.toString(d) + "/" + Integer.toString(e);
                        for (int j = 1; j <= a; j++) {
                            if (d % j == 0 && e % j == 0) {
                                d = d / j;
                                e = e / j;
                                string = Integer.toString(d) + "/" + Integer.toString(e);
                            }
                        }
                    }
                }
            }
            task.setText(Integer.toString(num1) + "/" + Integer.toString(num2) + " × (" + Integer.toString(num3) + "/" + Integer.toString(num4) + " - " + Integer.toString(num5) + "/" + Integer.toString(num6) + ") = ");
        }
        if (m == 16) {
            int num3 = random.nextInt(7) + 1;
            int num4 = num3 + 1;

            int num5 = random.nextInt(7) + 1;
            int num6 = num5 + 1;

            int a = (num3 * num6) + (num5 * num4);
            int b = num4 * num6;

            int c = 0;

            if (a == b) {
                c = 1;
            } else {
                for (int i = 1; i <= a; i++) {
                    if (a % i == 0 && b % i == 0) {
                        a = a / i;
                        b = b / i;
                        for (int j = 1; j <= a; j++) {
                            if (a % j == 0 && b % j == 0) {
                                a = a / j;
                                b = b / j;
                            }
                        }
                    }
                }
            }

            int num1 = random.nextInt(7) + 1;
            int num2 = num1 + 1;
            // num1/num2

            if (num1 == b && num2 == a) {
                string = Integer.toString(1);
            }
            if (c == 1) {
                string = Integer.toString(num1) + "/" + Integer.toString(num2);
            } else {
                int d = num1 * a;
                int e = num2 * b;
                if (d / e == 1) {
                    string = Integer.toString(1);
                }
                for (int i = 1; i <= d; i++) {
                    if (d % i == 0 && e % i == 0) {
                        d = d / i;
                        e = e / i;
                        string = Integer.toString(d) + "/" + Integer.toString(e);
                        for (int j = 1; j <= a; j++) {
                            if (d % j == 0 && e % j == 0) {
                                d = d / j;
                                e = e / j;
                                string = Integer.toString(d) + "/" + Integer.toString(e);
                            }
                        }
                    }
                }
            }
            task.setText(Integer.toString(num1) + "/" + Integer.toString(num2) + " × (" + Integer.toString(num3) + "/" + Integer.toString(num4) + " + " + Integer.toString(num5) + "/" + Integer.toString(num6) + ") = ");
        }

        if ((m < 8) || (m > 14)) {
            String send = bugs.add(task.getText().toString()) + string;
            bugs.add(send);
        }
        if (m > 7 && m < 15) {
            String send = bugs.add(textViewBigTask.getText().toString()) + string;
            bugs.add(send);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        CountDown countDown = new CountDown(900, 100);
        countDown.start();
    }

    private class CountDown extends CountDownTimer {
        private CountDown(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            long seconds = l / 1000;
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onFinish() {

            l1.setVisibility(View.GONE);
            l2.setVisibility(View.GONE);

            SpannableString content = new SpannableString("    ");
            content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
            answerTV.setText(content);
            bigAnswer.setText(content);
            userAnswer.delete(0, userAnswer.length());
            task.setScrollBarStyle(View.SCROLLBARS_INSIDE_INSET);

            Random random = new Random();
            int m = random.nextInt(17);
            if ((m > 0 && m < 8) || (m > 14)) {
                l2.setVisibility(View.VISIBLE);
                l1.setVisibility(View.GONE);
            }
            if (m > 7 && m < 15) {
                l2.setVisibility(View.GONE);
                l1.setVisibility(View.VISIBLE);
            }
            if (m == 0) {
                int a = random.nextInt(900) + 99;
                float num1 = (float) ((float) a) / 10;

                int b = random.nextInt(900) + 99;
                float num2 = (float) ((float) b) / 10;

                int c = random.nextInt(900) + 99;
                float num3 = (float) ((float) c) / 10;

                int d = (int) (((num1 + num2) + num3) * 10);
                float result = (float) ((float) d) / 10;

                string = Float.toString(result);
                task.setText(Float.toString(num1) + " + " + Float.toString(num2) + " + " + Float.toString(num3) + " = ");
            }
            if (m == 1) {
                int a = random.nextInt(900) + 99;
                float num1 = (float) ((float) a) / 10;

                int b = random.nextInt(900) + 99;
                float num2 = (float) ((float) b) / 10;

                int c = random.nextInt(900) + 99;
                float num3 = (float) ((float) c) / 10;

                int d = (int) (((num1 - num2) + num3) * 10);
                float result = (float) ((float) d) / 10;

                string = Float.toString(result);
                task.setText(Float.toString(num1) + " - " + Float.toString(num2) + " + " + Float.toString(num3) + " = ");
            }
            if (m == 2) {
                int a = random.nextInt(900) + 99;
                float num1 = (float) ((float) a) / 10;

                int b = random.nextInt(900) + 99;
                float num2 = (float) ((float) b) / 10;

                int c = random.nextInt(900) + 99;
                float num3 = (float) ((float) c) / 10;

                int d = (int) (((num1 + num2) - num3) * 10);
                float result = (float) ((float) d) / 10;

                string = Float.toString(result);
                task.setText(Float.toString(num1) + " + " + Float.toString(num2) + " - " + Float.toString(num3) + " = ");
            }
            if (m == 3) {
                int a = random.nextInt(900) + 99;
                float num1 = (float) ((float) a) / 10;

                int b = random.nextInt(900) + 99;
                float num2 = (float) ((float) b) / 10;

                int c = random.nextInt(900) + 99;
                float num3 = (float) ((float) c) / 10;

                int d = (int) (((num1 + num2) * num3) * 100);
                float result = (float) ((float) d) / 100;

                string = Float.toString(result);
                task.setText("(" + Float.toString(num1) + " + " + Float.toString(num2) + ") × " + Float.toString(num3) + " = ");
            }
            if (m == 4) {
                int a = random.nextInt(900) + 99;
                float num1 = (float) ((float) a) / 10;

                int b = random.nextInt(900) + 99;
                float num2 = (float) ((float) b) / 10;

                int c = random.nextInt(900) + 99;
                float num3 = (float) ((float) c) / 10;

                int d = (int) (((num1 * num2) + num3) * 100);
                float result = (float) ((float) d) / 100;

                string = Float.toString(result);
                task.setText(Float.toString(num1) + " × " + Float.toString(num2) + " + " + Float.toString(num3) + " = ");
            }
            if (m == 5) {
                int a = random.nextInt(900) + 99;
                float num1 = ((float) a) / 10;

                int b = random.nextInt(900) + 99;
                float num2 = ((float) b) / 10;

                int c = random.nextInt(900) + 99;
                float num3 = ((float) c) / 10;

                int d = (int) ((num1 - num2 - num3) * 10);
                float result = ((float) d) / 10;

                string = Float.toString(result);
                task.setText(Float.toString(num1) + " - " + Float.toString(num2) + " - " + Float.toString(num3) + " = ");
            }
            if (m == 6) {
                int a = random.nextInt(900) + 99;
                float num1 = ((float) a) / 10;

                int b = random.nextInt(900) + 99;
                float num2 = ((float) b) / 10;

                int c = random.nextInt(900) + 99;
                float num3 = ((float) c) / 10;

                int d = (int) (((num1 - num2) * num3) * 100);
                float result = ((float) d) / 100;

                string = Float.toString(result);
                task.setText("(" + Float.toString(num1) + " - " + Float.toString(num2) + ") × " + Float.toString(num3) + " = ");
            }
            if (m == 7) {
                int a = random.nextInt(900) + 99;
                float num1 = ((float) a) / 10;

                int b = random.nextInt(900) + 99;
                float num2 = ((float) b) / 10;

                int d = (int) ((num1 * num2) * 100);
                float num3 = ((float) d) / 100;

                int e = (int) (((num3 / num1)) * 1000);
                float result = ((float) e) / 1000;

                string = Float.toString(result);
                task.setText(Float.toString(num3) + " : " + Float.toString(num1) + " = ");

            }
            if (m == 8) {
                int num1 = (random.nextInt(9) + 1) * 10;
                textViewBigTask.setText("Трактор едет по дороге, проезжая " + Integer.toString(num1) + " метров за каждую секунду. Выразите его скорость в км/ч.");
                int result = (int) (num1 * 3.6);
                string = Integer.toString(result);
            }
            if (m == 9) {
                int num1 = (random.nextInt(40) + 10) * 10;
                int num2 = (random.nextInt(5) + 1) * 5;
                textViewBigTask.setText("Ежемесячная плата за телефон составляет " + Integer.toString(num1) + " рублей  в месяц. Сколько рублей составит ежемесячная плата за телефон, если она вырастет на " + Integer.toString(num2) + " %.");
                float result = (float) num1 + (((float) num1 * (((float) num2) / 100)));
                if (result % 2 == 0 || result % 3 == 0 || result % 5 == 0 || result % 7 == 0 || result % 9 == 0) {
                    result = (int) result;
                    string = Integer.toString((int) result);
                } else {
                    string = Float.toString(result);
                }
            }
            if (m == 10) {
                int num1 = random.nextInt(5) + 10;
                int x = random.nextInt(5) + 10;
                int result = x + num1;
                string = Integer.toString(x);
                textViewBigTask.setText("Найдите корень уравнения \n" + "X + " + Integer.toString(num1) + " = " + Integer.toString(result));
                //x + num1 = result
            }
            if (m == 11) {
                int num1 = random.nextInt(5) + 10;
                int num2 = random.nextInt(5) + 10;
                int x = random.nextInt(5) + 10;
                //(X + num1) x num2 = result
                int result = (x + num1) * num2;
                textViewBigTask.setText("Найдите корень уравнения \n" + "(X + " + Integer.toString(num1) + ") × " + Integer.toString(num2) + " = " + Integer.toString(result));
                string = Integer.toString(x);

            }
            if (m == 12) {
                int num1 = random.nextInt(10) + 5;
                int x = random.nextInt(10) + 5;
                //(x-num1) = (x - num1)^2
                int result = (int) Math.pow((x - num1), 2);

                //x-n=
                int a = (int) Math.pow(result, 0.5);
                int b = -(int) Math.pow(result, 0.5);

                int x1 = a + num1;
                int x2 = b + num1;
                textViewBigTask.setText("Найдите корень уравнения. Если уравнение имеет несколько корней, то запишите больший из них \n" + "(X - " + Integer.toString(num1) + ")^2 = " + Integer.toString(result));

                if (x1 > x2) {
                    string = Integer.toString(x1);
                } else {
                    string = Integer.toString(x2);
                }

            }
            if (m == 13) {
                int k = random.nextInt(5) + 1;
                int b = random.nextInt(5) + 1;
                int x = random.nextInt(4) + 1;
                textViewBigTask.setText("График задётся функцией y = " + Integer.toString(k) + "x + " + Integer.toString(b) + ". Найдите значение функции при x = " + Integer.toString(x));
                int result = (x * k) + b;
                string = Integer.toString(result);
            }
            if (m == 14) {
                int b1 = random.nextInt(9) + 2;
                int b2 = random.nextInt(9) - 15;
                //y = kx + b
                int k1 = random.nextInt(5) + 2;
                int k2 = k1 + 1;

                float x = ((float) b2 - (float) b1) / ((float) k1 - (float) k2);

                textViewBigTask.setText("Найдите координату x пересечения графиков y = " + Integer.toString(k1) + "x + " + Integer.toString(b1) + " и y = " + Integer.toString(k2) + "x " + Integer.toString(b2));
                if (((float) b2 - (float) b1) % ((float) k1 - (float) k2) == 0) {
                    string = Integer.toString((int) x);
                } else {
                    string = Float.toString(x);
                }

            }
            if (m == 15) {
                int num3 = random.nextInt(7) + 1;
                int num4 = num3 + 1;

                int num5 = random.nextInt(7) + 1;
                int num6 = num5 + 1;

                int a = (num3 * num6) - (num5 * num4);
                int b = num4 * num6;

                int c = 1;

                if ((num3 * num6) - (num5 * num4) == 0) {
                    c = 0;
                } else {
                    for (int i = 1; i <= a; i++) {
                        if (a % i == 0 && b % i == 0) {
                            a = a / i;
                            b = b / i;
                            for (int j = 1; j <= a; j++) {
                                if (a % j == 0 && b % j == 0) {
                                    a = a / j;
                                    b = b / j;
                                }
                            }
                        }
                    }
                }
                // a/b

                int num1 = random.nextInt(7) + 1;
                int num2 = num1 + 1;
                // num1/num2

                if (num1 == b && num2 == a) {
                    string = Integer.toString(1);
                }
                if (c == 0) {
                    string = Integer.toString(0);
                } else {
                    int d = num1 * a;
                    int e = num2 * b;
                    if (d / e == 1) {
                        string = Integer.toString(1);
                    }
                    if (d / e == -1) {
                        string = Integer.toString(-1);
                    }
                    for (int i = 1; i <= d; i++) {
                        if (d % i == 0 && e % i == 0) {
                            d = d / i;
                            e = e / i;
                            string = Integer.toString(d) + "/" + Integer.toString(e);
                            for (int j = 1; j <= a; j++) {
                                if (d % j == 0 && e % j == 0) {
                                    d = d / j;
                                    e = e / j;
                                    string = Integer.toString(d) + "/" + Integer.toString(e);
                                }
                            }
                        }
                    }
                }
                task.setText(Integer.toString(num1) + "/" + Integer.toString(num2) + " × (" + Integer.toString(num3) + "/" + Integer.toString(num4) + " - " + Integer.toString(num5) + "/" + Integer.toString(num6) + ") = ");
            }
            if (m == 16) {
                int num3 = random.nextInt(7) + 1;
                int num4 = num3 + 1;

                int num5 = random.nextInt(7) + 1;
                int num6 = num5 + 1;

                int a = (num3 * num6) + (num5 * num4);
                int b = num4 * num6;

                int c = 0;

                if (a == b) {
                    c = 1;
                } else {
                    for (int i = 1; i <= a; i++) {
                        if (a % i == 0 && b % i == 0) {
                            a = a / i;
                            b = b / i;
                            for (int j = 1; j <= a; j++) {
                                if (a % j == 0 && b % j == 0) {
                                    a = a / j;
                                    b = b / j;
                                }
                            }
                        }
                    }
                }

                int num1 = random.nextInt(7) + 1;
                int num2 = num1 + 1;
                // num1/num2

                if (num1 == b && num2 == a) {
                    string = Integer.toString(1);
                }
                if (c == 1) {
                    string = Integer.toString(num1) + "/" + Integer.toString(num2);
                } else {
                    int d = num1 * a;
                    int e = num2 * b;
                    if (d / e == 1) {
                        string = Integer.toString(1);
                    }
                    for (int i = 1; i <= d; i++) {
                        if (d % i == 0 && e % i == 0) {
                            d = d / i;
                            e = e / i;
                            string = Integer.toString(d) + "/" + Integer.toString(e);
                            for (int j = 1; j <= a; j++) {
                                if (d % j == 0 && e % j == 0) {
                                    d = d / j;
                                    e = e / j;
                                    string = Integer.toString(d) + "/" + Integer.toString(e);
                                }
                            }
                        }
                    }
                }
                task.setText(Integer.toString(num1) + "/" + Integer.toString(num2) + " × (" + Integer.toString(num3) + "/" + Integer.toString(num4) + " + " + Integer.toString(num5) + "/" + Integer.toString(num6) + ") = ");
            }

            if ((m < 8) || (m > 14)) {
                String send = bugs.add(task.getText().toString()) + string;
                bugs.add(send);
            }
            if (m > 7 && m < 15) {
                String send = bugs.add(textViewBigTask.getText().toString()) + string;
                bugs.add(send);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //STATISTIC CODE START (LOAD)
        SharedPreferences sharedPreferences = getSharedPreferences("statistic", MODE_PRIVATE);
        CountRightS = sharedPreferences.getLong("7-right", 0);
        CountNotRightS = sharedPreferences.getLong("7-not_right", 0);
        //--------------------
    }

    @Override
    public void onBackPressed() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            finish();
            overridePendingTransition(R.anim.anim_layout_enter_bottom, R.anim.anim_layout_close_top);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        //-------------------- (WRITE)
        SharedPreferences sharedPreferences = getSharedPreferences("statistic", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong("7-right", countRight + CountRightS);
        editor.putLong("7-not_right", countNotRight + CountNotRightS);
        editor.apply();
        //STATISTIC CODE END
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                finish();
                overridePendingTransition(R.anim.anim_layout_enter_bottom, R.anim.anim_layout_close_top);
            }
        }
        if (item.getItemId() == R.id.bugReport) {
            String message = "";
            int index = 0;
            if (bugs.size() == 1) {
                index = bugs.size() - 1;
                message = "Ошибка в примере " + bugs.get(index) + "\n" + "Комментарий: ";
            }
            if (bugs.size() > 1) {
                index = bugs.size() - 2;
                message = "Ошибка в примере " + bugs.get(index) + "\n" + "Комментарий: ";
            }
            String subject = "Отчёт об ошибке";
            String[] TO = {"plumsoftwareofficial@gmail.com"};

            /**
             Intent emailIntent = new Intent(Intent.ACTION_SEND);
             emailIntent.setData(Uri.parse("mailto:"));
             emailIntent.setType("text/plain");

             emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
             emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
             emailIntent.putExtra(Intent.EXTRA_TEXT, message);

             try {
             startActivity(Intent.createChooser(emailIntent, "Отправить с помощью."));
             finish();
             } catch (android.content.ActivityNotFoundException ex) {
             Toast.makeText(Class1.this, "Нет приложений для отправки письма с ошибкой.", Toast.LENGTH_SHORT).show();
             }
             **/
            Intent mailIntent = new Intent(Intent.ACTION_VIEW);
            Uri data = Uri.parse("mailto:?subject=" + subject + "&body=" + message + "&to=" + "plumsoftwareofficial@gmail.com");
            mailIntent.setData(data);
            startActivity(Intent.createChooser(mailIntent, "Отправить отчёт об ошибке с помощью..."));
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        return true;
    }

    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    public void onNumberClick(View view) {
        switch (view.getId()) {
            case R.id.cardOne:
            case R.id.textView3:
                userAnswer.append(1);
                break;
            case R.id.cardTwo:
            case R.id.textView4:
                userAnswer.append(2);
                break;
            case R.id.cardThree:
            case R.id.textView9:
                userAnswer.append(3);
                break;
            case R.id.cardFour:
            case R.id.textView10:
                userAnswer.append(4);
                break;
            case R.id.cardFive:
            case R.id.textView11:
                userAnswer.append(5);
                break;
            case R.id.cardSix:
            case R.id.textView12:
                userAnswer.append(6);
                break;
            case R.id.cardSeven:
            case R.id.textView13:
                userAnswer.append(7);
                break;
            case R.id.cardEight:
            case R.id.textView14:
                userAnswer.append(8);
                break;
            case R.id.cardNine:
            case R.id.textView15:
                userAnswer.append(9);
                break;
            case R.id.cardZero:
            case R.id.textView17:
                userAnswer.append(0);
                break;
            case R.id.cardA:
            case R.id.less:
                userAnswer.append("<");
                break;
            case R.id.cardB:
            case R.id.more:
                userAnswer.append(">");
                break;
            case R.id.cardC:
            case R.id.equal:
                userAnswer.append("=");
                break;
            case R.id.cardD:
            case R.id.minus:
                userAnswer.append("-");
                break;
            case R.id.cardE:
            case R.id.fraction:
                userAnswer.append("/");
                break;
            case R.id.cardF:
            case R.id.dot:
                userAnswer.append(".");
                break;
            case R.id.cardBackspace:
            case R.id.imageView7:
                if (userAnswer.length() != 0)
                    userAnswer.deleteCharAt(userAnswer.length() - 1);
                break;
        }

        SpannableString content = new SpannableString(userAnswer.toString());
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        answerTV.setText(content);
        bigAnswer.setText(content);
    }
}
