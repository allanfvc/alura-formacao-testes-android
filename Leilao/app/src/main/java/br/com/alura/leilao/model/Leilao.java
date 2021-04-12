package br.com.alura.leilao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Leilao implements Serializable {

    private final String descricao;
    private final List<Lance> lances;
    private double menorLance = Double.POSITIVE_INFINITY;
    private double maiorLance = Double.NEGATIVE_INFINITY;

    public Leilao(String descricao) {
        this.descricao = descricao;
        this.lances = new ArrayList<>();
    }

    public void propoe(Lance lance) {
        if(lance.getValor() > maiorLance) {
            maiorLance = lance.getValor();
        }

        if(lance.getValor() < menorLance) {
            menorLance = lance.getValor();
        }
        lances.add(lance);
    }

    public String getDescricao() {
        return descricao;
    }

    public double getMenorLance() {
        return menorLance;
    }

    public double getMaiorLance() {
        return maiorLance;
    }
}
