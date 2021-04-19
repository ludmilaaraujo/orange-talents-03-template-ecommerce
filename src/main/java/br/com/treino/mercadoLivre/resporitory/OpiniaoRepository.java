package br.com.treino.mercadoLivre.resporitory;

import br.com.treino.mercadoLivre.entidades.Opiniao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpiniaoRepository extends   JpaRepository<Opiniao, Long> {
}
