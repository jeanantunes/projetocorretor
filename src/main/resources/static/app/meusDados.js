$(document).ready(function () {

    $("#salvarDados").click(function () {

        var nomeCorretor = $("#nomeCorretorEdicao").val();
        var emailCorretor = $("#emailCorretorEdicao").val();
        var telefoneCorretor = $("#telefoneCorretorEdicao").val().replace(/\D/g, '');
        var cdForcaVenda = $("#cdForcaVenda").val();

        if (!ValidaNome(nomeCorretor)) {

            swal("Ops!", "Por favor preencha um nome válido", "error");
            return false;

        }

        if (telefoneCorretor.length < 10) {
            swal("Ops!", "Por favor preencha um celular válido", "error");
            return false;
        }


        if (!validateEmail(emailCorretor)) {
            swal("Ops!", "Por favor preencha um email válido", "error");
            return false;
        }

        if (!navigator.onLine) {
            swal("Ops!", "Você precisa estar online para atualizar seus dados", "error");
            return false;
        }
        return true;

    });


    function ValidaNome(fieldValue) {

        var splittedName = fieldValue.trim().split(/[\ |\']+/) // Separa o nome por espaços e apóstrofos (')
        var splittedNameSpace = fieldValue.split(/[\ ]+/)
        var totalWords = splittedName.length
        let firstName = splittedName[0]
        let lastName = splittedName[totalWords - 1]

        if (!fieldValue) {
            return false
        }

        // Se o nome completo contiver um apóstrofo seguido de qualquer caractere que não seja a-z, é inválido
        if (fieldValue.match(/'[^a-zà-ÿ ]/i)) {
            return false
        }

        // Se o primeiro nome tiver só uma letra e ela não for D, I, O, U ou Y, é inválido
        if (firstName.length === 1 && !firstName.match(/[D|I|O|U|Y]/i)) {
            return false
        }

        // Se o último nome tiver só uma letra e ela não for I, O, U ou Y, é inválido
        if (lastName.length === 1 && !lastName.match(/[I|O|U|Y]/i)) {
            return false
        }

        // Se o nome possuir conectivos que não 'e' ou 'y', é inválido
        for (let i in splittedName) {

            // Se o nome estiver vazio, é invalido
            if (splittedName[i] == "") {
                return false
            }

            // Se o nome possuir caracteres especiais, exceto apostrofo, é invalido
            if (!splittedName[i].match(/^[a-zA-ZÁÉÍÓÚÀÈÌÒÙàèìòùáéíóúâêîôûãõçÇ']+$/g)) {
                return false
            }

            if (i === '0' || parseInt(i) === (totalWords - 1)) {
                continue // Ignora o primeiro e o último nome
            }

            var nameWithApostrophe = splittedName[i] + "'" + splittedName[parseInt(i) + 1]

            if (
                splittedName[i].length === 1
                &&
                !splittedName[i].match(/[E|Y]/i)
                &&
                nameWithApostrophe != splittedNameSpace[i]
            ) {
                return false
            }
        }

        // Se o nome completo contiver um apóstrofo e não houver pelo menos três palavras, é inválido
        if (fieldValue.match(/'/i) && totalWords < 3) {
            return false
        }

        // Se o nome tiver só uma palavra, é inválido
        if (totalWords === 1) {
            return false
        }

        return true

    } //function ValidaNome

    function validateEmail(email) {
        var reg = /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/
        if (reg.test(email)) {
            return true;
        }

        return false;
    }

    // h t t p s : // gist .github .com /reggiegutter/5516382
    function validateCelular(celular) {
        var reg = /^(\(11\) (9\d{4})-\d{4})$/
        if (reg.test(celular)) {
            return true;
        }

        return false;
    }
});