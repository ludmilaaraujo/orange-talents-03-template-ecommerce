package br.com.treino.mercadoLivre.request;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class ImagensRequest {
    @Size(min = 1)
    @NotNull
    @Valid
    private List<MultipartFile>imagens = new ArrayList<>();

    public void setImagens(List<MultipartFile> imagens) {
        this.imagens = imagens;
    }

    public List<MultipartFile> getImagens() {

        return imagens;
    }
}
