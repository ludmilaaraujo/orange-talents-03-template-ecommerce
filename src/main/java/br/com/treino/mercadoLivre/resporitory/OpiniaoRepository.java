package br.com.treino.mercadoLivre.resporitory;

import br.com.treino.mercadoLivre.entidades.Opiniao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OpiniaoRepository extends   JpaRepository<Opiniao, Long> {

    @Query("SELECT AVG(o.nota) from br.com.treino.mercadoLivre.entidades.Opiniao o where o.produto.id = :idProduto")
    Double mediaNota(@Param("idProduto") Long idProduto);
}
