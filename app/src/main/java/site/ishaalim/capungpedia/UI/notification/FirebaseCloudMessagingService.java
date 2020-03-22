package site.ishaalim.capungpedia.UI.notification;

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

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import site.ishaalim.capungpedia.R;

public class FirebaseCloudMessagingService extends FirebaseMessagingService {
    FirebaseFirestore firestore;
    public String TAG = "FIREBASE MESSAGING";
    String body, NotifUrl;
    String Characters = "ABCDEFGHIJKMNOPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz0123456789";
    String docID = "";
    int IDLength = 20;
    Date date;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        setupFirestore();
        generateID();

        if (remoteMessage.getData().size() > 0){
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            body = remoteMessage.getData().get("body");
            NotifUrl = remoteMessage.getData().get("URL");
            date = new Date();
            saveNotification(body, NotifUrl, date);
            showNotification(body, NotifUrl);
        }

        Log.d(TAG, "From: " + remoteMessage.getFrom());
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

    private void setupFirestore(){
        firestore = FirebaseFirestore.getInstance();
    }

    private void generateID(){
        Random random = new Random();

        char[] chars = new char[IDLength];

        for (int i = 0; i < IDLength; i++){
            chars[i] = Characters.charAt(random.nextInt(Characters.length()));
        }

        for (int i = 0; i < chars.length; i++){
            docID += chars[i];
        }
    }

    private void saveNotification(String messageBody, String notifUrl, Date date){
        Map<String, Object> notification = new HashMap<>();
        notification.put("messageBody", messageBody);
        notification.put("notifUrl", notifUrl);
        notification.put("notifDate", date);

        firestore.collection("notification")
                .document(docID)
                .set(notification);
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

        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel notificationChannel=new NotificationChannel(channel_id,"pengamatan",NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setSound(defaultSoundUri,null);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        notificationManager.notify(0,builder.build());

    }
}
