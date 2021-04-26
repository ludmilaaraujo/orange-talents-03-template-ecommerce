package br.com.treino.mercadoLivre.component;

import org.springframework.stereotype.Component;

@Component
public class Fiscal {

    public void faturar(Long idCompra){
        System.out.println("Gerando Nota Fiscal: " + idCompra);
    }
}
