package com.tecnoinfsanjose.transportesronqui.CapaServicio;

import android.os.AsyncTask;

import com.tecnoinfsanjose.transportesronqui.CapaLogica.Entities.Usuario;

public class LoginServicio {
    private AsyncTask servicio;
    private Usuario usuario ;

    public LoginServicio() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public AsyncTask getServicio() {
        return servicio;
    }

    public void setServicio(AsyncTask servicio) {
        this.servicio = servicio;
    }

    public Usuario login(Usuario usu) throws Exception {
        usu.setKey("SoyLaKey");
        return usu;
    }

    //IMPLEMENTAR CLASE ASYNCTASK
}
