package com.tecnoinfsanjose.transportesronqui.CapaPresentacion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.tecnoinfsanjose.transportesronqui.CapaLogica.Controllers.ViajeControlador;
import com.tecnoinfsanjose.transportesronqui.CapaLogica.Entities.Viaje;
import com.tecnoinfsanjose.transportesronqui.CapaPresentacion.Adaptadores.AdaptadorMantenimiento;
import com.tecnoinfsanjose.transportesronqui.R;

import java.util.ArrayList;
import java.util.List;

public class MantenimientoLista extends AppCompatActivity {


    private ListView listView;
    private AdaptadorMantenimiento adapter;
    private List<Viaje> listaViajes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mantenimiento_lista);
        listaViajes = new ArrayList<Viaje>();
        listaViajes = new ViajeControlador().getViajes(getApplicationContext());
        if (listaViajes != null) {
            listView = (ListView) findViewById(R.id.listamante);
            adapter = new AdaptadorMantenimiento(getApplicationContext(), listaViajes);
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
