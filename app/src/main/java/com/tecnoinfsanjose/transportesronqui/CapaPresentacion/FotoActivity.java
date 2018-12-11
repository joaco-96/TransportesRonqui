package com.tecnoinfsanjose.transportesronqui.CapaPresentacion;

import android.app.DatePickerDialog;
import android.app.Dialog;
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
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tecnoinfsanjose.transportesronqui.CapaDatos.DataViajeDB;
import com.tecnoinfsanjose.transportesronqui.CapaDatos.ViajeDB;
import com.tecnoinfsanjose.transportesronqui.CapaLogica.Entities.Data_Viaje;
import com.tecnoinfsanjose.transportesronqui.CapaLogica.Entities.Viaje;
import com.tecnoinfsanjose.transportesronqui.R;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.tecnoinfsanjose.transportesronqui.R.id.btnCargarImg1;


public class FotoActivity extends AppCompatActivity {

    private final String CARPETA_RAIZ="MisImagenes/";
    private final String RUTA_IMAGEN=CARPETA_RAIZ+"MisFotos";

    private int mYearIni, mMonthIni, mDayIni, sYearIni, sMonthIni, sDayIni;
    static final int DATE_ID = 0;
    Calendar C = Calendar.getInstance();

    final int COD_SELECCIONA=10;
    final int COD_FOTO=20;
    String pathImg1;
    String pathImg2;
    String pathImg3;
    EditText FVFecha;
    TextView FVDestino;
    EditText FVToneladas;
    EditText FVKm;
    EditText FVRemito1;
    EditText FVRemito2;
    EditText FVObservaciones;


    Button cancelar;
    Button botonCargar1;
    ImageView imagen1;
    Button botonCargar2;
    ImageView imagen2;
    Button botonCargar3;
    Button finalizar ;
    ImageView imagen3;
    String path;
    Data_Viaje data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Viaje viaje = (Viaje) getIntent().getExtras().get("Viaje");
        setContentView(R.layout.activity_foto);
        //No abre teclado de entrada
//        FVFecha.setInputType(InputType.TYPE_NULL);

        FVFecha = (EditText) findViewById(R.id.FVFecha);
        data = new Data_Viaje();
        imagen1= (ImageView) findViewById(R.id.imagen1);
        botonCargar1= (Button) findViewById(R.id.btnCargarImg1);
        imagen2= (ImageView) findViewById(R.id.imagen2);
        botonCargar2= (Button) findViewById(R.id.btnCargarImg2);
        imagen3= (ImageView) findViewById(R.id.imagen3);
        botonCargar3= (Button) findViewById(R.id.btnCargarImg3);
        finalizar = (Button) findViewById(R.id.btnFinalizar);
        FVDestino = (TextView) findViewById(R.id.FVDestino);
        FVToneladas = (EditText) findViewById(R.id.FVToneladas);
        FVKm = (EditText) findViewById(R.id.FVKms);
        FVRemito1 = (EditText) findViewById(R.id.FVRemito1);
        FVRemito2 = (EditText) findViewById(R.id.FVRemito2);
        FVObservaciones= (EditText) findViewById(R.id.FVObservaciones);
        FVDestino.setText(viaje.getDestino());

        //Calendario
        sMonthIni = C.get(Calendar.MONTH);
        sDayIni = C.get(Calendar.DAY_OF_MONTH);
        sYearIni = C.get(Calendar.YEAR);
        FVFecha = (EditText) findViewById(R.id.FVFecha);

        FVFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATE_ID);
            }
        });


        if(validaPermisos()){
            botonCargar1.setEnabled(true);
            botonCargar2.setEnabled(true);
            botonCargar3.setEnabled(true);
        }else{
            botonCargar1.setEnabled(false);
            botonCargar2.setEnabled(false);
            botonCargar3.setEnabled(false);
        }

        cancelar = (Button)findViewById(R.id.btnVolver);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (getApplicationContext(), VerInfoViajeActivity.class);
                intent.putExtra("Viaje", viaje);
                startActivityForResult(intent, 0);
            }
        });
        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!FVFecha.getText().toString().equals("")&&
                        !FVKm.getText().toString().equals("")&&
                        !FVRemito1.getText().toString().equals("")&&
                        !FVRemito2.getText().toString().equals("")&&
                        !FVToneladas.getText().toString().equals("")){
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyy");
                    try {
                        data.setFecha(formatter.parse(FVFecha.getText().toString()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    data.setNro_Remito1(Integer.valueOf(FVRemito1.getText().toString()));
                    data.setNro_Remito2(Integer.valueOf(FVRemito2.getText().toString()));
                    data.setId(viaje.getId());
                    data.setKm(Integer.valueOf(FVKm.getText().toString()));
                    data.setToneladas(Float.parseFloat(FVToneladas.getText().toString()));
                    data.setImagen1(pathImg1);
                    data.setImagen2(pathImg2);
                    data.setImagen3(pathImg3);
                    DataViajeDB persist = new DataViajeDB();
                    if(persist.PersistirDataViaje(data,getApplicationContext())){
                        ViajeDB delete = new ViajeDB();
                        delete.deleteViaje(viaje.getId(),getApplicationContext());
                        Toast.makeText(getApplicationContext(),"Viaje Finalizado Correctamente",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"Error al grabar, Contactese con Soporte",Toast.LENGTH_LONG).show();
                    }

                }
                else{
                    Toast.makeText(getApplicationContext(),"Campos obligatorios sin llenar",Toast.LENGTH_LONG).show();
                }

            }
        });


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
        final CharSequence[] opciones={"Tomar Foto","Cancelar"};
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

    @RequiresApi(api = Build.VERSION_CODES.FROYO)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){

            switch (requestCode){
                case COD_SELECCIONA+1:
                    Uri miPath1=data.getData();
                    imagen1.setImageURI(miPath1);
                    if(miPath1 != null){
                        pathImg1 = miPath1.getPath();
                        Log.i("Ruta de almacenamiento","Path: "+pathImg1);
                    }
                    break;
                case COD_SELECCIONA+2:
                    Uri miPath2=data.getData();
                    imagen2.setImageURI(miPath2);
                    if(miPath2 != null){
                        pathImg2 = miPath2.getPath();
                        Log.i("Ruta de almacenamiento","Path: "+pathImg2);
                    }
                    break;
                case COD_SELECCIONA+3:
                    Uri miPath3=data.getData();
                    imagen3.setImageURI(miPath3);
                    if(miPath3 != null){
                        pathImg3 = miPath3.getPath();
                        Log.i("Ruta de almacenamiento","Path: "+pathImg3);
                    }
                    break;

                case COD_FOTO+1:
                    MediaScannerConnection.scanFile(this, new String[]{path}, null,
                            new MediaScannerConnection.OnScanCompletedListener() {
                                @Override
                                public void onScanCompleted(String path, Uri uri) {

                                    Log.i("Ruta de almacenamiento","Path: "+path);
                                }
                            });
                    pathImg1 = path;
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
                    pathImg2 = path;
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
                    pathImg3 = path;
                    Bitmap bitmap3= BitmapFactory.decodeFile(path);
                    imagen3.setImageBitmap(bitmap3);

                    break;
            }


        }
    }

    //Calendario
    private void colocar_fecha() {
        FVFecha.setText((mMonthIni + 1) + "/" + mDayIni + "/" + mYearIni+" ");
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    mYearIni = year;
                    mMonthIni = monthOfYear;
                    mDayIni = dayOfMonth;
                    colocar_fecha();
                }
            };

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_ID:
                return new DatePickerDialog(this, mDateSetListener, sYearIni, sMonthIni, sDayIni);
        }
        return null;
    }
}