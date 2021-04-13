package br.com.alura.leilao.model;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class LeilaoTest {

    private final Leilao LEILAO = new Leilao("Console");
    private final Usuario FRAN = new Usuario("Fran");
    private final Usuario ALEX = new Usuario("Alex");
    private final double DELTA = 0.0001;

    @Test
    public void deveDevolverDescricaoQuandoRecebeDescricao() {
        assertEquals("Console", LEILAO.getDescricao());
    }

    @Test
    public void deveDevolverMaiorValorQuandoRecebeApenasUmLance() {
        LEILAO.propoe(new Lance(FRAN, 300.00));
        assertEquals(300.00, LEILAO.getMaiorLance(), DELTA);
    }

    @Test
    public void deveDevolverMaiorValorQuandoRecebeLancesEmOrdemCrescente() {
        LEILAO.propoe(new Lance(FRAN, 9000.00));
        LEILAO.propoe(new Lance(ALEX, 10000.00));
        assertEquals(10000.00, LEILAO.getMaiorLance(), DELTA);
    }

    @Test
    public void deveDevolverMenorValorQuandoRecebeApenasUmLance() {
        LEILAO.propoe(new Lance(FRAN, 300.00));
        assertEquals(300.00, LEILAO.getMenorLance(), DELTA);
    }

    @Test
    public void deveDevolverMenorValorQuandoRecebeLancesEmOrdemCrescente() {
        LEILAO.propoe(new Lance(FRAN, 9000.00));
        LEILAO.propoe(new Lance(ALEX, 10000.00));
        assertEquals(9000.00, LEILAO.getMenorLance(), DELTA);
    }

    @Test
    public void deveDevolverTresMaioresLancesQuandoRecebeTresLances() {
        LEILAO.propoe(new Lance(ALEX, 200.00));
        LEILAO.propoe(new Lance(FRAN, 300.00));
        LEILAO.propoe(new Lance(ALEX, 400.00));

        List<Lance> tresMaioresLances =  LEILAO.getTresMaioresLances();

        assertEquals(3, tresMaioresLances.size());
        assertEquals(400.00, tresMaioresLances.get(0).getValor(), DELTA);
        assertEquals(300.00, tresMaioresLances.get(1).getValor(), DELTA);
        assertEquals(200.00, tresMaioresLances.get(2).getValor(), DELTA);
    }

    @Test
    public void deveDevolverTresMaioresLancesQuandoRecebeMaisDeTresLances() {
        LEILAO.propoe(new Lance(FRAN, 150.00));
        LEILAO.propoe(new Lance(ALEX, 200.00));
        LEILAO.propoe(new Lance(FRAN, 300.00));
        LEILAO.propoe(new Lance(ALEX, 400.00));

        List<Lance> tresMaioresLances =  LEILAO.getTresMaioresLances();

        assertEquals(3, tresMaioresLances.size());
        assertEquals(400.00, tresMaioresLances.get(0).getValor(), DELTA);
        assertEquals(300.00, tresMaioresLances.get(1).getValor(), DELTA);
        assertEquals(200.00, tresMaioresLances.get(2).getValor(), DELTA);
    }

    @Test
    public void deveDevolverTresMaioresLancesQuandoRecebeMenosDeTresLances() {
        LEILAO.propoe(new Lance(ALEX, 200.00));
        LEILAO.propoe(new Lance(FRAN, 300.00));

        List<Lance> tresMaioresLances =  LEILAO.getTresMaioresLances();

        assertEquals(2, tresMaioresLances.size());
        assertEquals(300.00, tresMaioresLances.get(0).getValor(), DELTA);
        assertEquals(200.00, tresMaioresLances.get(1).getValor(), DELTA);

    }

    @Test
    public void deveDevolverZeroParaMaiorLanceQuandoNaoTiverLances() {
        assertEquals(0.0, LEILAO.getMaiorLance(), DELTA);
    }

    @Test
    public void deveDevolverZeroParaMenorLanceQuandoNaoTiverLances() {
        assertEquals(0.0, LEILAO.getMenorLance(), DELTA);
    }

    @Test
    public void naoDeveAdicionarLanceQuandoForMenorQueOMaiorLance() {
        LEILAO.propoe(new Lance(ALEX, 200.00));
        LEILAO.propoe(new Lance(FRAN, 300.00));
        LEILAO.propoe(new Lance(ALEX, 299.00));

        List<Lance> tresMaioresLances =  LEILAO.getTresMaioresLances();

        assertEquals(2, tresMaioresLances.size());
        assertEquals(300.00, tresMaioresLances.get(0).getValor(), DELTA);
        assertEquals(200.00, tresMaioresLances.get(1).getValor(), DELTA);
    }

    @Test
    public void naoDeveAdicionarLanceQuandoForOMesmoUsuarioDoUltimoLance() {
        LEILAO.propoe(new Lance(ALEX, 200.00));
        LEILAO.propoe(new Lance(ALEX, 300.00));

        assertEquals(1, LEILAO.getQuantidadeLances());
    }
}