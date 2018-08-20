package com.sousa.ronny.cozinheja.services;

import com.sousa.ronny.cozinheja.model.Receita;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ReceitaService {
    @POST("Receitas")
    Call Insere(@Body Receita receita);

    @GET("Receitas")
    Call<ArrayList<Receita>> Seleciona();

    @GET("Receitas")
    Call<Receita> Seleciona(String id);


}
