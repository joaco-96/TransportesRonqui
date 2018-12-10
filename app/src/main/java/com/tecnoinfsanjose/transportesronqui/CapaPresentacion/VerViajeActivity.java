package com.tecnoinfsanjose.transportesronqui.CapaPresentacion;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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
    Button volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_viaje);
        listaViajes = new ArrayList<Viaje>();
        listaViajes = new ViajeControlador().getViajes(getApplicationContext());
        if (listaViajes != null) {
            listView = (ListView) findViewById(R.id.listView);
            adapter = new AdaptadorViaje(getApplicationContext(), listaViajes);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(getApplicationContext(), VerInfoViajeActivity.class);
                    intent.putExtra("Viaje", listaViajes.get(i));
                    startActivityForResult(intent, 0);
                }
            });


        } else {

        }

        volver = (Button)findViewById(R.id.button7);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (getApplicationContext(), MainActivity.class);
                startActivityForResult(intent, 0);
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                builder.setTitle("Confirmar");
                builder.setMessage("Estas seguro?");
            }
        });

    }
}
