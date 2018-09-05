$(document).ready(function () {
    setColorMenu();
});

function setColorMenu() {
    console.log(window.location.href);
    var url = window.location.href;

    if (url.indexOf("pme") !== -1)
        $("a[href='/venda_pme']").addClass('nav-active')
    if (url.indexOf("pf") !== -1)
        $("a[href='/venda_pf']").addClass('nav-active');
    else if (url.indexOf("/forcavenda/home") !== -1)
        $("a[href='/forcavenda/home']").addClass('nav-active');
    else if (url.indexOf("rede-credenciada") !== -1)
        $("a[href='/rede-credenciada']").addClass('nav-active');
    else if (url.indexOf("lista-propostas") !== -1)
        $("a[href='/lista-propostas']").addClass('nav-active');
    else if (url.indexOf("fale-conosco") !== -1)
        $("a[href='/fale-conosco']").addClass('nav-active');
    else if (url.indexOf("info-planos") !== -1)
        $("a[href='/info-planos']").addClass('nav-active');
    else if (url.indexOf("/alertas") !== -1)
        $("a[href='/lista-propostas']").addClass('nav-active');
    else if (url.indexOf("/corretora/equipe/home") !== -1)
        $("a[href='/corretora/equipe/home']").addClass('nav-active');
    else if (url.indexOf("/corretora/equipe/adicionar") !== -1)
        $("a[href='/corretora/equipe']").addClass('nav-active');
    else if (url.indexOf("/corretora/cadastro/editar") !== -1)
        $("a[href='/corretora/equipe']").addClass('nav-active');
    
    else if (url.indexOf("/corretora/editar/home") !== -1)
        $("a[href='/corretora/editar/home']").addClass('nav-active');
    
    else if (url.indexOf("/corretora/home") !== -1)
        $("a[href='/corretora/home']").addClass('nav-active');
    else if (url.indexOf("/material-divulgacao") !== -1)
        $("a[href='/material-divulgacao']").addClass('nav-active');
}