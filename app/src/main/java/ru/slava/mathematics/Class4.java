package ru.slava.mathematics;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
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

public class Class4 extends AppCompatActivity {
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
            getSupportActionBar().setTitle("4 Класс");
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

        Random random = new Random();
        String signsTask = "";
        int m = random.nextInt(11);
        if (m == 8 || m == 9 || m == 10) {
            getSupportActionBar().setSubtitle(null);
            l2.setVisibility(View.GONE);
            l1.setVisibility(View.VISIBLE);
        }
        if (m == 7) {
            getSupportActionBar().setSubtitle(" > , <  , = ");
            l2.setVisibility(View.VISIBLE);
            l1.setVisibility(View.GONE);
        }
        if (m <= 6) {
            getSupportActionBar().setSubtitle(null);
            l2.setVisibility(View.VISIBLE);
            l1.setVisibility(View.GONE);
        }

        if (m == 0) {
            int num1 = random.nextInt(10000);
            int num2 = random.nextInt(10001);
            int sum = num1 + num2;
            String stringSumTask = Integer.toString(num1) + " + " + Integer.toString(num2) + " = ";
            string = Integer.toString(sum);
            task.setText(stringSumTask);
        }
        if (m == 1) {
            int num1 = random.nextInt(10000);
            int num2 = random.nextInt(10000);
            int sum = num1 + num2;
            int sub = sum - num2;
            String stringSubTask = Integer.toString(sum) + " - " + Integer.toString(num2) + " = ";
            string = Integer.toString(num1);
            task.setText(stringSubTask);
        }
        if (m == 2) {
            int num1 = random.nextInt(1000);
            int num2 = random.nextInt(100);
            int mul = num1 * num2;
            String stringMulTask = Integer.toString(num1) + " × " + Integer.toString(num2) + " = ";
            string = Integer.toString(mul);
            task.setText(stringMulTask);
        }
        if (m == 3) {
            int num1 = random.nextInt(100) + 1;
            int num2 = random.nextInt(100) + 1;
            int mul = num1 * num2;
            int div = mul / num1;
            String stringDivTask = Integer.toString(mul) + " : " + Integer.toString(num1) + " = ";
            string = Integer.toString(div);
            task.setText(stringDivTask);
        }
        if (m == 4) {
            int num1 = random.nextInt(1000);
            int num2 = random.nextInt(10001);
            int num3 = random.nextInt(1000);
            int sumScobca = (num1 + num2) + num3;
            String stringSumScobcaTask = "(" + Integer.toString(num1) + " + " + Integer.toString(num2) + ") + " + Integer.toString(num3) + " = ";
            string = Integer.toString(sumScobca);
            task.setText(stringSumScobcaTask);
        }
        if (m == 5) {
            int num1 = random.nextInt(1000);
            int num2 = random.nextInt(1001);
            int num3 = random.nextInt(100);
            int sumMulScobca = (num1 + num2) * num3;
            String stringSumMulTask = "(" + Integer.toString(num1) + " + " + Integer.toString(num2) + ") × " + Integer.toString(num3) + " = ";
            string = Integer.toString(sumMulScobca);
            task.setText(stringSumMulTask);
        }
        if (m == 6) {
            int num1 = random.nextInt(1000) + 1;
            int num2 = random.nextInt(1000) + 1;
            int num3 = random.nextInt(1000);
            int mul = num1 * num2;
            int div = mul / num1;
            int mulDivScobca = div * num3;
            String stringMulDivTask = "(" + Integer.toString(mul) + " : " + Integer.toString(num1) + ") × " + Integer.toString(num3) + " = ";
            string = Integer.toString(mulDivScobca);
            task.setText(stringMulDivTask);
        }
        if (m == 7) {
            int num1 = random.nextInt(10000);
            int num2 = random.nextInt(10000);
            if (num1 > num2) {
                string = ">";
                task.setText(Integer.toString(num1));
                notation.setText(Integer.toString(num2));
            }
            if (num1 < num2) {
                string = "<";
                task.setText(Integer.toString(num1));
                notation.setText(Integer.toString(num2));
            }
            if (num1 == num2) {
                string = "=";
                task.setText(Integer.toString(num1));
                notation.setText(Integer.toString(num2));
            }
            signsTask = Integer.toString(num1) + " " + string + " " + Integer.toString(num2);
        }
        if (m == 8) {
            int m1 = random.nextInt(2);
            int width = random.nextInt(49) + 1;
            int length = random.nextInt(49) + 1;
            int SRect = width * length;
            if (m1 == 0) {
                textViewBigTask.setText("Стороны прямоугольника соответственно равны " + width + " и " + length + " см. Найдите его площадь.");
                string = Integer.toString(SRect);
            }
            if (m1 == 1) {
                int a = SRect / width;
                textViewBigTask.setText("Сторона прямоугольника и его площадь соответственно равны " + width + " и " + SRect + " см. Найдите его другую сторону.");
                string = Integer.toString(a);
            }
        }
        if (m == 9) {
            int m1 = random.nextInt(3);
            int v = random.nextInt(99) + 1;
            int t = random.nextInt(95) + 5;
            int s = v * t;
            if (m1 == 0) {
                textViewBigTask.setText("Расстояние между двумя пунктами " + s + " км. Автомобилист едет со скоростью " + v + " км/ч. Найдите время(в часах) за которое автомобилист преодолеет расстояние между пунктами.");
                string = Integer.toString(t);
            }
            if (m1 == 1) {
                textViewBigTask.setText("Автомобилист едет от одного пункта до другого со скоростью " + v + " км/ч " + t + " часов. Найдите расстояние между этими пунктами в километрах.");
                string = Integer.toString(s);
            }
            if (m1 == 2) {
                textViewBigTask.setText("Автомобиль едет от одного пункта до другого, расположенных на расстоянии " + s + " км. " + t + " часов. Найдите скорость(в км/ч) этого автомобиля.");
                string = Integer.toString(v);
            }
        }
        if (m == 10) {
            int m1 = random.nextInt(2);
            int b1 = random.nextInt(2) + 2;
            int b2 = random.nextInt(2) + 2;
            int b = b1 + b2;
            int g = b * 1000;
            if (m1 == 0) {
                int y = g - b1 * 400;
                int a = y / 200;
                textViewBigTask.setText(b + " кг варенья разложили по банкам. Банок по 400 г оказалось " + b1 + ". Сколько осталось банок по 200 г ?");
                string = Integer.toString(a);
            }
            if (m1 == 1) {
                int y = g - b1 * 200;
                int a = y / 400;
                textViewBigTask.setText(b + " кг варенья разложили по банкам. Банок по 200 г оказалось " + b1 + ". Сколько осталось банок по 400 г ?");
                string = Integer.toString(a);
            }
        }
        if (m < 7) {
            notation.setText(null);
            String send = bugs.add(task.getText().toString().substring(0, task.getText().toString().length() - 1)) + string;
            bugs.add(send);
        }
        if (m > 7) {
            String send = bugs.add(textViewBigTask.getText().toString().substring(0, textViewBigTask.getText().toString().length() - 1)) + string;
            bugs.add(send);
        } else bugs.add(signsTask);
    }

    @SuppressLint("SetTextI18n")
    public void check(View view) {
        view.startAnimation(animation);
        se = "";
        //showRewardedAd(countRight, countNotRight);
        //showAd(countRight, countNotRight);
        if (userAnswer.toString().equals(string)) {
            countRight++;
            right.setText(Integer.toString(countRight));
        }
        if (!userAnswer.toString().equals(string)) {
            countNotRight++;
            notRight.setText(Integer.toString(countNotRight));
        }

        rAnswer.setText(string);
        Random random = new Random();
        String signsTask = "";

        SpannableString content = new SpannableString("    ");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        answerTV.setText(content);
        bigAnswer.setText(content);
        userAnswer.delete(0, userAnswer.length());

        int m = random.nextInt(11);
        if (m == 8 || m == 9 || m == 10) {
            getSupportActionBar().setSubtitle(null);
            l2.setVisibility(View.GONE);
            l1.setVisibility(View.VISIBLE);
        }
        if (m == 7) {
            getSupportActionBar().setSubtitle(" > , <  , = ");
            l2.setVisibility(View.VISIBLE);
            l1.setVisibility(View.GONE);
        }
        if (m <= 6) {
            getSupportActionBar().setSubtitle(null);
            l2.setVisibility(View.VISIBLE);
            l1.setVisibility(View.GONE);
        }

        if (m == 0) {
            int num1 = random.nextInt(10000);
            int num2 = random.nextInt(10001);
            int sum = num1 + num2;
            String stringSumTask = Integer.toString(num1) + " + " + Integer.toString(num2) + " = ";
            string = Integer.toString(sum);
            task.setText(stringSumTask);
        }
        if (m == 1) {
            int num1 = random.nextInt(10000);
            int num2 = random.nextInt(10000);
            int sum = num1 + num2;
            int sub = sum - num2;
            String stringSubTask = Integer.toString(sum) + " - " + Integer.toString(num2) + " = ";
            string = Integer.toString(num1);
            task.setText(stringSubTask);
        }
        if (m == 2) {
            int num1 = random.nextInt(1000);
            int num2 = random.nextInt(100);
            int mul = num1 * num2;
            String stringMulTask = Integer.toString(num1) + " × " + Integer.toString(num2) + " = ";
            string = Integer.toString(mul);
            task.setText(stringMulTask);
        }
        if (m == 3) {
            int num1 = random.nextInt(100) + 1;
            int num2 = random.nextInt(100) + 1;
            int mul = num1 * num2;
            int div = mul / num1;
            String stringDivTask = Integer.toString(mul) + " : " + Integer.toString(num1) + " = ";
            string = Integer.toString(div);
            task.setText(stringDivTask);
        }
        if (m == 4) {
            int num1 = random.nextInt(1000);
            int num2 = random.nextInt(10001);
            int num3 = random.nextInt(1000);
            int sumScobca = (num1 + num2) + num3;
            String stringSumScobcaTask = "(" + Integer.toString(num1) + " + " + Integer.toString(num2) + ") + " + Integer.toString(num3) + " = ";
            string = Integer.toString(sumScobca);
            task.setText(stringSumScobcaTask);
        }
        if (m == 5) {
            int num1 = random.nextInt(1000);
            int num2 = random.nextInt(1001);
            int num3 = random.nextInt(100);
            int sumMulScobca = (num1 + num2) * num3;
            String stringSumMulTask = "(" + Integer.toString(num1) + " + " + Integer.toString(num2) + ") × " + Integer.toString(num3) + " = ";
            string = Integer.toString(sumMulScobca);
            task.setText(stringSumMulTask);
        }
        if (m == 6) {
            int num1 = random.nextInt(1000) + 1;
            int num2 = random.nextInt(1000) + 1;
            int num3 = random.nextInt(1000);
            int mul = num1 * num2;
            int div = mul / num1;
            int mulDivScobca = div * num3;
            String stringMulDivTask = "(" + Integer.toString(mul) + " : " + Integer.toString(num1) + ") × " + Integer.toString(num3) + " = ";
            string = Integer.toString(mulDivScobca);
            task.setText(stringMulDivTask);
        }
        if (m == 7) {
            int num1 = random.nextInt(10000);
            int num2 = random.nextInt(10000);
            if (num1 > num2) {
                string = ">";
                task.setText(Integer.toString(num1));
                notation.setText(Integer.toString(num2));
            }
            if (num1 < num2) {
                string = "<";
                task.setText(Integer.toString(num1));
                notation.setText(Integer.toString(num2));
            }
            if (num1 == num2) {
                string = "=";
                task.setText(Integer.toString(num1));
                notation.setText(Integer.toString(num2));
            }
            signsTask = Integer.toString(num1) + " " + string + " " + Integer.toString(num2);
        }
        if (m == 8) {
            int m1 = random.nextInt(2);
            int width = random.nextInt(49) + 1;
            int length = random.nextInt(49) + 1;
            int SRect = width * length;
            if (m1 == 0) {
                textViewBigTask.setText("Стороны прямоугольника соответственно равны " + width + " и " + length + " см. Найдите его площадь.");
                string = Integer.toString(SRect);
            }
            if (m1 == 1) {
                int a = SRect / width;
                textViewBigTask.setText("Сторона прямоугольника и его площадь соответственно равны " + width + " и " + SRect + " см. Найдите его другую сторону.");
                string = Integer.toString(a);
            }
        }
        if (m == 9) {
            int m1 = random.nextInt(3);
            int v = random.nextInt(99) + 1;
            int t = random.nextInt(95) + 5;
            int s = v * t;
            if (m1 == 0) {
                textViewBigTask.setText("Расстояние между двумя пунктами " + s + " км. Автомобилист едет со скоростью " + v + " км/ч. Найдите время(в часах) за которое автомобилист преодолеет расстояние между пунктами.");
                string = Integer.toString(t);
            }
            if (m1 == 1) {
                textViewBigTask.setText("Автомобилист едет от одного пункта до другого со скоростью " + v + " км/ч " + t + " часов. Найдите расстояние между этими пунктами в километрах.");
                string = Integer.toString(s);
            }
            if (m1 == 2) {
                textViewBigTask.setText("Автомобиль едет от одного пункта до другого, расположенных на расстоянии " + s + " км. " + t + " часов. Найдите скорость(в км/ч) этого автомобиля.");
                string = Integer.toString(v);
            }
        }
        if (m == 10) {
            int m1 = random.nextInt(2);
            int b1 = random.nextInt(2) + 2;
            int b2 = random.nextInt(2) + 2;
            int b = b1 + b2;
            int g = b * 1000;
            if (m1 == 0) {
                int y = g - b1 * 400;
                int a = y / 200;
                textViewBigTask.setText(b + " кг варенья разложили по банкам. Банок по 400 г оказалось " + b1 + ". Сколько осталось банок по 200 г ?");
                string = Integer.toString(a);
            }
            if (m1 == 1) {
                int y = g - b1 * 200;
                int a = y / 400;
                textViewBigTask.setText(b + " кг варенья разложили по банкам. Банок по 200 г оказалось " + b1 + ". Сколько осталось банок по 400 г ?");
                string = Integer.toString(a);
            }
        }
        if (m < 7) {
            notation.setText(null);
            String send = bugs.add(task.getText().toString().substring(0, task.getText().toString().length() - 1)) + string;
            bugs.add(send);
        }
        if (m > 7) {
            String send = bugs.add(textViewBigTask.getText().toString().substring(0, textViewBigTask.getText().toString().length() - 1)) + string;
            bugs.add(send);
        } else bugs.add(signsTask);
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
            Random random = new Random();
            String signsTask = "";
            int m = random.nextInt(11);
            if (m == 8 || m == 9 || m == 10) {
                getSupportActionBar().setSubtitle(null);
                l2.setVisibility(View.GONE);
                l1.setVisibility(View.VISIBLE);
            }
            if (m == 7) {
                getSupportActionBar().setSubtitle(" > , <  , = ");
                l2.setVisibility(View.VISIBLE);
                l1.setVisibility(View.GONE);
            }
            if (m <= 6) {
                getSupportActionBar().setSubtitle(null);
                l2.setVisibility(View.VISIBLE);
                l1.setVisibility(View.GONE);
            }

            if (m == 0) {
                int num1 = random.nextInt(10000);
                int num2 = random.nextInt(10001);
                int sum = num1 + num2;
                String stringSumTask = Integer.toString(num1) + " + " + Integer.toString(num2) + " = ";
                string = Integer.toString(sum);
                task.setText(stringSumTask);
            }
            if (m == 1) {
                int num1 = random.nextInt(10000);
                int num2 = random.nextInt(10000);
                int sum = num1 + num2;
                int sub = sum - num2;
                String stringSubTask = Integer.toString(sum) + " - " + Integer.toString(num2) + " = ";
                string = Integer.toString(num1);
                task.setText(stringSubTask);
            }
            if (m == 2) {
                int num1 = random.nextInt(1000);
                int num2 = random.nextInt(100);
                int mul = num1 * num2;
                String stringMulTask = Integer.toString(num1) + " × " + Integer.toString(num2) + " = ";
                string = Integer.toString(mul);
                task.setText(stringMulTask);
            }
            if (m == 3) {
                int num1 = random.nextInt(100) + 1;
                int num2 = random.nextInt(100) + 1;
                int mul = num1 * num2;
                int div = mul / num1;
                String stringDivTask = Integer.toString(mul) + " : " + Integer.toString(num1) + " = ";
                string = Integer.toString(div);
                task.setText(stringDivTask);
            }
            if (m == 4) {
                int num1 = random.nextInt(1000);
                int num2 = random.nextInt(10001);
                int num3 = random.nextInt(1000);
                int sumScobca = (num1 + num2) + num3;
                String stringSumScobcaTask = "(" + Integer.toString(num1) + " + " + Integer.toString(num2) + ") + " + Integer.toString(num3) + " = ";
                string = Integer.toString(sumScobca);
                task.setText(stringSumScobcaTask);
            }
            if (m == 5) {
                int num1 = random.nextInt(1000);
                int num2 = random.nextInt(1001);
                int num3 = random.nextInt(100);
                int sumMulScobca = (num1 + num2) * num3;
                String stringSumMulTask = "(" + Integer.toString(num1) + " + " + Integer.toString(num2) + ") × " + Integer.toString(num3) + " = ";
                string = Integer.toString(sumMulScobca);
                task.setText(stringSumMulTask);
            }
            if (m == 6) {
                int num1 = random.nextInt(1000) + 1;
                int num2 = random.nextInt(1000) + 1;
                int num3 = random.nextInt(1000);
                int mul = num1 * num2;
                int div = mul / num1;
                int mulDivScobca = div * num3;
                String stringMulDivTask = "(" + Integer.toString(mul) + " : " + Integer.toString(num1) + ") × " + Integer.toString(num3) + " = ";
                string = Integer.toString(mulDivScobca);
                task.setText(stringMulDivTask);
            }
            if (m == 7) {
                int num1 = random.nextInt(10000);
                int num2 = random.nextInt(10000);
                if (num1 > num2) {
                    string = ">";
                    task.setText(Integer.toString(num1));
                    notation.setText(Integer.toString(num2));
                }
                if (num1 < num2) {
                    string = "<";
                    task.setText(Integer.toString(num1));
                    notation.setText(Integer.toString(num2));
                }
                if (num1 == num2) {
                    string = "=";
                    task.setText(Integer.toString(num1));
                    notation.setText(Integer.toString(num2));
                }
                signsTask = Integer.toString(num1) + " " + string + " " + Integer.toString(num2);
            }
            if (m == 8) {
                int m1 = random.nextInt(2);
                int width = random.nextInt(49) + 1;
                int length = random.nextInt(49) + 1;
                int SRect = width * length;
                if (m1 == 0) {
                    textViewBigTask.setText("Стороны прямоугольника соответственно равны " + width + " и " + length + " см. Найдите его площадь.");
                    string = Integer.toString(SRect);
                }
                if (m1 == 1) {
                    int a = SRect / width;
                    textViewBigTask.setText("Сторона прямоугольника и его площадь соответственно равны " + width + " и " + SRect + " см. Найдите его другую сторону.");
                    string = Integer.toString(a);
                }
            }
            if (m == 9) {
                int m1 = random.nextInt(3);
                int v = random.nextInt(99) + 1;
                int t = random.nextInt(95) + 5;
                int s = v * t;
                if (m1 == 0) {
                    textViewBigTask.setText("Расстояние между двумя пунктами " + s + " км. Автомобилист едет со скоростью " + v + " км/ч. Найдите время(в часах) за которое автомобилист преодолеет расстояние между пунктами.");
                    string = Integer.toString(t);
                }
                if (m1 == 1) {
                    textViewBigTask.setText("Автомобилист едет от одного пункта до другого со скоростью " + v + " км/ч " + t + " часов. Найдите расстояние между этими пунктами в километрах.");
                    string = Integer.toString(s);
                }
                if (m1 == 2) {
                    textViewBigTask.setText("Автомобиль едет от одного пункта до другого, расположенных на расстоянии " + s + " км. " + t + " часов. Найдите скорость(в км/ч) этого автомобиля.");
                    string = Integer.toString(v);
                }
            }
            if (m == 10) {
                int m1 = random.nextInt(2);
                int b1 = random.nextInt(2) + 2;
                int b2 = random.nextInt(2) + 2;
                int b = b1 + b2;
                int g = b * 1000;
                if (m1 == 0) {
                    int y = g - b1 * 400;
                    int a = y / 200;
                    textViewBigTask.setText(b + " кг варенья разложили по банкам. Банок по 400 г оказалось " + b1 + ". Сколько осталось банок по 200 г ?");
                    string = Integer.toString(a);
                }
                if (m1 == 1) {
                    int y = g - b1 * 200;
                    int a = y / 400;
                    textViewBigTask.setText(b + " кг варенья разложили по банкам. Банок по 200 г оказалось " + b1 + ". Сколько осталось банок по 400 г ?");
                    string = Integer.toString(a);
                }
            }
            if (m < 7) {
                notation.setText(null);
                String send = bugs.add(task.getText().toString().substring(0, task.getText().toString().length() - 1)) + string;
                bugs.add(send);
            }
            if (m > 7) {
                String send = bugs.add(textViewBigTask.getText().toString().substring(0, textViewBigTask.getText().toString().length() - 1)) + string;
                bugs.add(send);
            } else bugs.add(signsTask);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //STATISTIC CODE START (LOAD)
        SharedPreferences sharedPreferences = getSharedPreferences("statistic", MODE_PRIVATE);
        CountRightS = sharedPreferences.getLong("4-right", 0);
        CountNotRightS = sharedPreferences.getLong("4-not_right", 0);
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
        editor.putLong("4-right", countRight + CountRightS);
        editor.putLong("4-not_right", countNotRight + CountNotRightS);
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
