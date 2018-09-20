package com.tecnoinfsanjose.transportesronqui.CapaLogica.Entities;

public class Viaje {
    private int Id;
    private  String Origen;
    private  String Destino;
    private  String Carga;
    private int Contacto;

    public Viaje(int id, String origen, String destino, String carga, int contacto) {
        Id = id;
        Origen = origen;
        Destino = destino;
        Carga = carga;
        Contacto = contacto;
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

    public int getContacto() {
        return Contacto;
    }

    public void setContacto(int contacto) {
        Contacto = contacto;
    }
}
