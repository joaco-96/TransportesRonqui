package com.tecnoinfsanjose.transportesronqui.CapaPresentacion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.tecnoinfsanjose.transportesronqui.CapaLogica.Entities.Mantenimiento;
import com.tecnoinfsanjose.transportesronqui.CapaPresentacion.Adaptadores.AdaptadorMantenimiento;
import com.tecnoinfsanjose.transportesronqui.R;

import java.util.ArrayList;
import java.util.List;

public class MantenimientoLista extends AppCompatActivity {


    private ListView listView;
    private AdaptadorMantenimiento adapter;
    private List<Mantenimiento> listaMantenimientos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mantenimiento_lista);
        listaMantenimientos = new ArrayList<Mantenimiento>();

        Mantenimiento mante1 = new Mantenimiento(1,false,"Cambio Aceite", 200, null);
        Mantenimiento mante2 = new Mantenimiento(2,false,"Rotar Cubiertas", -400, null);
        Mantenimiento mante3 = new Mantenimiento(3,false,"Balanceo", 200, null);
        Mantenimiento mante4 = new Mantenimiento(4,false,"Recargar Combustible", 350, null);
        Mantenimiento mante5 = new Mantenimiento(5,false,"Revisión Motor", -100, null);



        listaMantenimientos.add(mante1);
        listaMantenimientos.add(mante2);
        listaMantenimientos.add(mante3);
        listaMantenimientos.add(mante4);
        listaMantenimientos.add(mante5);


        if (listaMantenimientos != null) {
            listView = (ListView) findViewById(R.id.listamante);
            adapter = new AdaptadorMantenimiento(getApplicationContext(), listaMantenimientos);
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
    @Override public void onBackPressed() { moveTaskToBack(true); }
}
