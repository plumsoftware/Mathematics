package ru.slava.mathematics;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
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

public class Class2 extends AppCompatActivity {
    private String string, se = "";
    private int countRight = 0;
    private int countNotRight = 0;
    private Animation animation;
    private long CountRightS;
    private long CountNotRightS;
    private ArrayList<String> bugs = new ArrayList<>();
    //ADS
    //private BannerAdView mBannerAdView;
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
            getSupportActionBar().setTitle("2 Класс");
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

        SpannableString content = new SpannableString("    ");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        answerTV.setText(content);
        bigAnswer.setText(content);

        answerTV.setVisibility(View.GONE);
        bigAnswer.setVisibility(View.GONE);

        l1.setVisibility(View.GONE);
        l2.setVisibility(View.VISIBLE);

        task.setTextSize(30f);
        answerTV.setTextSize(30f);

        Random random = new Random();
        int m = random.nextInt(4);
        int num1 = random.nextInt(1000);
        int num2 = random.nextInt(1001);
        int sum = num1 + num2;
        int sub = sum - num2;
        int Anum1 = random.nextInt(10) + 1;
        int Anum2 = random.nextInt(10) + 1;
        int mul = Anum1 * Anum2;
        int div = mul / Anum1;
        String stringSumTask = Integer.toString(num1) + " + " + Integer.toString(num2) + " = ";
        String stringSubTask = Integer.toString(sum) + " - " + Integer.toString(num2) + " = ";
        String stringMulTask = Integer.toString(Anum1) + " × " + Integer.toString(Anum2) + " = ";
        String stringDivTask = Integer.toString(mul) + " : " + Integer.toString(Anum1) + " = ";
        switch (m) {
            case 0:
                string = Integer.toString(sum);
                task.setText(stringSumTask);
                answerTV.setVisibility(View.VISIBLE);
                break;
            case 1:
                string = Integer.toString(num1);
                task.setText(stringSubTask);
                answerTV.setVisibility(View.VISIBLE);
                break;
            case 2:
                string = Integer.toString(mul);
                task.setText(stringMulTask);
                answerTV.setVisibility(View.VISIBLE);
                break;
            case 3:
                string = Integer.toString(div);
                task.setText(stringDivTask);
                answerTV.setVisibility(View.VISIBLE);
                break;
        }
        String send = bugs.add(task.getText().toString().substring(0, task.getText().toString().length() - 1)) + string;
        bugs.add(send);
        answerTV.setText(content);
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
        int m = random.nextInt(4);
        int num1 = random.nextInt(1000);
        int num2 = random.nextInt(1001);
        int sum = num1 + num2;
        int sub = sum - num2;
        int Anum1 = random.nextInt(10) + 1;
        int Anum2 = random.nextInt(10) + 1;
        int mul = Anum1 * Anum2;
        int div = mul / Anum1;
        String stringSumTask = Integer.toString(num1) + " + " + Integer.toString(num2) + " = ";
        String stringSubTask = Integer.toString(sum) + " - " + Integer.toString(num2) + " = ";
        String stringMulTask = Integer.toString(Anum1) + " × " + Integer.toString(Anum2) + " = ";
        String stringDivTask = Integer.toString(mul) + " : " + Integer.toString(Anum1) + " = ";
        switch (m) {
            case 0:
                string = Integer.toString(sum);
                task.setText(stringSumTask);
                answerTV.setVisibility(View.VISIBLE);
                break;
            case 1:
                string = Integer.toString(num1);
                task.setText(stringSubTask);
                answerTV.setVisibility(View.VISIBLE);
                break;
            case 2:
                string = Integer.toString(mul);
                task.setText(stringMulTask);
                answerTV.setVisibility(View.VISIBLE);
                break;
            case 3:
                string = Integer.toString(div);
                task.setText(stringDivTask);
                answerTV.setVisibility(View.VISIBLE);
                break;
        }
        String send = bugs.add(task.getText().toString().substring(0, task.getText().toString().length() - 1)) + string;
        bugs.add(send);
        SpannableString content = new SpannableString("    ");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        answerTV.setText(content);
        bigAnswer.setText(content);
        userAnswer.delete(0, userAnswer.length());
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
            int m = random.nextInt(4);
            int num1 = random.nextInt(1000);
            int num2 = random.nextInt(1001);
            int sum = num1 + num2;
            int sub = sum - num2;
            int Anum1 = random.nextInt(10) + 1;
            int Anum2 = random.nextInt(10) + 1;
            int mul = Anum1 * Anum2;
            int div = mul / Anum1;
            SpannableString content = new SpannableString("    ");
            content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
            answerTV.setText(content);
            String stringSumTask = Integer.toString(num1) + " + " + Integer.toString(num2) + " = ";
            String stringSubTask = Integer.toString(sum) + " - " + Integer.toString(num2) + " = ";
            String stringMulTask = Integer.toString(Anum1) + " × " + Integer.toString(Anum2) + " = ";
            String stringDivTask = Integer.toString(mul) + " : " + Integer.toString(Anum1) + " = ";
            switch (m) {
                case 0:
                    string = Integer.toString(sum);
                    task.setText(stringSumTask);
                    answerTV.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    string = Integer.toString(num1);
                    task.setText(stringSubTask);
                    answerTV.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    string = Integer.toString(mul);
                    task.setText(stringMulTask);
                    answerTV.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    string = Integer.toString(div);
                    task.setText(stringDivTask);
                    answerTV.setVisibility(View.VISIBLE);
                    break;
            }
            String send = bugs.add(task.getText().toString().substring(0, task.getText().toString().length() - 1)) + string;
            bugs.add(send);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //STATISTIC CODE START (LOAD)
        SharedPreferences sharedPreferences = getSharedPreferences("statistic", MODE_PRIVATE);
        CountRightS = sharedPreferences.getLong("2-right", 0);
        CountNotRightS = sharedPreferences.getLong("2-not_right", 0);
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
        editor.putLong("2-right", countRight + CountRightS);
        editor.putLong("2-not_right", countNotRight + CountNotRightS);
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