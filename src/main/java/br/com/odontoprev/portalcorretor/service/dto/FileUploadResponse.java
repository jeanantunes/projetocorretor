package br.com.odontoprev.portalcorretor.service.dto;

import java.io.Serializable;

public class FileUploadResponse implements Serializable {

    private static final long serialVersionUID = 488991477748799482L;

    private long id;
    private String mensagem;

    public FileUploadResponse() {

    }

    public FileUploadResponse(long id) {
        this.id = id;
    }

    public FileUploadResponse(long id, String mensagem) {
        this.id = id;
        this.mensagem = mensagem;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
