package calculadora;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Classe para teste da calculadora")
public class CalculadoraTest {
	
	private Calculadora calc;
	
	@BeforeEach
	public void inicializa() {
		calc = new Calculadora();
	}
	
	@DisplayName("Testa a soma de dois n�meros")
	@Test
	public void testSomaDoisNumeros() {
		int soma = calc.soma(4, 5);		
		Assertions.assertEquals(9, soma);		
	}
	
	@Test
	public void testDivisaoDoisNumeros() {
		int divisao = calc.divisao(8, 4);
		assertTrue(divisao == 2);
	}
	
	@Test
	public void testDivisaoPorZero() {
		try {
			int divisao = calc.divisao(8, 0);
			fail("Exce��o n�o lan�ada");
		}catch (ArithmeticException e) {
			assertEquals("/ by zero", e.getMessage());
		}		
	}
	
	@Test
	public void testDivisaoPorZeroComAssertThrows() {
		assertThrows(ArithmeticException.class,
				() -> calc.divisao(8, 0));
	}


	// Verifica se a soma de dois números retorna o resultado correto
	@DisplayName("Testa a soma de dois números")
	@Test
	public void testSoma() {
		int soma = calc.soma(10, 5);
		assertEquals(15, soma);
	}

	// Verifica se a subtração de dois números positivos retorna o resultado correto
	@DisplayName("Testa a subtração de dois números")
	@Test
	public void testSubtracaoDoisNumeros() {
		int subtracao = calc.subtracao(9, 4);
		assertEquals(5, subtracao);
	}

	// Verifica se a multiplicação de dois números positivos retorna o resultado correto
	@DisplayName("Testa a multiplicação de dois números")
	@Test
	public void testMultiplicacaoDoisNumeros() {
		int multiplicacao = calc.multiplicacao(4, 5);
		assertEquals(20, multiplicacao);
	}

	// Verifica se multiplicar qualquer número por zero retorna zero
	@Test
	public void testMultiplicacaoPorZero() {
		int multiplicacao = calc.multiplicacao(4, 0);
		assertEquals(0, multiplicacao);
	}

	// Verifica se o somatório de 0 até n soma corretamente todos os valores no intervalo
	@DisplayName("Testa o somatório de 0 até n")
	@Test
	public void testSomatoriaDeZeroAteN() {
		int somatoria = calc.somatoria(4);
		assertEquals(10, somatoria);
	}

	// Verifica se um número positivo é reconhecido como positivo
	@DisplayName("Testa se um número é positivo")
	@Test
	public void testEhPositivoComNumeroPositivo() {
		assertTrue(calc.ehPositivo(5));
	}

	// Verifica se um número negativo é reconhecido como "não positivo"
	@Test
	public void testEhPositivoComNumeroNegativo() {
		Assertions.assertFalse(calc.ehPositivo(-1));
	}

	// Verifica se a comparação de dois números iguais retorna 0
	@DisplayName("Testa a comparação entre dois números")
	@Test
	public void testComparaNumerosIguais() {
		assertEquals(0, calc.compara(5, 5));
	}


    // ============================================================
    // Testes adicionais gerados pelo ChatGPT na atividade 2
    // ============================================================

    // Testa soma com números negativos
    @Test
    public void testSomaComNumerosNegativos() {
        assertEquals(-9, calc.soma(-4, -5));
    }

    // Testa soma de número positivo com número negativo
    @Test
    public void testSomaComNumeroPositivoENegativo() {
        assertEquals(5, calc.soma(10, -5));
    }

    // Testa soma com zero
    @Test
    public void testSomaComZero() {
        assertEquals(10, calc.soma(10, 0));
    }

    // Testa soma com Integer.MAX_VALUE e zero
    @Test
    public void testSomaComValorMaximo() {
        assertEquals(Integer.MAX_VALUE, calc.soma(Integer.MAX_VALUE, 0));
    }

    // Testa subtração com resultado negativo
    @Test
    public void testSubtracaoComResultadoNegativo() {
        assertEquals(-5, calc.subtracao(4, 9));
    }

    // Testa subtração de números negativos
    @Test
    public void testSubtracaoComNumerosNegativos() {
        assertEquals(1, calc.subtracao(-4, -5));
    }

    // Testa subtração por zero
    @Test
    public void testSubtracaoPorZero() {
        assertEquals(10, calc.subtracao(10, 0));
    }

    // Testa subtração de zero por um número
    @Test
    public void testSubtracaoDeZero() {
        assertEquals(-10, calc.subtracao(0, 10));
    }

    // Testa multiplicação com números negativos
    @Test
    public void testMultiplicacaoComNumerosNegativos() {
        assertEquals(20, calc.multiplicacao(-4, -5));
    }

    // Testa multiplicação de número positivo por negativo
    @Test
    public void testMultiplicacaoPositivoPorNegativo() {
        assertEquals(-20, calc.multiplicacao(4, -5));
    }

    // Testa multiplicação por 1
    @Test
    public void testMultiplicacaoPorUm() {
        assertEquals(10, calc.multiplicacao(10, 1));
    }

    // Testa multiplicação de zero por zero
    @Test
    public void testMultiplicacaoZeroPorZero() {
        assertEquals(0, calc.multiplicacao(0, 0));
    }

    // Testa divisão com números negativos
    @Test
    public void testDivisaoComNumerosNegativos() {
        assertEquals(2, calc.divisao(-8, -4));
    }

    // Testa divisão de número positivo por negativo
    @Test
    public void testDivisaoPositivoPorNegativo() {
        assertEquals(-2, calc.divisao(8, -4));
    }

    // Testa divisão de zero por um número
    @Test
    public void testDivisaoDeZero() {
        assertEquals(0, calc.divisao(0, 5));
    }

    // Testa divisão com resultado inteiro truncado
    @Test
    public void testDivisaoComResultadoTruncado() {
        assertEquals(2, calc.divisao(5, 2));
    }

    // Testa divisão de um número por ele mesmo
    @Test
    public void testDivisaoDoNumeroPorEleMesmo() {
        assertEquals(1, calc.divisao(7, 7));
    }

    // Testa somatória de zero
    @Test
    public void testSomatoriaDeZero() {
        assertEquals(0, calc.somatoria(0));
    }

    // Testa somatória de um
    @Test
    public void testSomatoriaDeUm() {
        assertEquals(1, calc.somatoria(1));
    }

    // Testa somatória de um número maior
    @Test
    public void testSomatoriaDeDez() {
        assertEquals(55, calc.somatoria(10));
    }

    // Testa somatória com número negativo.
    // Pela implementação atual, o while não é executado.
    @Test
    public void testSomatoriaComNumeroNegativo() {
        assertEquals(0, calc.somatoria(-5));
    }

    // Testa se zero é considerado positivo pela implementação atual
    @Test
    public void testEhPositivoComZero() {
        assertTrue(calc.ehPositivo(0));
    }

    // Testa número positivo maior
    @Test
    public void testEhPositivoComNumeroPositivoMaior() {
        assertTrue(calc.ehPositivo(100));
    }

    // Testa número negativo menor
    @Test
    public void testEhPositivoComNumeroNegativoMaior() {
        Assertions.assertFalse(calc.ehPositivo(-100));
    }

    // Testa comparação quando o primeiro número é maior
    @Test
    public void testComparaQuandoPrimeiroNumeroEMaior() {
        assertEquals(1, calc.compara(10, 5));
    }

    // Testa comparação quando o primeiro número é menor
    @Test
    public void testComparaQuandoPrimeiroNumeroEMenor() {
        assertEquals(-1, calc.compara(5, 10));
    }

    // Testa comparação entre números negativos iguais
    @Test
    public void testComparaNumerosNegativosIguais() {
        assertEquals(0, calc.compara(-5, -5));
    }

    // Testa comparação entre números negativos quando o primeiro é maior
    @Test
    public void testComparaNumerosNegativosPrimeiroMaior() {
        assertEquals(1, calc.compara(-3, -5));
    }

    // Testa comparação entre números negativos quando o primeiro é menor
    @Test
    public void testComparaNumerosNegativosPrimeiroMenor() {
        assertEquals(-1, calc.compara(-5, -3));
    }

    // Testa comparação envolvendo zero
    @Test
    public void testComparaZeroComNumeroPositivo() {
        assertEquals(-1, calc.compara(0, 5));
    }

    // Testa comparação envolvendo zero
    @Test
    public void testComparaNumeroPositivoComZero() {
        assertEquals(1, calc.compara(5, 0));
    }

    // Testa comparação entre Integer.MAX_VALUE e Integer.MIN_VALUE
    @Test
    public void testComparaValoresExtremos() {
        assertEquals(1, calc.compara(Integer.MAX_VALUE, Integer.MIN_VALUE));
    }





}
