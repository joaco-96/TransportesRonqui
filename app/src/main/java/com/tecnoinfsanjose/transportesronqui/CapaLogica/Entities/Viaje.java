package com.tecnoinfsanjose.transportesronqui.CapaLogica.Entities;

import java.io.Serializable;

public class Viaje implements Serializable {
    private int Id;
    private  String Origen;
    private  String Destino;
    private  String Carga;
    private String Contacto;
    private long Tel;
    private long Central;


    public Viaje(int id, String origen, String destino, String carga, String contacto, long tel, long central) {
        Id = id;
        Origen = origen;
        Destino = destino;
        Carga = carga;
        Contacto = contacto;
        Tel = tel;
        Central = central;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getOrigen() {
        return Origen;
    }

    public void setOrigen(String origen) {
        Origen = origen;
    }

    public String getDestino() {
        return Destino;
    }

    public void setDestino(String destino) {
        Destino = destino;
    }

    public String getCarga() {
        return Carga;
    }

    public void setCarga(String carga) {
        Carga = carga;
    }

    public String getContacto() {
        return Contacto;
    }

    public void setContacto(String contacto) {
        Contacto = contacto;
    }

    public long getTel() {
        return Tel;
    }

    public void setTel(long tel) {
        Tel = tel;
    }

    public long getCentral() {
        return Central;
    }

    public void setCentral(long central) {
        Central = central;
    }
}
