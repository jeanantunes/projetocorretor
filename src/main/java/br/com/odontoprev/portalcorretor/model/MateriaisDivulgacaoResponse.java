package br.com.odontoprev.portalcorretor.model;

import java.util.List;

public class MateriaisDivulgacaoResponse {

    List<CategoriasMaterialDivulgacao> categoriasMaterialDivulgacao;

    public List<CategoriasMaterialDivulgacao> getCategoriasMaterialDivulgacao() {
        return categoriasMaterialDivulgacao;
    }

    public void setCategoriasMaterialDivulgacao(List<CategoriasMaterialDivulgacao> categoriasMaterialDivulgacao) {
        this.categoriasMaterialDivulgacao = categoriasMaterialDivulgacao;
    }

    @Override
    public String toString() {
        return "{" +
                "categoriasMaterialDivulgacao=" + categoriasMaterialDivulgacao +
                '}';
    }
}
