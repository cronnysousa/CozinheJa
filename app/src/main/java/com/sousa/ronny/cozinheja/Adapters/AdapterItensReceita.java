package com.sousa.ronny.cozinheja.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sousa.ronny.cozinheja.R;
import com.sousa.ronny.cozinheja.model.ItensReceita;

import java.util.List;

public class AdapterItensReceita extends BaseAdapter {
    List<ItensReceita> listaItensReceitas;
    Context context;

    public AdapterItensReceita(Context context, List<ItensReceita>  listaItensReceitas)
    {
        this.listaItensReceitas = listaItensReceitas;
        this.context = context;

        OrdenarLista();


    }

    private void OrdenarLista() {
        ItensReceita temp;
        for (int i = 0; i < listaItensReceitas.size() -1; i++) {
            for (int j = i+1; j < listaItensReceitas.size() ; j++) {
                if(listaItensReceitas.get(i).getIngrediente().getNome().compareToIgnoreCase(listaItensReceitas.get(j).getIngrediente().getNome())>1)
                {
                    temp=listaItensReceitas.get(i);
                    listaItensReceitas.set(i,listaItensReceitas.get(j));
                    listaItensReceitas.set(j,temp);
                }

            }

        }
    }


    @Override
    public int getCount() {
        return listaItensReceitas.size();
    }

    @Override
    public Object getItem(int position) {
        return listaItensReceitas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.itensreceita_item_layout, parent, false);
        TextView nome = view.findViewById(R.id.txtNomeItensReceita);
        nome.setText(listaItensReceitas.get(position).getIngrediente().getNome());
        TextView qt = view.findViewById(R.id.txtQtItensReceita);
        if (listaItensReceitas.get(position).getQt() == 0) {
            if(listaItensReceitas.get(position).getFracao()!=null) {
                qt.setText(String.valueOf(listaItensReceitas.get(position).getFracao()));
            }
        } else {
            if(listaItensReceitas.get(position).getFracao()!=null) {
                qt.setText(String.format("%1$d e %2$s",listaItensReceitas.get(position).getQt(),listaItensReceitas.get(position).getFracao()));
            }
            else
            {
                qt.setText(String.format("%0$d",listaItensReceitas.get(position).getQt()));
            }
        }
        TextView unidade= view.findViewById(R.id.txtUnidadeItensReceita);
        unidade.setText(listaItensReceitas.get(position).getUnidade());

        return view;
    }
}
