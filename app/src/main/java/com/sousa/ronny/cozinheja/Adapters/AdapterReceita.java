package com.sousa.ronny.cozinheja.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sousa.ronny.cozinheja.R;
import com.sousa.ronny.cozinheja.model.Ingrediente;
import com.sousa.ronny.cozinheja.model.Receita;

import java.util.List;

public class AdapterReceita extends BaseAdapter {
    List<Ingrediente> listaIngredientes;
    List<Receita> listaReceitas;
    Context context;

    public AdapterReceita( Context context,List<Receita> listaReceitas, List<Ingrediente>  listaIngredientesSelecionados)
    {
        this.listaReceitas=listaReceitas;
        this.listaIngredientes = listaIngredientesSelecionados;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listaReceitas.size();
    }

    @Override
    public Object getItem(int position) {
        return listaReceitas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= ((Activity) context).getLayoutInflater();
        View view= inflater.inflate(R.layout.receita_layout,parent,false);
        TextView nome= view.findViewById(R.id.txtNomeReceita);
        TextView per = view.findViewById(R.id.txtCompletoPercReceita);
        ProgressBar progressBar = view.findViewById(R.id.progReceita);

        double percentual=listaReceitas.get(position).PercencentualIngredientes(listaIngredientes) * 100;

        progressBar.setProgress((int) percentual);
        per.setText( String.valueOf (percentual));

        nome.setText(listaReceitas.get(position).getNome());
        return view;
    }
}
