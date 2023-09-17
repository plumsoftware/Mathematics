package ru.slava.mathematics;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
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
import com.yandex.mobile.ads.common.AdRequest;
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

public class Class3 extends AppCompatActivity {
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
            getSupportActionBar().setTitle("3 Класс");
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
                        startActivity(new Intent(Class3.this, MainActivity.class));
                    }

                    @Override
                    public void onAdClicked() {
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(new Intent(Class3.this, MainActivity.class));
                    }

                    @Override
                    public void onAdImpression(@Nullable ImpressionData impressionData) {

                    }
                });
                mInterstitialAd.show(Class3.this);
            }

            @Override
            public void onAdFailedToLoad(@NonNull final AdRequestError adRequestError) {
                finish();
                overridePendingTransition(0, 0);
                startActivity(new Intent(Class3.this, MainActivity.class));
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

        answerTV.setVisibility(View.VISIBLE);
        bigAnswer.setVisibility(View.GONE);

        l1.setVisibility(View.GONE);
        l2.setVisibility(View.VISIBLE);

        Random random = new Random();
        int num1 = random.nextInt(1000);
        int num2 = random.nextInt(1001);
        int num3 = random.nextInt(1000);
        int num11 = random.nextInt(100) + 1;
        int num22 = random.nextInt(100) + 1;
        int sum = num1 + num2;
        int sub = sum - num2;
        int mul = num11 * num22;
        int div = mul / num11;
        int sumScobca = (num1 + num2) + num3;
        int sumMulScobca = (num1 + num2) * num11;
        int mulDivScobca = div * num1;
        String signsTask = "";
        int m = random.nextInt(8);
        switch (m) {
            case 0:
                getSupportActionBar().setSubtitle(null);
                String stringSumTask = Integer.toString(num1) + " + " + Integer.toString(num2) + " = ";
                string = Integer.toString(sum);
                task.setText(stringSumTask);
                break;
            case 1:
                getSupportActionBar().setSubtitle(null);
                String stringSubTask = Integer.toString(sum) + " - " + Integer.toString(num2) + " = ";
                string = Integer.toString(num1);
                task.setText(stringSubTask);
                break;
            case 2:
                getSupportActionBar().setSubtitle(null);
                String stringMulTask = Integer.toString(num11) + " × " + Integer.toString(num22) + " = ";
                string = Integer.toString(mul);
                task.setText(stringMulTask);
                break;
            case 3:
                getSupportActionBar().setSubtitle(null);
                String stringDivTask = Integer.toString(mul) + " : " + Integer.toString(num11) + " = ";
                string = Integer.toString(div);
                task.setText(stringDivTask);
                break;
            case 4:
                getSupportActionBar().setSubtitle(null);
                String stringSumScobcaTask = "(" + Integer.toString(num1) + " + " + Integer.toString(num2) + ") + " + Integer.toString(num3) + " = ";
                string = Integer.toString(sumScobca);
                task.setText(stringSumScobcaTask);
                break;
            case 5:
                getSupportActionBar().setSubtitle(null);
                String stringSumMulTask = "(" + Integer.toString(num1) + " + " + Integer.toString(num2) + ") × " + Integer.toString(num11) + " = ";
                string = Integer.toString(sumMulScobca);
                task.setText(stringSumMulTask);
                break;
            case 6:
                getSupportActionBar().setSubtitle(null);
                String stringMulDivTask = "(" + Integer.toString(mul) + " : " + Integer.toString(num11) + ") × " + Integer.toString(num1) + " = ";
                string = Integer.toString(mulDivScobca);
                task.setText(stringMulDivTask);
                break;
            case 7:
                getSupportActionBar().setSubtitle(" > , <  , = ");
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
                    task.setHintTextColor(Color.parseColor("#FF802B"));
                    string = "=";
                    task.setText(Integer.toString(num1));
                    notation.setText(Integer.toString(num2));
                }
                signsTask = Integer.toString(num1) + " " + string + " " + Integer.toString(num2);
                break;
        }
        if (m != 7) {
            notation.setText(null);
            String send = bugs.add(task.getText().toString().substring(0, task.getText().toString().length() - 1)) + string;
            bugs.add(send);
        } else
            bugs.add(signsTask);
    }

    @SuppressLint("SetTextI18n")
    public void check(View view) {
        view.startAnimation(animation);
        se = "";
        answerTV.setVisibility(View.VISIBLE);
        bigAnswer.setVisibility(View.GONE);

        l1.setVisibility(View.GONE);
        l2.setVisibility(View.VISIBLE);
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
        int num1 = random.nextInt(1000);
        int num2 = random.nextInt(1001);
        int num3 = random.nextInt(1000);
        int num11 = random.nextInt(100) + 1;
        int num22 = random.nextInt(100) + 1;
        int sum = num1 + num2;
        int sub = sum - num2;
        int mul = num11 * num22;
        int div = mul / num11;
        int sumScobca = (num1 + num2) + num3;
        int sumMulScobca = (num1 + num2) * num11;
        int mulDivScobca = div * num1;
        String signsTask = "";
        int m = random.nextInt(8);
        switch (m) {
            case 0:
                getSupportActionBar().setSubtitle(null);
                String stringSumTask = Integer.toString(num1) + " + " + Integer.toString(num2) + " = ";
                string = Integer.toString(sum);
                task.setText(stringSumTask);
                break;
            case 1:
                getSupportActionBar().setSubtitle(null);
                String stringSubTask = Integer.toString(sum) + " - " + Integer.toString(num2) + " = ";
                string = Integer.toString(num1);
                task.setText(stringSubTask);
                break;
            case 2:
                getSupportActionBar().setSubtitle(null);
                String stringMulTask = Integer.toString(num11) + " × " + Integer.toString(num22) + " = ";
                string = Integer.toString(mul);
                task.setText(stringMulTask);
                break;
            case 3:
                getSupportActionBar().setSubtitle(null);
                String stringDivTask = Integer.toString(mul) + " : " + Integer.toString(num11) + " = ";
                string = Integer.toString(div);
                task.setText(stringDivTask);
                break;
            case 4:
                getSupportActionBar().setSubtitle(null);
                String stringSumScobcaTask = "(" + Integer.toString(num1) + " + " + Integer.toString(num2) + ") + " + Integer.toString(num3) + " = ";
                string = Integer.toString(sumScobca);
                task.setText(stringSumScobcaTask);
                break;
            case 5:
                getSupportActionBar().setSubtitle(null);
                String stringSumMulTask = "(" + Integer.toString(num1) + " + " + Integer.toString(num2) + ") × " + Integer.toString(num11) + " = ";
                string = Integer.toString(sumMulScobca);
                task.setText(stringSumMulTask);
                break;
            case 6:
                getSupportActionBar().setSubtitle(null);
                String stringMulDivTask = "(" + Integer.toString(mul) + " : " + Integer.toString(num11) + ") × " + Integer.toString(num1) + " = ";
                string = Integer.toString(mulDivScobca);
                task.setText(stringMulDivTask);
                break;
            case 7:
                getSupportActionBar().setSubtitle(" > , <  , = ");
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
                    task.setHintTextColor(Color.parseColor("#FF802B"));
                    string = "=";
                    task.setText(Integer.toString(num1));
                    notation.setText(Integer.toString(num2));
                }
                signsTask = Integer.toString(num1) + " " + string + " " + Integer.toString(num2);
                break;
        }

        if (m != 7) {
            notation.setText(null);
            String send = bugs.add(task.getText().toString().substring(0, task.getText().toString().length() - 1)) + string;
            bugs.add(send);
        } else
            bugs.add(signsTask);

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
            int num1 = random.nextInt(1000);
            int num2 = random.nextInt(1001);
            int num3 = random.nextInt(1000);
            int num11 = random.nextInt(100) + 1;
            int num22 = random.nextInt(100) + 1;
            int sum = num1 + num2;
            int sub = sum - num2;
            int mul = num11 * num22;
            int div = mul / num11;
            int sumScobca = (num1 + num2) + num3;
            int sumMulScobca = (num1 + num2) * num11;
            int mulDivScobca = div * num1;
            String signsTask = "";
            int m = random.nextInt(8);
            switch (m) {
                case 0:
                    getSupportActionBar().setSubtitle(null);
                    String stringSumTask = Integer.toString(num1) + " + " + Integer.toString(num2) + " = ";
                    string = Integer.toString(sum);
                    task.setText(stringSumTask);
                    break;
                case 1:
                    getSupportActionBar().setSubtitle(null);
                    String stringSubTask = Integer.toString(sum) + " - " + Integer.toString(num2) + " = ";
                    string = Integer.toString(num1);
                    task.setText(stringSubTask);
                    break;
                case 2:
                    getSupportActionBar().setSubtitle(null);
                    String stringMulTask = Integer.toString(num11) + " × " + Integer.toString(num22) + " = ";
                    string = Integer.toString(mul);
                    task.setText(stringMulTask);
                    break;
                case 3:
                    getSupportActionBar().setSubtitle(null);
                    String stringDivTask = Integer.toString(mul) + " : " + Integer.toString(num11) + " = ";
                    string = Integer.toString(div);
                    task.setText(stringDivTask);
                    break;
                case 4:
                    getSupportActionBar().setSubtitle(null);
                    String stringSumScobcaTask = "(" + Integer.toString(num1) + " + " + Integer.toString(num2) + ") + " + Integer.toString(num3) + " = ";
                    string = Integer.toString(sumScobca);
                    task.setText(stringSumScobcaTask);
                    break;
                case 5:
                    getSupportActionBar().setSubtitle(null);
                    String stringSumMulTask = "(" + Integer.toString(num1) + " + " + Integer.toString(num2) + ") × " + Integer.toString(num11) + " = ";
                    string = Integer.toString(sumMulScobca);
                    task.setText(stringSumMulTask);
                    break;
                case 6:
                    getSupportActionBar().setSubtitle(null);
                    String stringMulDivTask = "(" + Integer.toString(mul) + " : " + Integer.toString(num11) + ") × " + Integer.toString(num1) + " = ";
                    string = Integer.toString(mulDivScobca);
                    task.setText(stringMulDivTask);
                    break;
                case 7:
                    getSupportActionBar().setSubtitle(" > , <  , = ");
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
                        task.setHintTextColor(Color.parseColor("#FF802B"));
                        string = "=";
                        task.setText(Integer.toString(num1));
                        notation.setText(Integer.toString(num2));
                    }
                    signsTask = Integer.toString(num1) + " " + string + " " + Integer.toString(num2);
                    break;
            }
            if (m != 7) {
                notation.setText(null);
                String send = bugs.add(task.getText().toString().substring(0, task.getText().toString().length() - 1)) + string;
                bugs.add(send);
            } else
                bugs.add(signsTask);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //STATISTIC CODE START (LOAD)
        SharedPreferences sharedPreferences = getSharedPreferences("statistic", MODE_PRIVATE);
        CountRightS = sharedPreferences.getLong("3-right", 0);
        CountNotRightS = sharedPreferences.getLong("3-not_right", 0);
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
        editor.putLong("3-right", countRight + CountRightS);
        editor.putLong("3-not_right", countNotRight + CountNotRightS);
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
