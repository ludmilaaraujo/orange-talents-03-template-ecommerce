package br.com.treino.mercadoLivre.validator;

import br.com.treino.mercadoLivre.request.ProdutoRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Set;

public class ProibeCaracteristicaRepetidaValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return ProdutoRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if(errors.hasErrors()){
            return;
        }
        ProdutoRequest request = (ProdutoRequest) o;
        Set<String>nomesRepetidos = request.buscaCaracteristicasRepetidas();
        if(!nomesRepetidos.isEmpty()) {
           errors.rejectValue("caracteristicas",
                            "Olha, você tem caracteristicas repetidas");
        }
    }
}
