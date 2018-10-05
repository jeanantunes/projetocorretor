var metodoUpload = "";

$(document).ready(function () {

    getPropertie("odontoprev.fileupload.lotedcms.metodo", function (key) {

        metodoUpload = key;

    });

    $("input:file").change(function (){

        var file = $('#file').prop('files')[0];
        var reader = new FileReader();

        reader.onloadend = function() {

            var fileName = $('input[type=file]').val();
            var nameClean = fileName.split('\\').pop();
            var fileSize = $('#file')[0].files[0].size;
            var base64Split = reader.result.split(',');
            var tipoConteudo = base64Split[0].split(";")[0].split(":")[1];

            var jsonString = {
                "arquivoBase64": base64Split[1],
                "nomeArquivo": nameClean,
                "tipoConteudo": tipoConteudo,
                "tamanho": fileSize
            }

            swal({
                title: "Aguarde",
                text: "\n Estamos processando os dados do seu arquivo",
                content: "input",
                showCancelButton: false,
                showConfirmButton: false,
                imageUrl: "../img/icon-aguarde.gif",
                allowEscapeKey: false,
                allowOutsideClick: false,
                icon: "info",
                button: {
                    text: "...",
                    closeModal: false
                }
            });

            $.ajax({
                async: true,
                url: metodoUpload,
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                data: JSON.stringify(jsonString),
                success: function (resp) {
                    console.log(resp);
                    var link = document.createElement('a');
                    link.href = 'data:' + resp.tipoConteudo + ';base64,' + resp.arquivoBase64;
                    link.download = resp.nomeArquivo;
                    swal.close();
                    link.dispatchEvent(new MouseEvent('click'));
                },
                error: function (xhr) {
                    swal("Ops!", "Erro no carregamento do arquivo", "error");
                }
            });

        }

        reader.error = function(){

            swal("Ops!", "Erro no carregamento do arquivo", "error");

        }

        reader.readAsDataURL(file);
    });

});