package com.sousa.ronny.cozinheja.model;

import java.util.ArrayList;

public class Receita {
    ArrayList<ItensReceita> ingredientes;
    String preparo;
    String url;
    String foto;
    String nome;

    public boolean ContemIngrediente(Ingrediente item)
    {
        boolean resp = false;
        for (ItensReceita a:ingredientes) {
            if(a.getIngrediente().nome ==item.nome)
            {
                resp= true;
            }
        }
        return resp;
    }

    public boolean ContemTodosIngredientes(ArrayList<Ingrediente> lista)
    {
        boolean respfin=true;
        for (ItensReceita a:ingredientes) {
            boolean resp = false;
            for (Ingrediente b:lista) {
                if(a.getIngrediente().nome==b.nome)
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
