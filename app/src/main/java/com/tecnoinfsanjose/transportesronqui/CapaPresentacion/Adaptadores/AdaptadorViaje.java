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

public class AdaptadorViaje extends BaseAdapter {

    private Context context;
    private List<Viaje> ListaViajes;

    public AdaptadorViaje(Context context, List<Viaje> listaViajes) {
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

        view = LayoutInflater.from(context).inflate(R.layout.list_item_viaje,null);
        ImageView image = (ImageView) view.findViewById(R.id.ImagenListItem);
        TextView origen = (TextView) view.findViewById(R.id.origenItem);
        TextView destino = (TextView) view.findViewById(R.id.destinoItem);
        TextView carga = (TextView) view.findViewById(R.id.cargaItem);


        origen.setText("Origen: "+item.getOrigen());
        destino.setText("Destino: "+item.getDestino());
        carga.setText("Tipo de Carga: "+item.getCarga());
        return view;
    }
}
