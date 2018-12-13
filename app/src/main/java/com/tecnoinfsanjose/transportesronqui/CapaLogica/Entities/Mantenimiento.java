package com.tecnoinfsanjose.transportesronqui.CapaLogica.Entities;

import java.util.Date;

public class Mantenimiento {
    private int id;
    private boolean porfecha;
    private String nombre;
    private int kmts;
    private Date fecha;

    public Mantenimiento(int id, boolean porfecha, String nombre, int kmts, Date fecha) {
        this.id = id;
        this.porfecha = porfecha;
        this.nombre = nombre;
        this.kmts = kmts;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setId(int id) {

        this.id = id;
    }

    public boolean isPorfecha() {
        return porfecha;
    }

    public void setPorfecha(boolean porfecha) {
        this.porfecha = porfecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getKmts() {
        return kmts;
    }

    public void setKmts(int kmts) {
        this.kmts = kmts;
    }
}
