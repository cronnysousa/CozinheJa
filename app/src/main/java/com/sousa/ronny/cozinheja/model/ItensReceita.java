package com.sousa.ronny.cozinheja.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ItensReceita implements Serializable {
    private String id;
    private Ingrediente ingrediente;

    private   int qt;
    private  String fracao;
    private String unidade;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

    public int getQt() {
        return qt;
    }

    public void setQt(int qt) {
        this.qt = qt;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getFracao() {
        return fracao;
    }

    public void setFracao(String fracao) {
        this.fracao = fracao;
    }
}
