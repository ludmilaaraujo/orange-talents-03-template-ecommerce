package br.com.treino.mercadoLivre.resporitory;

import br.com.treino.mercadoLivre.entidades.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinalizaCompraRepository extends JpaRepository<Compra, Long> {
}
