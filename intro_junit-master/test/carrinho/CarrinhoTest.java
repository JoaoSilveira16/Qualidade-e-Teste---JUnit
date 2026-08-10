package carrinho;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import produto.Produto;
import produto.ProdutoNaoEncontradoException;

@DisplayName("Classe para teste do carrinho de compras")
public class CarrinhoTest {

	private Carrinho carrinho;
	private Produto livro;
	private Produto caneta;

	// Recriando o carrinho e os produtos de teste antes de cada @Test
	@BeforeEach
	public void inicializa() {
		carrinho = new Carrinho();
		livro = new Produto("Livro 01", 100.00);
		caneta = new Produto("Caneta", 2.50);
	}

	// Verifica se um carrinho recém-criado começa com zero itens e valor total zero
	@Test
	@DisplayName("Carrinho recém-criado deve estar vazio")
	public void testCarrinhoNovoEstaVazio() {
		assertEquals(0, carrinho.getQtdeItems());
		assertTrue(carrinho.getValorTotal() == 0.0);
	}

	// Verifica se adicionar um item reflete corretamente na quantidade e no valor total
	@Test
	@DisplayName("Adicionar item deve aumentar a quantidade e o valor total")
	public void testAddItem() {
		carrinho.addItem(livro);

		assertEquals(1, carrinho.getQtdeItems());
		assertEquals(100.00, carrinho.getValorTotal());
	}

	// Verifica se o valor total soma corretamente o preço de múltiplos itens no carrinho
	@Test
	@DisplayName("Valor total deve somar o preço de todos os itens")
	public void testValorTotalComVariosItens() {
		carrinho.addItem(livro);
		carrinho.addItem(caneta);

		assertEquals(2, carrinho.getQtdeItems());
		assertEquals(102.50, carrinho.getValorTotal());
	}

	// Verifica se tentar remover um item que não está no carrinho lança a exceção esperada
	@Test
	@DisplayName("Remover item inexistente deve lançar ProdutoNaoEncontradoException")
	public void testRemoveItemInexistenteLancaExcecao() {
		assertThrows(ProdutoNaoEncontradoException.class,
				() -> carrinho.removeItem(livro));
	}

	// Verifica se esvaziar o carrinho zera a quantidade de itens e o valor total, mesmo com vários itens adicionados antes
	@Test
	@DisplayName("Esvaziar o carrinho deve zerar quantidade e valor total")
	public void testEsvaziaCarrinho() {
		carrinho.addItem(livro);
		carrinho.addItem(caneta);

		carrinho.esvazia();

		assertEquals(0, carrinho.getQtdeItems());
		assertTrue(carrinho.getValorTotal() == 0.0);
	}

}
