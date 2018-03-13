function ValidaNome (fieldValue) {

    var splittedName = fieldValue.split(/[\ |\']+/) // Separa o nome por espaços e apóstrofos (')

    var totalWords = splittedName.length

    let firstName = splittedName[0]

    let lastName = splittedName[totalWords - 1]

 

    if (!fieldValue) return false

 

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

        if (i === '0' || parseInt(i) === (totalWords - 1)) continue // Ignora o primeiro e o último nome

        if (splittedName[i].length === 1 && !splittedName[i].match(/[E|Y]/i)) {

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

}