package com.tecnoinfsanjose.transportesronqui.CapaLogica.Controllers;

import android.content.Context;
import android.widget.Toast;

import com.tecnoinfsanjose.transportesronqui.CapaDatos.DataViajeDB;
import com.tecnoinfsanjose.transportesronqui.Utilidades.Connectivity;

import java.text.ParseException;

public class SincronizacionDatos {


    static public boolean isSincroniced(Context context) throws ParseException {
        DataViajeDB db = new DataViajeDB();
        return db.getViajes(context).size()==0;
    }

    static public boolean Sincroniced(Context context){
        DataViajeDB db = new DataViajeDB();
        if(Connectivity.isConnectedFast(context)){
            db.deleteAllDataViaje(context);
            Toast.makeText(context,"Datos Sincronizados Correctamente",Toast.LENGTH_LONG).show();
            return true;
        }
        else{
            Toast.makeText(context,"La conexion debe ser WIFI, LTE, o H",Toast.LENGTH_LONG).show();
            return false;
        }

    }

}
