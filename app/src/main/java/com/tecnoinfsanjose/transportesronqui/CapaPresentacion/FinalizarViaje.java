package com.tecnoinfsanjose.transportesronqui.CapaPresentacion;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.tecnoinfsanjose.transportesronqui.R;

public class FinalizarViaje extends AppCompatActivity implements OnClickListener {
    Button TomarFoto1;
    ImageView Imagen1;
    Intent intent;
    Bitmap BMapImage;
    final static int result = 0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalizar_viaje);
        init();
    }
    public void init(){
        TomarFoto1 = (Button) findViewById(R.id.TomarFoto1);
        TomarFoto1.setOnClickListener(this);
        Imagen1 = (ImageView) findViewById(R.id.image1);

    }

    @Override
    public void onClick(View view) {
        int id;
        id=view.getId();
        switch (id){
            case R.id.TomarFoto1:
            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent,result);
            break;

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == Activity.RESULT_OK){
            Bundle ext = data.getExtras();
            BMapImage = (Bitmap)ext.get("data");
            Imagen1.setImageBitmap(BMapImage);
        }
    }
}
