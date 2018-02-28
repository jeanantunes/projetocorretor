package br.com.odontoprev.portalcorretor.Service.dto;

public class LoginResponse {
    int codigo;
    String nome;
    int codigoCorretora;
    String nomeCorretora;
    String perfil;
    String documento;

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodigoCorretora() {
        return codigoCorretora;
    }

    public void setCodigoCorretora(int codigoCorretora) {
        this.codigoCorretora = codigoCorretora;
    }

    public String getNomeCorretora() {
        return nomeCorretora;
    }

    public void setNomeCorretora(String nomeCorretora) {
        this.nomeCorretora = nomeCorretora;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
}
