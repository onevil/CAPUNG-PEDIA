package site.ishaalim.capungpedia.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import site.ishaalim.capungpedia.R;

public class FirebaseCloudMessagingService extends FirebaseMessagingService {
    public String TAG = "FIREBASE MESSAGING";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        if (remoteMessage.getData().size() > 0){
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            showNotification(remoteMessage.getData().get("body"), remoteMessage.getData().get("URL"));
        }

        Log.d(TAG, "From: " + remoteMessage.getFrom());
//        showNotification(remoteMessage.getNotification().getBody());
        if (remoteMessage.getNotification() != null){
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }
    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        sendRegistrationToServer(s);
    }

    private void sendRegistrationToServer(String s) {

    }

    private void showNotification(String messageBody, String notifUrl) {
        Intent intent = new Intent(this, NotificationActivity.class);
        intent.putExtra("URL", notifUrl);
        String channel_id = "pengamatan_capung";
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder  = new NotificationCompat.Builder(getApplicationContext(), channel_id)
                .setSmallIcon(R.drawable.ic_dragonfly)
                .setContentTitle("Capung Pedia")
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .setLargeIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(),
                        R.mipmap.ic_launcher_round))
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

//        NotificationManager notificationManager =
//                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel notificationChannel=new NotificationChannel(channel_id,"pengamatan",NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setSound(defaultSoundUri,null);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        notificationManager.notify(0,builder.build());



//        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getApplicationContext());
//        managerCompat.notify(0, notification);
//        Log.d(TAG, "NOTIF!");
//        notificationManager.notify(0, notificationBuilder.build());
    }
}
