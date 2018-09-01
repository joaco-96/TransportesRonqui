package com.tecnoinfsanjose.transportesronqui;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.tecnoinfsanjose.transportesronqui.MainActivity;
import com.tecnoinfsanjose.transportesronqui.R;

public class VerViajeActivity extends AppCompatActivity {


    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_viaje);

        Button btn2 = (Button) findViewById(R.id.button3);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent (v.getContext(), MainActivity.class);
                startActivityForResult(intent2, 0);
            }
        });

        Button btn = (Button) findViewById(R.id.button8);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (view.getContext(), FotoActivity.class);
                startActivityForResult(intent, 0);
            }
        });

    }


}
