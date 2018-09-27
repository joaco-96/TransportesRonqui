package com.tecnoinfsanjose.transportesronqui.CapaPresentacion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.tecnoinfsanjose.transportesronqui.CapaLogica.Controllers.ViajeControlador;
import com.tecnoinfsanjose.transportesronqui.CapaLogica.Entities.Viaje;
import com.tecnoinfsanjose.transportesronqui.CapaPresentacion.Adaptadores.AdaptadorViaje;
import com.tecnoinfsanjose.transportesronqui.R;

import java.util.ArrayList;
import java.util.List;

public class VerViajeActivity extends AppCompatActivity {


    private ListView listView;
    private AdaptadorViaje adapter;
    private List<Viaje> listaViajes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_viaje);
        listaViajes = new ArrayList<Viaje>();
            listaViajes = new ViajeControlador().getViajes(getApplicationContext());
//        Viaje viaje1 = new Viaje(1,"San Jose","Montevideo","Soja",98848100);
//        Viaje viaje2 = new Viaje(2,"San Jose","Montevideo","Soja",98848100);
//        Viaje viaje3 = new Viaje(3,"San Jose","Montevideo","Soja",98848100);
//        Viaje viaje4 = new Viaje(4,"San Jose","Montevideo","Soja",98848100);
//        Viaje viaje5 = new Viaje(5,"San Jose","Montevideo","Soja",98848100);
//        Viaje viaje6 = new Viaje(6,"San Jose","Montevideo","Soja",98848100);
//        Viaje viaje8 = new Viaje(8,"San Jose","Montevideo","Soja",98848100);
//        Viaje viaje9 = new Viaje(9,"San Jose","Montevideo","Soja",98848100);
//        Viaje viaje10 = new Viaje(10,"San Jose","Montevideo","Soja",98848100);
//        Viaje viaje11 = new Viaje(11,"San Jose","Montevideo","Soja",98848100);
//        Viaje viaje12 = new Viaje(12,"San Jose","Montevideo","Soja",98848100);
//        Viaje viaje13 = new Viaje(13,"San Jose","Montevideo","Soja",98848100);
//        listaViajes.add(viaje1);
//        listaViajes.add(viaje2);
//        listaViajes.add(viaje3);
//        listaViajes.add(viaje4);
//        listaViajes.add(viaje5);
//        listaViajes.add(viaje6);
//        listaViajes.add(viaje8);
//        listaViajes.add(viaje9);
//        listaViajes.add(viaje10);
//        listaViajes.add(viaje11);
//        listaViajes.add(viaje12);
//        listaViajes.add(viaje13);
        if(listaViajes!=null){
            listView = (ListView) findViewById(R.id.listView);
            adapter = new AdaptadorViaje(getApplicationContext(),listaViajes);
            listView.setAdapter(adapter);
        }else{

        }



    }


}
