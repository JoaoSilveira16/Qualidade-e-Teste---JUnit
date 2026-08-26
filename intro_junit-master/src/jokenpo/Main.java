package jokenpo;

public class Main {

    public int jogar(int jogador1, int jogador2) {
        int retorno = 0;

        if ((jogador1 > 0) && (jogador1 <= 3)
                && (jogador2 > 0) && (jogador2 <= 3)) {

            if (jogador1 != jogador2) {
                if ((jogador1 - jogador2) == -1
                        || (jogador1 - jogador2) == 2) {
                    retorno = 1;
                } else {
                    retorno = 2;
                }
            }
        } else {
            retorno = -1;
        }

        return retorno;
    }
}