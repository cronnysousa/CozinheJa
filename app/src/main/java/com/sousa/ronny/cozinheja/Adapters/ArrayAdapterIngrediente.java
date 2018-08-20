package com.sousa.ronny.cozinheja.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sousa.ronny.cozinheja.R;
import com.sousa.ronny.cozinheja.model.Ingrediente;

import java.util.List;

public class ArrayAdapterIngrediente extends BaseAdapter {
    List<Ingrediente> listaIngredientes;
    Context context;

    public ArrayAdapterIngrediente(Context context, List<Ingrediente>  listaIngredientes)
    {
        this.listaIngredientes = listaIngredientes;
        this.context = context;


    }


    @Override
    public int getCount() {
        return listaIngredientes.size();
    }

    @Override
    public Object getItem(int position) {
        return listaIngredientes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= ((Activity) context).getLayoutInflater();
        View view= inflater.inflate(R.layout.ingrediente_item_layout,parent,false);
        TextView nome= view.findViewById(R.id.txtNomeIngrediente);
        nome.setText(listaIngredientes.get(position).getNome());
        return view;
    }
}
