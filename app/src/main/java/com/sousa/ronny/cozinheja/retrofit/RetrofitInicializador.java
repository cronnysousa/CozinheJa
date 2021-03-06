package com.sousa.ronny.cozinheja.retrofit;

import com.sousa.ronny.cozinheja.services.IngredienteService;
import com.sousa.ronny.cozinheja.services.ReceitaService;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitInicializador {
    private final Retrofit retrofit;
    public RetrofitInicializador()
    {
        retrofit= new Retrofit.Builder()
                .baseUrl("http://www.programacaoronny.kinghost.net/api/")
                .addConverterFactory(JacksonConverterFactory.create()).build();
        JacksonConverterFactory factory= JacksonConverterFactory.create();


    }

    public IngredienteService getIngredienteService()
    {
       return retrofit.create(IngredienteService.class );
    }

    public ReceitaService getReceitaService() {
        return retrofit.create(ReceitaService.class);
    }
}
