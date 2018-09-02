package com.sousa.ronny.cozinheja.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sousa.ronny.cozinheja.R;
import com.sousa.ronny.cozinheja.model.Ingrediente;

import java.util.List;

public class AdapterIngrediente extends BaseAdapter {
    List<Ingrediente> listaIngredientes;
    Context context;

    public AdapterIngrediente(Context context, List<Ingrediente>  listaIngredientes)
    {
        this.listaIngredientes = listaIngredientes;
        this.context = context;

        OrdenarLista();


    }

    private void OrdenarLista() {
        Ingrediente temp;
        for (int i = 0; i < listaIngredientes.size() -1; i++) {
            for (int j = i+1; j < listaIngredientes.size() ; j++) {
                if(listaIngredientes.get(i).getNome().compareToIgnoreCase(listaIngredientes.get(j).getNome())>1)
                {
                    temp=listaIngredientes.get(i);
                    listaIngredientes.set(i,listaIngredientes.get(j));
                    listaIngredientes.set(j,temp);
                }

            }

        }
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
