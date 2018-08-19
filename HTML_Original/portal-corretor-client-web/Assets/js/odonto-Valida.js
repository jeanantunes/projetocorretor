
    
    function limpa_formulário_cep() {
            //Limpa valores do formulário de cep.
            document.getElementById('endereco').value=("");
            document.getElementById('bairro').value=("");
            document.getElementById('cidade').value=("");
            document.getElementById('estado').value=("");
          //  document.getElementById('ibge').value=("");
    }

    function meu_callback(conteudo) {
        if (!("erro" in conteudo)) {
            //Atualiza os campos com os valores.
            document.getElementById('endereco').value=(conteudo.logradouro);
            document.getElementById('bairro').value=(conteudo.bairro);
            document.getElementById('cidade').value=(conteudo.localidade);
            document.getElementById('estado').value=(conteudo.uf);
            //document.getElementById('ibge').value=(conteudo.ibge);
        } //end if.
        else {
            //CEP não Encontrado.
            limpa_formulário_cep();
            alert("CEP não encontrado.");
        }
    }
        
    function pesquisacep(valor) {

console.log(valor);
        //Nova variável "cep" somente com dígitos.
        var cep = valor.replace(/\D/g, '');

        //Verifica se campo cep possui valor informado.
        if (cep != "") {

            //Expressão regular para validar o CEP.
            var validacep = /^[0-9]{8}$/;

            //Valida o formato do CEP.
            if(validacep.test(cep)) {

                //Preenche os campos com "..." enquanto consulta webservice.
                document.getElementById('endereco').value="...";
                document.getElementById('bairro').value="...";
                document.getElementById('cidade').value="...";
                document.getElementById('estado').value="...";
                //document.getElementById('ibge').value="...";

                //Cria um elemento javascript.
                var script = document.createElement('script');

                //Sincroniza com o callback.
                script.src = 'https://viacep.com.br/ws/'+ cep + '/json/?callback=meu_callback';
                console.log(script.toString());
                //Insere script no documento e carrega o conteúdo.
                document.body.appendChild(script);

            } //end if.
            else {
                //cep é inválido.
                limpa_formulário_cep();
                alert("Formato de CEP inválido.");
            }
        } //end if.
        else {
            //cep sem valor, limpa formulário.
            limpa_formulário_cep();
        }
    };

    
//<!-- Função Valida CPF -->

        function TestaCPF(strCPF) {
            var Soma;
            var Resto;
            Soma = 0;
            if (strCPF == "00000000000" || strCPF == "11111111111" || strCPF == "22222222222" || strCPF == "33333333333" || strCPF == "44444444444" || strCPF == "55555555555" || strCPF == "66666666666" || strCPF == "77777777777" || strCPF == "88888888888" || strCPF == "99999999999") return false;
            
            for (i=1; i<=9; i++) Soma = Soma + parseInt(strCPF.substring(i-1, i)) * (11 - i);
            Resto = (Soma * 10) % 11;
        
            if ((Resto == 10) || (Resto == 11))  Resto = 0;
            if (Resto != parseInt(strCPF.substring(9, 10)) ) return false;
        
            Soma = 0;
            for (i = 1; i <= 10; i++) Soma = Soma + parseInt(strCPF.substring(i-1, i)) * (12 - i);
            Resto = (Soma * 10) % 11;
        
            if ((Resto == 10) || (Resto == 11))  Resto = 0;
            if (Resto != parseInt(strCPF.substring(10, 11) ) ) return false;
            return true;
        }

   
//<!--Mascara -->
   
        $(document).ready(function(){
                      $('.data').mask('00/00/0000');
                      $('.tempo').mask('00:00:00');
                      $('.data_tempo').mask('00/00/0000 00:00:00');
                      $('.cep').mask('00000-000');
                      $('.tel').mask('00000-0000');
                      $('.telefone').mask('(00) 0000-0000');
                      $('.celular').mask('(00) 00000-0000'); 
                      $('.celularModalPF').mask('(00) 00000-0000'); 
                      $('.celularCadastroSouDono').mask('(00) 00000-0000'); 
                      $('.celularCadastroNaoSouDono').mask('(00) 00000-0000');
                      $('.confirmeCelularCadastroNaoSouDonoSP').mask('(00) 00000-0000');
                      $('.cpf').mask('000.000.000-00');
                      $('.cpfSubCadastroSouDono').mask('000.000.000-00');
                      $('.cpfModalPF').mask('000.000.000-00');
                      $('.cpfCadastroNaoSouDonoSP').mask('000.000.000-00');
                      $('.cpfCadastroSouDono').mask('000.000.000-00');
                      $('.cpfCadastroNaoSouDono').mask('000.000.000-00');
                      $('.cnpj').mask('00.000.000/0000-00');
                      $('.cnjpCadastroNaoSouDonoSP').mask('00.000.000/0000-00');
                      $('.cnpjCadastroSouDono').mask('00.000.000/0000-00');
                      $('.cnpjCadastroNaoSouDono').mask('00.000.000/0000-00');
                      $('.dataAbertura').mask('00/00/0000'); 
                      $('.dataNascimento').mask('00/00/0000');
                      $('.dataNascimentoModalPF').mask('00/00/0000');
                      $('.validade').mask('00/0000'); 
                      $('.dinheiro').mask('000.000.000.000.000,00' , { reverse : true});
                      $('.dinheiro2').mask("#.##0,00" , { reverse:true});

                      $('.cor_hex').mask('#xxxxxx' , {

                                      translation: {
                                          'x': {
                                            pattern: /[a-fA-F0-9]/
                                          },
                                          '#' : ''
                                        }
                                      });
                  
                    $('.placeholder').mask("00/00/0000", { placeholder: "__/__/____" }) ;


                    $('#unmask').click(function(){
                      var unmask_value = $('.cpf').cleanVal();
                      $('#clearcpf').html(unmask_value);
                    });

             //CPF

             $(".form-control").css("border-top", "transparent");
             $(".form-control").css("border-left", "transparent");
             $(".form-control").css("border-right", "transparent");
             $(".form-control").css("box-shadow", "0 0 0 0");
             $(".form-control").css("border-radius", "0");
             $(".form-control").css("border-bottom", "2px solid #CBC9C6");

 
             $(".cpf").focus(function(){
             if($(this).val() == "")
                 {
                     $(this).css({"border-color" : "#1974CE"});
                     $(".cpf").css("color", "#1974CE");
                     $(".label-cpf").css("color", "#1974CE");
                 }
             });
          
             $(".cpf").blur(function(){

        var stringteste = $(this).val().replace(".","");
        stringteste = stringteste.replace("-","");
        stringteste = stringteste.replace(".","");

        
             if($(this).val() == "" || TestaCPF(stringteste) == false)
                 {
                     $(this).css({"border-color" : "#FF4141"});
                     $(".label-cpf").css("color", "#FF4141");
                     $(".cpf").css("color", "#FF4141");
                 }
             });

                $(".cpf").keyup(function(){
             if($(this).val() != "")
                 {
                     $(this).css({"border-color" : "#1974CE"});
                     $(".cpf").css("color", "#1974CE");
                     $(".label-cpf").css("color", "#1974CE");
                     $("input:-webkit-autofill").css("color", "#1974CE");
                 }
             });


              

               // sexo

             $(".sexo").focus(function(){
             if($(this).val() == "")
                 {
                     $(this).css({"border-color" : "#1974CE"});
                     $(".sexo").css("color", "#1974CE");
                     $(".label-sexo").css("color", "#1974CE");
                 }
             });

            $(".sexo").blur(function(){
            if($(this).val() == "")
                 {
                     $(this).css({"border-color" : "#FF4141"});
                     $(".label-sexo").css("color", "#FF4141");
                 }
             });

            $(".sexo").keyup(function(){
             if($(this).val() != "")
                 {
                     $(this).css({"border-color" : "#1974CE"});
                     $(".sexo").css("color", "#1974CE");
                     $(".label-sexo").css("color", "#1974CE");
                 }
             });


                           // Textarea mensagem - fale conosco

             $(".mensagem").focus(function(){
             if($(this).val() == "")
                 {
                     $(this).css({"border-color" : "#1974CE"});
                     $(".mensagem").css("color", "#1974CE");
                     $(".label-mensagem").css("color", "#1974CE");
                 }
             });

            $(".mensagem").blur(function(){
            if($(this).val() == "")
                 {
                     $(this).css({"border-color" : "#FF4141"});
                     $(".label-mensagem").css("color", "#FF4141");
                 }
             });

            $(".mensagem").keyup(function(){
             if($(this).val() != "")
                 {
                     $(this).css({"border-color" : "#1974CE"});
                     $(".mensagem").css("color", "#1974CE");
                     $(".label-mensagem").css("color", "#1974CE");
                 }
             });








               // Data nascimento

             $(".nascimento").focus(function(){
             if($(this).val() == "")
                 {
                     $(this).css({"border-color" : "#1974CE"});
                     $(".nascimento").css("color", "#1974CE");
                     $(".label-nascimento").css("color", "#1974CE");
                 }
             });

            $(".nascimento").blur(function(){
            if($(this).val() == "")
                 {
                     $(this).css({"border-color" : "#FF4141"});
                     $(".label-nascimento").css("color", "#FF4141");
                 }
             });

            $(".nascimento").keyup(function(){
             if($(this).val() != "")
                 {
                     $(this).css({"border-color" : "#1974CE"});
                     $(".nascimento").css("color", "#1974CE");
                     $(".label-nascimento").css("color", "#1974CE");
                 }
             });


            // Dependentes

             $(".dependente").focus(function(){
             if($(this).val() == "")
                 {
                     $(this).css({"border-color" : "#1974CE"});
                     $(".dependente").css("color", "#1974CE");
                     $(".label-dependente").css("color", "#1974CE");
                 }
             });

            $(".dependente").blur(function(){
            if($(this).val() == "")
                 {
                     $(this).css({"border-color" : "#FF4141"});
                     $(".label-dependente").css("color", "#FF4141");
                 }
             });

            $(".dependente").keyup(function(){
             if($(this).val() != "")
                 {
                     $(this).css({"border-color" : "#1974CE"});
                     $(".dependente").css("color", "#1974CE");
                     $(".label-dependente").css("color", "#1974CE");
                 }
             });



               // Nome da mae

             $(".nome-mae").focus(function(){
             if($(this).val() == "")
                 {
                     $(this).css({"border-color" : "#1974CE"});
                     $(".nome-mae").css("color", "#1974CE");
                     $(".label-nome-mae").css("color", "#1974CE");
                 }
             });

            $(".nome-mae").blur(function(){
            if($(this).val() == "")
                 {
                     $(this).css({"border-color" : "#FF4141"});
                     $(".label-nome-mae").css("color", "#FF4141");
                 }
             });

            $(".nome-mae").keyup(function(){
             if($(this).val() != "")
                 {
                     $(this).css({"border-color" : "#1974CE"});
                     $(".nome-mae").css("color", "#1974CE");
                     $(".label-nome-mae").css("color", "#1974CE");
                 }
             });


     

             // CNPJ

             $(".cnpj").focus(function(){
             if($(this).val() == "")
                 {
                     $(this).css({"border-color" : "#1974CE"});
                     $(".cnpj").css("color", "#1974CE");
                     $(".label-cnpj").css("color", "#1974CE");
                 }
             });

            $(".cnpj").blur(function(){
                var stringteste = $(this).val().replace(".","");
                stringteste = stringteste.replace("-","");
                stringteste = stringteste.replace(".","");
            if($(this).val() == "" || isCNPJValid(stringteste) == false )
                 {
                     $(this).css({"border-color" : "#FF4141"});
                     $(".cnpj").css("color", "#FF4141");
                     $(".label-cnpj").css("color", "#FF4141");
                 }
             });

            $(".cnpj").keyup(function(){
             if($(this).val() != "")
                 {
                     $(this).css({"border-color" : "#1974CE"});
                     $(".cnpj").css("color", "#1974CE");
                     $(".label-cnpj").css("color", "#1974CE");
                 }
             });

             // NOME

             $(".nome").focus(function(){
             if($(this).val() == "")
                 {
                     $(this).css({"border-color" : "#1974CE"});
                     $(".nome").css("color", "#1974CE");
                     $(".label-nome").css("color", "#1974CE");
                 }
             });

             $(".nome").blur(function(){
             if($(this).val() == "")
                 {
                     $(this).css({"border-color" : "#FF4141"});
                     $(".label-nome").css("color", "#FF4141");
                 }
             });

             $(".nome").keyup(function(){
             if($(this).val() != "")
                 {
                     $(this).css({"border-color" : "#1974CE"});
                     $(".nome").css("color", "#1974CE");
                     $(".label-nome").css("color", "#1974CE");
                 }
              });

             // telefone

             $(".telefone").focus(function(){
             if($(this).val() == "")
                 {
                     $(this).css({"border-color" : "#1974CE"});
                     $(".telefone").css("color", "#1974CE");
                     $(".label-telefone").css("color", "#1974CE");
                 }
              });


            $(".telefone").blur(function(){
             if($(this).val() == "")
                 {
                     $(this).css({"border-color" : "#FF4141"});
                     $(".label-telefone").css("color", "#FF4141");
                 }
              });

            $(".telefone").keyup(function(){
             if($(this).val() != "")
                 {
                     $(this).css({"border-color" : "#1974CE"});
                     $(".telefone").css("color", "#1974CE");
                     $(".label-telefone").css("color", "#1974CE");
                 }
              });

             // Celular
            
             $(".celular").focus(function(){
             if($(this).val() == "")
                 {
                     $(this).css({"border-color" : "#1974CE"});
                     $(".celular").css("color", "#1974CE");
                     $(".label-celular").css("color", "#1974CE");
                 }
             });


             $(".celular").blur(function(){
             if($(this).val() == "")
                 {
                     $(this).css({"border-color" : "#FF4141"});
                     $(".label-celular").css("color", "#FF4141");
                 }
             });


             $(".celular").keyup(function(){
             if($(this).val() != "")
                 {
                     $(this).css({"border-color" : "#1974CE"});
                     $(".celular").css("color", "#1974CE");
                     $(".label-celular").css("color", "#1974CE");
                 }
            });

            // CEP

             $(".cep").focus(function(){
             if($(this).val() == "")
                 {
                     $(this).css({"border-color" : "#1974CE"});
                     $(".cep").css("color", "#1974CE");
                     $(".label-cep").css("color", "#1974CE");
                 }
             });
         
            $(".cep").blur(function(){
             if($(this).val() == "")
                 {
                     $(this).css({"border-color" : "#FF4141"});
                     $(".label-cep").css("color", "#FF4141");
                 }
            });

            $(".cep").keyup(function(){
             if($(this).val() != "")
                 {
                     $(this).css({"border-color" : "#1974CE"});
                     $(".cep").css("color", "#1974CE");
                     $(".label-cep").css("color", "#1974CE");
                 }
             });

            // RAZAO SOCIAL

             $(".razao-social").focus(function(){
             if($(this).val() == "")
                 {
                   $(this).css({"border-color" : "#1974CE"});
                   $(".razao-social").css("color", "#1974CE");
                   $(".label-razao-social").css("color", "#1974CE");
                 }
             });

             $(".razao-social").blur(function(){
             if($(this).val() == "")
                 {
                     $(this).css({"border-color" : "#FF4141"});
                     $(".label-razao-social").css("color", "#FF4141");
                 }
             });

             $(".razao-social").keyup(function(){
             if($(this).val() != "")
                 {
                     $(this).css({"border-color" : "#1974CE"});
                     $(".razao-social").css("color", "#1974CE");
                     $(".label-razao-social").css("color", "#1974CE");
                 }
             });

            // endereço
            $(".endereco").focus(function(){
            if($(this).val() == "")
                 {
                     $(this).css({"border-color" : "#1974CE"});
                     $(".endereco").css("color", "#1974CE");
                     $(".label-endereco").css("color", "#1974CE");
                 }
             });

            $(".endereco").blur(function(){
             if($(this).val() == "")
                 {
                     $(this).css({"border-color" : "#FF4141"});
                     $(".label-endereco").css("color", "#FF4141");
                 }
            });

            $(".endereco").keyup(function(){
             if($(this).val() != "")
                 {
                     $(this).css({"border-color" : "#1974CE"});
                     $(".endereco").css("color", "#1974CE");
                     $(".label-endereco").css("color", "#1974CE");
                 }
            });

            // numero
             $(".numero").focus(function(){
             if($(this).val() == "")
                 {
                     $(this).css({"border-color" : "#1974CE"});
                     $(".numero").css("color", "#1974CE" );
                     $(".label-numero").css("color", "#1974CE");
                     $(".label-numero").css("font-weight" , "bold");
                 }
             });

             $(".numero").blur(function(){
                 if($(this).val() == "")
                     {
                         $(this).css({"border-color" : "#FF4141"});
                         $(".label-numero").css("color", "#FF4141");
                         
                     }
             });

             $(".numero").keyup(function(){
             if($(this).val() != "")
                 {
                     $(this).css({"border-color" : "#1974CE"});
                     $(".numero").css("color", "#1974CE");
                     $(".label-numero").css("color", "#1974CE");
                 }
             });

             // EMAIL

             $(".email").focus(function(){

             if($(this).val() == "")
                 {
                     $(this).css({"border-color" : "#1974CE"});
                     $(".email").css("color", "#1974CE");
                     $(".label-email").css("color", "#1974CE");
                 }
             });

             $('.email').blur(function(){
            //atribuindo o valor do campo
            var sEmail  = $(".email").val();
            // filtros
            var emailFilter=/^.+@.+\..{2,}$/;
            var illegalChars= /[\(\)\<\>\,\;\:\\\/\"\[\]]/
            // condição

            if(!(emailFilter.test(sEmail))||sEmail.match(illegalChars)){
              
              $(".label-email").css("color", "#FF4141");
              $(".email").css("color", "#FF4141");
              $(".email").css("border-color", "#FF4141");
            }

            else{

              $(".label-email").css("color", "#1974CE");
              $(".email").css("color", "#1974CE");
              $(".email").css("border-color", "#1974CE");

            }

          });

             $(".email").keyup(function(){
             if($(this).val() != "")
                 {
                     $(this).css({"border-color" : "#1974CE"});
                     $(".email").css("color", "#1974CE");
                     $(".label-email").css("color", "#1974CE");
                 }
            });



          $('.email').blur(function(){

            //atribuindo o valor do campo
            var sEmail  = $(".email").val();
            // filtros
            var emailFilter=/^.+@.+\..{2,}$/;
            var illegalChars= /[\(\)\<\>\,\;\:\\\/\"\[\]]/
            // condição

            if(!(emailFilter.test(sEmail))||sEmail.match(illegalChars)){
              
              $(".label-email").css("color", "#FF4141");
              $(".email").css("color", "#FF4141");
              $(".label-email").css("color", "#FF4141");
            }


            else{

              $(".label-email").css("color", "#1974CE");
              $(".email").css("color", "#1974CE");
              $(".label-email").css("color", "#1974CE");

             }

             });
         

             // COMPLEMENTO
             $(".complemento").focus(function(){
             if($(this).val() == "")
                 {
                     $(this).css({"border-color" : "#1974CE"});
                     $(".complemento").css("color", "#1974CE");
                     $(".label-complemento").css("color", "#1974CE");
                 }
             });

             $(".complemento").blur(function(){
                 if($(this).val() == "")
                     {
                         $(this).css({"border-color" : "#FF4141"});
                         $(".label-complemento").css("color", "#FF4141");
                     }
                });

             $(".complemento").keyup(function(){
             if($(this).val() != "")
                 {
                     $(this).css({"border-color" : "#1974CE"});
                     $(".complemento").css("color", "#1974CE");
                     $(".label-complemento").css("color", "#1974CE");
                 }
             });

             // BAIRRO
             $(".bairro").focus(function(){
             if($(this).val() == "")
                 {
                     $(this).css({"border-color" : "#1974CE"});
                     $(".bairro").css("color", "#1974CE");
                     $(".label-bairro").css("color", "#1974CE");
                 }
             });

             $(".bairro").blur(function(){
                 if($(this).val() == "")
                     {
                         $(this).css({"border-color" : "#FF4141"});
                         $(".label-bairro").css("color", "#FF4141");
                     }
                });

             $(".bairro").keyup(function(){
             if($(this).val() != "")
                 {
                     $(this).css({"border-color" : "#1974CE"});
                     $(".bairro").css("color", "#1974CE");
                     $(".label-bairro").css("color", "#1974CE");
                 }
             });


               // sennha
             $(".password").focus(function(){
             if($(this).val() == "")
                 {
                     $(this).css({"border-color" : "#1974CE"});
                     $(".password").css("color", "#1974CE");
                     $(".label-password").css("color", "#1974CE");
                 }
             });

             $(".password").blur(function(){
                 if($(this).val() == "")
                     {
                         $(this).css({"border-color" : "#FF4141"});
                         $(".label-password").css("color", "#FF4141");
                     }
                });

             $(".password").keyup(function(){
             if($(this).val() != "")
                 {
                     $(this).css({"border-color" : "#1974CE"});
                     $(".password").css("color", "#1974CE");
                     $(".label-password").css("color", "#1974CE");
                 }
             });


             // CIDADE

             $(".cidade").focus(function(){
             if($(this).val() == "")
                 {
                     $(this).css({"border-color" : "#1974CE"});
                     $(".cidade").css("color", "#1974CE");
                     $(".label-cidade").css("color", "#1974CE");
                 }
              });
         
              $(".cidade").blur(function(){
                 if($(this).val() == "")
                     {
                         $(this).css({"border-color" : "#FF4141"});
                         $(".label-cidade").css("color", "#FF4141");
                     }
               });

               $(".cidade").keyup(function(){
               if($(this).val() != "")
                   {
                       $(this).css({"border-color" : "#1974CE"});
                       $(".cidade").css("color", "#1974CE");
                       $(".label-cidade").css("color", "#1974CE");
                   }
               });

                // nomeMae

             $(".nomeMae").focus(function(){
                if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".nomeMae").css("color", "#1974CE");
                        $(".label-nomeMae").css("color", "#1974CE");
                    }
                 });
            
                 $(".nomeMae").blur(function(){
                    if($(this).val() == "")
                        {
                            $(this).css({"border-color" : "#FF4141"});
                            $(".label-nomeMae").css("color", "#FF4141");
                        }
                  });
   
                  $(".nomeMae").keyup(function(){
                  if($(this).val() != "")
                      {
                          $(this).css({"border-color" : "#1974CE"});
                          $(".nomeMae").css("color", "#1974CE");
                          $(".label-nomeMae").css("color", "#1974CE");
                      }
                  });

                   // dataNascimento

             $(".dataNascimento").focus(function(){
                if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".dataNascimento").css("color", "#1974CE");
                        $(".label-dataNascimento").css("color", "#1974CE");
                    }
                 });
            
                 $(".dataNascimento").blur(function(){
                    if($(this).val() == "")
                        {
                            $(this).css({"border-color" : "#FF4141"});
                            $(".label-dataNascimento").css("color", "#FF4141");
                        }
                  });
   
                  $(".dataNascimento").keyup(function(){
                  if($(this).val() != "")
                      {
                          $(this).css({"border-color" : "#1974CE"});
                          $(".dataNascimento").css("color", "#1974CE");
                          $(".label-dataNascimento").css("color", "#1974CE");
                      }
                  });

                  // estado

             $(".estado").focus(function(){
                if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".estado").css("color", "#1974CE");
                        $(".label-estado").css("color", "#1974CE");
                    }
                 });
            
                 $(".estado").blur(function(){
                    if($(this).val() == "")
                        {
                            $(this).css({"border-color" : "#FF4141"});
                            $(".label-estado").css("color", "#FF4141");
                        }
                  });
   
                  $(".estado").keyup(function(){
                  if($(this).val() != "")
                      {
                          $(this).css({"border-color" : "#1974CE"});
                          $(".estado").css("color", "#1974CE");
                          $(".label-estado").css("color", "#1974CE");
                      }
                  });
                  // departamento

             $(".departamento").focus(function(){
                if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".departamento").css("color", "#1974CE");
                        $(".label-departamento").css("color", "#1974CE");
                    }
                 });
            
                 $(".departamento").blur(function(){
                    if($(this).val() == "")
                        {
                            $(this).css({"border-color" : "#FF4141"});
                            $(".label-departamento").css("color", "#FF4141");
                        }
                  });
   
                  $(".departamento").keyup(function(){
                  if($(this).val() != "")
                      {
                          $(this).css({"border-color" : "#1974CE"});
                          $(".departamento").css("color", "#1974CE");
                          $(".label-departamento").css("color", "#1974CE");
                      }
                  });

                   // cargo

             $(".cargo").focus(function(){
                if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".cargo").css("color", "#1974CE");
                        $(".label-cargo").css("color", "#1974CE");
                    }
                 });
            
                 $(".cargo").blur(function(){
                    if($(this).val() == "")
                        {
                            $(this).css({"border-color" : "#FF4141"});
                            $(".label-cargo").css("color", "#FF4141");
                        }
                  });
   
                  $(".cargo").keyup(function(){
                  if($(this).val() != "")
                      {
                          $(this).css({"border-color" : "#1974CE"});
                          $(".cargo").css("color", "#1974CE");
                          $(".label-cargo").css("color", "#1974CE");
                      }
                  });

                    // cnpjCadastroSouDono

             $(".cnpjCadastroSouDono").focus(function(){
                if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".cnpjCadastroSouDono").css("color", "#1974CE");
                        $(".label-cnpjCadastroSouDono").css("color", "#1974CE");
                    }
                });
   
               $(".cnpjCadastroSouDono").blur(function(){

                var stringteste = $(this).val().replace(".","");
                stringteste = stringteste.replace("-","");
                stringteste = stringteste.replace(".","");
                
               if($(this).val() == "" || isCNPJValid(stringteste) == false )
                    {
                        $(this).css({"border-color" : "#FF4141"});
                        $(".cnpjCadastroSouDono").css("color", "#FF4141");
                        $(".label-cnpjCadastroSouDono").css("color", "#FF4141");
                    }
                });
   
               $(".cnpjCadastroSouDono").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".cnpjCadastroSouDono").css("color", "#1974CE");
                        $(".label-cnpjCadastroSouDono").css("color", "#1974CE");
                    }
                });

                  // cnpjCadastroNaoSouDono

             $(".cnpjCadastroNaoSouDono").focus(function(){
                if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".cnpjCadastroNaoSouDono").css("color", "#1974CE");
                        $(".label-cnpjCadastroNaoSouDono").css("color", "#1974CE");
                    }
                });
   
               $(".cnpjCadastroNaoSouDono").blur(function(){
               if($(this).val() == "" )
                    {
                        $(this).css({"border-color" : "#FF4141"});
                        $(".label-cnpjCadastroNaoSouDono").css("color", "#FF4141");
                    }
                });
   
               $(".cnpjCadastroNaoSouDono").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".cnpjCadastroNaoSouDono").css("color", "#1974CE");
                        $(".label-cnpjCadastroNaoSouDono").css("color", "#1974CE");
                    }
                });

                // cnae

             $(".cnae").focus(function(){
                if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".cnae").css("color", "#1974CE");
                        $(".label-cnae").css("color", "##1974CE");
                    }
                });
   
               $(".cnae").blur(function(){
               if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#FF4141"});
                        $(".label-cnae").css("color", "#FF4141");
                    }
                });
   
               $(".cnae").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".cnae").css("color", "#1974CE");
                        $(".label-cnae").css("color", "#1974CE");
                    }
                });

                  // simplesNacional

             $(".simplesNacional").focus(function(){
                if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".simplesNacional").css("color", "#1974CE");
                        $(".label-simplesNacional").css("color", "#1974CE");
                    }
                });
   
               $(".simplesNacional").blur(function(){
               if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#FF4141"});
                        $(".label-simplesNacional").css("color", "#FF4141");
                    }
                });
   
               $(".simplesNacional").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".simplesNacional").css("color", "#1974CE");
                        $(".label-simplesNacional").css("color", "#1974CE");
                    }
                });

                // dataAbertura

             $(".dataAbertura").focus(function(){
                if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".dataAbertura").css("color", "#1974CE");
                        $(".label-dataAbertura").css("color", "#1974CE");
                    }
                });
   
               $(".dataAbertura").blur(function(){
               if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#FF4141"});
                        $(".label-dataAbertura").css("color", "#FF4141");
                    }
                });
   
               $(".dataAbertura").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".dataAbertura").css("color", "#1974CE");
                        $(".label-dataAbertura").css("color", "#1974CE");
                    }
                });

                // statusCnpjCadastroSouDono  nomeRepresentanteCadastroSouDono

             $(".statusCnpjCadastroSouDono").focus(function(){
                if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".statusCnpjCadastroSouDono").css("color", "#1974CE");
                        $(".label-statusCnpjCadastroSouDono").css("color", "#1974CE");
                    }
                });
   
               $(".statusCnpjCadastroSouDono").blur(function(){
               if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#FF4141"});
                        $(".label-statusCnpjCadastroSouDono").css("color", "#FF4141");
                    }
                });
   
               $(".statusCnpjCadastroSouDono").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".statusCnpjCadastroSouDono").css("color", "#1974CE");
                        $(".label-statusCnpjCadastroSouDono").css("color", "#1974CE");
                    }
                });

                //   nomeRepresentanteCadastroSouDono

             $(".nomeRepresentanteCadastroSouDono").focus(function(){
                if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".nomeRepresentanteCadastroSouDono").css("color", "#1974CE");
                        $(".label-nomeRepresentanteCadastroSouDono").css("color", "#1974CE");
                    }
                });
   
               $(".nomeRepresentanteCadastroSouDono").blur(function(){
               if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#FF4141"});
                        $(".label-nomeRepresentanteCadastroSouDono").css("color", "#FF4141");
                    }
                });
   
               $(".nomeRepresentanteCadastroSouDono").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".nomeRepresentanteCadastroSouDono").css("color", "#1974CE");
                        $(".label-nomeRepresentanteCadastroSouDono").css("color", "#1974CE");
                    }
                });

                 //   cpfCadastroSouDono

             $(".cpfCadastroSouDono").focus(function(){
                if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".cpfCadastroSouDono").css("color", "#1974CE");
                        $(".label-cpfCadastroSouDono").css("color", "#1974CE");
                    }
                });
            

   
               $(".cpfCadastroSouDono").blur(function(){
                var stringteste = $(this).val().replace(".","");
                stringteste = stringteste.replace("-","");
                stringteste = stringteste.replace(".","");
               if($(this).val() == "" || TestaCPF(stringteste) == false)
                    {
                        $(this).css({"border-color" : "#FF4141"});
                        $(".cpfCadastroSouDono").css("color", "#FF4141");
                        $(".label-cpfCadastroSouDono").css("color", "#FF4141");
                    }
                });
   
               $(".cpfCadastroSouDono").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".cpfCadastroSouDono").css("color", "#1974CE");
                        $(".label-cpfCadastroSouDono").css("color", "#1974CE");
                    }
                });

                 //   cpfCadastroNaoSouDono

             $(".cpfCadastroNaoSouDono").focus(function(){
                if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".cpfCadastroNaoSouDono").css("color", "#1974CE");
                        $(".label-cpfCadastroNaoSouDono").css("color", "#1974CE");
                    }
                });
   
                $(".cpfCadastroNaoSouDono").blur(function(){
                    var stringteste = $(this).val().replace(".","");
                    stringteste = stringteste.replace("-","");
                    stringteste = stringteste.replace(".","");
                   if($(this).val() == "" || TestaCPF(stringteste) == false)
                        {
                            $(this).css({"border-color" : "#FF4141"});
                            $(".cpfCadastroNaoSouDono").css("color", "#FF4141");
                            $(".label-cpfCadastroNaoSouDono").css("color", "#FF4141");
                        }
                    });
   
               $(".cpfCadastroNaoSouDono").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".cpfCadastroNaoSouDono").css("color", "#1974CE");
                        $(".label-cpfCadastroNaoSouDono").css("color", "#1974CE");
                    }
                });

                  //   nomeSubCadastroSouDono

             $(".nomeSubCadastroSouDono").focus(function(){
                if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".nomeSubCadastroSouDono").css("color", "#1974CE");
                        $(".label-nomeSubCadastroSouDono").css("color", "#1974CE");
                    }
                });
   
               $(".nomeSubCadastroSouDono").blur(function(){
               if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#FF4141"});
                        $(".label-nomeSubCadastroSouDono").css("color", "#FF4141");
                    }
                });
   
               $(".nomeSubCadastroSouDono").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".nomeSubCadastroSouDono").css("color", "#1974CE");
                        $(".label-nomeSubCadastroSouDono").css("color", "#1974CE");
                    }
                });

                //   cpfSubCadastroSouDono

             $(".cpfSubCadastroSouDono").focus(function(){
                if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".cpfSubCadastroSouDono").css("color", "#1974CE");
                        $(".label-cpfSubCadastroSouDono").css("color", "#1974CE");
                    }
                });
   
               $(".cpfSubCadastroSouDono").blur(function(){
               if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#FF4141"});
                        $(".label-cpfSubCadastroSouDono").css("color", "#FF4141");
                    }
                });
   
               $(".cpfSubCadastroSouDono").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".cpfSubCadastroSouDono").css("color", "#1974CE");
                        $(".label-cpfSubCadastroSouDono").css("color", "#1974CE");
                    }
                });

                 //   celularCadastroNaoSouDono

             $(".celularCadastroNaoSouDono").focus(function(){
                if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".celularCadastroNaoSouDono").css("color", "#1974CE");
                        $(".label-celularCadastroNaoSouDono").css("color", "#1974CE");
                    }
                });
   
               $(".celularCadastroNaoSouDono").blur(function(){
               if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#FF4141"});
                        $(".label-celularCadastroNaoSouDono").css("color", "#FF4141");
                    }
                });
   
               $(".celularCadastroNaoSouDono").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".celularCadastroNaoSouDono").css("color", "#1974CE");
                        $(".label-celularCadastroNaoSouDono").css("color", "#1974CE");
                    }
                });

                  //   celularCadastroSouDono

             $(".celularCadastroSouDono").focus(function(){
                if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".celularCadastroSouDono").css("color", "#1974CE");
                        $(".label-celularCadastroSouDono").css("color", "#1974CE");
                    }
                });
   
               $(".celularCadastroSouDono").blur(function(){
               if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#FF4141"});
                        $(".celularCadastroSouDono").css("color", "#FF4141");
                        $(".label-celularCadastroSouDono").css("color", "#FF4141");
                    }
                });
   
               $(".celularCadastroSouDono").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".celularCadastroSouDono").css("color", "#1974CE");
                        $(".label-celularCadastroSouDono").css("color", "#1974CE");
                    }
                });

                
                
                 //   banco

             $(".banco").focus(function(){
                if($(this).val() != " ")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".banco").css("color", "#1974CE");
                        $(".label-banco").css("color", "#1974CE");
                    }
                });
   
               $(".banco").blur(function(){
               if($(this).val() == " ")
                    {
                        $(this).css({"border-color" : "#FF4141"});
                        $(".banco").css("color", "#FF4141");
                        $(".label-banco").css("color", "#FF4141");
                    }
                });
   
               $(".banco").keyup(function(){
                if($(this).val() != " ")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".banco").css("color", "#1974CE");
                        $(".label-banco").css("color", "#1974CE");
                    }
                });

                
                 //   conta

             $(".conta").focus(function(){
                if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".conta").css("color", "#1974CE");
                        $(".label-conta").css("color", "#1974CE");
                    }
                });
   
               $(".conta").blur(function(){
               if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#FF4141"});
                        $(".label-conta").css("color", "#FF4141");
                    }
                });
   
               $(".conta").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".conta").css("color", "#1974CE");
                        $(".label-conta").css("color", "#1974CE");
                    }
                });

                 //   agencia

             $(".agencia").focus(function(){
                if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".agencia").css("color", "#1974CE");
                        $(".label-agencia").css("color", "#1974CE");
                    }
                });
   
               $(".agencia").blur(function(){
               if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#FF4141"});
                        $(".label-agencia").css("color", "#FF4141");
                    }
                });
   
               $(".agencia").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".agencia").css("color", "#1974CE");
                        $(".label-agencia").css("color", "#1974CE");
                    }
                });


                 //   nomeCadastroNaoSouDono

             $(".nomeCadastroNaoSouDono").focus(function(){
                if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".nomeCadastroNaoSouDono").css("color", "#1974CE");
                        $(".label-nomeCadastroNaoSouDono").css("color", "#1974CE");
                    }
                });
   
               $(".nomeCadastroNaoSouDono").blur(function(){
               if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#FF4141"});
                        $(".label-nomeCadastroNaoSouDono").css("color", "#FF4141");
                    }
                });
   
               $(".nomeCadastroNaoSouDono").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".nomeCadastroNaoSouDono").css("color", "#1974CE");
                        $(".label-nomeCadastroNaoSouDono").css("color", "#1974CE");
                    }
                });
                // nomeCadastroSouDono

                $(".nomeCadastroSouDono").focus(function(){
                    if($(this).val() == "")
                        {
                            $(this).css({"border-color" : "#1974CE"});
                            $(".nomeCadastroSouDono").css("color", "#1974CE");
                            $(".label-nomeCadastroSouDono").css("color", "#1974CE");
                        }
                    });
       
                   $(".nomeCadastroSouDono").blur(function(){
                   if($(this).val() == "")
                        {
                            $(this).css({"border-color" : "#FF4141"});
                            $(".label-nomeCadastroSouDono").css("color", "#FF4141");
                        }
                    });
       
                   $(".nomeCadastroSouDono").keyup(function(){
                    if($(this).val() != "")
                        {
                            $(this).css({"border-color" : "#1974CE"});
                            $(".nomeCadastroSouDono").css("color", "#1974CE");
                            $(".label-nomeCadastroSouDono").css("color", "#1974CE");
                        } });
                
                 //   sennhaCadastroSouDono  

             $(".sennhaCadastroSouDono").focus(function(){
                if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".sennhaCadastroSouDono").css("color", "#1974CE");
                        $(".label-sennhaCadastroSouDono").css("color", "#1974CE");
                    }
                });
   
               $(".sennhaCadastroSouDono").blur(function(){
               if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#FF4141"});
                        $(".label-sennhaCadastroSouDono").css("color", "#FF4141");
                    }
                });
   
               $(".sennhaCadastroSouDono").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".sennhaCadastroSouDono").css("color", "#1974CE");
                        $(".label-sennhaCadastroSouDono").css("color", "#1974CE");
                    }
                });
                
                 //  senhaCadastroNaoSouDono

             $(".senhaCadastroNaoSouDono").focus(function(){
                if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".senhaCadastroNaoSouDono").css("color", "#1974CE");
                        $(".label-senhaCadastroNaoSouDono").css("color", "#1974CE");
                    }
                });
   
               $(".senhaCadastroNaoSouDono").blur(function(){
               if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#FF4141"});
                        $(".label-senhaCadastroNaoSouDono").css("color", "#FF4141");
                    }
                });
   
               $(".senhaCadastroNaoSouDono").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".senhaCadastroNaoSouDono").css("color", "#1974CE");
                        $(".label-senhaCadastroNaoSouDono").css("color", "#1974CE");
                    }
                });

                  //  cpfCadastroNaoSouDonoSP

             $(".cpfCadastroNaoSouDonoSP").focus(function(){
                if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".cpfCadastroNaoSouDonoSP").css("color", "#1974CE");
                        $(".label-cpfCadastroNaoSouDonoSP").css("color", "#1974CE");
                    }
                });
   
               $(".cpfCadastroNaoSouDonoSP").blur(function(){
               if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#FF4141"});
                        $(".label-cpfCadastroNaoSouDonoSP").css("color", "#FF4141");
                    }
                });
   
               $(".cpfCadastroNaoSouDonoSP").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".cpfCadastroNaoSouDonoSP").css("color", "#1974CE");
                        $(".label-cpfCadastroNaoSouDonoSP").css("color", "#1974CE");
                    }
                });

                        //  cpfCadastroNaoSouDonoSP  

             $(".cpfCadastroNaoSouDonoSP").focus(function(){
                if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".cpfCadastroNaoSouDonoSP").css("color", "#1974CE");
                        $(".label-cpfCadastroNaoSouDonoSP").css("color", "#1974CE");
                    }
                });
   
               $(".cpfCadastroNaoSouDonoSP").blur(function(){
               if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#FF4141"});
                        $(".label-cpfCadastroNaoSouDonoSP").css("color", "#FF4141");
                    }
                });
   
               $(".cpfCadastroNaoSouDonoSP").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".cpfCadastroNaoSouDonoSP").css("color", "#1974CE");
                        $(".label-cpfCadastroNaoSouDonoSP").css("color", "#1974CE");
                    }
                });

                  //    ConfirmeNomeCadastroNaoSouDonoSP

             $(".ConfirmeNomeCadastroNaoSouDonoSP").focus(function(){
                if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".ConfirmeNomeCadastroNaoSouDonoSP").css("color", "#1974CE");
                        $(".label-ConfirmeNomeCadastroNaoSouDonoSP").css("color", "#1974CE");
                    }
                });
   
               $(".ConfirmeNomeCadastroNaoSouDonoSP").blur(function(){
               if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#FF4141"});
                        $(".label-ConfirmeNomeCadastroNaoSouDonoSP").css("color", "#FF4141");
                    }
                });
   
               $(".ConfirmeNomeCadastroNaoSouDonoSP").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".ConfirmeNomeCadastroNaoSouDonoSP").css("color", "#1974CE");
                        $(".label-ConfirmeNomeCadastroNaoSouDonoSP").css("color", "#1974CE");
                    }
                });

                   //      confirmeCelularCadastroNaoSouDonoSP

             $(".confirmeNomeCadastroNaoSouDonoSP").focus(function(){
                if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".confirmeNomeCadastroNaoSouDonoSP").css("color", "#1974CE");
                        $(".label-confirmeNomeCadastroNaoSouDonoSP").css("color", "#1974CE");
                    }
                });
   
               $(".confirmeNomeCadastroNaoSouDonoSP").blur(function(){
               if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#FF4141"});
                        $(".label-confirmeNomeCadastroNaoSouDonoSP").css("color", "#FF4141");
                    }
                });
   
               $(".confirmeNomeCadastroNaoSouDonoSP").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".confirmeNomeCadastroNaoSouDonoSP").css("color", "#1974CE");
                        $(".label-confirmeNomeCadastroNaoSouDonoSP").css("color", "#1974CE");
                    }
                });
                 // confirmeEmailCadastroNaoSouDonoSP

             $(".confirmeCelularCadastroNaoSouDonoSP").focus(function(){
                if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".confirmeCelularCadastroNaoSouDonoSP").css("color", "#1974CE");
                        $(".label-confirmeCelularCadastroNaoSouDonoSP").css("color", "#1974CE");
                    }
                });
   
               $(".confirmeCelularCadastroNaoSouDonoSP").blur(function(){
               if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#FF4141"});
                        $(".label-confirmeCelularCadastroNaoSouDonoSP").css("color", "#FF4141");
                    }
                });
   
               $(".confirmeCelularCadastroNaoSouDonoSP").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".confirmeCelularCadastroNaoSouDonoSP").css("color", "#1974CE");
                        $(".label-confirmeCelularCadastroNaoSouDonoSP").css("color", "#1974CE");
                    }
                });

                 //  confirmeEmailCadastroNaoSouDonoSP

             $(".confirmeEmailCadastroNaoSouDonoSP").focus(function(){
                if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".confirmeEmailCadastroNaoSouDonoSP").css("color", "#1974CE");
                        $(".label-confirmeEmailCadastroNaoSouDonoSP").css("color", "#1974CE");
                    }
                });
   
               $(".confirmeEmailCadastroNaoSouDonoSP").blur(function(){
               if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#FF4141"});
                        $(".label-confirmeEmailCadastroNaoSouDonoSP").css("color", "#FF4141");
                    }
                });
   
               $(".confirmeEmailCadastroNaoSouDonoSP").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".confirmeEmailCadastroNaoSouDonoSP").css("color", "#1974CE");
                        $(".label-confirmeEmailCadastroNaoSouDonoSP").css("color", "#1974CE");
                    }
                });


                 //  cnjpCadastroNaoSouDonoSP

             $(".cnjpCadastroNaoSouDonoSP").focus(function(){
                if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".cnjpCadastroNaoSouDonoSP").css("color", "#1974CE");
                        $(".label-cnjpCadastroNaoSouDonoSP").css("color", "#1974CE");
                    }
                });
   
               $(".cnjpCadastroNaoSouDonoSP").blur(function(){
               if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#FF4141"});
                        $(".label-cnjpCadastroNaoSouDonoSP").css("color", "#FF4141");
                    }
                });
   
               $(".cnjpCadastroNaoSouDonoSP").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".cnjpCadastroNaoSouDonoSP").css("color", "#1974CE");
                        $(".label-cnjpCadastroNaoSouDonoSP").css("color", "#1974CE");
                    }
                });

                 //  nomeCorretoraCadastroNaoSouDonoSP

             $(".nomeCorretoraCadastroNaoSouDonoSP").focus(function(){
                if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".nomeCorretoraCadastroNaoSouDonoSP").css("color", "#1974CE");
                        $(".label-nomeCorretoraCadastroNaoSouDonoSP").css("color", "#1974CE");
                    }
                });
   
               $(".nomeCorretoraCadastroNaoSouDonoSP").blur(function(){
               if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#FF4141"});
                        $(".label-nomeCorretoraCadastroNaoSouDonoSP").css("color", "#FF4141");
                    }
                });
   
               $(".nomeCorretoraCadastroNaoSouDonoSP").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".nomeCorretoraCadastroNaoSouDonoSP").css("color", "#1974CE");
                        $(".label-nomeCorretoraCadastroNaoSouDonoSP").css("color", "#1974CE");
                    }
                });
                  //  senhaCadastroNaoSouDonoSP  

             $(".senhaCadastroNaoSouDonoSP").focus(function(){
                if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".senhaCadastroNaoSouDonoSP").css("color", "#1974CE");
                        $(".label-senhaCadastroNaoSouDonoSP").css("color", "#1974CE");
                    }
                });
   
               $(".senhaCadastroNaoSouDonoSP").blur(function(){
               if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#FF4141"});
                        $(".label-senhaCadastroNaoSouDonoSP").css("color", "#FF4141");
                    }
                });
   
               $(".senhaCadastroNaoSouDonoSP").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".senhaCadastroNaoSouDonoSP").css("color", "#1974CE");
                        $(".label-senhaCadastroNaoSouDonoSP").css("color", "#ACAAA1974CE5");
                    }
                });

                 //  validade  

             $(".validade").focus(function(){
                if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".validade").css("color", "#1974CE");
                        $(".label-validade").css("color", "#1974CE");
                    }
                });
   
               $(".validade").blur(function(){
               if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#FF4141"});
                        $(".label-validade").css("color", "#FF4141");
                    }
                });
   
               $(".validade").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".validade").css("color", "#1974CE");
                        $(".label-validade").css("color", "#1974CE");
                    }
                });

                  //  cvv  

             $(".cvv").focus(function(){
                if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".cvv").css("color", "#1974CE");
                        $(".label-cvv").css("color", "#1974CE");
                    }
                });
   
               $(".cvv").blur(function(){
               if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#FF4141"});
                        $(".label-cvv").css("color", "#FF4141");
                    }
                });
   
               $(".cvv").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".cvv").css("color", "#1974CE");
                        $(".label-cvv").css("color", "#1974CE");
                    }
                });

                 //  numeroCartaoCredito  

             $(".numeroCartaoCredito").focus(function(){
                if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".numeroCartaoCredito").css("color", "#1974CE");
                        $(".label-numeroCartaoCredito").css("color", "#1974CE");
                    }
                });
   
               $(".numeroCartaoCredito").blur(function(){
               if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#FF4141"});
                        $(".label-numeroCartaoCredito").css("color", "#FF4141");
                    }
                });
   
               $(".numeroCartaoCredito").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".numeroCartaoCredito").css("color", "#1974CE");
                        $(".label-numeroCartaoCredito").css("color", "#1974CE");
                    }
                });

  // nomeModalPF

  $(".nomeModalPF").focus(function(){
    if($(this).val() == "")
        {
            $(this).css({"border-color" : "#1974CE"});
            $(".nomeModalPF").css("color", "#1974CE");
            $(".label-nomeModalPF").css("color", "#1974CE");
        }
    });

    $(".nomeModalPF").blur(function(){
    if($(this).val() == "")
        {
            $(this).css({"border-color" : "#FF4141"});
            $(".label-nomeModalPF").css("color", "#FF4141");
        }
    });

    $(".nomeModalPF").keyup(function(){
    if($(this).val() != "")
        {
            $(this).css({"border-color" : "#1974CE"});
            $(".nomeModalPF").css("color", "#1974CE");
            $(".label-nomeModalPF").css("color", "#1974CE");
        }
    });
        //   cpfModalPF

        $(".cpfModalPF").focus(function(){
            if($(this).val() == "")
                {
                    $(this).css({"border-color" : "#1974CE"});
                    $(".cpfModalPF").css("color", "#1974CE");
                    $(".label-cpfModalPF").css("color", "#1974CE");
                }
            });



        $(".cpfModalPF").blur(function(){
            var stringteste = $(this).val().replace(".","");
            stringteste = stringteste.replace("-","");
            stringteste = stringteste.replace(".","");
        if($(this).val() == "" || TestaCPF(stringteste) == false)
                {
                    $(this).css({"border-color" : "#FF4141"});
                    $(".cpfModalPF").css("color", "#FF4141");
                    $(".label-cpfModalPF").css("color", "#FF4141");
                }
            });

        $(".cpfModalPF").keyup(function(){
            if($(this).val() != "")
                {
                    $(this).css({"border-color" : "#1974CE"});
                    $(".cpfModalPF").css("color", "#1974CE");
                    $(".label-cpfModalPF").css("color", "#1974CE");
                }
            });
               // dataNascimentoModalPF

     $(".dataNascimentoModalPF").focus(function(){
        if($(this).val() == "")
            {
                $(this).css({"border-color" : "#1974CE"});
                $(".dataNascimentoModalPF").css("color", "#1974CE");
                $(".label-dataNascimentoModalPF").css("color", "#1974CE");
            }
         });
    
         $(".dataNascimentoModalPF").blur(function(){
            if($(this).val() == "")
                {
                    $(this).css({"border-color" : "#FF4141"});
                    $(".label-dataNascimentoModalPF").css("color", "#FF4141");
                }
          });

          $(".dataNascimentoModalPF").keyup(function(){
          if($(this).val() != "")
              {
                  $(this).css({"border-color" : "#1974CE"});
                  $(".dataNascimentoModalPF").css("color", "#1974CE");
                  $(".label-dataNascimentoModalPF").css("color", "#1974CE");
              }
          });

           // nomeMaeModalPF

$(".nomeMaeModalPF").focus(function(){
    if($(this).val() == "")
        {
            $(this).css({"border-color" : "#1974CE"});
            $(".nomeMaeModalPF").css("color", "#1974CE");
            $(".label-nomeMaeModalPF").css("color", "#1974CE");
        }
    });

    $(".nomeMaeModalPF").blur(function(){
    if($(this).val() == "")
        {
            $(this).css({"border-color" : "#FF4141"});
            $(".label-nomeMaeModalPF").css("color", "#FF4141");
        }
    });

    $(".nomeMaeModalPF").keyup(function(){
    if($(this).val() != "")
        {
            $(this).css({"border-color" : "#1974CE"});
            $(".nomeMaeModalPF").css("color", "#1974CE");
            $(".label-nomeMaeModalPF").css("color", "#1974CE");
        }
    });
     // celularModalPF
    
     $(".celularModalPF").focus(function(){
        if($(this).val() == "")
            {
                $(this).css({"border-color" : "#1974CE"});
                $(".celularModalPF").css("color", "#1974CE");
                $(".label-celularModalPF").css("color", "#1974CE");
            }
        });


        $(".celularModalPF").blur(function(){
        if($(this).val() == "")
            {
                $(this).css({"border-color" : "#FF4141"});
                $(".label-celularModalPF").css("color", "#FF4141");
            }
        });


        $(".celularModalPF").keyup(function(){
        if($(this).val() != "")
            {
                $(this).css({"border-color" : "#1974CE"});
                $(".celularModalPF").css("color", "#1974CE");
                $(".label-celularModalPF").css("color", "#1974CE");
            }
       });
        // emailModalPF

     $(".emailModalPF").focus(function(){

        if($(this).val() == "")
            {
                $(this).css({"border-color" : "#1974CE"});
                $(".emailModalPF").css("color", "#1974CE");
                $(".label-emailModalPF").css("color", "#1974CE");
            }
        });

        $('.emailModalPF').blur(function(){
       //atribuindo o valor do campo
       var sEmail  = $(".emailModalPF").val();
       // filtros
       var emailFilter=/^.+@.+\..{2,}$/;
       var illegalChars= /[\(\)\<\>\,\;\:\\\/\"\[\]]/
       // condição

       if(!(emailFilter.test(sEmail))||sEmail.match(illegalChars)){
         
         $(".label-emailModalPF").css("color", "#FF4141");
         $(".emailModalPF").css("color", "#FF4141");
         $(".emailModalPF").css("border-color", "#FF4141");
       }

       else{

         $(".label-emailModalPF").css("color", "#1974CE");
         $(".emailModalPF").css("color", "#1974CE");
         $(".emailModalPF").css("border-color", "#1974CE");

       }

     });

        $(".emailModalPF").keyup(function(){
        if($(this).val() != "")
            {
                $(this).css({"border-color" : "#1974CE"});
                $(".emailModalPF").css("color", "#1974CE");
                $(".label-emailModalPF").css("color", "#1974CE");
            }
       });



     $('.emailModalPF').blur(function(){

       //atribuindo o valor do campo
       var sEmail  = $(".emailModalPF").val();
       // filtros
       var emailFilter=/^.+@.+\..{2,}$/;
       var illegalChars= /[\(\)\<\>\,\;\:\\\/\"\[\]]/
       // condição

       if(!(emailFilter.test(sEmail))||sEmail.match(illegalChars)){
         
         $(".label-emailModalPF").css("color", "#FF4141");
         $(".emailModalPF").css("color", "#FF4141");
         $(".label-emailModalPF").css("color", "#FF4141");
       }


       else{

         $(".label-emailModalPF").css("color", "#1974CE");
         $(".emailModalPF").css("color", "#1974CE");
         $(".label-emailModalPF").css("color", "#1974CE");

        }

        });
    


                 // emailCadastroNaoSouDono

             $(".emailCadastroNaoSouDono").focus(function(){

                if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".emailCadastroNaoSouDono").css("color", "#1974CE");
                        $(".label-ememailCadastroNaoSouDonoail").css("color", "#1974CE");
                    }
                });
   
                $('.emailCadastroNaoSouDono').blur(function(){
               //atribuindo o valor do campo
               var sEmail  = $(".emailCadastroNaoSouDono").val();
               // filtros
               var emailFilter=/^.+@.+\..{2,}$/;
               var illegalChars= /[\(\)\<\>\,\;\:\\\/\"\[\]]/
               // condição
   
               if(!(emailFilter.test(sEmail))||sEmail.match(illegalChars)){
                 
                 $(".label-emailCadastroNaoSouDono").css("color", "#FF4141");
                 $(".emailCadastroNaoSouDono").css("color", "#FF4141");
                 $(".emailCadastroNaoSouDono").css("border-color", "#FF4141");
               }
   
               else{
   
                 $(".label-emailCadastroNaoSouDono").css("color", "#1974CE");
                 $(".emailCadastroNaoSouDono").css("color", "#1974CE");
                 $(".emailCadastroNaoSouDono").css("border-color", "#1974CE");
   
               }
   
             });
   
                $(".emailCadastroNaoSouDono").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".emailCadastroNaoSouDono").css("color", "#1974CE");
                        $(".label-emailCadastroNaoSouDono").css("color", "#1974CE");
                    }
               });
   
   
   
             $('.emailCadastroNaoSouDono').blur(function(){
   
               //atribuindo o valor do campo
               var sEmail  = $(".emailCadastroNaoSouDono").val();
               // filtros
               var emailFilter=/^.+@.+\..{2,}$/;
               var illegalChars= /[\(\)\<\>\,\;\:\\\/\"\[\]]/
               // condição
   
               if(!(emailFilter.test(sEmail))||sEmail.match(illegalChars)){
                 
                 $(".label-emailCadastroNaoSouDono").css("color", "#FF4141");
                 $(".emailCadastroNaoSouDono").css("color", "#FF4141");
                 $(".label-emailCadastroNaoSouDono").css("color", "#FF4141");
               }
   
   
               else{
   
                 $(".label-emailCadastroNaoSouDono").css("color", "#1974CE");
                 $(".emailCadastroNaoSouDono").css("color", "#1974CE");
                 $(".label-emailCadastroNaoSouDono").css("color", "#1974CE");
   
                }
   
                });


                // emailCadastroSouDono

                $(".emailCadastroSouDono").focus(function(){

                    if($(this).val() == "")
                        {
                            $(this).css({"border-color" : "#1974CE"});
                            $(".emailCadastroSouDono").css("color", "#1974CE");
                            $(".label-emailCadastroSouDono").css("color", "#1974CE");
                        }
                    });

                    $('.emailCadastroSouDono').blur(function(){
                //atribuindo o valor do campo
                var sEmail  = $(".emailCadastroSouDono").val();
                // filtros
                var emailFilter=/^.+@.+\..{2,}$/;
                var illegalChars= /[\(\)\<\>\,\;\:\\\/\"\[\]]/
                // condição

                if(!(emailFilter.test(sEmail))||sEmail.match(illegalChars)){
                    
                    $(".label-emailCadastroSouDono").css("color", "#FF4141");
                    $(".emailCadastroSouDono").css("color", "#FF4141");
                    $(".emailCadastroSouDono").css("border-color", "#FF4141");
                }

                else{

                    $(".label-emailCadastroSouDono").css("color", "#1974CE");
                    $(".emailCadastroSouDono").css("color", "#1974CE");
                    $(".emailCadastroSouDono").css("border-color", "#1974CE");

                }

                });

                    $(".emailCadastroSouDono").keyup(function(){
                    if($(this).val() != "")
                        {
                            $(this).css({"border-color" : "#1974CE"});
                            $(".emailCadastroSouDono").css("color", "#1974CE");
                            $(".label-emailCadastroSouDono").css("color", "#1974CE");
                        }
                });



                $('.emailCadastroSouDono').blur(function(){

                //atribuindo o valor do campo
                var sEmail  = $(".emailCadastroSouDono").val();
                // filtros
                var emailFilter=/^.+@.+\..{2,}$/;
                var illegalChars= /[\(\)\<\>\,\;\:\\\/\"\[\]]/
                // condição

                if(!(emailFilter.test(sEmail))||sEmail.match(illegalChars)){
                    
                    $(".label-emailCadastroSouDono").css("color", "#FF4141");
                    $(".emailCadastroSouDono").css("color", "#FF4141");
                    $(".label-emailCadastroSouDono").css("color", "#FF4141");
                }


                else{

                    $(".label-emailCadastroSouDono").css("color", "#1974CE");
                    $(".emailCadastroSouDono").css("color", "#1974CE");
                    $(".label-emailCadastroSouDono").css("color", "#1974CE");

                    }

                    });




            
               $("#cpfcnpj").keydown(function(){

                try {

                $("#cpfcnpj").unmask();

                } catch (e) {}



                var tamanho = $("#cpfcnpj").val().length;



                if(tamanho <12){

                $("#cpfcnpj").mask("000.000.000-000");

                } else if(tamanho >= 12){

                $("#cpfcnpj").mask("00.000.000/0000-00");

                }



                // ajustando foco

                var elem = this;

                setTimeout(function(){

                // mudo a posição do seletor

                elem.selectionStart = elem.selectionEnd = 10000;

                }, 0);

                // reaplico o valor para mudar o foco

                var currentValue = $(this).val();

                $(this).val('');

                $(this).val(currentValue);

                });




                $(".form-control").css("border-top", "transparent");

                $(".form-control").css("border-left", "transparent");

                $(".form-control").css("border-right", "transparent");

                $(".form-control").css("box-shadow", "0 0 0 0");

                $(".form-control").css("border-radius", "0");

                $(".form-control").css("border-bottom", "2px solid #CBC9C6");



                $(".cpfcnpj").focus(function(){

                if($(this).val() == "")

                {

                $(this).css({"border-color" : "#1974CE"});

                $(".cpfcnpj").css("color", "#1974CE");

                $(".label-cpfcnpj").css("color", "#1974CE");

                }

                });


                $(".cpfcnpj").blur(function(){



                var stringteste = $(this).val().replace(".","");

                stringteste = stringteste.replace("-","");

                stringteste = stringteste.replace(".","");



                console.log(stringteste);



                if($(this).val() == "" || TestaCPF(stringteste) == false && isCNPJValid(stringteste) == false ) 

                { 



                $(this).css({"border-color" : "#FF4141"});

                $(".label-cpfcnpj").css("color", "#FF4141");

                $(".cpfcnpj").css("color", "#FF4141");

                }

                });



                $(".cpfcnpj").keyup(function(){

                if($(this).val() != "")

                {

                $(this).css({"border-color" : "#1974CE"});

                $(".cpfcnpj").css("color", "#1974CE");

                $(".label-cpfcnpj").css("color", "#1974CE");

                $("input:-webkit-autofill").css("color", "#1974CE");

                }

                });

                //cnpj
                function validateCNPJ(field, rules, i, options) {

                var valido = isCNPJValid(field.val()); //implementar a validação

                if (!valido) {

                //internacionalização
                return options.allrules.cnpj.alertText;
                }

                }

                function isCNPJValid(cnpj) {

                cnpj = cnpj.replace(/[^\d]+/g, '');

                if (cnpj == '') return false;

                if (cnpj.length != 14)

                return false;

                // Elimina CNPJs invalidos conhecidos

                if (cnpj == "00000000000000" ||

                cnpj == "11111111111111" ||

                cnpj == "22222222222222" ||

                cnpj == "33333333333333" ||

                cnpj == "44444444444444" ||

                cnpj == "55555555555555" ||

                cnpj == "66666666666666" ||

                cnpj == "77777777777777" ||

                cnpj == "88888888888888" ||

                cnpj == "99999999999999")

                return false;


                // Valida DVs

                tamanho = cnpj.length - 2

                numeros = cnpj.substring(0, tamanho);

                digitos = cnpj.substring(tamanho);

                soma = 0;

                pos = tamanho - 7;

                for (i = tamanho; i >= 1; i--) {

                soma += numeros.charAt(tamanho - i) * pos--;

                if (pos < 2)

                pos = 9;

                }

                resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;

                if (resultado != digitos.charAt(0))

                return false;


                tamanho = tamanho + 1;

                numeros = cnpj.substring(0, tamanho);

                soma = 0;

                pos = tamanho - 7;

                for (i = tamanho; i >= 1; i--) {

                soma += numeros.charAt(tamanho - i) * pos--;

                if (pos < 2)

                pos = 9;

                }

                resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;

                if (resultado != digitos.charAt(1))

                return false;


                return true;

                }


                //comando para não permitir ctrl C, ctrl V e CTRL X. 
            $(document).ready(function() {
                $('#cpfcnpj').bind('cut copy paste', function(event) {
                    event.preventDefault();
                }); 
            });
            
            $(document).ready(function() {
                $('#senha-index').bind('cut copy paste', function(event) {
                    event.preventDefault();
                }); 
            });
                    
           //revelar senha digitada ao clicar no icone   
            var senhaI= $('#senha-index');
            var olhoI= $("#icone-revelar");

            olhoI.mousedown(function() {
            senhaI.attr("type", "text");
            });

            olhoI.mouseup(function() {
            senhaI.attr("type", "password");
            });
            
            $( "#icone-revelar" ).mouseout(function() { 
            $("#senha-index").attr("type", "password");
            });               
         
          

            var senha= $('#senhaCadastroSouDono');
            var olho= $("#icone-revelarSouDono");

            olho.mousedown(function() {
            senha.attr("type", "text");
            });

            olho.mouseup(function() {
            senha.attr("type", "password");
            });
            
            $( "#icone-revelarSouDono" ).mouseout(function() { 
            $("#senhaCadastroSouDono").attr("type", "password");
            });   


            var senhaC= $('#senhaCadastroNaoSouDono');
            var olhoC= $("#icone-revelar");

            olhoC.mousedown(function() {
            senhaC.attr("type", "text");
            });

            olhoC.mouseup(function() {
            senhaC.attr("type", "password");
            });
            
            $( "#icone-revelar" ).mouseout(function() { 
            $("#senhaCadastroNaoSouDono").attr("type", "password");
            });               
            

            var senhaD= $('#senhaEditarCadastroSouDono');
            var olhoD= $("#icone-senhaEditarCadastroSouDono");

            olhoD.mousedown(function() {
            senhaD.attr("type", "text");
            });

            olhoD.mouseup(function() {
            senhaD.attr("type", "password");
            });
            
            $( "#icone-senhaEditarCadastroSouDono" ).mouseout(function() { 
            $("#senhaEditarCadastroSouDono").attr("type", "password");
            });      


            var senhaE= $('#editarSenhaMeusDados');
            var olhoE= $("#icone-editarSenhaMeusDados");

            olhoE.mousedown(function() {
            senhaE.attr("type", "text");
            });

            olhoE.mouseup(function() {
            senhaE.attr("type", "password");
            });
            
            $( "#icone-editarSenhaMeusDados" ).mouseout(function() { 
            $("#editarSenhaMeusDados").attr("type", "password");
            });      

          });
           
          
      