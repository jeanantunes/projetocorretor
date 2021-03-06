package br.com.odontoprev.portalcorretor.service.dto;

import java.io.Serializable;
import java.util.Objects;

//201805171759 - esert - COR-170
public class EmpresaResponse implements Serializable {

    private static final long serialVersionUID = -8687258848218725652L;

    private long id;
    private String mensagem;

    public EmpresaResponse() { //201805171934 - esert - COR-170
        super();
    }

    public EmpresaResponse(long id) {
        this.id = id;
    }

    public EmpresaResponse(long id, String mensagem) {
        this.id = id;
        this.mensagem = mensagem;
    }

    public long getId() {
        return id;
    }

    public String getMensagem() {
        return mensagem;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmpresaResponse that = (EmpresaResponse) o;
        return id == that.id &&
                Objects.equals(mensagem, that.mensagem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mensagem);
    }
}
