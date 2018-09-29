package com.tecnoinfsanjose.transportesronqui.CapaPresentacion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tecnoinfsanjose.transportesronqui.R;

public class VerInfoViajeActivity extends AppCompatActivity {
    Button FinalizarViaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_info_viaje);
        FinalizarViaje = (Button)findViewById(R.id.FinalizarViaje);
        FinalizarViaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (getApplicationContext(), FotoActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }
}
