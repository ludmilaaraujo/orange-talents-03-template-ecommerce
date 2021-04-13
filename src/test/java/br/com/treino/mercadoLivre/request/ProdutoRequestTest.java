package br.com.treino.mercadoLivre.request;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class ProdutoRequestTest {

    @Test
    public void buscaCaracteristicasRepetidas() {
        Set<CaracteristicaRequest>
                listaCaracteristicas = new HashSet<CaracteristicaRequest>();
        CaracteristicaRequest c1
                = new CaracteristicaRequest("Teste1" ,"Dteste");
        CaracteristicaRequest c2
                = new CaracteristicaRequest("Teste1" ,"Dteste");
        listaCaracteristicas.add(c1);
        listaCaracteristicas.add(c2);
        ProdutoRequest p = new ProdutoRequest(
                null,
                null,
                0,
                null,
                null,
                null,
                listaCaracteristicas);

        Set<String> listaDeRepetidos = p.buscaCaracteristicasRepetidas();
        Assert.assertTrue(!listaDeRepetidos.isEmpty());
        Assert.assertEquals(1,listaDeRepetidos.size());
        Assert.assertTrue(listaDeRepetidos.contains("Teste1"));
    }
}