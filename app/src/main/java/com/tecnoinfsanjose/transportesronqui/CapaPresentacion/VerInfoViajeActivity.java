package com.tecnoinfsanjose.transportesronqui.CapaPresentacion;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.tecnoinfsanjose.transportesronqui.CapaLogica.Entities.Viaje;
import com.tecnoinfsanjose.transportesronqui.R;

public class VerInfoViajeActivity extends AppCompatActivity {


    Button FinalizarViaje;
    Button volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_info_viaje);
        final Viaje viaje = (Viaje) getIntent().getExtras().get("Viaje");

        EditText etxt1 = (EditText) findViewById(R.id.editText1);
        EditText etxt2 = (EditText) findViewById(R.id.editText2);
        EditText etxt3 = (EditText) findViewById(R.id.editText3);
        EditText etxt4 = (EditText) findViewById(R.id.editText4);
        EditText etxt5 = (EditText) findViewById(R.id.editText5);
        EditText etxt6 = (EditText) findViewById(R.id.editText6);

        etxt1.setInputType(InputType.TYPE_NULL);
        etxt1.setText(viaje.getOrigen());
        etxt2.setText(viaje.getDestino());
        etxt3.setText(viaje.getCarga());
        etxt4.setText(String.valueOf(viaje.getContacto()));
        etxt5.setText(String.valueOf(viaje.getTel()));
        etxt6.setText(String.valueOf(viaje.getCentral()));

        FinalizarViaje = (Button)findViewById(R.id.FinalizarViaje);
        FinalizarViaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (getApplicationContext(), FotoActivity.class);
                intent.putExtra("Viaje", viaje);
                startActivityForResult(intent, 0);
            }
        });

        ImageButton btntel = (ImageButton) findViewById(R.id.buttonTel);
        btntel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+viaje.getTel()));
                startActivity(i);
            }
        });

        ImageButton btntelemp = (ImageButton) findViewById(R.id.buttonTelEmp);
        btntelemp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                Intent in = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+viaje.getCentral()));
                startActivity(in);
            }
        });



        volver = (Button)findViewById(R.id.volver1);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (getApplicationContext(), VerViajeActivity.class);
                intent.putExtra("Viaje", viaje);
                startActivityForResult(intent, 0);
            }
        });

    }
    @Override public void onBackPressed() { moveTaskToBack(true); }
}
