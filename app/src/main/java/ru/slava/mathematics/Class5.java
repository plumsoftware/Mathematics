package ru.slava.mathematics;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.yandex.mobile.ads.common.AdError;
import com.yandex.mobile.ads.common.AdRequestConfiguration;
import com.yandex.mobile.ads.common.AdRequestError;
import com.yandex.mobile.ads.common.ImpressionData;
import com.yandex.mobile.ads.common.InitializationListener;
import com.yandex.mobile.ads.common.MobileAds;
import com.yandex.mobile.ads.interstitial.InterstitialAd;
import com.yandex.mobile.ads.interstitial.InterstitialAdEventListener;
import com.yandex.mobile.ads.interstitial.InterstitialAdLoadListener;
import com.yandex.mobile.ads.interstitial.InterstitialAdLoader;

import java.util.ArrayList;
import java.util.Random;

public class Class5 extends AppCompatActivity {
    private String string, se = "";
    private int countRight = 0;
    private int countNotRight = 0;
    private Animation animation;
    private long CountRightS;
    private long CountNotRightS;
    private ArrayList<String> bugs = new ArrayList<>();
    //ADS
    @Nullable
    private InterstitialAd mInterstitialAd = null;
    @Nullable
    private InterstitialAdLoader mInterstitialAdLoader = null;

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
            getSupportActionBar().setTitle("5 Класс");
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
        mInterstitialAdLoader = new InterstitialAdLoader(this);
        mInterstitialAdLoader.setAdLoadListener(new InterstitialAdLoadListener() {
            @Override
            public void onAdLoaded(@NonNull final InterstitialAd interstitialAd) {
                mInterstitialAd = interstitialAd;
                mInterstitialAd.setAdEventListener(new InterstitialAdEventListener() {
                    @Override
                    public void onAdShown() {

                    }

                    @Override
                    public void onAdFailedToShow(@NonNull AdError adError) {

                    }

                    @Override
                    public void onAdDismissed() {
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(new Intent(Class5.this, MainActivity.class));
                    }

                    @Override
                    public void onAdClicked() {
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(new Intent(Class5.this, MainActivity.class));
                    }

                    @Override
                    public void onAdImpression(@Nullable ImpressionData impressionData) {

                    }
                });
                mInterstitialAd.show(Class5.this);
            }

            @Override
            public void onAdFailedToLoad(@NonNull final AdRequestError adRequestError) {
                finish();
                overridePendingTransition(0, 0);
                startActivity(new Intent(Class5.this, MainActivity.class));
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
        String signsTask = "";

        Random random = new Random();
        int m = random.nextInt(13);
        if (m == 3) {
            getSupportActionBar().setSubtitle(" >  , <  , = ");
            l2.setVisibility(View.VISIBLE);
            l1.setVisibility(View.GONE);
        }
        if (m != 3) {
            getSupportActionBar().setSubtitle(null);
            notation.setText(null);
            l2.setVisibility(View.VISIBLE);
            l1.setVisibility(View.GONE);
        }
        if (m < 5 || m == 8) {
            l2.setVisibility(View.VISIBLE);
            l1.setVisibility(View.GONE);
        }
        if (m > 4 && m != 8) {
            l2.setVisibility(View.GONE);
            l1.setVisibility(View.VISIBLE);
        }
        if (m == 0) {
            int i1 = random.nextInt(9) + 1;
            float i2 = (float) i1;
            float a = random.nextFloat() + i2;
            float a1 = a * 100;
            int a2 = Math.round(a1);
            float a3 = (float) a2;
            float num1 = a3 / 100;
            int y1 = random.nextInt(9) + 1;
            float y2 = (float) y1;
            float b = random.nextFloat() + y2;
            float b1 = b * 100;
            int b2 = Math.round(b1);
            float b3 = (float) b2;
            float num2 = b3 / 100;
            float sum = num1 + num2;
            float s1 = sum * 100;
            int s2 = Math.round(s1);
            float s3 = (float) s2;
            float s4 = s3 / 100;
            String stringSumTask1 = Float.toString(num1) + " + " + Float.toString(num2) + " = ";
            string = Float.toString(s4);
            task.setText(stringSumTask1);
        }
        if (m == 1) {
            int i1 = random.nextInt(9) + 1;
            float i2 = (float) i1;
            float a = random.nextFloat() + i2;
            float a1 = a * 100;
            int a2 = Math.round(a1);
            float a3 = (float) a2;
            float num1 = a3 / 100;
            int y1 = random.nextInt(9) + 1;
            float y2 = (float) y1;
            float b = random.nextFloat() + y2;
            float b1 = b * 100;
            int b2 = Math.round(b1);
            float b3 = (float) b2;
            float num2 = b3 / 100;
            float sum = num1 + num2;
            float s1 = sum * 100;
            int s2 = Math.round(s1);
            float s3 = (float) s2;
            float s4 = s3 / 100;
            float sub = s4 - num2;
            float sb1 = sub * 100;
            int sb2 = Math.round(sb1);
            float sb3 = (float) sb2;
            float sb4 = sb3 / 100;
            String stringSubTask1 = Float.toString(s4) + " - " + Float.toString(num2) + " = ";
            string = Float.toString(sb4);
            task.setText(stringSubTask1);
        }
        if (m == 2) {
            int i1 = random.nextInt(9) + 1;
            float i2 = (float) i1;
            float a = random.nextFloat() + i2;
            float a1 = a * 10;
            int a2 = Math.round(a1);
            float a3 = (float) a2;
            float num1 = a3 / 10;
            int y1 = random.nextInt(9) + 1;
            float y2 = (float) y1;
            float b = random.nextFloat() + y2;
            float b1 = b * 10;
            int b2 = Math.round(b1);
            float b3 = (float) b2;
            float num2 = b3 / 10;
            float mul = num1 * num2;
            float m1 = mul * 100;
            int m2 = Math.round(m1);
            float m3 = (float) m2;
            float m4 = m3 / 100;
            String stringSubTask1 = Float.toString(num1) + " × " + Float.toString(num2) + " = ";
            string = Float.toString(m4);
            task.setText(stringSubTask1);
        }
        if (m == 3) {
            int i1 = random.nextInt(9) + 1;
            float i2 = (float) i1;
            float a = random.nextFloat() + i2;
            float a1 = a * 100;
            int a2 = Math.round(a1);
            float a3 = (float) a2;
            float num1 = a3 / 100;
            int y1 = random.nextInt(9) + 1;
            float y2 = (float) y1;
            float b = random.nextFloat() + y2;
            float b1 = b * 100;
            int b2 = Math.round(b1);
            float b3 = (float) b2;
            float num2 = b3 / 100;
            if (num1 > num2) {
                string = ">";
            }
            if (num1 < num2) {
                string = "<";
            }
            if (num1 == num2) {
                string = "=";
            }
            task.setText(Float.toString(num1));
            notation.setText(Float.toString(num2));
            signsTask = Float.toString(num1) + " " + string + " " + Float.toString(num2);
        }
        if (m == 4) {
            int num1 = random.nextInt(7) + 3;
            int num2 = random.nextInt(num1) + 3;
            int num3 = random.nextInt(num1 + num2) + num1 + num2;
            int sum = num1 + num2;
            String stringOS = Integer.toString(num1) + "/" + Integer.toString(num3) + " + " + Integer.toString(num2) + "/" + Integer.toString(num3) + " = ";
            task.setText(stringOS);
            for (int i = 1; i <= num3; i++) {
                if (sum % i == 0 && num3 % i == 0) {
                    sum = sum / i;
                    num3 = num3 / i;
                    string = Integer.toString(sum) + "/" + Integer.toString(num3);
                    for (int j = 1; j <= num3; j++) {
                        if (sum % j == 0 && num3 % j == 0) {
                            sum = sum / j;
                            num3 = num3 / j;
                            string = Integer.toString(sum) + "/" + Integer.toString(num3);
                        }
                    }
                }
            }
        }
        if (m == 5) {
            int num1 = random.nextInt(8) + 3;
            int num2 = random.nextInt(num1) + num1 + 1;
            textViewBigTask.setText("Представьте число " + Integer.toString(num1) + " в виде дроби со знаменателем " + Integer.toString(num2) + ".");
            //СОКРАЩЕНИЕ ДРОБИ ЗДЕСЬ
            for (int i = 1; i <= num2; i++) {
                if (num1 % i == 0 && num2 % i == 0) {
                    num1 = num1 / i;
                    num2 = num2 / i;
                    string = Integer.toString(num1) + "/" + Integer.toString(num2);
                    for (int j = 1; j <= num2; j++) {
                        if (num1 % j == 0 && num2 % j == 0) {
                            num1 = num1 / j;
                            num2 = num2 / j;
                            string = Integer.toString(num1) + "/" + Integer.toString(num2);
                        }
                    }
                }
            }
        }
        if (m == 6) {
            int num1 = random.nextInt(6) + 2;  //p
            int num2 = random.nextInt(num1) + 3; //c
            int num3 = random.nextInt(num1 + num2) + num1 + num2; //z
            int result = (num1 * num3) + num2;
            textViewBigTask.setText("Представьте в виде обыкновенной дроби смешанное число " + Integer.toString(num1) + " целых " + Integer.toString(num2) + "/" + Integer.toString(num3) + ". Дробь сокращать не нужно.");
            string = Integer.toString(result) + "/" + Integer.toString(num3);
        }
        if (m == 7) {
            int num1 = random.nextInt(10) + 10;
            int num2 = random.nextInt(num1) + num1 + 1;
            int sum = num1 + num2;
            textViewBigTask.setText("Какое число должно стоять на месте пропуска, чтобы равенство стало верным? \n_ + " + Integer.toString(num2) + " = " + Integer.toString(sum));
            string = Integer.toString(num1);
        }
        if (m == 8) {
            int num1 = random.nextInt(999) + 1;
            int num2 = random.nextInt(99) + 1;
            int num3 = random.nextInt(99) + 1;
            int num4 = random.nextInt(99) + 1;
            int num5 = random.nextInt(9) + 1;
            int mul = num1 * num2;
            int sumS = num3 + num4;
            int result = mul / num2 + (sumS - num4) * num5;
            task.setText(Integer.toString(mul) + " : " + Integer.toString(num2) + " +(" + Integer.toString(sumS) + " - " + Integer.toString(num4) + ")× " + Integer.toString(num5) + " = ");
            string = Integer.toString(result);
        }
        if (m == 9) {
            int num1 = random.nextInt(10) + 10;
            int num2 = random.nextInt(num1) + num1 + 1;
            int sum = num1 + num2;
            int sub = sum - num2;
            textViewBigTask.setText("Какое число должно стоять на месте пропуска, чтобы равенство стало верным? \n_ - " + Integer.toString(num2) + " = " + Integer.toString(sub));
            string = Integer.toString(sum);
        }
        if (m == 10) {
            int cost = (random.nextInt(10) + 1) * 1000;
            int per = (random.nextInt(6) + 1) * 10;
            textViewBigTask.setText("Зимой куртка стоит " + Integer.toString(cost) + " рублей. Летом на куртку скидка " + Integer.toString(per) + "%. Сколько стоит куртка летом?");
            float a = (float) per;
            float b = a / 100;
            float c = (float) cost;
            float d = c * b;
            float e = c - d;
            int res = (int) e;
            string = Integer.toString(res);
        }
        if (m == 11) {
            int v = random.nextInt(40) + 40;
            int t = random.nextInt(5) + 4;
            int s1 = v * t;
            int t2 = random.nextInt(8) + 4;
            int s2 = t2 * v;
            textViewBigTask.setText("Принтер печатает " + Integer.toString(s1) + " страниц за " + Integer.toString(t) + " минут. За сколько минут этот же принтер напечатает " + Integer.toString(s2) + " страниц?");
            string = Integer.toString(t2);
        }
        if (m == 12) {
            int num1 = random.nextInt(9) + 10;
            int result = (num1) * (random.nextInt(9) + 10);
            int num2 = random.nextInt(result - 91) + 3;
            int res = 1;
            textViewBigTask.setText("Напишите наименьшее двузначное число, которое больше " + Integer.toString(num1) + " и делится на " + Integer.toString(num1) + ", но не делится на " + Integer.toString(num2) + " .");
            for (int i = 1; i <= result; i++) {
                res++;
                if (res > num1 && res % num1 == 0 && res % num2 != 0) {
                    string = Integer.toString(res);
                    break;
                }
            }
        }

        if (m > 4 && m != 8) {
            String send = bugs.add(textViewBigTask.getText().toString()) + string;
            bugs.add(send);
        }
        if (m == 3) {
            bugs.add(signsTask);
        }
        if (m < 3) {
            String send = bugs.add(task.getText().toString()) + string;
            bugs.add(send);
        }
    }

    @SuppressLint("SetTextI18n")
    public void check(View view) {
        view.startAnimation(animation);
        se = "";
        if (userAnswer.toString().equals(string)) {
            countRight++;
            TextView textView = (TextView) findViewById(R.id.right);
            textView.setText(Integer.toString(countRight));
        }
        if (!userAnswer.toString().equals(string)) {
            countNotRight++;
            TextView textView = (TextView) findViewById(R.id.notRight);
            textView.setText(Integer.toString(countNotRight));
        }
        rAnswer.setText(string);

        SpannableString content = new SpannableString("    ");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        answerTV.setText(content);
        bigAnswer.setText(content);
        userAnswer.delete(0, userAnswer.length());

        String signsTask = "";
        Random random = new Random();
        int m = random.nextInt(13);
        if (m == 3) {
            getSupportActionBar().setSubtitle(" >  , <  , = ");
            l2.setVisibility(View.VISIBLE);
            l1.setVisibility(View.GONE);
        }
        if (m != 3) {
            getSupportActionBar().setSubtitle(null);
            notation.setText(null);
            l2.setVisibility(View.VISIBLE);
            l1.setVisibility(View.GONE);
        }
        if (m < 5 || m == 8) {
            l2.setVisibility(View.VISIBLE);
            l1.setVisibility(View.GONE);
        }
        if (m > 4 && m != 8) {
            l2.setVisibility(View.GONE);
            l1.setVisibility(View.VISIBLE);
        }
        if (m == 0) {
            int i1 = random.nextInt(9) + 1;
            float i2 = (float) i1;
            float a = random.nextFloat() + i2;
            float a1 = a * 100;
            int a2 = Math.round(a1);
            float a3 = (float) a2;
            float num1 = a3 / 100;
            int y1 = random.nextInt(9) + 1;
            float y2 = (float) y1;
            float b = random.nextFloat() + y2;
            float b1 = b * 100;
            int b2 = Math.round(b1);
            float b3 = (float) b2;
            float num2 = b3 / 100;
            float sum = num1 + num2;
            float s1 = sum * 100;
            int s2 = Math.round(s1);
            float s3 = (float) s2;
            float s4 = s3 / 100;
            String stringSumTask1 = Float.toString(num1) + " + " + Float.toString(num2) + " = ";
            string = Float.toString(s4);
            task.setText(stringSumTask1);
        }
        if (m == 1) {
            int i1 = random.nextInt(9) + 1;
            float i2 = (float) i1;
            float a = random.nextFloat() + i2;
            float a1 = a * 100;
            int a2 = Math.round(a1);
            float a3 = (float) a2;
            float num1 = a3 / 100;
            int y1 = random.nextInt(9) + 1;
            float y2 = (float) y1;
            float b = random.nextFloat() + y2;
            float b1 = b * 100;
            int b2 = Math.round(b1);
            float b3 = (float) b2;
            float num2 = b3 / 100;
            float sum = num1 + num2;
            float s1 = sum * 100;
            int s2 = Math.round(s1);
            float s3 = (float) s2;
            float s4 = s3 / 100;
            float sub = s4 - num2;
            float sb1 = sub * 100;
            int sb2 = Math.round(sb1);
            float sb3 = (float) sb2;
            float sb4 = sb3 / 100;
            String stringSubTask1 = Float.toString(s4) + " - " + Float.toString(num2) + " = ";
            string = Float.toString(sb4);
            task.setText(stringSubTask1);
        }
        if (m == 2) {
            int i1 = random.nextInt(9) + 1;
            float i2 = (float) i1;
            float a = random.nextFloat() + i2;
            float a1 = a * 10;
            int a2 = Math.round(a1);
            float a3 = (float) a2;
            float num1 = a3 / 10;
            int y1 = random.nextInt(9) + 1;
            float y2 = (float) y1;
            float b = random.nextFloat() + y2;
            float b1 = b * 10;
            int b2 = Math.round(b1);
            float b3 = (float) b2;
            float num2 = b3 / 10;
            float mul = num1 * num2;
            float m1 = mul * 100;
            int m2 = Math.round(m1);
            float m3 = (float) m2;
            float m4 = m3 / 100;
            String stringSubTask1 = Float.toString(num1) + " × " + Float.toString(num2) + " = ";
            string = Float.toString(m4);
            task.setText(stringSubTask1);
        }
        if (m == 3) {
            int i1 = random.nextInt(9) + 1;
            float i2 = (float) i1;
            float a = random.nextFloat() + i2;
            float a1 = a * 100;
            int a2 = Math.round(a1);
            float a3 = (float) a2;
            float num1 = a3 / 100;
            int y1 = random.nextInt(9) + 1;
            float y2 = (float) y1;
            float b = random.nextFloat() + y2;
            float b1 = b * 100;
            int b2 = Math.round(b1);
            float b3 = (float) b2;
            float num2 = b3 / 100;
            if (num1 > num2) {
                string = ">";
            }
            if (num1 < num2) {
                string = "<";
            }
            if (num1 == num2) {
                string = "=";
            }
            task.setText(Float.toString(num1));
            notation.setText(Float.toString(num2));
            signsTask = Float.toString(num1) + " " + string + " " + Float.toString(num2);
        }
        if (m == 4) {
            int num1 = random.nextInt(7) + 3;
            int num2 = random.nextInt(num1) + 3;
            int num3 = random.nextInt(num1 + num2) + num1 + num2;
            int sum = num1 + num2;
            String stringOS = Integer.toString(num1) + "/" + Integer.toString(num3) + " + " + Integer.toString(num2) + "/" + Integer.toString(num3) + " = ";
            task.setText(stringOS);
            for (int i = 1; i <= num3; i++) {
                if (sum % i == 0 && num3 % i == 0) {
                    sum = sum / i;
                    num3 = num3 / i;
                    string = Integer.toString(sum) + "/" + Integer.toString(num3);
                    for (int j = 1; j <= num3; j++) {
                        if (sum % j == 0 && num3 % j == 0) {
                            sum = sum / j;
                            num3 = num3 / j;
                            string = Integer.toString(sum) + "/" + Integer.toString(num3);
                        }
                    }
                }
            }
        }
        if (m == 5) {
            int num1 = random.nextInt(8) + 3;
            int num2 = random.nextInt(num1) + num1 + 1;
            textViewBigTask.setText("Представьте число " + Integer.toString(num1) + " в виде дроби со знаменателем " + Integer.toString(num2) + ".");
            //СОКРАЩЕНИЕ ДРОБИ ЗДЕСЬ
            for (int i = 1; i <= num2; i++) {
                if (num1 % i == 0 && num2 % i == 0) {
                    num1 = num1 / i;
                    num2 = num2 / i;
                    string = Integer.toString(num1) + "/" + Integer.toString(num2);
                    for (int j = 1; j <= num2; j++) {
                        if (num1 % j == 0 && num2 % j == 0) {
                            num1 = num1 / j;
                            num2 = num2 / j;
                            string = Integer.toString(num1) + "/" + Integer.toString(num2);
                        }
                    }
                }
            }
        }
        if (m == 6) {
            int num1 = random.nextInt(6) + 2;  //p
            int num2 = random.nextInt(num1) + 3; //c
            int num3 = random.nextInt(num1 + num2) + num1 + num2; //z
            int result = (num1 * num3) + num2;
            textViewBigTask.setText("Представьте в виде обыкновенной дроби смешанное число " + Integer.toString(num1) + " целых " + Integer.toString(num2) + "/" + Integer.toString(num3) + ". Дробь сокращать не нужно.");
            string = Integer.toString(result) + "/" + Integer.toString(num3);
        }
        if (m == 7) {
            int num1 = random.nextInt(10) + 10;
            int num2 = random.nextInt(num1) + num1 + 1;
            int sum = num1 + num2;
            textViewBigTask.setText("Какое число должно стоять на месте пропуска, чтобы равенство стало верным? \n_ + " + Integer.toString(num2) + " = " + Integer.toString(sum));
            string = Integer.toString(num1);
        }
        if (m == 8) {
            int num1 = random.nextInt(999) + 1;
            int num2 = random.nextInt(99) + 1;
            int num3 = random.nextInt(99) + 1;
            int num4 = random.nextInt(99) + 1;
            int num5 = random.nextInt(9) + 1;
            int mul = num1 * num2;
            int sumS = num3 + num4;
            int result = mul / num2 + (sumS - num4) * num5;
            task.setText(Integer.toString(mul) + " : " + Integer.toString(num2) + " +(" + Integer.toString(sumS) + " - " + Integer.toString(num4) + ")× " + Integer.toString(num5) + " = ");
            string = Integer.toString(result);
        }
        if (m == 9) {
            int num1 = random.nextInt(10) + 10;
            int num2 = random.nextInt(num1) + num1 + 1;
            int sum = num1 + num2;
            int sub = sum - num2;
            textViewBigTask.setText("Какое число должно стоять на месте пропуска, чтобы равенство стало верным? \n_ - " + Integer.toString(num2) + " = " + Integer.toString(sub));
            string = Integer.toString(sum);
        }
        if (m == 10) {
            int cost = (random.nextInt(10) + 1) * 1000;
            int per = (random.nextInt(6) + 1) * 10;
            textViewBigTask.setText("Зимой куртка стоит " + Integer.toString(cost) + " рублей. Летом на куртку скидка " + Integer.toString(per) + "%. Сколько стоит куртка летом?");
            float a = (float) per;
            float b = a / 100;
            float c = (float) cost;
            float d = c * b;
            float e = c - d;
            int res = (int) e;
            string = Integer.toString(res);
        }
        if (m == 11) {
            int v = random.nextInt(40) + 40;
            int t = random.nextInt(5) + 4;
            int s1 = v * t;
            int t2 = random.nextInt(8) + 4;
            int s2 = t2 * v;
            textViewBigTask.setText("Принтер печатает " + Integer.toString(s1) + " страниц за " + Integer.toString(t) + " минут. За сколько минут этот же принтер напечатает " + Integer.toString(s2) + " страниц?");
            string = Integer.toString(t2);
        }
        if (m == 12) {
            int num1 = random.nextInt(9) + 10;
            int result = (num1) * (random.nextInt(9) + 10);
            int num2 = random.nextInt(result - 91) + 3;
            int res = 1;
            textViewBigTask.setText("Напишите наименьшее двузначное число, которое больше " + Integer.toString(num1) + " и делится на " + Integer.toString(num1) + ", но не делится на " + Integer.toString(num2) + " .");
            for (int i = 1; i <= result; i++) {
                res++;
                if (res > num1 && res % num1 == 0 && res % num2 != 0) {
                    string = Integer.toString(res);
                    break;
                }
            }
        }

        if (m > 4 && m != 8) {
            String send = bugs.add(textViewBigTask.getText().toString()) + string;
            bugs.add(send);
        }
        if (m == 3) {
            bugs.add(signsTask);
        }
        if (m < 3) {
            String send = bugs.add(task.getText().toString()) + string;
            bugs.add(send);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        CountDown countDown = new CountDown(900, 100);
        countDown.start();
    }

    @SuppressLint("SetTextI18n")
    private class CountDown extends CountDownTimer {
        private CountDown(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            long seconds = l / 1000;
        }

        @Override
        public void onFinish() {
            SpannableString content = new SpannableString("    ");
            content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
            answerTV.setText(content);
            bigAnswer.setText(content);
            userAnswer.delete(0, userAnswer.length());

            String signsTask = "";
            Random random = new Random();
            int m = random.nextInt(13);
            if (m == 3) {
                getSupportActionBar().setSubtitle(" >  , <  , = ");
                l2.setVisibility(View.VISIBLE);
                l1.setVisibility(View.GONE);
            }
            if (m != 3) {
                getSupportActionBar().setSubtitle(null);
                notation.setText(null);
                l2.setVisibility(View.VISIBLE);
                l1.setVisibility(View.GONE);
            }
            if (m < 5 || m == 8) {
                l2.setVisibility(View.VISIBLE);
                l1.setVisibility(View.GONE);
            }
            if (m > 4 && m != 8) {
                l2.setVisibility(View.GONE);
                l1.setVisibility(View.VISIBLE);
            }
            if (m == 0) {
                int i1 = random.nextInt(9) + 1;
                float i2 = (float) i1;
                float a = random.nextFloat() + i2;
                float a1 = a * 100;
                int a2 = Math.round(a1);
                float a3 = (float) a2;
                float num1 = a3 / 100;
                int y1 = random.nextInt(9) + 1;
                float y2 = (float) y1;
                float b = random.nextFloat() + y2;
                float b1 = b * 100;
                int b2 = Math.round(b1);
                float b3 = (float) b2;
                float num2 = b3 / 100;
                float sum = num1 + num2;
                float s1 = sum * 100;
                int s2 = Math.round(s1);
                float s3 = (float) s2;
                float s4 = s3 / 100;
                String stringSumTask1 = Float.toString(num1) + " + " + Float.toString(num2) + " = ";
                string = Float.toString(s4);
                task.setText(stringSumTask1);
            }
            if (m == 1) {
                int i1 = random.nextInt(9) + 1;
                float i2 = (float) i1;
                float a = random.nextFloat() + i2;
                float a1 = a * 100;
                int a2 = Math.round(a1);
                float a3 = (float) a2;
                float num1 = a3 / 100;
                int y1 = random.nextInt(9) + 1;
                float y2 = (float) y1;
                float b = random.nextFloat() + y2;
                float b1 = b * 100;
                int b2 = Math.round(b1);
                float b3 = (float) b2;
                float num2 = b3 / 100;
                float sum = num1 + num2;
                float s1 = sum * 100;
                int s2 = Math.round(s1);
                float s3 = (float) s2;
                float s4 = s3 / 100;
                float sub = s4 - num2;
                float sb1 = sub * 100;
                int sb2 = Math.round(sb1);
                float sb3 = (float) sb2;
                float sb4 = sb3 / 100;
                String stringSubTask1 = Float.toString(s4) + " - " + Float.toString(num2) + " = ";
                string = Float.toString(sb4);
                task.setText(stringSubTask1);
            }
            if (m == 2) {
                int i1 = random.nextInt(9) + 1;
                float i2 = (float) i1;
                float a = random.nextFloat() + i2;
                float a1 = a * 10;
                int a2 = Math.round(a1);
                float a3 = (float) a2;
                float num1 = a3 / 10;
                int y1 = random.nextInt(9) + 1;
                float y2 = (float) y1;
                float b = random.nextFloat() + y2;
                float b1 = b * 10;
                int b2 = Math.round(b1);
                float b3 = (float) b2;
                float num2 = b3 / 10;
                float mul = num1 * num2;
                float m1 = mul * 100;
                int m2 = Math.round(m1);
                float m3 = (float) m2;
                float m4 = m3 / 100;
                String stringSubTask1 = Float.toString(num1) + " × " + Float.toString(num2) + " = ";
                string = Float.toString(m4);
                task.setText(stringSubTask1);
            }
            if (m == 3) {
                int i1 = random.nextInt(9) + 1;
                float i2 = (float) i1;
                float a = random.nextFloat() + i2;
                float a1 = a * 100;
                int a2 = Math.round(a1);
                float a3 = (float) a2;
                float num1 = a3 / 100;
                int y1 = random.nextInt(9) + 1;
                float y2 = (float) y1;
                float b = random.nextFloat() + y2;
                float b1 = b * 100;
                int b2 = Math.round(b1);
                float b3 = (float) b2;
                float num2 = b3 / 100;
                if (num1 > num2) {
                    string = ">";
                }
                if (num1 < num2) {
                    string = "<";
                }
                if (num1 == num2) {
                    string = "=";
                }
                task.setText(Float.toString(num1));
                notation.setText(Float.toString(num2));
                signsTask = Float.toString(num1) + " " + string + " " + Float.toString(num2);
            }
            if (m == 4) {
                int num1 = random.nextInt(7) + 3;
                int num2 = random.nextInt(num1) + 3;
                int num3 = random.nextInt(num1 + num2) + num1 + num2;
                int sum = num1 + num2;
                String stringOS = Integer.toString(num1) + "/" + Integer.toString(num3) + " + " + Integer.toString(num2) + "/" + Integer.toString(num3) + " = ";
                task.setText(stringOS);
                for (int i = 1; i <= num3; i++) {
                    if (sum % i == 0 && num3 % i == 0) {
                        sum = sum / i;
                        num3 = num3 / i;
                        string = Integer.toString(sum) + "/" + Integer.toString(num3);
                        for (int j = 1; j <= num3; j++) {
                            if (sum % j == 0 && num3 % j == 0) {
                                sum = sum / j;
                                num3 = num3 / j;
                                string = Integer.toString(sum) + "/" + Integer.toString(num3);
                            }
                        }
                    }
                }
            }
            if (m == 5) {
                int num1 = random.nextInt(8) + 3;
                int num2 = random.nextInt(num1) + num1 + 1;
                textViewBigTask.setText("Представьте число " + Integer.toString(num1) + " в виде дроби со знаменателем " + Integer.toString(num2) + ".");
                //СОКРАЩЕНИЕ ДРОБИ ЗДЕСЬ
                for (int i = 1; i <= num2; i++) {
                    if (num1 % i == 0 && num2 % i == 0) {
                        num1 = num1 / i;
                        num2 = num2 / i;
                        string = Integer.toString(num1) + "/" + Integer.toString(num2);
                        for (int j = 1; j <= num2; j++) {
                            if (num1 % j == 0 && num2 % j == 0) {
                                num1 = num1 / j;
                                num2 = num2 / j;
                                string = Integer.toString(num1) + "/" + Integer.toString(num2);
                            }
                        }
                    }
                }
            }
            if (m == 6) {
                int num1 = random.nextInt(6) + 2;  //p
                int num2 = random.nextInt(num1) + 3; //c
                int num3 = random.nextInt(num1 + num2) + num1 + num2; //z
                int result = (num1 * num3) + num2;
                textViewBigTask.setText("Представьте в виде обыкновенной дроби смешанное число " + Integer.toString(num1) + " целых " + Integer.toString(num2) + "/" + Integer.toString(num3) + ". Дробь сокращать не нужно.");
                string = Integer.toString(result) + "/" + Integer.toString(num3);
            }
            if (m == 7) {
                int num1 = random.nextInt(10) + 10;
                int num2 = random.nextInt(num1) + num1 + 1;
                int sum = num1 + num2;
                textViewBigTask.setText("Какое число должно стоять на месте пропуска, чтобы равенство стало верным? \n_ + " + Integer.toString(num2) + " = " + Integer.toString(sum));
                string = Integer.toString(num1);
            }
            if (m == 8) {
                int num1 = random.nextInt(999) + 1;
                int num2 = random.nextInt(99) + 1;
                int num3 = random.nextInt(99) + 1;
                int num4 = random.nextInt(99) + 1;
                int num5 = random.nextInt(9) + 1;
                int mul = num1 * num2;
                int sumS = num3 + num4;
                int result = mul / num2 + (sumS - num4) * num5;
                task.setText(Integer.toString(mul) + " : " + Integer.toString(num2) + " +(" + Integer.toString(sumS) + " - " + Integer.toString(num4) + ")× " + Integer.toString(num5) + " = ");
                string = Integer.toString(result);
            }
            if (m == 9) {
                int num1 = random.nextInt(10) + 10;
                int num2 = random.nextInt(num1) + num1 + 1;
                int sum = num1 + num2;
                int sub = sum - num2;
                textViewBigTask.setText("Какое число должно стоять на месте пропуска, чтобы равенство стало верным? \n_ - " + Integer.toString(num2) + " = " + Integer.toString(sub));
                string = Integer.toString(sum);
            }
            if (m == 10) {
                int cost = (random.nextInt(10) + 1) * 1000;
                int per = (random.nextInt(6) + 1) * 10;
                textViewBigTask.setText("Зимой куртка стоит " + Integer.toString(cost) + " рублей. Летом на куртку скидка " + Integer.toString(per) + "%. Сколько стоит куртка летом?");
                float a = (float) per;
                float b = a / 100;
                float c = (float) cost;
                float d = c * b;
                float e = c - d;
                int res = (int) e;
                string = Integer.toString(res);
            }
            if (m == 11) {
                int v = random.nextInt(40) + 40;
                int t = random.nextInt(5) + 4;
                int s1 = v * t;
                int t2 = random.nextInt(8) + 4;
                int s2 = t2 * v;
                textViewBigTask.setText("Принтер печатает " + Integer.toString(s1) + " страниц за " + Integer.toString(t) + " минут. За сколько минут этот же принтер напечатает " + Integer.toString(s2) + " страниц?");
                string = Integer.toString(t2);
            }
            if (m == 12) {
                int num1 = random.nextInt(9) + 10;
                int result = (num1) * (random.nextInt(9) + 10);
                int num2 = random.nextInt(result - 91) + 3;
                int res = 1;
                textViewBigTask.setText("Напишите наименьшее двузначное число, которое больше " + Integer.toString(num1) + " и делится на " + Integer.toString(num1) + ", но не делится на " + Integer.toString(num2) + " .");
                for (int i = 1; i <= result; i++) {
                    res++;
                    if (res > num1 && res % num1 == 0 && res % num2 != 0) {
                        string = Integer.toString(res);
                        break;
                    }
                }
            }

            if (m > 4 && m != 8) {
                String send = bugs.add(textViewBigTask.getText().toString()) + string;
                bugs.add(send);
            }
            if (m == 3) {
                bugs.add(signsTask);
            }
            if (m < 3) {
                String send = bugs.add(task.getText().toString()) + string;
                bugs.add(send);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //STATISTIC CODE START (LOAD)
        SharedPreferences sharedPreferences = getSharedPreferences("statistic", MODE_PRIVATE);
        CountRightS = sharedPreferences.getLong("5-right", 0);
        CountNotRightS = sharedPreferences.getLong("5-not_right", 0);
        //--------------------
    }

    @Override
    public void onBackPressed() {
        if (mInterstitialAdLoader != null ) {
            final AdRequestConfiguration adRequestConfiguration =
                    new AdRequestConfiguration.Builder(getResources().getString(R.string.Interstitial_id)).build();
            mInterstitialAdLoader.loadAd(adRequestConfiguration);
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
        editor.putLong("5-right", countRight + CountRightS);
        editor.putLong("5-not_right", countNotRight + CountNotRightS);
        editor.apply();
        //STATISTIC CODE END
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (mInterstitialAdLoader != null ) {
                final AdRequestConfiguration adRequestConfiguration =
                        new AdRequestConfiguration.Builder(getResources().getString(R.string.Interstitial_id)).build();
                mInterstitialAdLoader.loadAd(adRequestConfiguration);
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
