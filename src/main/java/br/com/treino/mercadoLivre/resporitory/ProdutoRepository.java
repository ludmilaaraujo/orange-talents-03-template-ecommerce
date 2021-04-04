package br.com.treino.mercadoLivre.resporitory;

import br.com.treino.mercadoLivre.entidades.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
