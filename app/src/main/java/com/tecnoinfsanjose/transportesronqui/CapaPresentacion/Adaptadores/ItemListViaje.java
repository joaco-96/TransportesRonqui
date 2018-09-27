package com.tecnoinfsanjose.transportesronqui.CapaPresentacion.Adaptadores;

public class ItemListViaje {

    private String Titulo;
    private String Descripcion;
    private String Auxiliar;

    public ItemListViaje(String titulo, String descripcion, String auxiliar) {
        Titulo = titulo;
        Descripcion = descripcion;
        Auxiliar = auxiliar;
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
}
