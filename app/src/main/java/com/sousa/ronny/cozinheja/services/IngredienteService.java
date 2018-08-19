package com.sousa.ronny.cozinheja.services;

import com.sousa.ronny.cozinheja.model.Ingrediente;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IngredienteService {
    @POST("Ingredientes")
    Call Insere(@Body Ingrediente ingrediente);

    @GET("Ingredientes")
    Call<ArrayList <Ingrediente>> Seleciona();

}
