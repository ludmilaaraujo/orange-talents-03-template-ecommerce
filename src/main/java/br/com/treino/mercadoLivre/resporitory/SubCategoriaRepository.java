package br.com.treino.mercadoLivre.resporitory;

import br.com.treino.mercadoLivre.entidades.SubCategoria;
import br.com.treino.mercadoLivre.request.SubCategoriaRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubCategoriaRepository extends JpaRepository<SubCategoria, Long> {
}
