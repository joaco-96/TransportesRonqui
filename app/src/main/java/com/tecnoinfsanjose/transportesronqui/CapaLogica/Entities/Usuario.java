package com.tecnoinfsanjose.transportesronqui.CapaLogica.Entities;

public class Usuario {
    private int Ci;
    private String Password;
    private String key;

    public Usuario(int ci, String password) {
        Ci = ci;
        Password = password;
        key = "";
    }

    public int getCi() {
        return Ci;
    }

    public void setCi(int ci) {
        Ci = ci;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
