package br.com.treino.mercadoLivre.resporitory;

import br.com.treino.mercadoLivre.entidades.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerguntaRepository extends JpaRepository <Pergunta, Long> {
}
