package com.tecnoinfsanjose.transportesronqui.CapaLogica.Controllers;

import android.content.Context;
import android.content.SharedPreferences;

import com.tecnoinfsanjose.transportesronqui.CapaDatos.UsuarioDB;
import com.tecnoinfsanjose.transportesronqui.CapaLogica.Entities.Usuario;
import com.tecnoinfsanjose.transportesronqui.CapaServicio.LoginServicio;

public class UsuarioControlador {
    public String login(int CI,String Password,Context context ) throws Exception {
        if(CI != 0 && !Password.isEmpty()){
            Usuario usu = new Usuario(CI,Password);
            try {
                usu = new LoginServicio().login(usu);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(usu.getKey().isEmpty()){
                return "";
            }else{
                if(new UsuarioDB().PersistirUsuario(usu, context)){
                    return usu.getKey();
                }
                else{
                    return "";
                }
            }

        }else{
            return "";
        }
    }

    public boolean logout(int ci,Context context){
        UsuarioDB db = new UsuarioDB();
        if(db.deleteUsuario(ci, context)){
            SharedPreferences sharedPref = context.getSharedPreferences("USER_DATA",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.clear();
            editor.commit();
            return true;
        }else{
            return false;
        }

    }
}
