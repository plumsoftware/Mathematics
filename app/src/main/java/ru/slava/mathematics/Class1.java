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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.yandex.mobile.ads.rewarded.Reward;
import com.yandex.mobile.ads.rewarded.RewardedAd;
import com.yandex.mobile.ads.rewarded.RewardedAdEventListener;

import java.util.ArrayList;
import java.util.Random;

public class Class1 extends AppCompatActivity {
    private String string, se = "";
    private int countRight = 0;
    private int countNotRight = 0;
    private TextView textView;
    private Animation animation;
    private long CountRightS;
    private long CountNotRightS;
    private ArrayList<String> bugs = new ArrayList<>();

    protected StringBuilder userAnswer = new StringBuilder();
    protected TextView answerTV, rAnswer, right, notRight, task, notation, textViewBigTask, bigAnswer;
    protected LinearLayout l1, l2;
    //ADS
    /**
     * private InterstitialAd mInterstitialAd;
     * private AdView adView;
     * private RewardedAd mRewardedAd;
     **/
    //BUTTONS
    private Button frac, dot, more, less, equal, minus;

    //YANDEX ADS
    //private BannerAdView mBannerAdView;
    @Nullable
    private InterstitialAd mInterstitialAd = null;
    @Nullable
    private InterstitialAdLoader mInterstitialAdLoader = null;

    @SuppressLint({"SetTextI18n", "SourceLockedOrientationActivity", "CutPasteId"})
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppThemeClasses);
        setContentView(R.layout.classes_layout);
        try {
            getSupportActionBar().setTitle("1 Класс");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayUseLogoEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // ADS CODE START
        // INTERSTITIAL
        //ID_ADS id_ads = new ID_ADS();
        /**
         MobileAds.initialize(Class1.this, new OnInitializationCompleteListener() {
        @Override public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {

        }
        });
         **/

        /** Yandex **/
        MobileAds.initialize(this, new InitializationListener() {
            @Override
            public void onInitializationCompleted() {

            }
        });

        // Создание экземпляра mBannerAdView.
//        mBannerAdView = (BannerAdView) findViewById(R.id.adView);
//        mBannerAdView.setAdUnitId(getResources().getString(R.string.Banner_id));
//        mBannerAdView.setAdSize(AdSize.BANNER_320x50);
//
//        // Создание объекта таргетирования рекламы.
//        final AdRequest adRequest = new AdRequest.Builder().build();
//
//        // Регистрация слушателя для отслеживания событий, происходящих в баннерной рекламе.
//        mBannerAdView.setBannerAdEventListener(new BannerAdEventListener() {
//            @Override
//            public void onAdLoaded() {
//
//            }
//
//            @Override
//            public void onAdFailedToLoad(@NonNull AdRequestError adRequestError) {
//
//            }
//
//            @Override
//            public void onAdClicked() {
//
//            }
//
//            @Override
//            public void onLeftApplication() {
//
//            }
//
//            @Override
//            public void onReturnedToApplication() {
//
//            }
//
//            @Override
//            public void onImpression(@Nullable ImpressionData impressionData) {
//
//            }
//        });
//
//        // Загрузка объявления.
//        mBannerAdView.loadAd(adRequest);

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
                        startActivity(new Intent(Class1.this, MainActivity.class));
                    }

                    @Override
                    public void onAdClicked() {
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(new Intent(Class1.this, MainActivity.class));
                    }

                    @Override
                    public void onAdImpression(@Nullable ImpressionData impressionData) {

                    }
                });
                mInterstitialAd.show(Class1.this);
            }

            @Override
            public void onAdFailedToLoad(@NonNull final AdRequestError adRequestError) {
                finish();
                overridePendingTransition(0, 0);
                startActivity(new Intent(Class1.this, MainActivity.class));
            }
        });
        /** **/

        /**
         AdRequest adRequest = new AdRequest.Builder().build();

         InterstitialAd.load(this, id_ads.ID_INTERSTITIAL_AD_1, adRequest,
         new InterstitialAdLoadCallback() {
        @Override public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
        // The mInterstitialAd reference will be null until
        // an ad is loaded.
        mInterstitialAd = interstitialAd;
        }

        @Override public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
        // Handle the error
        mInterstitialAd = null;
        }
        });
         **/

        /**
         if (mInterstitialAd != null) {
         mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
        @Override public void onAdDismissedFullScreenContent() {
        // Called when fullscreen content is dismissed.

        try {
        finish();
        overridePendingTransition(R.anim.anim_layout_enter_bottom, R.anim.anim_layout_close_top);
        } catch (Exception e) {
        e.printStackTrace();
        }
        }

        @Override public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
        // Called when fullscreen content failed to show.
        }

        @Override public void onAdShowedFullScreenContent() {
        // Called when fullscreen content is shown.
        // Make sure to set your reference to null so you don't
        // show it a second time.
        mInterstitialAd = null;
        }
        });
         }

         //INTERSTITIAL AD2
         //BANNER
         adView = (AdView) findViewById(R.id.adView);
         AdRequest adRequest1 = new AdRequest.Builder().build();
         //adView.loadAd(adRequest1);
         adView.setVisibility(View.VISIBLE);
         //ADS CODE END
         **/

        animation = AnimationUtils.loadAnimation(this, R.anim.click_anim);
        TextView textView1 = (TextView) findViewById(R.id.right);
        textView = (TextView) findViewById(R.id.task);
        //textView.setTextSize(30);
        textView1.setText(Integer.toString(countRight));
        TextView textView2 = (TextView) findViewById(R.id.notRight);
        textView2.setText(Integer.toString(countNotRight));

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
        int m = random.nextInt(2);
        int num1 = random.nextInt(100);
        int num2 = random.nextInt(101);
        int sum = num1 + num2;
        int sub = sum - num1;
        String stringSumTask = Integer.toString(num1) + " + " + Integer.toString(num2) + " = ";
        String stringSubTask = Integer.toString(sum) + " - " + Integer.toString(num1) + " = ";
        switch (m) {
            case 0:
                string = Integer.toString(sum);
                textView.setText(stringSumTask);
                answerTV.setVisibility(View.VISIBLE);
                break;
            case 1:
                string = Integer.toString(num2);
                textView.setText(stringSubTask);
                answerTV.setVisibility(View.VISIBLE);
                break;
        }

        String send = bugs.add(textView.getText().toString().substring(0, textView.getText().toString().length() - 1)) + string;
        bugs.add(send);
    }

    @SuppressLint("SetTextI18n")
    public void check(View view) {
        view.startAnimation(animation);
        se = "";
        bigAnswer.setVisibility(View.GONE);
        answerTV.setVisibility(View.GONE);
        //setClickers();
        //showRewardedAd(countRight, countNotRight);
        //showAd(countRight, countNotRight);
        //TextView editText = (TextView) findViewById(R.id.answer);
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
        TextView textView1 = (TextView) findViewById(R.id.rAnswer);
        textView1.setText(string);
        textView = (TextView) findViewById(R.id.task);
        Random random = new Random();
        int m = random.nextInt(2);
        int num1 = random.nextInt(100);
        int num2 = random.nextInt(101);
        int sum = num1 + num2;
        int sub = sum - num1;
        String stringSumTask = Integer.toString(num1) + " + " + Integer.toString(num2) + " = ";
        String stringSubTask = Integer.toString(sum) + " - " + Integer.toString(num1) + " = ";
        switch (m) {
            case 0:
                string = Integer.toString(sum);
                textView.setText(stringSumTask);
                answerTV.setVisibility(View.VISIBLE);
                break;
            case 1:
                string = Integer.toString(num2);
                textView.setText(stringSubTask);
                answerTV.setVisibility(View.VISIBLE);
                break;
        }
        String send = bugs.add(textView.getText().toString().substring(0, textView.getText().toString().length() - 1)) + string;
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
        //adView.destroy();
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
            textView = (TextView) findViewById(R.id.task);
            Random random = new Random();
            //setClickers();
            answerTV.setVisibility(View.GONE);
            bigAnswer.setVisibility(View.GONE);
            int m = random.nextInt(2);
            int num1 = random.nextInt(100);
            int num2 = random.nextInt(101);
            int sum = num1 + num2;
            int sub = sum - num1;
            String stringSumTask = Integer.toString(num1) + " + " + Integer.toString(num2) + " = ";
            String stringSubTask = Integer.toString(sum) + " - " + Integer.toString(num1) + " = ";
            switch (m) {
                case 0:
                    string = Integer.toString(sum);
                    textView.setText(stringSumTask);
                    answerTV.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    string = Integer.toString(num2);
                    textView.setText(stringSubTask);
                    answerTV.setVisibility(View.VISIBLE);
                    break;
            }
            String send = bugs.add(textView.getText().toString().substring(0, textView.getText().toString().length() - 1)) + string;
            bugs.add(send);

            SpannableString content = new SpannableString("    ");
            content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
            answerTV.setText(content);
            bigAnswer.setText(content);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //adView.resume();
        //STATISTIC CODE START (LOAD)
        SharedPreferences sharedPreferences = getSharedPreferences("statistic", MODE_PRIVATE);
        CountRightS = sharedPreferences.getLong("1-right", 0);
        CountNotRightS = sharedPreferences.getLong("1-not_right", 0);
        //--------------------
    }

    @Override
    public void onBackPressed() {
        /**
         if (mInterstitialAd != null) {
         mInterstitialAd.show(Class1.this);//show ads
         } else {
         finish();
         overridePendingTransition(R.anim.anim_layout_enter_bottom, R.anim.anim_layout_close_top);
         }
         **/
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
        editor.putLong("1-right", countRight + CountRightS);
        editor.putLong("1-not_right", countNotRight + CountNotRightS);
        editor.apply();
        //STATISTIC CODE END
    }

    @SuppressLint("IntentReset")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            /**
             if (mInterstitialAd != null) {
             mInterstitialAd.show(Class1.this);//show ads
             } else {
             **/
            //finish();
            //overridePendingTransition(R.anim.anim_layout_enter_bottom, R.anim.anim_layout_close_top);
            //}
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

    //Clickers
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
