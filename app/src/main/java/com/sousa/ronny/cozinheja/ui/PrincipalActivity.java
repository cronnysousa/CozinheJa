package com.sousa.ronny.cozinheja.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sousa.ronny.cozinheja.R;
import com.sousa.ronny.cozinheja.model.Ingrediente;
import com.sousa.ronny.cozinheja.retrofit.RetrofitInicializador;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrincipalActivity extends AppCompatActivity {
    ArrayList<Ingrediente> listaIngrediente=null;
    ArrayList<Ingrediente> listaIngredienteFiltrados=null;
    String filtroIngrediente="";
    ListView lstIngrediente;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        lstIngrediente = findViewById(R.id.lstIngredientes);
        preencheListaIngrediente();



    }

    private void preencheListaIngrediente() {
        RetrofitInicializador retrofit = new RetrofitInicializador();
         retrofit.getIngredienteService().Seleciona().enqueue(new Callback<ArrayList<Ingrediente>>() {
             @Override
             public void onResponse(Call<ArrayList<Ingrediente>> call, Response<ArrayList<Ingrediente>> response) {
                 Log.i("Ingredientes", "onResponse: "+response.body().size());
                 listaIngrediente =response.body();
                 PreencheListaIngredientes();


             }

             @Override
             public void onFailure(Call<ArrayList<Ingrediente>> call, Throwable t) {
                 Log.i("Ingredientes", "onFailure: "+t.getMessage());

             }
         });
    }

    private void PreencheListaIngredientes() {
        if(filtroIngrediente=="")
        {
            listaIngredienteFiltrados = listaIngrediente;
        }

        ArrayAdapter<Ingrediente> adapter = new ArrayAdapter<Ingrediente>(PrincipalActivity.this,android.R.layout.simple_list_item_1,listaIngredienteFiltrados);
        lstIngrediente.setAdapter(adapter);
    }


}
