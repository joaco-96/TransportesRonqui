package com.tecnoinfsanjose.transportesronqui.Utilidades;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.media.MediaPlayer;

import android.os.Build;
import android.support.v4.app.NotificationCompat;


import com.tecnoinfsanjose.transportesronqui.CapaDatos.DataViajeDB;
import com.tecnoinfsanjose.transportesronqui.CapaLogica.Entities.Data_Viaje;
import com.tecnoinfsanjose.transportesronqui.CapaPresentacion.MainActivity;
import com.tecnoinfsanjose.transportesronqui.R;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import java.util.Random;


public class AlarmReceiver extends BroadcastReceiver {
    MediaPlayer mMediaPlayer;
    public boolean procedencia;
    boolean activado=false;
    Calendar cal = Calendar.getInstance();
    public DataViajeDB FinalizarViajes;
    private NotificationManager notifManager;
    private Intent intent;
    @Override
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            Calendar ca = Calendar.getInstance();
            ca.set(Calendar.HOUR_OF_DAY, 12);
            ca.set(Calendar.MINUTE, 35);
            ca.set(Calendar.SECOND, 0);
            if(ca.compareTo(cal) <=0)
                ca.add(Calendar.DATE,1);

            AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            Intent alarmIntent = new Intent(context, AlarmReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);
            manager.setRepeating(AlarmManager.RTC_WAKEUP, ca.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, pendingIntent);
        }


        FinalizarViajes = new DataViajeDB();
        try {
            List<Data_Viaje> viajes = FinalizarViajes.getViajes(context);


            if(viajes.size()>=1){


                    createNotification("Existen Datos por Sincronizar", context);

                    Calendar ca = Calendar.getInstance();
                    ca.set(Calendar.HOUR_OF_DAY, 12);
                    ca.set(Calendar.MINUTE, 30);
                    ca.set(Calendar.SECOND, 0);
                    if(ca.compareTo(cal) <=0)
                        ca.add(Calendar.DATE,1);

                    AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                    Intent alarmIntent = new Intent(context, AlarmReceiver.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);
                    manager.setRepeating(AlarmManager.RTC_WAKEUP, ca.getTimeInMillis(),
                            AlarmManager.INTERVAL_DAY, pendingIntent);

            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void createNotification(String aMessage, Context context) {
        final int NOTIFY_ID = 0; // ID of notification
        String id = "Transportes Ronqui"; // default_channel_id
        String title = "Sincronizar Datos";
        PendingIntent pendingIntent;
        NotificationCompat.Builder builder;
        if (notifManager == null) {
            notifManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = notifManager.getNotificationChannel(id);
            if (mChannel == null) {
                mChannel = new NotificationChannel(id, title, importance);
                mChannel.enableVibration(true);
                mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                notifManager.createNotificationChannel(mChannel);
            }
            builder = new NotificationCompat.Builder(context, id);
            intent = new Intent(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            builder.setContentTitle(aMessage)                            // required
                    .setSmallIcon(android.R.drawable.ic_popup_reminder)   // required
                    .setContentText(context.getString(R.string.app_name)) // required
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setTicker(aMessage)
                    .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        }
        else {
            builder = new NotificationCompat.Builder(context, id);
            intent = new Intent(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            builder.setContentTitle(aMessage)                            // required
                    .setSmallIcon(android.R.drawable.ic_popup_reminder)   // required
                    .setContentText(context.getString(R.string.app_name)) // required
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setTicker(aMessage)
                    .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400})
                    .setPriority(Notification.PRIORITY_HIGH);
        }
        Notification notification = builder.build();
        notifManager.notify(NOTIFY_ID, notification);
    }

}
