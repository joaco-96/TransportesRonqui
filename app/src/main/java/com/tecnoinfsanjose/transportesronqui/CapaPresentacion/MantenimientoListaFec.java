package com.tecnoinfsanjose.transportesronqui.CapaPresentacion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.tecnoinfsanjose.transportesronqui.CapaLogica.Controllers.ViajeControlador;
import com.tecnoinfsanjose.transportesronqui.CapaLogica.Entities.Mantenimiento;
import com.tecnoinfsanjose.transportesronqui.CapaLogica.Entities.Viaje;
import com.tecnoinfsanjose.transportesronqui.CapaPresentacion.Adaptadores.AdaptadorMantenimiento;
import com.tecnoinfsanjose.transportesronqui.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MantenimientoListaFec extends AppCompatActivity {


    private ListView listView;
    private AdaptadorMantenimiento adapter;
    private List<Mantenimiento> listaMantenimientosFec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mantenimiento_lista_fec);
        listaMantenimientosFec = new ArrayList<Mantenimiento>();

        Mantenimiento mante1 = new Mantenimiento(1,true,"Libreta Conducir", 0, new Date(118,11,27));
        Mantenimiento mante2 = new Mantenimiento(3,true,"SUCTA", 0, new Date(118,11,06));
        Mantenimiento mante3 = new Mantenimiento(5,true,"Patente", 0, new Date(118,11,27));
        Mantenimiento mante4 = new Mantenimiento(6,true,"Seguro", 0, new Date(118,11,13));

        listaMantenimientosFec.add(mante1);
        listaMantenimientosFec.add(mante2);
        listaMantenimientosFec.add(mante3);
        listaMantenimientosFec.add(mante4);

        if (listaMantenimientosFec != null) {
            listView = (ListView) findViewById(R.id.listamantefec);
            adapter = new AdaptadorMantenimiento(getApplicationContext(), listaMantenimientosFec);
            listView.setAdapter(adapter);
        } else {

        }

        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MantenimientoActivity.class);
                startActivityForResult(intent, 0);
            }
        });

    }
}
