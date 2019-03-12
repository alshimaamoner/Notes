package com.example.hp.notes;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

public class TaskAlarmBroadCastReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String title=intent.getStringExtra("title");
        String desc =intent.getStringExtra("desc");

 showNotifications(title,desc,context);
    }
  public void   showNotifications(String Title,String desc,Context context){
      NotificationManager notificationManager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
      NotificationCompat.Builder builder= new NotificationCompat.Builder(context,"Todo")
              .setSmallIcon(R.mipmap.ic_launcher).setContentText(desc).
                      setContentTitle(Title);
      Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
      builder.setSound(uri);
      //vibrate
      long[] v = {500,1000};
      builder.setVibrate(v);
              notificationManager.notify(1,builder.build());

    }


}
