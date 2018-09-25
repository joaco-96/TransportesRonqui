package com.tecnoinfsanjose.transportesronqui.CapaLogica.Entities;

import java.util.Date;

public class Data_Viaje {
    private int Id;
    private Date Fecha;
    private int Km;
    private Float Toneladas;
    private int Nro_Remito;
    private String[] Imagenes;


    public Data_Viaje(int id, Date fecha, int km, Float toneladas, int nro_Remito) {
        Id = id;
        Fecha = fecha;
        Km = km;
        Toneladas = toneladas;
        Nro_Remito = nro_Remito;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date fecha) {
        Fecha = fecha;
    }

    public int getKm() {
        return Km;
    }

    public void setKm(int km) {
        Km = km;
    }

    public Float getToneladas() {
        return Toneladas;
    }

    public void setToneladas(Float toneladas) {
        Toneladas = toneladas;
    }

    public int getNro_Remito() {
        return Nro_Remito;
    }

    public void setNro_Remito(int nro_Remito) {
        Nro_Remito = nro_Remito;
    }

    public String[] getImagenes() {
        return Imagenes;
    }

    public void setImagenes(String[] imagenes) {
        Imagenes = imagenes;
    }
}
