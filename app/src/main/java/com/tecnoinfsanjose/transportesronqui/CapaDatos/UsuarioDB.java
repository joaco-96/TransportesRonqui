package com.tecnoinfsanjose.transportesronqui.CapaDatos;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tecnoinfsanjose.transportesronqui.CapaLogica.Entities.Usuario;
import com.tecnoinfsanjose.transportesronqui.Utilidades.Utilidades;

public class UsuarioDB {

    public boolean PersistirUsuario(Usuario usu, Context context){
            ConexionSQliteHelper conn=new ConexionSQliteHelper(context ,"bdRonqui",null,1);

            SQLiteDatabase db=conn.getWritableDatabase();

            ContentValues values=new ContentValues();
            values.put(Utilidades.CAMPO_KEY,usu.getKey());
            values.put(Utilidades.CAMPO_PASSWORD,usu.getPassword());
            values.put(Utilidades.CAMPO_CI,usu.getCi());

            Long idResultante=db.insert(Utilidades.TABLA_USUARIO,Utilidades.CAMPO_CI,values);
            db.close();
            // Consulta DE PRUEBA
            SQLiteDatabase dbp=conn.getReadableDatabase();
            String[] parametros={String.valueOf(usu.getCi())};

            Cursor cursor=dbp.rawQuery("SELECT "+Utilidades.CAMPO_CI+","+Utilidades.CAMPO_PASSWORD+
                    " FROM "+Utilidades.TABLA_USUARIO+" WHERE "+Utilidades.CAMPO_CI+"=? ",parametros);
            cursor.moveToFirst();

        System.out.println("ACAAAAAA DEBERIA VER LOS USUARIOS SSS ----------------------------------------------");
        System.out.println(cursor.getString(0)+"-------"+cursor.getString(1));

        System.out.println("ESTA ES LA IMPRESION DE PANTALLA QUE TENGO QUE VEEEEERERRRRRRRRRRRRRR");
            if(idResultante!=0){
                return true;
            }
            else{
                return false;
            }

    }
}
