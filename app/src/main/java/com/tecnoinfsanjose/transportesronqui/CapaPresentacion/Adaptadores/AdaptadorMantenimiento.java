package com.tecnoinfsanjose.transportesronqui.CapaPresentacion.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tecnoinfsanjose.transportesronqui.CapaLogica.Entities.Mantenimiento;
import com.tecnoinfsanjose.transportesronqui.CapaLogica.Entities.Viaje;
import com.tecnoinfsanjose.transportesronqui.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AdaptadorMantenimiento extends BaseAdapter {

    private Context context;
    private List<Mantenimiento> ListaMantenimiento;

    public AdaptadorMantenimiento(Context context, List<Mantenimiento> listaMantenimiento) {
        this.context = context;
        ListaMantenimiento = listaMantenimiento;
    }

    @Override
    public int getCount() {
        return ListaMantenimiento.size();
    }

    @Override
    public Object getItem(int i) {
        return ListaMantenimiento.get(i);
    }

    @Override
    public long getItemId(int i) {
        return ListaMantenimiento.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Mantenimiento item = (Mantenimiento) getItem(i);

        view = LayoutInflater.from(context).inflate(R.layout.list_item_mantenimientos, null);
        ImageView imagen = (ImageView) view.findViewById(R.id.imageViewMant);
        TextView titulo = (TextView) view.findViewById(R.id.TituloItemMant);
        TextView kms = (TextView) view.findViewById(R.id.DescripcionItemMant);

         if(item.isPorfecha()==false) {
             if (item.getKmts() < 0) {
                 titulo.setText("Acción: " + item.getNombre());
                 kms.setText("Kilometros Restantes: " + item.getKmts());
                 imagen.setImageResource(R.drawable.brojo);
             } else if (item.getKmts() >= 0) {
                 titulo.setText("Acción: " + item.getNombre());
                 kms.setText("Kilometros Restantes: " + item.getKmts());
                 imagen.setImageResource(R.drawable.bamarillo);
             }
         }
         else if(item.isPorfecha()==true){

             SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
             Calendar hoy = Calendar.getInstance();

             if (hoy.getTime().compareTo(item.getFecha()) > 0) {

                 titulo.setText("Acción: " + item.getNombre());
                 kms.setText("Fecha: " + simpleDateFormat.format(item.getFecha()));
                 imagen.setImageResource(R.drawable.brojo);

             } else {
                 titulo.setText("Acción: " + item.getNombre());
                 kms.setText("Fecha: " + simpleDateFormat.format(item.getFecha()));
                 imagen.setImageResource(R.drawable.bamarillo);
             }
         }
        return view;

    }
}
