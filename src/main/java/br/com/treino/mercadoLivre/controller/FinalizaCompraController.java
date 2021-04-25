package br.com.treino.mercadoLivre.controller;

import br.com.treino.mercadoLivre.component.EnviaEmail;
import br.com.treino.mercadoLivre.entidades.Compra;
import br.com.treino.mercadoLivre.entidades.Produto;
import br.com.treino.mercadoLivre.entidades.Usuario;
import br.com.treino.mercadoLivre.request.FinalizaCompraRequest;
import br.com.treino.mercadoLivre.finalizaCompra.GatewayPagamento;
import br.com.treino.mercadoLivre.resporitory.FinalizaCompraRepository;
import br.com.treino.mercadoLivre.resporitory.ProdutoRepository;
import br.com.treino.mercadoLivre.resporitory.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
public class FinalizaCompraController {

    @Autowired
    private EnviaEmail enviaEmail;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    FinalizaCompraRepository finalizaCompraRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @PostMapping(value = "/compras")
    @Transactional
    public String finalizaCompra(@RequestBody FinalizaCompraRequest finalizaCompraRequest) {

        Produto produtoQueSeraComprado = finalizaCompraRequest.
                devolverProdutoBanco(produtoRepository);
        if (produtoQueSeraComprado.temEstoque()) {
            if (produtoQueSeraComprado.abateEstoque(finalizaCompraRequest.getQuantidade())) {
                Usuario usuario = usuarioRepository.findByLogin("ludmila@yahoo.com.br").get();
                Compra novaCompra = new Compra(produtoQueSeraComprado,
                        finalizaCompraRequest.getQuantidade(), usuario,
                        finalizaCompraRequest.getGatewayPagamento());
                produtoRepository.save(produtoQueSeraComprado);
                finalizaCompraRepository.save(novaCompra);
                enviaEmail.enviar(novaCompra.getEmailVendedor());

                if (finalizaCompraRequest.getGatewayPagamento().equals(GatewayPagamento.paypal)) {
                    return "paypal.com?buyerId={idGeradoDaCompra}" +
                            "&redirectUrl={urlRetornoAppPosPagamento}";
                } else {
                    return "pagseguro.com?returnId={idGeradoDaCompra}" +
                            "&redirectUrl={urlRetornoAppPosPagamento}";
                }

            }
            return "Não foi possível realizar a compra";
        }
        return "Não tem estoque";
    }

}