package com.tecnoinfsanjose.transportesronqui.CapaPresentacion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tecnoinfsanjose.transportesronqui.CapaDatos.ViajeDB;
import com.tecnoinfsanjose.transportesronqui.CapaLogica.Entities.Viaje;
import com.tecnoinfsanjose.transportesronqui.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                Viaje viaje1 = new Viaje(1,"San Jose","Montevideo","Soja",98848100);
                Viaje viaje2 = new Viaje(2,"San Jose","Montevideo","Soja",98848100);
                Viaje viaje3 = new Viaje(3,"San Jose","Montevideo","Soja",98848100);
                Viaje viaje4 = new Viaje(4,"San Jose","Montevideo","Soja",98848100);
                Viaje viaje5 = new Viaje(5,"San Jose","Montevideo","Soja",98848100);
                Viaje viaje6 = new Viaje(6,"San Jose","Montevideo","Soja",98848100);
                Viaje viaje7 = new Viaje(7,"San Jose","Montevideo","Soja",98848100);
                ViajeDB DBViaje = new ViajeDB();
                if(
                DBViaje.PersistirViaje(viaje1, getApplicationContext())&&
                DBViaje.PersistirViaje(viaje2, getApplicationContext())&&
                DBViaje.PersistirViaje(viaje3, getApplicationContext())&&
                DBViaje.PersistirViaje(viaje4, getApplicationContext())&&
                DBViaje.PersistirViaje(viaje5, getApplicationContext())&&
                DBViaje.PersistirViaje(viaje6, getApplicationContext())&&
                DBViaje.PersistirViaje(viaje7, getApplicationContext()))
                {
                    Toast.makeText(getApplicationContext(),"Datos Cargados Correctamente",Toast.LENGTH_LONG).show();

                }

            }
        });
    }
}
