package com.sousa.ronny.cozinheja.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.sousa.ronny.cozinheja.Adapters.ArrayAdapterIngrediente;
import com.sousa.ronny.cozinheja.FormatadorString;
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
    ArrayList<Ingrediente> listaIngredienteSelecionado=new ArrayList<>();
    String filtroIngrediente="";
    ListView lstIngrediente;
    Button btPesquisar;
    EditText txtFiltroIngrediente;
    ListView lstIngredientesSelecionados;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        //Preenche views
        lstIngrediente = findViewById(R.id.lstIngredientes);
        btPesquisar = findViewById(R.id.btPesquisar);
        txtFiltroIngrediente = findViewById(R.id.txtFiltroIngrediente);
        lstIngredientesSelecionados = findViewById(R.id.lstIngredientesSelecionados);

        //Definir Listeners
        btPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listaIngredienteFiltrados= new ArrayList<>();
                filtroIngrediente = txtFiltroIngrediente.getText().toString();
                PreencheListaIngredientes();

            }
        });

        lstIngrediente.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Ingrediente ingrediente= (Ingrediente) parent.getItemAtPosition(position);
                if(ingrediente!=null) {
                    if(!listaIngredienteSelecionado.contains(ingrediente)) {
                        listaIngredienteSelecionado.add(ingrediente);
                    }
                }

                preencheListaIngredientesSelecionados();

            }
        });

        lstIngredientesSelecionados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Ingrediente ingrediente= (Ingrediente) parent.getItemAtPosition(position);
                if(ingrediente!=null) {
                    listaIngredienteSelecionado.remove(ingrediente);
                }

                preencheListaIngredientesSelecionados();

            }
        });


        preencheListaIngredienteRetrofit();



    }

    private void preencheListaIngredientesSelecionados() {
        ArrayAdapterIngrediente adapter = new ArrayAdapterIngrediente(this,listaIngredienteSelecionado);
        lstIngredientesSelecionados.setAdapter(adapter);


    }

    private void preencheListaIngredienteRetrofit() {
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
        else
        {
            for (Ingrediente ingrediente:listaIngrediente) {
                if(FormatadorString.semAcento( ingrediente.getNome()).toUpperCase().contains(FormatadorString.semAcento( filtroIngrediente).toString().toUpperCase()))
                {
                    listaIngredienteFiltrados.add(ingrediente);
                }
            }
        }

        ArrayAdapterIngrediente adapter = new ArrayAdapterIngrediente(PrincipalActivity.this,listaIngredienteFiltrados);
        lstIngrediente.setAdapter(adapter);


    }


}
