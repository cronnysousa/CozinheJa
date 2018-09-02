package com.sousa.ronny.cozinheja.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Receita implements Serializable {
    private ArrayList<ItensReceita> itensReceita;
    private String preparo;
    private String url;
    private String foto;
    private String nome;
    private String id;

    public boolean ContemIngrediente(Ingrediente item) {
        boolean resp = false;
        for (ItensReceita a : getItensReceita()) {
            if (a.getIngrediente().getNome() == item.getNome()) {
                resp = true;
            }
        }
        return resp;
    }

    public boolean ContemAlgunsIngredientes(List<Ingrediente> lista) {
        boolean respfin = false;
        for (ItensReceita a : getItensReceita()) {
            boolean resp = false;
            for (Ingrediente b : lista) {
                if (a.getIngrediente().getNome().equals(b.getNome())) {
                    resp = true;
                }
            }
            if (resp) {
                return true;
            }
        }
        return respfin;

    }

    public int ContemQuantosIngredientes(List<Ingrediente> lista) {
        int qt = 0;
        for (ItensReceita a : getItensReceita()) {
            boolean resp = false;
            for (Ingrediente b : lista) {
                if (a.getIngrediente().getNome().equals(b.getNome())) {
                    resp = true;
                    break;
                }
            }
            if (resp) {
                qt++;
            }
        }
        return qt;

    }

    public double PercencentualIngredientes(List<Ingrediente> lista) {
        double qt = getItensReceita().size();
        double ct = ContemQuantosIngredientes(lista);
        double pc =  ct/qt ;
        return pc;

    }


    public boolean ContemTodosIngredientes(List<Ingrediente> lista) {
        boolean respfin = true;
        for (ItensReceita a : getItensReceita()) {
            boolean resp = false;
            for (Ingrediente b : lista) {
                if (a.getIngrediente().getNome().equals(b.getNome())) {
                    resp = true;
                }
            }
            if (!resp) {
                respfin = false;
            }
        }
        return respfin;
    }

    @Override
    public String toString() {
        return getNome().toString();
    }

    public ArrayList<ItensReceita> getItensReceita() {
        return itensReceita;
    }

    public void setItensReceita(ArrayList<ItensReceita> itensReceita) {
        this.itensReceita = itensReceita;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
