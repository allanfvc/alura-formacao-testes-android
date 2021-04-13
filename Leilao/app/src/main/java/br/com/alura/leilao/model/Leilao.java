package br.com.alura.leilao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
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
        calcularMaiorLance(lance.getValor());
        calculaMenorLance(lance.getValor());
        lances.add(lance);
        Collections.sort(lances);
    }

    private void calculaMenorLance(double valorLance) {
        if(valorLance < menorLance) {
            menorLance = valorLance;
        }
    }

    private void calcularMaiorLance(double valorLance) {
        if(valorLance > maiorLance) {
            maiorLance = valorLance;
        }
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

    public List<Lance> getTresMaioresLances() {
        if(this.lances.size() >= 3) {
            return this.lances.subList(0,3);
        } else {
            return this.lances.subList(0, this.lances.size());
        }
    }
}
