package com.tecnoinfsanjose.transportesronqui.CapaPresentacion;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.tecnoinfsanjose.transportesronqui.R;

import java.io.File;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.tecnoinfsanjose.transportesronqui.R.id.btnCargarImg1;


public class FotoActivity extends AppCompatActivity {

    private final String CARPETA_RAIZ="MisImagenes/";
    private final String RUTA_IMAGEN=CARPETA_RAIZ+"MisFotos";

    final int COD_SELECCIONA=10;
    final int COD_FOTO=20;

    Button botonCargar1;
    ImageView imagen1;
    Button botonCargar2;
    ImageView imagen2;
    Button botonCargar3;
    Button finalizar ;
    ImageView imagen3;
    String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto);

        imagen1= (ImageView) findViewById(R.id.imagen1);
        botonCargar1= (Button) findViewById(R.id.btnCargarImg1);
        imagen2= (ImageView) findViewById(R.id.imagen2);
        botonCargar2= (Button) findViewById(R.id.btnCargarImg2);
        imagen3= (ImageView) findViewById(R.id.imagen3);
        botonCargar3= (Button) findViewById(R.id.btnCargarImg3);
        finalizar = (Button) findViewById();


        if(validaPermisos()){
            botonCargar1.setEnabled(true);
            botonCargar2.setEnabled(true);
            botonCargar3.setEnabled(true);
        }else{
            botonCargar1.setEnabled(false);
            botonCargar2.setEnabled(false);
            botonCargar3.setEnabled(false);
        }

    }

    private boolean validaPermisos() {

        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            return true;
        }

        if((checkSelfPermission(CAMERA)==PackageManager.PERMISSION_GRANTED)&&
                (checkSelfPermission(WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED)){
            return true;
        }

        if((shouldShowRequestPermissionRationale(CAMERA)) ||
                (shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE))){
            cargarDialogoRecomendacion();
        }else{
            requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE,CAMERA},100);
        }

        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==100){
            if(grantResults.length==2 && grantResults[0]==PackageManager.PERMISSION_GRANTED
                    && grantResults[1]==PackageManager.PERMISSION_GRANTED){
                botonCargar1.setEnabled(true);
                botonCargar2.setEnabled(true);
                botonCargar3.setEnabled(true);
            }else{
                solicitarPermisosManual();
            }
        }

    }

    private void solicitarPermisosManual() {
        final CharSequence[] opciones={"si","no"};
        final AlertDialog.Builder alertOpciones=new AlertDialog.Builder(FotoActivity.this);
        alertOpciones.setTitle("¿Desea configurar los permisos de forma manual?");
        alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (opciones[i].equals("si")){
                    Intent intent=new Intent();
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri=Uri.fromParts("package",getPackageName(),null);
                    intent.setData(uri);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Los permisos no fueron aceptados",Toast.LENGTH_SHORT).show();
                    dialogInterface.dismiss();
                }
            }
        });
        alertOpciones.show();
    }

    private void cargarDialogoRecomendacion() {
        AlertDialog.Builder dialogo=new AlertDialog.Builder(FotoActivity.this);
        dialogo.setTitle("Permisos Desactivados");
        dialogo.setMessage("Debe aceptar los permisos para el correcto funcionamiento de la App");

        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE,CAMERA},100);
            }
        });
        dialogo.show();
    }

    public void onclick(View view) {
        cargarImagen(view.getId());
    }

    private void cargarImagen(final int id ) {
        final CharSequence[] opciones={"Tomar Foto","Cargar Imagen","Cancelar"};
        final AlertDialog.Builder alertOpciones=new AlertDialog.Builder(FotoActivity.this);
        switch (id){
            case R.id.btnCargarImg1:

                alertOpciones.setTitle("Seleccione una Opción");
                alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (opciones[i].equals("Tomar Foto")){
                            tomarFotografia(id);
                        }else{
                            if (opciones[i].equals("Cargar Imagen")){
                                Intent intent=new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                intent.setType("image/");
                                startActivityForResult(intent.createChooser(intent,"Seleccione la Aplicación"),COD_SELECCIONA+1);
                            }else{
                                dialogInterface.dismiss();
                            }
                        }
                    }
                });
                alertOpciones.show();
                break;
            case R.id.btnCargarImg2:
                alertOpciones.setTitle("Seleccione una Opción");
                alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (opciones[i].equals("Tomar Foto")){
                            tomarFotografia(id);
                        }else{
                            if (opciones[i].equals("Cargar Imagen")){
                                Intent intent=new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                intent.setType("image/");
                                startActivityForResult(intent.createChooser(intent,"Seleccione la Aplicación"),COD_SELECCIONA+2);
                            }else{
                                dialogInterface.dismiss();
                            }
                        }
                    }
                });
                alertOpciones.show();
                break;
            case R.id.btnCargarImg3:
                alertOpciones.setTitle("Seleccione una Opción");
                alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (opciones[i].equals("Tomar Foto")){
                            tomarFotografia(id);
                        }else{
                            if (opciones[i].equals("Cargar Imagen")){
                                Intent intent=new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                intent.setType("image/");
                                startActivityForResult(intent.createChooser(intent,"Seleccione la Aplicación"),COD_SELECCIONA+3);
                            }else{
                                dialogInterface.dismiss();
                            }
                        }
                    }
                });
                alertOpciones.show();
                break;
        }



    }

    private void tomarFotografia(int id) {
        File fileImagen=new File(Environment.getExternalStorageDirectory(),RUTA_IMAGEN);
        boolean isCreada=fileImagen.exists();
        String nombreImagen="";
        if(isCreada==false){
            isCreada=fileImagen.mkdirs();
        }

        if(isCreada==true){
            nombreImagen=(System.currentTimeMillis()/1000)+".jpg";
        }


        path=Environment.getExternalStorageDirectory()+
                File.separator+RUTA_IMAGEN+File.separator+nombreImagen;

        File imagen=new File(path);

        Intent intent=null;
        intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        ////
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N)
        {
            String authorities=getApplicationContext().getPackageName()+".provider";
            Uri imageUri=FileProvider.getUriForFile(this,authorities,imagen);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        }else
        {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imagen));
        }
        switch (id){
            case R.id.btnCargarImg1:
                startActivityForResult(intent,COD_FOTO+1);
                break;
            case R.id.btnCargarImg2:
                startActivityForResult(intent,COD_FOTO+2);
                break;
            case R.id.btnCargarImg3:
                startActivityForResult(intent,COD_FOTO+3);
                break;
        }


        ////
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){

            switch (requestCode){
                case COD_SELECCIONA+1:
                    Uri miPath1=data.getData();
                    imagen1.setImageURI(miPath1);
                    break;
                case COD_SELECCIONA+2:
                    Uri miPath2=data.getData();
                    imagen2.setImageURI(miPath2);
                    break;
                case COD_SELECCIONA+3:
                    Uri miPath3=data.getData();
                    imagen3.setImageURI(miPath3);
                    break;

                case COD_FOTO+1:
                    MediaScannerConnection.scanFile(this, new String[]{path}, null,
                            new MediaScannerConnection.OnScanCompletedListener() {
                                @Override
                                public void onScanCompleted(String path, Uri uri) {
                                    Log.i("Ruta de almacenamiento","Path: "+path);
                                }
                            });

                    Bitmap bitmap1= BitmapFactory.decodeFile(path);
                    imagen1.setImageBitmap(bitmap1);

                    break;
                case COD_FOTO+2:
                    MediaScannerConnection.scanFile(this, new String[]{path}, null,
                            new MediaScannerConnection.OnScanCompletedListener() {
                                @Override
                                public void onScanCompleted(String path, Uri uri) {
                                    Log.i("Ruta de almacenamiento","Path: "+path);
                                }
                            });

                    Bitmap bitmap2= BitmapFactory.decodeFile(path);
                    imagen2.setImageBitmap(bitmap2);

                    break;
                case COD_FOTO+3:
                    MediaScannerConnection.scanFile(this, new String[]{path}, null,
                            new MediaScannerConnection.OnScanCompletedListener() {
                                @Override
                                public void onScanCompleted(String path, Uri uri) {
                                    Log.i("Ruta de almacenamiento","Path: "+path);
                                }
                            });

                    Bitmap bitmap3= BitmapFactory.decodeFile(path);
                    imagen3.setImageBitmap(bitmap3);

                    break;
            }


        }
    }
}