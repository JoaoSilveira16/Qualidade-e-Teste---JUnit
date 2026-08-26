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


    // ============================================================
    // Testes adicionais gerados pelo ChatGPT na atividade 2
    // ============================================================

    // Verifica se é possível adicionar o mesmo produto mais de uma vez
    @Test
    @DisplayName("Adicionar o mesmo produto duas vezes deve contabilizar dois itens")
    public void testAdicionarMesmoProdutoDuasVezes() {
        carrinho.addItem(livro);
        carrinho.addItem(livro);

        assertEquals(2, carrinho.getQtdeItems());
        assertEquals(200.00, carrinho.getValorTotal());
    }

    // Verifica se a remoção de um produto existente funciona corretamente
    @Test
    @DisplayName("Remover item existente deve diminuir a quantidade")
    public void testRemoveItemExistente() throws ProdutoNaoEncontradoException {
        carrinho.addItem(livro);

        carrinho.removeItem(livro);

        assertEquals(0, carrinho.getQtdeItems());
        assertEquals(0.0, carrinho.getValorTotal());
    }

    // Verifica se apenas o item removido deixa de fazer parte do total
    @Test
    @DisplayName("Remover um item deve manter os demais itens no carrinho")
    public void testRemoveItemMantemDemaisItens() throws ProdutoNaoEncontradoException {
        carrinho.addItem(livro);
        carrinho.addItem(caneta);

        carrinho.removeItem(livro);

        assertEquals(1, carrinho.getQtdeItems());
        assertEquals(2.50, carrinho.getValorTotal());
    }

    // Verifica se a remoção de um dos itens duplicados remove somente uma ocorrência
    @Test
    @DisplayName("Remover item duplicado deve remover apenas uma ocorrência")
    public void testRemoveItemDuplicado() throws ProdutoNaoEncontradoException {
        carrinho.addItem(livro);
        carrinho.addItem(livro);

        carrinho.removeItem(livro);

        assertEquals(1, carrinho.getQtdeItems());
        assertEquals(100.00, carrinho.getValorTotal());
    }

    // Verifica se remover um item de um carrinho vazio lança a exceção esperada
    @Test
    @DisplayName("Remover item de carrinho vazio deve lançar exceção")
    public void testRemoveItemCarrinhoVazio() {
        assertThrows(ProdutoNaoEncontradoException.class,
                () -> carrinho.removeItem(livro));
    }

    // Verifica se esvaziar um carrinho que já está vazio não causa erro
    @Test
    @DisplayName("Esvaziar carrinho vazio deve continuar vazio")
    public void testEsvaziaCarrinhoJaVazio() {
        carrinho.esvazia();

        assertEquals(0, carrinho.getQtdeItems());
        assertEquals(0.0, carrinho.getValorTotal());
    }

    // Verifica se é possível adicionar itens novamente após esvaziar o carrinho
    @Test
    @DisplayName("Adicionar item após esvaziar o carrinho deve funcionar")
    public void testAdicionarItemDepoisDeEsvaziar() {
        carrinho.addItem(livro);
        carrinho.esvazia();

        carrinho.addItem(caneta);

        assertEquals(1, carrinho.getQtdeItems());
        assertEquals(2.50, carrinho.getValorTotal());
    }

    // Verifica o valor total quando vários itens iguais são adicionados
    @Test
    @DisplayName("Valor total de vários itens iguais deve ser calculado corretamente")
    public void testValorTotalComItensIguais() {
        carrinho.addItem(caneta);
        carrinho.addItem(caneta);
        carrinho.addItem(caneta);

        assertEquals(3, carrinho.getQtdeItems());
        assertEquals(7.50, carrinho.getValorTotal());
    }

    // Verifica o valor total de vários produtos diferentes
    @Test
    @DisplayName("Valor total de vários produtos diferentes deve ser calculado corretamente")
    public void testValorTotalComVariosProdutos() {
        Produto caderno = new Produto("Caderno", 15.75);
        Produto lapis = new Produto("Lápis", 1.25);

        carrinho.addItem(livro);
        carrinho.addItem(caneta);
        carrinho.addItem(caderno);
        carrinho.addItem(lapis);

        assertEquals(4, carrinho.getQtdeItems());
        assertEquals(119.50, carrinho.getValorTotal());
    }

    // Verifica se adicionar um produto de valor zero não altera o valor total
    @Test
    @DisplayName("Produto com preço zero não deve alterar o valor total")
    public void testProdutoComPrecoZero() {
        Produto produtoGratis = new Produto("Produto grátis", 0.0);

        carrinho.addItem(produtoGratis);

        assertEquals(1, carrinho.getQtdeItems());
        assertEquals(0.0, carrinho.getValorTotal());
    }

    // Verifica se um produto com preço decimal é somado corretamente
    @Test
    @DisplayName("Produto com preço decimal deve ser calculado corretamente")
    public void testProdutoComPrecoDecimal() {
        Produto produto = new Produto("Produto decimal", 10.99);

        carrinho.addItem(produto);

        assertEquals(1, carrinho.getQtdeItems());
        assertEquals(10.99, carrinho.getValorTotal(), 0.001);
    }

    // Verifica se a quantidade de itens aumenta corretamente após várias inserções
    @Test
    @DisplayName("Quantidade deve aumentar a cada item adicionado")
    public void testQuantidadeAumentaComAdicoes() {
        carrinho.addItem(livro);
        assertEquals(1, carrinho.getQtdeItems());

        carrinho.addItem(caneta);
        assertEquals(2, carrinho.getQtdeItems());

        carrinho.addItem(livro);
        assertEquals(3, carrinho.getQtdeItems());
    }

    // Verifica se remover todos os itens um por um deixa o carrinho vazio
    @Test
    @DisplayName("Remover todos os itens um por um deve deixar o carrinho vazio")
    public void testRemoverTodosOsItens() throws ProdutoNaoEncontradoException {
        carrinho.addItem(livro);
        carrinho.addItem(caneta);

        carrinho.removeItem(livro);
        carrinho.removeItem(caneta);

        assertEquals(0, carrinho.getQtdeItems());
        assertEquals(0.0, carrinho.getValorTotal());
    }

    // Verifica se, após remover um produto, ele pode ser adicionado novamente
    @Test
    @DisplayName("Produto removido pode ser adicionado novamente")
    public void testAdicionarProdutoNovamenteAposRemocao()
            throws ProdutoNaoEncontradoException {

        carrinho.addItem(livro);
        carrinho.removeItem(livro);
        carrinho.addItem(livro);

        assertEquals(1, carrinho.getQtdeItems());
        assertEquals(100.00, carrinho.getValorTotal());
    }

    // Verifica se o mesmo carrinho pode ser esvaziado várias vezes
    @Test
    @DisplayName("Esvaziar o mesmo carrinho várias vezes deve funcionar")
    public void testEsvaziarCarrinhoVariasVezes() {
        carrinho.addItem(livro);
        carrinho.esvazia();
        carrinho.esvazia();
        carrinho.esvazia();

        assertEquals(0, carrinho.getQtdeItems());
        assertEquals(0.0, carrinho.getValorTotal());
    }


}
