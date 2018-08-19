package com.sousa.ronny.cozinheja.model;

public class Ingrediente {
    private String id;
    private String nome;


    @Override
    public String toString() {
        return getNome().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
