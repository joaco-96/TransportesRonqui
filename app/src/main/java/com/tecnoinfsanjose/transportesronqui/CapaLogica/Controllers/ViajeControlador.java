package com.tecnoinfsanjose.transportesronqui.CapaLogica.Controllers;

import android.content.Context;

import com.tecnoinfsanjose.transportesronqui.CapaDatos.ViajeDB;
import com.tecnoinfsanjose.transportesronqui.CapaLogica.Entities.Viaje;

import java.util.List;

public class ViajeControlador {

    public List<Viaje> getViajes(Context context){
        return new ViajeDB().getViajes(context);
    }
}
