package com.tecnoinfsanjose.transportesronqui.CapaPresentacion;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tecnoinfsanjose.transportesronqui.CapaDatos.DataViajeDB;
import com.tecnoinfsanjose.transportesronqui.CapaDatos.ViajeDB;
import com.tecnoinfsanjose.transportesronqui.CapaLogica.Entities.Data_Viaje;
import com.tecnoinfsanjose.transportesronqui.CapaLogica.Entities.Viaje;
import com.tecnoinfsanjose.transportesronqui.R;
import com.tecnoinfsanjose.transportesronqui.Utilidades.AlarmReceiver;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {



    private PendingIntent pendingIntent = null;
    private AlarmManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Intent alarmIntent = new Intent(MainActivity.this, AlarmReceiver.class);
        if(pendingIntent==null){
            pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
            startAt20();
        }

        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), VerViajeActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        Button btn2 = (Button) findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent (v.getContext(), MantenimientoActivity.class);
                startActivityForResult(intent2, 0);
            }
        });

        Button BotonDB = (Button) findViewById(R.id.BotonDB);
        BotonDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    AlarmReceiver al = new AlarmReceiver();
                    al.onReceive(getApplicationContext(),alarmIntent);

                Notification.Builder nbuild = new Notification.Builder(getApplicationContext());
                nbuild.setAutoCancel(true);
                nbuild.setVibrate(new long[] {100, 250, 100, 500});
                nbuild.setContentText("Esta es la notificacion");
                nbuild.setContentTitle("Este es el Titulo");
                nbuild.setContentInfo("esta es la Info");
                nbuild.setTicker("Esta es la aprimera Notificacion");
                nbuild.setSmallIcon(android.R.drawable.alert_dark_frame);
                NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.notify(21312,nbuild.build());
//                DataViajeDB prueba = new DataViajeDB();
//                Viaje viaje1 = new Viaje(1,"San Jose","Montevideo","Soja","Lucas",98848100,43422063);
//                Viaje viaje2 = new Viaje(2,"San Jose","Montevideo","Soja","Lucas",98848100,43422063);
//                Viaje viaje3 = new Viaje(3,"San Jose","Montevideo","Soja","Lucas",98848100,43422063);
//                Viaje viaje4 = new Viaje(4,"San Jose","Montevideo","Soja","Lucas",98848100,43422063);
//                Viaje viaje5 = new Viaje(5,"San Jose","Montevideo","Soja","Lucas",98848100,43422063);
//                Viaje viaje6 = new Viaje(6,"San Jose","Montevideo","Soja","Lucas",98848100,43422063);
//                Viaje viaje7 = new Viaje(7,"San Jose","Montevideo","Soja","Lucas",98848100,43422063);
//                ViajeDB DBViaje = new ViajeDB();
//                if(
//                DBViaje.PersistirViaje(viaje1, getApplicationContext())&&
//                DBViaje.PersistirViaje(viaje2, getApplicationContext())&&
//                DBViaje.PersistirViaje(viaje3, getApplicationContext())&&
//                DBViaje.PersistirViaje(viaje4, getApplicationContext())&&
//                DBViaje.PersistirViaje(viaje5, getApplicationContext())&&
//                DBViaje.PersistirViaje(viaje6, getApplicationContext()))
//                {
//                    Toast.makeText(getApplicationContext(),"Datos Cargados Correctamente",Toast.LENGTH_LONG).show();
//
//                }
//                Date fecha = new Date();
//                float ton = 123;
////                prueba.deleteViaje(1,getApplicationContext());
//                Data_Viaje data1 = new Data_Viaje(1,fecha,123,ton,123,2134,"observaciones","ruta1","ruta2","ruta3");
//                if(prueba.PersistirDataViaje(data1,getApplicationContext())){
//                    try {
//                        prueba.getViajes(getApplicationContext());
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//                }
            }
        });

    }

    public void startAt20() {
        manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        /* Set the alarm to start at 20:00 hs */
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 10);
        calendar.set(Calendar.SECOND, 0);
        Calendar cal = Calendar.getInstance();
        if(calendar.compareTo(cal) <=0)
            calendar.add(Calendar.DATE,1);
        /* Repeating on every one day interval */
        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);
    }
    @Override public void onBackPressed() { moveTaskToBack(true); }
}
