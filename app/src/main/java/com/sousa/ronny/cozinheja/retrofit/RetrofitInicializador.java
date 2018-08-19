package com.sousa.ronny.cozinheja.retrofit;

import com.sousa.ronny.cozinheja.services.IngredienteService;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitInicializador {
    private final Retrofit retrofit;
    public RetrofitInicializador()
    {
        retrofit= new Retrofit.Builder()
                .baseUrl("http://www.programacaoronny.kinghost.net/api/")
                .addConverterFactory(JacksonConverterFactory.create()).build();
    }

    public IngredienteService getIngredienteService()
    {
       return retrofit.create(IngredienteService.class );
    }

}
