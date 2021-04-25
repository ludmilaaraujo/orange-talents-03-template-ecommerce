package br.com.treino.mercadoLivre.controller;

import br.com.treino.mercadoLivre.component.EnviaEmail;
import br.com.treino.mercadoLivre.entidades.Caracteristica;
import br.com.treino.mercadoLivre.entidades.Produto;
import br.com.treino.mercadoLivre.entidades.SubCategoria;
import br.com.treino.mercadoLivre.entidades.Usuario;
import br.com.treino.mercadoLivre.request.CaracteristicaRequest;
import br.com.treino.mercadoLivre.request.FinalizaCompraRequest;
import br.com.treino.mercadoLivre.resporitory.FinalizaCompraRepository;
import br.com.treino.mercadoLivre.resporitory.ProdutoRepository;
import br.com.treino.mercadoLivre.resporitory.UsuarioRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static br.com.treino.mercadoLivre.finalizaCompra.GatewayPagamento.pagSeguro;
import static br.com.treino.mercadoLivre.finalizaCompra.GatewayPagamento.paypal;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class FinalizaCompraControllerTest {

    @InjectMocks
    private FinalizaCompraController finalizaCompraController;

    @Mock
    private EnviaEmail enviaEmail;

    @Mock
    UsuarioRepository usuarioRepository;

    @Mock
    FinalizaCompraRepository finalizaCompraRepository;

    @Mock
    ProdutoRepository produtoRepository;

    @Mock
    private Produto produtoQueSeraComprado;

    @Mock
    private SubCategoria subCategoria;

    @Mock
    private Usuario usuario;

    @Test
    public void finalizaCompraSemEstoque() {
        FinalizaCompraRequest finalizaCompraRequest
                = new FinalizaCompraRequest(1, 1l, pagSeguro);
        when(finalizaCompraRequest.devolverProdutoBanco(produtoRepository)).thenReturn(produtoQueSeraComprado);
        when(produtoQueSeraComprado.temEstoque()).thenReturn(false);
        String stringDevolvida = finalizaCompraController.finalizaCompra(finalizaCompraRequest);
        assertEquals("Não tem estoque", stringDevolvida);
    }

    @Test
    public void finalizaCompraComEstoquePagSeguro() {
        Set<CaracteristicaRequest> caracteristicas = new HashSet<CaracteristicaRequest>();
        caracteristicas.add(new CaracteristicaRequest("Altura","10.00"));
        caracteristicas.add(new CaracteristicaRequest("Peso","5.00"));
        FinalizaCompraRequest finalizaCompraRequest
                = new FinalizaCompraRequest(1, 1l, pagSeguro);
        Produto produto = new Produto(subCategoria, "Produto TEste",
                10, "Teste", new BigDecimal(10.00), usuario, caracteristicas);
        when(finalizaCompraRequest.devolverProdutoBanco(produtoRepository)).thenReturn(produto);
        Optional<Usuario> optionalUsuario = Optional.of(usuario);
        when(usuarioRepository.findByLogin("ludmila@yahoo.com.br")).thenReturn(optionalUsuario);
        String stringDevolvida = finalizaCompraController.finalizaCompra(finalizaCompraRequest);
        assertEquals(new Integer(9), produto.getQuantidade());
        assertEquals("pagseguro.com?returnId={idGeradoDaCompra}&redirectUrl={urlRetornoAppPosPagamento}", stringDevolvida);
    }

    @Test
    public void finalizaCompraComEstoquePaypal() {
        Set<CaracteristicaRequest> caracteristicas = new HashSet<CaracteristicaRequest>();
        caracteristicas.add(new CaracteristicaRequest("Altura","10.00"));
        caracteristicas.add(new CaracteristicaRequest("Peso","5.00"));
        FinalizaCompraRequest finalizaCompraRequest
                = new FinalizaCompraRequest(1, 1l, paypal);
        Produto produto = new Produto(subCategoria, "Produto TEste",
                10, "Teste", new BigDecimal(10.00), usuario, caracteristicas);
        when(finalizaCompraRequest.devolverProdutoBanco(produtoRepository)).thenReturn(produto);
        Optional<Usuario> optionalUsuario = Optional.of(usuario);
        when(usuarioRepository.findByLogin("ludmila@yahoo.com.br")).thenReturn(optionalUsuario);
        String stringDevolvida = finalizaCompraController.finalizaCompra(finalizaCompraRequest);
        assertEquals(new Integer(9), produto.getQuantidade());
        assertEquals("paypal.com?buyerId={idGeradoDaCompra}" +
                "&redirectUrl={urlRetornoAppPosPagamento}", stringDevolvida);
    }

    @Test
    public void finalizaCompraNaoConsegueAbaterEstoque(){
        FinalizaCompraRequest finalizaCompraRequest
                = new FinalizaCompraRequest(2, 1l, pagSeguro);
        Set<CaracteristicaRequest> caracteristicas = new HashSet<CaracteristicaRequest>();
        caracteristicas.add(new CaracteristicaRequest("Altura","10.00"));
        caracteristicas.add(new CaracteristicaRequest("Peso","5.00"));
        Produto produto = new Produto(subCategoria, "Produto TEste",
                1, "Teste", new BigDecimal(10.00), usuario, caracteristicas);
        when(finalizaCompraRequest.devolverProdutoBanco(produtoRepository)).thenReturn(produto);

        String stringDevolvida = finalizaCompraController.finalizaCompra(finalizaCompraRequest);

        assertEquals("Não foi possível realizar a compra", stringDevolvida);
    }
}