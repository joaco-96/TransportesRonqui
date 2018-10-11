package com.tecnoinfsanjose.transportesronqui.CapaLogica.Entities;

import java.util.Date;

public class Data_Viaje {
    private int Id;
    private Date Fecha;
    private int Km;
    private Float Toneladas;
    private int Nro_Remito1;
    private int Nro_Remito2;
    private String Observaciones;
    private String Imagen1;
    private String Imagen2;
    private String Imagen3;


    public Data_Viaje(int id, Date fecha, int km, Float toneladas, int nro_Remito1,int nro_Remito2,String observaciones ,String imagen1,String imagen2,String imagen3) {
        Id = id;
        Fecha = fecha;
        Km = km;
        Toneladas = toneladas;
        Nro_Remito1 = nro_Remito1;
        Nro_Remito2 = nro_Remito2;
        Observaciones = observaciones;
        Imagen1 = imagen1;
        Imagen2 = imagen2;
        Imagen3 = imagen3;
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

    public int getNro_Remito1() {
        return Nro_Remito1;
    }

    public void setNro_Remito1(int nro_Remito1) {
        Nro_Remito1 = nro_Remito1;
    }

    public int getNro_Remito2() {
        return Nro_Remito2;
    }

    public void setNro_Remito2(int nro_Remito2) {
        Nro_Remito2 = nro_Remito2;
    }

    public String getImagen1() {
        return Imagen1;
    }

    public void setImagen1(String imagen1) {
        Imagen1 = imagen1;
    }

    public String getImagen2() {
        return Imagen2;
    }

    public void setImagen2(String imagen2) {
        Imagen2 = imagen2;
    }

    public String getImagen3() {
        return Imagen3;
    }

    public void setImagen3(String imagen3) {
        Imagen3 = imagen3;
    }

    public String getObservaciones() {
        return Observaciones;
    }

    public void setObservaciones(String observaciones) {
        Observaciones = observaciones;
    }
}
