package com.tecnoinfsanjose.transportesronqui.CapaLogica.Entities;

public class Mantenimiento {
    String [] PorFecha;
    String [] PorKm;


    public Mantenimiento(String[] porFecha, String[] porKm) {
        PorFecha = porFecha;
        PorKm = porKm;
    }

    public String[] getPorFecha() {
        return PorFecha;
    }

    public void setPorFecha(String[] porFecha) {
        PorFecha = porFecha;
    }

    public String[] getPorKm() {
        return PorKm;
    }

    public void setPorKm(String[] porKm) {
        PorKm = porKm;
    }
}
