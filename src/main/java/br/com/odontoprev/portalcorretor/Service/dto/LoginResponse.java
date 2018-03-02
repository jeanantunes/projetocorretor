package br.com.odontoprev.portalcorretor.Service.dto;

public class LoginResponse {

    long codigoDcss;
    long codigoUsuario;
    String nomeUsuario;
    String documento;
    int codigoCorretora;
    String nomeCorretora;
    String perfil;

    public long getCodigoDcss() {
        return codigoDcss;
    }

    public void setCodigoDcss(long codigoDcss) {
        this.codigoDcss = codigoDcss;
    }

    public long getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
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
