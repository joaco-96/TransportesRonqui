package com.tecnoinfsanjose.transportesronqui.CapaPresentacion;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tecnoinfsanjose.transportesronqui.CapaLogica.Controllers.UsuarioControlador;
import com.tecnoinfsanjose.transportesronqui.R;

import org.w3c.dom.Text;

import static android.app.PendingIntent.getActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText TextCI = (EditText) findViewById(R.id.TextCI);
        final EditText TextPassword = (EditText) findViewById(R.id.TextPassword);

        Button btn = (Button) findViewById(R.id.Btn_Ingresar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Password = TextPassword.getText().toString();
                int CI = Integer.valueOf(TextCI.getText().toString());
                if(Password.isEmpty() || Password.length()< 5){
                    Toast.makeText(getApplicationContext(),"La Contraseña debe tener mas de 4 digitos...",Toast.LENGTH_LONG).show();
                }else{
                    String key;
                    try {
                        key = new UsuarioControlador().login(CI,Password);
                        if(key.equals("")){
                            Toast.makeText(getApplicationContext(),"Usuario o Contraseña Incorrecta",Toast.LENGTH_LONG).show();
                            //SACAR ESTE CODIGO CUANDO CONSUMAMOS WEB SERVICES PARA QUE INICIE BIEN
                            Intent intent = new Intent (v.getContext(), MainActivity.class);
                            startActivityForResult(intent, 0);
                        }else{
                            SharedPreferences sharedPref = getSharedPreferences("USER_DATA",Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putString("USER_KEY",key);
                            editor.commit();
                            Intent intent = new Intent (v.getContext(), MainActivity.class);
                            startActivityForResult(intent, 0);
                        }
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(),"Error al Iniciar Sesion",Toast.LENGTH_LONG).show();
                    }

                }


            }
        });
    }
}
