package com.sousa.ronny.cozinheja.services;

import com.sousa.ronny.cozinheja.model.ItensReceita;
import com.sousa.ronny.cozinheja.model.Receita;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ItensReceitaService {
    @POST("ItensReceita")
    Call Insere(@Body ItensReceita itensReceita);

    @GET("ItensReceita")
    Call<ArrayList<ItensReceita>> Seleciona();

    @GET("ItensReceita")
    Call<ItensReceita> Seleciona(String id);
}
