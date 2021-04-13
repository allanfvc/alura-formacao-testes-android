package br.com.alura.leilao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao implements Serializable {

    private final String descricao;
    private final List<Lance> lances;
    private double menorLance = 0.00;
    private double maiorLance = 0.00;

    public Leilao(String descricao) {
        this.descricao = descricao;
        this.lances = new ArrayList<>();
    }

    public void propoe(Lance lance) {
        if (lanceNaoValido(lance)) return;
        calculaMaiorEMenorLance(lance);
        lances.add(lance);
        Collections.sort(lances);

    }

    private boolean lanceNaoValido(Lance lance) {
        if (lance.getValor() < maiorLance) return true;
        if(!lances.isEmpty()) {
            if (usuarioUltimoLance(lance)) return true;
            if (usuarioDeuCincoLances(lance)) return true;
        }

        return false;
    }

    private boolean usuarioDeuCincoLances(Lance lance) {
        int lancesDoUsuario = 0;
        for (Lance l: lances) {
            if(l.getUsuario().equals(lance.getUsuario())) {
                lancesDoUsuario++;
                if(lancesDoUsuario == 5) return true;
            }
        }
        return false;
    }

    private boolean usuarioUltimoLance(Lance lance) {
        Lance ultimoLance = lances.get(0);
        if(ultimoLance.getUsuario().equals(lance.getUsuario())) return true;
        return false;
    }

    private void calculaMaiorEMenorLance(Lance lance) {
        if (lances.isEmpty()) {
            maiorLance = lance.getValor();
            menorLance = lance.getValor();
        } else {
            calcularMaiorLance(lance.getValor());
            calculaMenorLance(lance.getValor());
        }
    }

    private void calculaMenorLance(double valorLance) {
        if (valorLance < menorLance) {
            menorLance = valorLance;
        }
    }

    private void calcularMaiorLance(double valorLance) {
        if (valorLance > maiorLance) {
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
        if (this.lances.size() >= 3) {
            return this.lances.subList(0, 3);
        } else {
            return this.lances.subList(0, this.lances.size());
        }
    }

    public int getQuantidadeLances() {
        return this.lances.size();
    }
}
