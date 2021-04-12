package br.com.alura.leilao.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class LeilaoTest {

    @Test
    public void getDescricaoQuandoRecebeDescricaoDevolveDescricao() {
        final Leilao console = new Leilao("Console");
        assertEquals("Console", console.getDescricao());
    }

    @Test
    public void getMaiorLanceQuandoRecebeApenasUmLanceDevolveMaiorValor() {
        final Leilao console = new Leilao("Console");
        console.propoe(new Lance(new Usuario("Fran"), 300.00));
        assertEquals(300.00, console.getMaiorLance(), 0.0001);
    }

    @Test
    public void getMaiorLanceQuandoRecebeLancesOrdemDescrescenteDevolveMaiorValor() {
        final Leilao computador = new Leilao("Computador");
        computador.propoe(new Lance(new Usuario("Fran"), 300.00));
        computador.propoe(new Lance(new Usuario("Alex"), 200.00));
        assertEquals(300.00, computador.getMaiorLance(), 0.0001);
    }


    @Test
    public void getMaiorLanceQuandoRecebeLancesOrdemCrescenteDevolveMaiorValor() {
        final Leilao leilao = new Leilao("Carro");
        leilao.propoe(new Lance(new Usuario("Fran"), 9000.00));
        leilao.propoe(new Lance(new Usuario("Alex"), 10000.00));
        assertEquals(10000.00, leilao.getMaiorLance(), 0.0001);
    }
}