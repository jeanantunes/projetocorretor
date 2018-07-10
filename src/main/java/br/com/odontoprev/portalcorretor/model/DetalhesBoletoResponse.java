package br.com.odontoprev.portalcorretor.model;

public class DetalhesBoletoResponse {

    String codigo;
    String dataInicial;
    String dataFinal;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(String dataInicial) {
        this.dataInicial = dataInicial;
    }

    public String getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }

    @Override
    public String toString() {
        return "DetalhesBoletoResponse{" +
                "codigo='" + codigo + '\'' +
                ", dataInicial='" + dataInicial + '\'' +
                ", dataFinal='" + dataFinal + '\'' +
                '}';
    }
}
