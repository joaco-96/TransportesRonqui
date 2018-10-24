package com.tecnoinfsanjose.transportesronqui.Utilidades;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;


import com.tecnoinfsanjose.transportesronqui.CapaDatos.DataViajeDB;
import com.tecnoinfsanjose.transportesronqui.CapaLogica.Entities.Data_Viaje;
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
    @Override
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            Calendar ca = Calendar.getInstance();
            ca.set(Calendar.HOUR_OF_DAY, 22);
            ca.set(Calendar.MINUTE, 55);
            ca.set(Calendar.SECOND, 0);
            if(ca.compareTo(cal) <=0)
                ca.add(Calendar.DATE,1);

            AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            Intent alarmIntent = new Intent(context, AlarmReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);
            manager.setRepeating(AlarmManager.RTC_WAKEUP, ca.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, pendingIntent);
        }
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        int color = context.getResources().getColor(R.color.colorPrimary);
        //Intent resultIntent = new Intent(context, MainActivity.class);
        //PendingIntent resultPendingIntent = PendingIntent.getActivity(context, 3, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);




        Notification.Builder nbuild = new Notification.Builder(context);
        nbuild.setAutoCancel(true);
        nbuild.setVibrate(new long[] {100, 250, 100, 500});
        nbuild.setContentText("Esta es la notificacion");
        nbuild.setContentTitle("Este es el Titulo");
        nbuild.setContentInfo("esta es la Info");
        nbuild.setTicker("Esta es la aprimera Notificacion");
        nbuild.setSmallIcon(android.R.drawable.alert_dark_frame);

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Random rand = new Random();
        int n= 0;

        FinalizarViajes = new DataViajeDB();
        try {
            List<Data_Viaje> viajes = FinalizarViajes.getViajes(context);


            if(viajes.size()>=1){
                if(cal.get(Calendar.HOUR_OF_DAY) == 21 && cal.get(Calendar.MINUTE) >=15) {

                    n = rand.nextInt(999) + 1;
                    mNotificationManager.notify(n, nbuild.build());

                    Calendar ca = Calendar.getInstance();
                    ca.set(Calendar.HOUR_OF_DAY, 20);
                    ca.set(Calendar.MINUTE, 0);
                    ca.set(Calendar.SECOND, 0);
                    if(ca.compareTo(cal) <=0)
                        ca.add(Calendar.DATE,1);

                    AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                    Intent alarmIntent = new Intent(context, AlarmReceiver.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);
                    manager.setRepeating(AlarmManager.RTC_WAKEUP, ca.getTimeInMillis(),
                            AlarmManager.INTERVAL_DAY, pendingIntent);
                }

                else{
                    //Codigo comentado para la entrega del módulo 2
                    /*Calendar ca = Calendar.getInstance();
                    ca.setTimeInMillis(ca.getTimeInMillis() + 900000); //Le agrego a la hora actual 15 minutos (en milisegundos)
                    AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                    Intent alarmIntent = new Intent(context, AlarmReceiver.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 2, alarmIntent, 0);
                    manager.setExact(AlarmManager.RTC_WAKEUP, ca.getTimeInMillis(), pendingIntent);*/

                    nbuild.setContentTitle("Informacion sin Sincronizar");
                    nbuild.setContentText("Tienes datos sin sincronizar, recuerde tener conexión");
                    n = rand.nextInt(999) + 1;
                    mNotificationManager.notify(n, nbuild.build());
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
