package ru.slava.mathematics;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.RemoteMessage;

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
public class MyFirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    private static final int NOTIFICATION_ID = 101;
    private static final String CHANNEL_ID = "FIREBASE_NOTIFICATION";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        try {
            sendNotification(remoteMessage.getNotification().getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onMessageReceived(remoteMessage);
    }

    private void sendNotification(String messageBody) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                        .setAutoCancel(true)
                        .setSmallIcon(R.drawable.ic_notification_logo)
                        .setWhen(System.currentTimeMillis())
                        .setContentIntent(pendingIntent)
                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                        .setTicker("Новое уведомление!")
                        .setContentTitle(this.getString(R.string.app_name))
                        .setContentText(messageBody)
                        .setSound(defaultSound)
                        .setVibrate(new long[]{300L, 300L})
                        .setColor(Color.argb(100, 141, 153, 227))
                        .setPriority(NotificationCompat.PRIORITY_MAX);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        assert notificationManager != null;
        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build());
    }
}
