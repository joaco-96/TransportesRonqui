package com.tecnoinfsanjose.transportesronqui.CapaPresentacion.Adaptadores;

public class ItemListViaje {
    private int Id;
    private String Titulo;
    private String Descripcion;
    private String Auxiliar;

    public ItemListViaje(int id,String titulo, String descripcion, String auxiliar) {
        Titulo = titulo;
        Descripcion = descripcion;
        Auxiliar = auxiliar;
        Id = id;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getAuxiliar() {
        return Auxiliar;
    }

    public void setAuxiliar(String auxiliar) {
        Auxiliar = auxiliar;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
