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
}
