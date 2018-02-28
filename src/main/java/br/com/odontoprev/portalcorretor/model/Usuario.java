package br.com.odontoprev.portalcorretor.model;

public class Usuario {
    private final String codido;

    private final String login;

    private final String nome;

    private final String corretora;

    private final String perfil;

    public Usuario(String codido, String login, String nome, String corretora, String perfil) {
        this.codido = codido;
        this.login = login;
        this.nome = nome;
        this.corretora = corretora;
        this.perfil = perfil;
    }

    public String getCodido() {
        return codido;
    }

    public String getPerfil() {
        return perfil;
    }

    public String getLogin() {
        return login;
    }

    public String getNome() {
        return nome;
    }

    public String getCorretora() {
        return corretora;
    }
}
