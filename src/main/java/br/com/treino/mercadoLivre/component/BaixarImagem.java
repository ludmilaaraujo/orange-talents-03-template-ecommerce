package br.com.treino.mercadoLivre.component;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class BaixarImagem {
    public List<String> envia(List<MultipartFile> imagens) {

        List<String> links = new ArrayList<String>();

        for (MultipartFile i: imagens) {
            links.add("http://bucket.io/" + i.getOriginalFilename() + " - " +
                    UUID.randomUUID().toString());
        }
        return links;
    }
}
