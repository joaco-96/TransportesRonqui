package com.tecnoinfsanjose.transportesronqui.CapaPresentacion.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tecnoinfsanjose.transportesronqui.CapaLogica.Entities.Viaje;
import com.tecnoinfsanjose.transportesronqui.R;

import java.util.List;

public class AdaptadorMantenimiento extends BaseAdapter {

    private Context context;
    private List<Viaje> ListaViajes;

    public AdaptadorMantenimiento(Context context, List<Viaje> listaViajes) {
        this.context = context;
        ListaViajes = listaViajes;
    }

    @Override
    public int getCount() {
        return ListaViajes.size();
    }

    @Override
    public Object getItem(int i) {
        return ListaViajes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return ListaViajes.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Viaje item = (Viaje) getItem(i);

        view = LayoutInflater.from(context).inflate(R.layout.list_item_mantenimientos, null);
        ImageView imagen = (ImageView) view.findViewById(R.id.imageViewMant);
        TextView titulo = (TextView) view.findViewById(R.id.TituloItemMant);
        TextView kms = (TextView) view.findViewById(R.id.DescripcionItemMant);

//        if (item.getTipoMant == "F"){
            titulo.setText("Mantenimiento: "+item.getDestino());
            kms.setText("Kilometros: "+item.getCarga());

            switch (item.getCarga()) {
                case "Trigo":   imagen.setImageResource(R.drawable.brojo);
                    break;
                case "Soja":   imagen.setImageResource(R.drawable.bamarillo);
                    break;
                case "Maiz":   imagen.setImageResource(R.drawable.brojo);
                    break;
            }
//        }
//        else if (item.getTipoMant == "K"){
//            titulo.setText("Mantenimiento: "+item.getDestino());
//            descripcion.setText("Kilometros: "+item.getCarga());
//
//            switch (item.getKilometros()) {
//                case item.getKilometros >= 0 :   imagen.setImageResource(R.drawable.bamarillo);
//                    break;
//                case item.getKilometros < 0 :   imagen.setImageResource(R.drawable.brojo);
//                    break;
//            }
//        }

        return view;

    }
}
