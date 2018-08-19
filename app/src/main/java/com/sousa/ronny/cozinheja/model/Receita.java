package com.sousa.ronny.cozinheja.model;

import java.util.ArrayList;

public class Receita {
    private ArrayList<ItensReceita> ingredientes;
    private String preparo;
    private String url;
    private String foto;
    private String nome;

    public boolean ContemIngrediente(Ingrediente item)
    {
        boolean resp = false;
        for (ItensReceita a: getIngredientes()) {
            if(a.getIngrediente().getNome() == item.getNome())
            {
                resp= true;
            }
        }
        return resp;
    }

    public boolean ContemTodosIngredientes(ArrayList<Ingrediente> lista)
    {
        boolean respfin=true;
        for (ItensReceita a: getIngredientes()) {
            boolean resp = false;
            for (Ingrediente b:lista) {
                if(a.getIngrediente().getNome() == b.getNome())
                {
                    resp=true;
                }
            }
            if(!resp)
            {
                respfin=false;
            }
        }
        return respfin;
    }

    @Override
    public String toString() {
        return getNome().toString();
    }

    public ArrayList<ItensReceita> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(ArrayList<ItensReceita> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getPreparo() {
        return preparo;
    }

    public void setPreparo(String preparo) {
        this.preparo = preparo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
