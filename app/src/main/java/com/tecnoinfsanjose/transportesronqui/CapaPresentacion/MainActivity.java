package com.tecnoinfsanjose.transportesronqui.CapaPresentacion;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.tecnoinfsanjose.transportesronqui.CapaDatos.DataViajeDB;
import com.tecnoinfsanjose.transportesronqui.CapaDatos.ViajeDB;
import com.tecnoinfsanjose.transportesronqui.CapaLogica.Controllers.SincronizacionDatos;
import com.tecnoinfsanjose.transportesronqui.CapaLogica.Controllers.UsuarioControlador;
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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#000000"));

        final Intent alarmIntent = new Intent(MainActivity.this, AlarmReceiver.class);
        if(pendingIntent==null){
            pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
            startAt1230();
        }


        ImageButton Sinc = (ImageButton) findViewById(R.id.SincButton);
        Sinc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(!SincronizacionDatos.isSincroniced(getApplicationContext())){
                        SincronizacionDatos.Sincroniced(getApplicationContext());

                    }
                    else{
                        Toast.makeText(getApplicationContext(),"No existen Datos para Sincronizar",Toast.LENGTH_LONG).show();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

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
                    new AlarmReceiver().createNotification("Existen Datos a Sincronizar",getApplicationContext());

                DataViajeDB prueba = new DataViajeDB();
                Viaje viaje1 = new Viaje(1,"San Jose","Montevideo","Soja","Lucas",98848100,43422063);
                Viaje viaje2 = new Viaje(2,"San Jose","Montevideo","Soja","Lucas",98848100,43422063);
                Viaje viaje3 = new Viaje(3,"San Jose","San Jose","Trigo","Carlos",98848100,43422063);
                Viaje viaje4 = new Viaje(4,"San Jose","Rocha","Maiz","Pepe",98848100,43422063);
                Viaje viaje7 = new Viaje(7,"San Jose","Canelones","Soja","Jose",98848100,43422063);
                ViajeDB DBViaje = new ViajeDB();
                if(DBViaje.PersistirViaje(viaje1, getApplicationContext())&&
                        DBViaje.PersistirViaje(viaje2, getApplicationContext())&&
                        DBViaje.PersistirViaje(viaje3, getApplicationContext())&&
                        DBViaje.PersistirViaje(viaje4, getApplicationContext())&&
                        DBViaje.PersistirViaje(viaje7, getApplicationContext()))
                {
                    Toast.makeText(getApplicationContext(),"Datos Cargados Correctamente",Toast.LENGTH_LONG).show();

                }
                Date fecha = new Date();
                float ton = 123;
//                MainActivity.deleteViaje(1,getApplicationContext());
                Data_Viaje data1 = new Data_Viaje(1,fecha,123,ton,123,2134,"observaciones","ruta1","ruta2","ruta3");
                if(prueba.PersistirDataViaje(data1,getApplicationContext())){
                    try {
                        prueba.getViajes(getApplicationContext());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    public void startAt1230() {
        manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        /* Set the alarm to start at 20:00 hs */
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 30);
        calendar.set(Calendar.SECOND, 0);
        Calendar cal = Calendar.getInstance();
        if(calendar.compareTo(cal) <=0)
            calendar.add(Calendar.DATE,1);
        /* Repeating on every one day interval */
        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);
    }
    @Override public void onBackPressed() { moveTaskToBack(true); }

    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.logout:
                UsuarioControlador cont = new UsuarioControlador();
                SharedPreferences preferences = getSharedPreferences("USER_DATA",Context.MODE_PRIVATE);
                String key = preferences.getString("USER_KEY",null);
                Integer ci = preferences.getInt("USER_CI",0);
                String pass = preferences.getString("USER_PASS",null);
                cont.logout(ci,getApplicationContext());
                Intent intent2 = new Intent (getApplicationContext(), LoginActivity.class);
                startActivityForResult(intent2, 0);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
