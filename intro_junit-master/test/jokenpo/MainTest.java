package jokenpo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MainTest {

    private Main main = new Main();

    @Test
    void deveRetornarEmpateQuandoAmbosEscolhemPapel() {
        assertEquals(0, main.jogar(1, 1));
    }

    @Test
    void deveRetornarEmpateQuandoAmbosEscolhemPedra() {
        assertEquals(0, main.jogar(2, 2));
    }

    @Test
    void deveRetornarEmpateQuandoAmbosEscolhemTesoura() {
        assertEquals(0, main.jogar(3, 3));
    }

    @Test
    void jogador1DeveVencerQuandoPapelContraPedra() {
        assertEquals(1, main.jogar(1, 2));
    }

    @Test
    void jogador1DeveVencerQuandoPedraContraTesoura() {
        assertEquals(1, main.jogar(2, 3));
    }

    @Test
    void jogador1DeveVencerQuandoTesouraContraPapel() {
        assertEquals(1, main.jogar(3, 1));
    }

    @Test
    void jogador2DeveVencerQuandoPedraContraPapel() {
        assertEquals(2, main.jogar(2, 1));
    }

    @Test
    void jogador2DeveVencerQuandoTesouraContraPedra() {
        assertEquals(2, main.jogar(3, 2));
    }

    @Test
    void jogador2DeveVencerQuandoPapelContraTesoura() {
        assertEquals(2, main.jogar(1, 3));
    }

    @Test
    void deveRetornarMenosUmQuandoJogador1EscolheZero() {
        assertEquals(-1, main.jogar(0, 1));
    }

    @Test
    void deveRetornarMenosUmQuandoJogador1EscolheValorNegativo() {
        assertEquals(-1, main.jogar(-1, 1));
    }

    @Test
    void deveRetornarMenosUmQuandoJogador1EscolheValorMaiorQueTres() {
        assertEquals(-1, main.jogar(4, 1));
    }

    @Test
    void deveRetornarMenosUmQuandoJogador2EscolheZero() {
        assertEquals(-1, main.jogar(1, 0));
    }

    @Test
    void deveRetornarMenosUmQuandoJogador2EscolheValorNegativo() {
        assertEquals(-1, main.jogar(1, -1));
    }

    @Test
    void deveRetornarMenosUmQuandoJogador2EscolheValorMaiorQueTres() {
        assertEquals(-1, main.jogar(1, 4));
    }
}