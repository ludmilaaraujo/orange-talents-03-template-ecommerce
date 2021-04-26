package br.com.treino.mercadoLivre.controller;

import br.com.treino.mercadoLivre.component.EnviaEmail;
import br.com.treino.mercadoLivre.component.Fiscal;
import br.com.treino.mercadoLivre.entidades.Compra;
import br.com.treino.mercadoLivre.entidades.Produto;
import br.com.treino.mercadoLivre.entidades.StatusPagamento;
import br.com.treino.mercadoLivre.entidades.Usuario;
import br.com.treino.mercadoLivre.request.FinalizaCompraRequest;
import br.com.treino.mercadoLivre.finalizaCompra.GatewayPagamento;
import br.com.treino.mercadoLivre.request.PosVendaRequest;
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
    private Fiscal fiscal;

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

    @PostMapping("/posVenda")
    public String posVenda(PosVendaRequest posVendaRequest){
        if(posVendaRequest.getStatusPagamento().equals(StatusPagamento.APROVADO)) {
            Compra compra = finalizaCompraRepository.getOne(posVendaRequest.getIdCompra());
            enviaEmail.enviar(compra.getEmailComprador());
            fiscal.faturar(posVendaRequest.getIdCompra());
            Produto produtoComprado = compra.getProdutoQueSeraComprado();
            produtoComprado.sobeNoRanking();
            produtoRepository.save(produtoComprado);
            finalizaCompraRepository.save(compra);
            return "Sucesso na compra";
        }  else if(posVendaRequest.getStatusPagamento().equals(StatusPagamento.REPROVADO)) {
            Compra compra = finalizaCompraRepository.getOne(posVendaRequest.getIdCompra());
            enviaEmail.enviar(compra.getEmailComprador());
            return "Compra reprovada";
        }
            return "Compra com erro";

    }

}