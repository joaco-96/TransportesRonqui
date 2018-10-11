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
        Usuario user = getUsuario(context);
        if(user==null){
            ConexionSQliteHelper conn = new ConexionSQliteHelper(context, "bdRonqui", null, 3);

            SQLiteDatabase db = conn.getWritableDatabase();


            ContentValues values = new ContentValues();
            values.put(Utilidades.CAMPO_CI, usu.getCi());
            values.put(Utilidades.CAMPO_PASSWORD, usu.getPassword());

            Long idResultante = db.insert(Utilidades.TABLA_USUARIO, Utilidades.CAMPO_CI, values);
            db.close();

            if (idResultante != 0) {
                return true;
            } else {
                return false;
            }
        }
        else{
            if(user.getCi()==usu.getCi() && user.getPassword().equals( usu.getPassword())){
                return true;
            }else{
                deleteUsuario(user.getCi(),context);
                return PersistirUsuario(usu,context);
            }

        }
    }
    public Usuario getUsuario(Context context){
        ConexionSQliteHelper conn=new ConexionSQliteHelper(context ,"bdRonqui",null,3);
        SQLiteDatabase dbp=conn.getReadableDatabase();

        Cursor cursor=dbp.rawQuery("SELECT "+Utilidades.CAMPO_CI+","+Utilidades.CAMPO_PASSWORD+
                " FROM "+Utilidades.TABLA_USUARIO,null);

        cursor.moveToFirst();
        if(cursor.getCount() == 1){
            Usuario retorno = new Usuario(Integer.valueOf(cursor.getString(0)),cursor.getString(1));
            return retorno;
        }else{
            return null;
        }

    }
    public boolean deleteUsuario(int id, Context context){
        ConexionSQliteHelper conn=new ConexionSQliteHelper(context ,"bdRonqui",null,3);
        SQLiteDatabase db=conn.getWritableDatabase();
        String[] parametros= {String.valueOf(id)};

        db.delete(Utilidades.TABLA_USUARIO,Utilidades.CAMPO_CI+"=?",parametros);
        db.close();
        return true;
    }
    public boolean updateUsuario(Usuario usu, Context context){
        ConexionSQliteHelper conn=new ConexionSQliteHelper(context ,"bdRonqui",null,3);
        SQLiteDatabase db=conn.getWritableDatabase();
        String[] parametros={String.valueOf(usu.getCi())};
        ContentValues values=new ContentValues();
        values.put(Utilidades.CAMPO_PASSWORD,usu.getPassword());

        db.update(Utilidades.TABLA_USUARIO,values,Utilidades.CAMPO_CI+"=?",parametros);
        db.close();
        return true;
    }
}
