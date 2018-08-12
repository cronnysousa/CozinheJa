package com.sousa.ronny.cozinheja.model;

import java.util.ArrayList;

public class Receita {
    ArrayList<Ingrediente> ingredientes;
    String preparo;
    String url;
    ArrayList<String> foto;
    String nome;

    public boolean ContemIngrediente(Ingrediente item)
    {
        boolean resp = false;
        for (Ingrediente a:ingredientes) {
            if(a.nome ==item.nome)
            {
                resp= true;
            }
        }
        return resp;
    }

    public boolean ContemTodosIngredientes(ArrayList<Ingrediente> lista)
    {
        boolean respfin=true;
        for (Ingrediente a:ingredientes) {
            boolean resp = false;
            for (Ingrediente b:lista) {
                if(a.nome==b.nome)
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
        return nome.toString();
    }
}
