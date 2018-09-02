package com.sousa.ronny.cozinheja.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.sousa.ronny.cozinheja.adapters.AdapterReceita;
import com.sousa.ronny.cozinheja.adapters.AdapterIngrediente;
import com.sousa.ronny.cozinheja.formaters.FormatadorString;
import com.sousa.ronny.cozinheja.R;
import com.sousa.ronny.cozinheja.model.Ingrediente;
import com.sousa.ronny.cozinheja.model.Receita;
import com.sousa.ronny.cozinheja.retrofit.RetrofitInicializador;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrincipalActivity extends AppCompatActivity {
    RetrofitInicializador retrofit;


    String filtroIngrediente = "";
    ListView lstIngrediente;
    EditText txtFiltroIngrediente;
    ListView lstIngredientesSelecionados;
    ListView lstReceitasSelecionadas;
    private Callback<ArrayList<Ingrediente>> callbackIngredientes;
    private Callback<ArrayList<Receita>> callbackReceita;
    private ArrayList<Ingrediente> listaIngrediente;
    private ArrayList<Ingrediente> listaIngredienteFiltrados;
    private ArrayList<Receita> listaReceitas;
    private ArrayList<Receita> listaReceitasCombinacao;
    private ArrayList<Ingrediente> listaIngredienteSelecionado;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        listaIngrediente = new ArrayList<>();
        listaIngredienteFiltrados = new ArrayList<Ingrediente>();
        listaIngredienteSelecionado = new ArrayList<Ingrediente>();
        listaReceitas = new ArrayList<Receita>();
        listaReceitasCombinacao = new ArrayList<Receita>();

        //Preenche views
        lstIngrediente = findViewById(R.id.lstIngredientes);
        txtFiltroIngrediente = findViewById(R.id.txtFiltroIngrediente);
        lstIngredientesSelecionados = findViewById(R.id.lstIngredientesSelecionados);
        lstReceitasSelecionadas = findViewById(R.id.lstReceitasSelecionadas);

        //Definir Listeners

        txtFiltroIngrediente.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                listaIngredienteFiltrados = new ArrayList<>();
                filtroIngrediente = txtFiltroIngrediente.getText().toString();
                PreencheListaIngredientes();

            }
        });

        lstIngrediente.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Ingrediente ingrediente = (Ingrediente) parent.getItemAtPosition(position);
                if (ingrediente != null) {
                    if (!listaIngredienteSelecionado.contains(ingrediente)) {
                        listaIngredienteSelecionado.add(ingrediente);
                    }
                }
                verificaReceitas();

                preencheListaIngredientesSelecionados();

            }
        });

        lstIngredientesSelecionados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Ingrediente ingrediente = (Ingrediente) parent.getItemAtPosition(position);
                if (ingrediente != null) {
                    listaIngredienteSelecionado.remove(ingrediente);
                }
                verificaReceitas();

                preencheListaIngredientesSelecionados();

            }
        });

        lstReceitasSelecionadas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Receita receita = (Receita) parent.getItemAtPosition(position);
                Intent intent = new Intent(PrincipalActivity.this, ReceitaActivity.class);
                intent.putExtra("receita", receita);
                startActivity(intent);
            }
        });

        retrofit = new RetrofitInicializador();

    }


    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

    }

    @Override
    protected void onResume() {
        super.onResume();

        preencheListaIngredienteRetrofit();
        preencheListaReceitaRetrofit();
    }

    private void verificaReceitas() {
        listaReceitasCombinacao.clear();

        for (Receita r : listaReceitas) {
            if (r.ContemAlgunsIngredientes(listaIngredienteSelecionado)) {
                listaReceitasCombinacao.add(r);
            }
        }

        OrdenaListaReceitas();

        preencheListaReceita();
    }

    private void OrdenaListaReceitas() {
        Receita troca;
        for (int i = 0; i < listaReceitasCombinacao.size() - 1; i++)

        {
            for (int j = i + 1; j < listaReceitasCombinacao.size(); j++) {
                if (listaReceitasCombinacao.get(i).PercencentualIngredientes(listaIngredienteSelecionado) < listaReceitasCombinacao.get(j).PercencentualIngredientes(listaIngredienteSelecionado)) {
                    troca = listaReceitasCombinacao.get(i);
                    listaReceitasCombinacao.set(i, listaReceitasCombinacao.get(j));
                    listaReceitasCombinacao.set(j, troca);

                }
            }
        }

    }


    private void preencheListaReceita() {
        AdapterReceita adapter = new AdapterReceita(this, listaReceitasCombinacao, listaIngredienteSelecionado);

        lstReceitasSelecionadas.setAdapter(adapter);
    }


    private void preencheListaIngredientesSelecionados() {
        AdapterIngrediente adapter = new AdapterIngrediente(this, listaIngredienteSelecionado);
        lstIngredientesSelecionados.setAdapter(adapter);


    }

    private void preencheListaReceitaRetrofit() {

        callbackReceita = new Callback<ArrayList<Receita>>() {
            @Override
            public void onResponse(Call<ArrayList<Receita>> call, Response<ArrayList<Receita>> response) {
                Log.i("Receita", "onResponse: " + response.body().size());
                listaReceitas = response.body();
            }

            @Override
            public void onFailure(Call<ArrayList<Receita>> call, Throwable t) {
                Log.i("Receita", "onFailure: " + t.getMessage());

            }
        };
        retrofit.getReceitaService().Seleciona().enqueue(callbackReceita);

    }

    private void preencheListaIngredienteRetrofit() {
        callbackIngredientes = new Callback<ArrayList<Ingrediente>>() {
            @Override
            public void onResponse(Call<ArrayList<Ingrediente>> call, Response<ArrayList<Ingrediente>> response) {
                Log.i("Ingredientes", "onResponse: " + response.body().size());
                listaIngrediente = response.body();
                PreencheListaIngredientes();


            }

            @Override
            public void onFailure(Call<ArrayList<Ingrediente>> call, Throwable t) {
                Log.i("Ingredientes", "onFailure: " + t.getMessage());

            }
        };

        retrofit.getIngredienteService().Seleciona().enqueue(callbackIngredientes);
    }

    private void PreencheListaIngredientes() {
        if (filtroIngrediente == "") {
            listaIngredienteFiltrados = listaIngrediente;
        } else {
            listaIngredienteFiltrados.clear();
            for (Ingrediente ingrediente : listaIngrediente) {
                if (FormatadorString.semAcento(ingrediente.getNome()).toUpperCase().contains(FormatadorString.semAcento(filtroIngrediente).toString().toUpperCase())) {
                    listaIngredienteFiltrados.add(ingrediente);
                }
            }
        }

        AdapterIngrediente adapter = new AdapterIngrediente(PrincipalActivity.this, listaIngredienteFiltrados);
        lstIngrediente.setAdapter(adapter);


    }


}
