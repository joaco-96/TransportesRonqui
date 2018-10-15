package com.tecnoinfsanjose.transportesronqui.CapaDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tecnoinfsanjose.transportesronqui.CapaLogica.Entities.Data_Viaje;
import com.tecnoinfsanjose.transportesronqui.CapaLogica.Entities.Viaje;
import com.tecnoinfsanjose.transportesronqui.Utilidades.Utilidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataViajeDB {

    public boolean PersistirDataViaje(Data_Viaje viaje, Context context){
        if(viaje!=null){
            ConexionSQliteHelper conn = new ConexionSQliteHelper(context, "bdRonqui", null, 3);

            SQLiteDatabase db = conn.getWritableDatabase();

            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            ContentValues values = new ContentValues();
            values.put(Utilidades.DATA_ID, viaje.getId());
            values.put(Utilidades.DATA_FECHA, formatter.format(viaje.getFecha()));
            values.put(Utilidades.DATA_KM, viaje.getKm());
            values.put(Utilidades.DATA_TONELADAS, viaje.getToneladas());
            values.put(Utilidades.DATA_NRO_REMITO1, viaje.getNro_Remito1());
            values.put(Utilidades.DATA_NRO_REMITO2, viaje.getNro_Remito2());
            values.put(Utilidades.DATA_OBSERVACIONES, viaje.getObservaciones());
            values.put(Utilidades.DATA_IMG1, viaje.getImagen1());
            values.put(Utilidades.DATA_IMG2, viaje.getImagen2());
            values.put(Utilidades.DATA_IMG3, viaje.getImagen3());

            Long idResultante = db.insert(Utilidades.TABLA_DATA_VIAJE, Utilidades.DATA_ID, values);
            db.close();

            if (idResultante != 0) {
                return true;
            } else {
                return false;
            }
        }else{
            return false;
        }

    }
    public List<Data_Viaje> getViajes(Context context) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        List<Data_Viaje> viajes = new ArrayList<Data_Viaje>();
        ConexionSQliteHelper conn=new ConexionSQliteHelper(context ,"bdRonqui",null,3);
        SQLiteDatabase dbp=conn.getReadableDatabase();

        Cursor cursor=dbp.rawQuery("SELECT "+Utilidades.DATA_ID+","+Utilidades.DATA_FECHA+","+Utilidades.DATA_KM+","+Utilidades.DATA_TONELADAS+","+Utilidades.DATA_NRO_REMITO1+","+Utilidades.DATA_NRO_REMITO2+
                ","+Utilidades.DATA_OBSERVACIONES+","+Utilidades.DATA_IMG1+","+Utilidades.DATA_IMG2+","+Utilidades.DATA_IMG3+" FROM "+Utilidades.TABLA_DATA_VIAJE,null);

        while (cursor.moveToNext()){
            Data_Viaje nuevo = new Data_Viaje(Integer.valueOf(cursor.getString(0)),formatter.parse(cursor.getString(1)),Integer.valueOf(cursor.getString(2)),Float.valueOf(cursor.getString(3)),Integer.valueOf(cursor.getString(4)),Integer.valueOf(cursor.getString(5)),cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getString(9));
            viajes.add(nuevo);
            System.out.println(nuevo.getFecha().toString());
            System.out.println(cursor.getString(1));
        }
        cursor.close();
        return viajes;


    }
    public boolean deleteViaje(int id, Context context){
        ConexionSQliteHelper conn=new ConexionSQliteHelper(context ,"bdRonqui",null,3);
        SQLiteDatabase db=conn.getWritableDatabase();
        String[] parametros= {String.valueOf(id)};

        db.delete(Utilidades.TABLA_DATA_VIAJE,Utilidades.DATA_ID+"=?",parametros);
        db.close();
        return true;
    }
    public boolean updateViaje(Data_Viaje viaje, Context context){
        ConexionSQliteHelper conn=new ConexionSQliteHelper(context ,"bdRonqui",null,3);
        SQLiteDatabase db=conn.getWritableDatabase();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String[] parametros={String.valueOf(viaje.getId())};
        ContentValues values=new ContentValues();
        values.put(Utilidades.DATA_FECHA, formatter.format(viaje.getFecha()));
        values.put(Utilidades.DATA_KM, viaje.getKm());
        values.put(Utilidades.DATA_TONELADAS, viaje.getToneladas());
        values.put(Utilidades.DATA_NRO_REMITO1, viaje.getNro_Remito1());
        values.put(Utilidades.DATA_NRO_REMITO2, viaje.getNro_Remito2());
        values.put(Utilidades.DATA_OBSERVACIONES, viaje.getObservaciones());
        values.put(Utilidades.DATA_IMG1, viaje.getImagen1());
        values.put(Utilidades.DATA_IMG2, viaje.getImagen2());
        values.put(Utilidades.DATA_IMG3, viaje.getImagen3());
        db.update(Utilidades.TABLA_DATA_VIAJE,values,Utilidades.DATA_ID+"=?",parametros);
        db.close();
        return true;
    }
    public Date convertDate(String fecha ){
        Date fec = new Date();
        System.out.println(fec.toString());
        return null;
    }
}
