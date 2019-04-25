package com.stayabode.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.stayabode.R;
import com.stayabode.features.login.activities.AdminLoginActivity;


import java.util.HashSet;
import java.util.Set;

import utils.IntentKeys;
import utils.SharedPrefManager;

/**
 * Created by VarunBhalla on 01/09/16.
 */

public class AppFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";
    private static final String IS_PERSISTANT = "1";
    private String mMessage;
    private String mClickAction;
    private String mTitle;
    private String mIsPersistant;
    private String mNotificationId;
    private String mEventId;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //Displaying data in log
        //It is optional
        // Log.d(TAG, "From: " + remoteMessage.getFrom());

        //Calling method to generate notification
        //body title clickaction

        mMessage = remoteMessage.getData().get(IntentKeys.INTENT_NOTIFICATION_BODY);
        mClickAction = remoteMessage.getData().get(IntentKeys.INTENT_NOTIFICATION_CLICK_ACTION);
        mEventId = remoteMessage.getData().get(IntentKeys.INTENT_NOTIFICATION_EVENT_ID);

        mTitle = remoteMessage.getData().get(IntentKeys.INTENT_NOTIFICATION_TITLE);
        mIsPersistant = remoteMessage.getData().get(IntentKeys.INTENT_NOTIFICATION_IS_PERSISTANT);
        mNotificationId = remoteMessage.getData().get(IntentKeys.INTENT_NOTIFICATION_ID);

        Log.i("onMessageReceived",mMessage+" "+mClickAction+" "+mTitle+" "+mIsPersistant);

        sendNotification();
    }

    //This method is only generating push notification
    //It is same as we did in earlier posts
    private void sendNotification() {

        // call in forground


        if(mIsPersistant.equals(IS_PERSISTANT)){
            Set<String> tempUnreadNotifications = new HashSet<>();

            if(null != SharedPrefManager.getInstance().getTempUnReadNotifications()){
                tempUnreadNotifications = SharedPrefManager.getInstance().getTempUnReadNotifications();
            }

            tempUnreadNotifications.add(mNotificationId);
            SharedPrefManager.getInstance().setTempUnReadNotifications(tempUnreadNotifications);
        }


        Intent intent = new Intent(this, AdminLoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);

        intent.putExtra(IntentKeys.INTENT_NOTIFICATION_EVENT_ID,mEventId);
        intent.putExtra(IntentKeys.INTENT_NOTIFICATION_CLICK_ACTION,mClickAction);
        intent.putExtra(IntentKeys.INTENT_NOTIFICATION_IS_PERSISTANT,mIsPersistant);
        intent.putExtra(IntentKeys.INTENT_NOTIFICATION_ID,mNotificationId);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_notification)
                .setContentTitle(mTitle)
                .setContentText(mMessage)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }

}
