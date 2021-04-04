package br.com.treino.mercadoLivre.resporitory;

import br.com.treino.mercadoLivre.entidades.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
