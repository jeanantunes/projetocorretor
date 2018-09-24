package br.com.odontoprev.portalcorretor.service.dto;

public class LoginResponse {

    long codigoDcss;
    long codigoUsuario;
    String nomeUsuario;
    String documento;
    int codigoCorretora;
    String nomeCorretora;
    String perfil;
    private String dtAceiteContrato;
    private boolean temBloqueio;

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

    public String getDtAceiteContrato() {
        return dtAceiteContrato;
    }

    public void setDtAceiteContrato(String dtAceiteContrato) {
        this.dtAceiteContrato = dtAceiteContrato;
    }

    public boolean getTemBloqueio() {
        return temBloqueio;
    }

    public void setTemBloqueio(boolean temBloqueio) {
        this.temBloqueio = temBloqueio;
    }
}
