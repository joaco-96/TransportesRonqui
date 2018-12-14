package com.tecnoinfsanjose.transportesronqui.CapaDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tecnoinfsanjose.transportesronqui.CapaLogica.Entities.Viaje;
import com.tecnoinfsanjose.transportesronqui.Utilidades.Utilidades;

import java.util.ArrayList;
import java.util.List;

public class ViajeDB {

    public boolean PersistirViaje(Viaje viaje, Context context){
        if(viaje!=null){
            ConexionSQliteHelper conn = new ConexionSQliteHelper(context, "bdRonqui", null, 3);

            SQLiteDatabase db = conn.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(Utilidades.VIAJE_ID, viaje.getId());
            values.put(Utilidades.VIAJE_ORIGEN, viaje.getOrigen());
            values.put(Utilidades.VIAJE_DESTINO, viaje.getDestino());
            values.put(Utilidades.VIAJE_CARGA, viaje.getCarga());
            values.put(Utilidades.VIAJE_CONTACTO, viaje.getContacto());
            values.put(Utilidades.VIAJE_TEL, viaje.getTel());
            values.put(Utilidades.VIAJE_CENTRAL, viaje.getCentral());

            Long idResultante = db.insert(Utilidades.TABLA_VIAJES, Utilidades.VIAJE_ID, values);
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
    public List<Viaje> getViajes(Context context){
        List<Viaje> viajes = new ArrayList<Viaje>();
        ConexionSQliteHelper conn=new ConexionSQliteHelper(context ,"bdRonqui",null,3);
        SQLiteDatabase dbp=conn.getReadableDatabase();

        Cursor cursor=dbp.rawQuery("SELECT "+Utilidades.VIAJE_ID+","+Utilidades.VIAJE_ORIGEN+","+Utilidades.VIAJE_DESTINO+","+Utilidades.VIAJE_CARGA+","+Utilidades.VIAJE_CONTACTO+
                ","+Utilidades.VIAJE_TEL+","+Utilidades.VIAJE_CENTRAL+" FROM "+Utilidades.TABLA_VIAJES,null);

        while (cursor.moveToNext()){
            Viaje nuevo = new Viaje(Integer.valueOf(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),Long.valueOf(cursor.getString(5)),Long.valueOf(cursor.getString(6)));
            viajes.add(nuevo);
        }
        cursor.close();
        return viajes;


    }
    public boolean deleteViaje(int id, Context context){
        ConexionSQliteHelper conn=new ConexionSQliteHelper(context ,"bdRonqui",null,3);
        SQLiteDatabase db=conn.getWritableDatabase();
        String[] parametros= {String.valueOf(id)};

        db.delete(Utilidades.TABLA_VIAJES,Utilidades.DATA_ID+"=?",parametros);
        db.close();
        return true;
    }

    public boolean deleteAllViaje( Context context){
        ConexionSQliteHelper conn=new ConexionSQliteHelper(context ,"bdRonqui",null,3);
        SQLiteDatabase db=conn.getWritableDatabase();

        db.execSQL("delete from "+ Utilidades.TABLA_VIAJES);
        db.close();
        return true;
    }
    public boolean updateViaje(Viaje viaje, Context context){
        ConexionSQliteHelper conn=new ConexionSQliteHelper(context ,"bdRonqui",null,3);
        SQLiteDatabase db=conn.getWritableDatabase();
        String[] parametros={String.valueOf(viaje.getId())};
        ContentValues values=new ContentValues();
        values.put(Utilidades.VIAJE_ORIGEN,viaje.getOrigen());
        values.put(Utilidades.VIAJE_DESTINO,viaje.getDestino());
        values.put(Utilidades.VIAJE_CARGA,viaje.getCarga());
        values.put(Utilidades.VIAJE_CONTACTO,viaje.getContacto());
        values.put(Utilidades.VIAJE_TEL,viaje.getTel());
        values.put(Utilidades.VIAJE_CENTRAL,viaje.getCentral());
        db.update(Utilidades.TABLA_VIAJES,values,Utilidades.VIAJE_ID+"=?",parametros);
        db.close();
        return true;
    }
}
