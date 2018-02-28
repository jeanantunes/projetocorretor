package br.com.odontoprev.portalcorretor.model;

public class Usuario {

    private final String login ;

    private final String nome;

    private final String corretora;

    public Usuario(String login, String nome, String corretora) {
        this.login = login;
        this.nome = nome;
        this.corretora = corretora;
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
