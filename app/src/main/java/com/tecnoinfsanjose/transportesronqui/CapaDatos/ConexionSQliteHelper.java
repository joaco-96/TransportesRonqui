package com.tecnoinfsanjose.transportesronqui.CapaDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tecnoinfsanjose.transportesronqui.Utilidades.Utilidades;

public class ConexionSQliteHelper extends SQLiteOpenHelper{

    public ConexionSQliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilidades.CREAR_TABLA_USUARIO);
        db.execSQL(Utilidades.CREAR_TABLA_VIAJES);
        db.execSQL(Utilidades.CREAR_TABLA_DATA_VIAJES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_USUARIO);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_VIAJES);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_DATA_VIAJE);

        onCreate(db);
    }
}