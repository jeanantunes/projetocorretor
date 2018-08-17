
    
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
                     $(this).css({"border-color" : "#F00"});
                     $(".label-cpf").css("color", "red");
                     $(".cpf").css("color", "red");
                 }
             });

                $(".cpf").keyup(function(){
             if($(this).val() != "")
                 {
                     $(this).css({"border-color" : "#3A94FB"});
                     $(".cpf").css("color", "#3A94FB");
                     $(".label-cpf").css("color", "#3A94FB");
                     $("input:-webkit-autofill").css("color", "#3A94FB");
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
                     $(this).css({"border-color" : "#F00"});
                     $(".label-sexo").css("color", "red");
                 }
             });

            $(".sexo").keyup(function(){
             if($(this).val() != "")
                 {
                     $(this).css({"border-color" : "#3A94FB"});
                     $(".sexo").css("color", "#3A94FB");
                     $(".label-sexo").css("color", "#3A94FB");
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
                     $(this).css({"border-color" : "#F00"});
                     $(".label-mensagem").css("color", "red");
                 }
             });

            $(".mensagem").keyup(function(){
             if($(this).val() != "")
                 {
                     $(this).css({"border-color" : "#3A94FB"});
                     $(".mensagem").css("color", "#3A94FB");
                     $(".label-mensagem").css("color", "#3A94FB");
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
                     $(this).css({"border-color" : "#F00"});
                     $(".label-nascimento").css("color", "red");
                 }
             });

            $(".nascimento").keyup(function(){
             if($(this).val() != "")
                 {
                     $(this).css({"border-color" : "#3A94FB"});
                     $(".nascimento").css("color", "#3A94FB");
                     $(".label-nascimento").css("color", "#3A94FB");
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
                     $(this).css({"border-color" : "#F00"});
                     $(".label-dependente").css("color", "red");
                 }
             });

            $(".dependente").keyup(function(){
             if($(this).val() != "")
                 {
                     $(this).css({"border-color" : "#3A94FB"});
                     $(".dependente").css("color", "#3A94FB");
                     $(".label-dependente").css("color", "#3A94FB");
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
                     $(this).css({"border-color" : "#F00"});
                     $(".label-nome-mae").css("color", "red");
                 }
             });

            $(".nome-mae").keyup(function(){
             if($(this).val() != "")
                 {
                     $(this).css({"border-color" : "#3A94FB"});
                     $(".nome-mae").css("color", "#3A94FB");
                     $(".label-nome-mae").css("color", "#3A94FB");
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
                     $(this).css({"border-color" : "red"});
                     $(".cnpj").css("color", "red");
                     $(".label-cnpj").css("color", "red");
                 }
             });

            $(".cnpj").keyup(function(){
             if($(this).val() != "")
                 {
                     $(this).css({"border-color" : "#3A94FB"});
                     $(".cnpj").css("color", "#3A94FB");
                     $(".label-cnpj").css("color", "#3A94FB");
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
                     $(this).css({"border-color" : "#F00"});
                     $(".label-nome").css("color", "red");
                 }
             });

             $(".nome").keyup(function(){
             if($(this).val() != "")
                 {
                     $(this).css({"border-color" : "#3A94FB"});
                     $(".nome").css("color", "#3A94FB");
                     $(".label-nome").css("color", "#3A94FB");
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
                     $(this).css({"border-color" : "#F00"});
                     $(".label-telefone").css("color", "red");
                 }
              });

            $(".telefone").keyup(function(){
             if($(this).val() != "")
                 {
                     $(this).css({"border-color" : "#3A94FB"});
                     $(".telefone").css("color", "#3A94FB");
                     $(".label-telefone").css("color", "#3A94FB");
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
                     $(this).css({"border-color" : "#F00"});
                     $(".label-celular").css("color", "red");
                 }
             });


             $(".celular").keyup(function(){
             if($(this).val() != "")
                 {
                     $(this).css({"border-color" : "#3A94FB"});
                     $(".celular").css("color", "#3A94FB");
                     $(".label-celular").css("color", "#3A94FB");
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
                     $(this).css({"border-color" : "#F00"});
                     $(".label-cep").css("color", "red");
                 }
            });

            $(".cep").keyup(function(){
             if($(this).val() != "")
                 {
                     $(this).css({"border-color" : "#3A94FB"});
                     $(".cep").css("color", "#3A94FB");
                     $(".label-cep").css("color", "#3A94FB");
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
                     $(this).css({"border-color" : "#F00"});
                     $(".label-razao-social").css("color", "red");
                 }
             });

             $(".razao-social").keyup(function(){
             if($(this).val() != "")
                 {
                     $(this).css({"border-color" : "#3A94FB"});
                     $(".razao-social").css("color", "#3A94FB");
                     $(".label-razao-social").css("color", "#3A94FB");
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
                     $(this).css({"border-color" : "#F00"});
                     $(".label-endereco").css("color", "red");
                 }
            });

            $(".endereco").keyup(function(){
             if($(this).val() != "")
                 {
                     $(this).css({"border-color" : "#3A94FB"});
                     $(".endereco").css("color", "#3A94FB");
                     $(".label-endereco").css("color", "#3A94FB");
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
                         $(this).css({"border-color" : "#F00"});   
                         $(".label-numero").css("color", "red");
                         
                     }
             });

             $(".numero").keyup(function(){
             if($(this).val() != "")
                 {
                     $(this).css({"border-color" : "#3A94FB"});
                     $(".numero").css("color", "#3A94FB");
                     $(".label-numero").css("color", "#3A94FB");
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
              
              $(".label-email").css("color", "red");
              $(".email").css("color", "red");
              $(".email").css("border-color", "red");
            }

            else{

              $(".label-email").css("color", "#3A94FB");
              $(".email").css("color", "#3A94FB");
              $(".email").css("border-color", "#3A94FB");

            }

          });

             $(".email").keyup(function(){
             if($(this).val() != "")
                 {
                     $(this).css({"border-color" : "#3A94FB"});
                     $(".email").css("color", "#3A94FB");
                     $(".label-email").css("color", "#3A94FB");
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
              
              $(".label-email").css("color", "red");
              $(".email").css("color", "red");
              $(".label-email").css("color", "red");
            }


            else{

              $(".label-email").css("color", "#3A94FB");
              $(".email").css("color", "#3A94FB");
              $(".label-email").css("color", "#3A94FB");

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
                         $(this).css({"border-color" : "#F00"});
                         $(".label-complemento").css("color", "red");
                     }
                });

             $(".complemento").keyup(function(){
             if($(this).val() != "")
                 {
                     $(this).css({"border-color" : "#3A94FB"});
                     $(".complemento").css("color", "#3A94FB");
                     $(".label-complemento").css("color", "#3A94FB");
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
                         $(this).css({"border-color" : "#F00"});
                         $(".label-bairro").css("color", "red");
                     }
                });

             $(".bairro").keyup(function(){
             if($(this).val() != "")
                 {
                     $(this).css({"border-color" : "#3A94FB"});
                     $(".bairro").css("color", "#3A94FB");
                     $(".label-bairro").css("color", "#3A94FB");
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
                         $(this).css({"border-color" : "#F00"});
                         $(".label-password").css("color", "red");
                     }
                });

             $(".password").keyup(function(){
             if($(this).val() != "")
                 {
                     $(this).css({"border-color" : "#3A94FB"});
                     $(".password").css("color", "#3A94FB");
                     $(".label-password").css("color", "#3A94FB");
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
                         $(this).css({"border-color" : "#F00"});
                         $(".label-cidade").css("color", "red");
                     }
               });

               $(".cidade").keyup(function(){
               if($(this).val() != "")
                   {
                       $(this).css({"border-color" : "#3A94FB"});
                       $(".cidade").css("color", "#3A94FB");
                       $(".label-cidade").css("color", "#3A94FB");
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
                            $(this).css({"border-color" : "#F00"});
                            $(".label-nomeMae").css("color", "red");
                        }
                  });
   
                  $(".nomeMae").keyup(function(){
                  if($(this).val() != "")
                      {
                          $(this).css({"border-color" : "#3A94FB"});
                          $(".nomeMae").css("color", "#3A94FB");
                          $(".label-nomeMae").css("color", "#3A94FB");
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
                            $(this).css({"border-color" : "#F00"});
                            $(".label-dataNascimento").css("color", "red");
                        }
                  });
   
                  $(".dataNascimento").keyup(function(){
                  if($(this).val() != "")
                      {
                          $(this).css({"border-color" : "#3A94FB"});
                          $(".dataNascimento").css("color", "#3A94FB");
                          $(".label-dataNascimento").css("color", "#3A94FB");
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
                            $(this).css({"border-color" : "#F00"});
                            $(".label-estado").css("color", "red");
                        }
                  });
   
                  $(".estado").keyup(function(){
                  if($(this).val() != "")
                      {
                          $(this).css({"border-color" : "#3A94FB"});
                          $(".estado").css("color", "#3A94FB");
                          $(".label-estado").css("color", "#3A94FB");
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
                            $(this).css({"border-color" : "#F00"});
                            $(".label-departamento").css("color", "red");
                        }
                  });
   
                  $(".departamento").keyup(function(){
                  if($(this).val() != "")
                      {
                          $(this).css({"border-color" : "#3A94FB"});
                          $(".departamento").css("color", "#3A94FB");
                          $(".label-departamento").css("color", "#3A94FB");
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
                            $(this).css({"border-color" : "#F00"});
                            $(".label-cargo").css("color", "red");
                        }
                  });
   
                  $(".cargo").keyup(function(){
                  if($(this).val() != "")
                      {
                          $(this).css({"border-color" : "#3A94FB"});
                          $(".cargo").css("color", "#3A94FB");
                          $(".label-cargo").css("color", "#3A94FB");
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
                        $(this).css({"border-color" : "#F00"});
                        $(".cnpjCadastroSouDono").css("color", "red");
                        $(".label-cnpjCadastroSouDono").css("color", "red");
                    }
                });
   
               $(".cnpjCadastroSouDono").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#3A94FB"});
                        $(".cnpjCadastroSouDono").css("color", "#3A94FB");
                        $(".label-cnpjCadastroSouDono").css("color", "#3A94FB");
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
                        $(this).css({"border-color" : "#F00"});
                        $(".label-cnpjCadastroNaoSouDono").css("color", "red");
                    }
                });
   
               $(".cnpjCadastroNaoSouDono").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#3A94FB"});
                        $(".cnpjCadastroNaoSouDono").css("color", "#3A94FB");
                        $(".label-cnpjCadastroNaoSouDono").css("color", "#3A94FB");
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
                        $(this).css({"border-color" : "#F00"});
                        $(".label-cnae").css("color", "red");
                    }
                });
   
               $(".cnae").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#3A94FB"});
                        $(".cnae").css("color", "#3A94FB");
                        $(".label-cnae").css("color", "#3A94FB");
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
                        $(this).css({"border-color" : "#F00"});
                        $(".label-simplesNacional").css("color", "red");
                    }
                });
   
               $(".simplesNacional").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#3A94FB"});
                        $(".simplesNacional").css("color", "#3A94FB");
                        $(".label-simplesNacional").css("color", "#3A94FB");
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
                        $(this).css({"border-color" : "#F00"});
                        $(".label-dataAbertura").css("color", "red");
                    }
                });
   
               $(".dataAbertura").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#3A94FB"});
                        $(".dataAbertura").css("color", "#3A94FB");
                        $(".label-dataAbertura").css("color", "#3A94FB");
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
                        $(this).css({"border-color" : "#F00"});
                        $(".label-statusCnpjCadastroSouDono").css("color", "red");
                    }
                });
   
               $(".statusCnpjCadastroSouDono").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#3A94FB"});
                        $(".statusCnpjCadastroSouDono").css("color", "#3A94FB");
                        $(".label-statusCnpjCadastroSouDono").css("color", "#3A94FB");
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
                        $(this).css({"border-color" : "#F00"});
                        $(".label-nomeRepresentanteCadastroSouDono").css("color", "red");
                    }
                });
   
               $(".nomeRepresentanteCadastroSouDono").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#3A94FB"});
                        $(".nomeRepresentanteCadastroSouDono").css("color", "#3A94FB");
                        $(".label-nomeRepresentanteCadastroSouDono").css("color", "#3A94FB");
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
                        $(this).css({"border-color" : "red"});
                        $(".cpfCadastroSouDono").css("color", "red");
                        $(".label-cpfCadastroSouDono").css("color", "red");
                    }
                });
   
               $(".cpfCadastroSouDono").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#3A94FB"});
                        $(".cpfCadastroSouDono").css("color", "#3A94FB");
                        $(".label-cpfCadastroSouDono").css("color", "#3A94FB");
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
                            $(this).css({"border-color" : "red"});
                            $(".cpfCadastroNaoSouDono").css("color", "red");
                            $(".label-cpfCadastroNaoSouDono").css("color", "red");
                        }
                    });
   
               $(".cpfCadastroNaoSouDono").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#3A94FB"});
                        $(".cpfCadastroNaoSouDono").css("color", "#3A94FB");
                        $(".label-cpfCadastroNaoSouDono").css("color", "#3A94FB");
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
                        $(this).css({"border-color" : "#F00"});
                        $(".label-nomeSubCadastroSouDono").css("color", "red");
                    }
                });
   
               $(".nomeSubCadastroSouDono").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#3A94FB"});
                        $(".nomeSubCadastroSouDono").css("color", "#3A94FB");
                        $(".label-nomeSubCadastroSouDono").css("color", "#3A94FB");
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
                        $(this).css({"border-color" : "#F00"});
                        $(".label-cpfSubCadastroSouDono").css("color", "red");
                    }
                });
   
               $(".cpfSubCadastroSouDono").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#3A94FB"});
                        $(".cpfSubCadastroSouDono").css("color", "#3A94FB");
                        $(".label-cpfSubCadastroSouDono").css("color", "#3A94FB");
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
                        $(this).css({"border-color" : "#F00"});
                        $(".label-celularCadastroNaoSouDono").css("color", "red");
                    }
                });
   
               $(".celularCadastroNaoSouDono").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#3A94FB"});
                        $(".celularCadastroNaoSouDono").css("color", "#3A94FB");
                        $(".label-celularCadastroNaoSouDono").css("color", "#3A94FB");
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
                        $(this).css({"border-color" : "red"});
                        $(".celularCadastroSouDono").css("color", "red");
                        $(".label-celularCadastroSouDono").css("color", "red");
                    }
                });
   
               $(".celularCadastroSouDono").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#3A94FB"});
                        $(".celularCadastroSouDono").css("color", "#3A94FB");
                        $(".label-celularCadastroSouDono").css("color", "#3A94FB");
                    }
                });

                
                
                 //   banco

             $(".banco").focus(function(){
                if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#1974CE"});
                        $(".banco").css("color", "#1974CE");
                        $(".label-banco").css("color", "#1974CE");
                    }
                });
   
               $(".banco").blur(function(){
               if($(this).val() == "")
                    {
                        $(this).css({"border-color" : "#F00"});
                        $(".label-banco").css("color", "red");
                    }
                });
   
               $(".banco").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#3A94FB"});
                        $(".banco").css("color", "#3A94FB");
                        $(".label-banco").css("color", "#3A94FB");
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
                        $(this).css({"border-color" : "#F00"});
                        $(".label-conta").css("color", "red");
                    }
                });
   
               $(".conta").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#3A94FB"});
                        $(".conta").css("color", "#3A94FB");
                        $(".label-conta").css("color", "#3A94FB");
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
                        $(this).css({"border-color" : "#F00"});
                        $(".label-agencia").css("color", "red");
                    }
                });
   
               $(".agencia").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#3A94FB"});
                        $(".agencia").css("color", "#3A94FB");
                        $(".label-agencia").css("color", "#3A94FB");
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
                        $(this).css({"border-color" : "#F00"});
                        $(".label-nomeCadastroNaoSouDono").css("color", "red");
                    }
                });
   
               $(".nomeCadastroNaoSouDono").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#3A94FB"});
                        $(".nomeCadastroNaoSouDono").css("color", "#3A94FB");
                        $(".label-nomeCadastroNaoSouDono").css("color", "#3A94FB");
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
                            $(this).css({"border-color" : "#F00"});
                            $(".label-nomeCadastroSouDono").css("color", "red");
                        }
                    });
       
                   $(".nomeCadastroSouDono").keyup(function(){
                    if($(this).val() != "")
                        {
                            $(this).css({"border-color" : "#3A94FB"});
                            $(".nomeCadastroSouDono").css("color", "#3A94FB");
                            $(".label-nomeCadastroSouDono").css("color", "#3A94FB");
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
                        $(this).css({"border-color" : "#F00"});
                        $(".label-sennhaCadastroSouDono").css("color", "red");
                    }
                });
   
               $(".sennhaCadastroSouDono").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#3A94FB"});
                        $(".sennhaCadastroSouDono").css("color", "#3A94FB");
                        $(".label-sennhaCadastroSouDono").css("color", "#3A94FB");
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
                        $(this).css({"border-color" : "#F00"});
                        $(".label-senhaCadastroNaoSouDono").css("color", "red");
                    }
                });
   
               $(".senhaCadastroNaoSouDono").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#3A94FB"});
                        $(".senhaCadastroNaoSouDono").css("color", "#3A94FB");
                        $(".label-senhaCadastroNaoSouDono").css("color", "#3A94FB");
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
                        $(this).css({"border-color" : "#F00"});
                        $(".label-cpfCadastroNaoSouDonoSP").css("color", "red");
                    }
                });
   
               $(".cpfCadastroNaoSouDonoSP").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#3A94FB"});
                        $(".cpfCadastroNaoSouDonoSP").css("color", "#3A94FB");
                        $(".label-cpfCadastroNaoSouDonoSP").css("color", "#3A94FB");
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
                        $(this).css({"border-color" : "#F00"});
                        $(".label-cpfCadastroNaoSouDonoSP").css("color", "red");
                    }
                });
   
               $(".cpfCadastroNaoSouDonoSP").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#3A94FB"});
                        $(".cpfCadastroNaoSouDonoSP").css("color", "#3A94FB");
                        $(".label-cpfCadastroNaoSouDonoSP").css("color", "#3A94FB");
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
                        $(this).css({"border-color" : "#F00"});
                        $(".label-ConfirmeNomeCadastroNaoSouDonoSP").css("color", "red");
                    }
                });
   
               $(".ConfirmeNomeCadastroNaoSouDonoSP").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#3A94FB"});
                        $(".ConfirmeNomeCadastroNaoSouDonoSP").css("color", "#3A94FB");
                        $(".label-ConfirmeNomeCadastroNaoSouDonoSP").css("color", "#3A94FB");
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
                        $(this).css({"border-color" : "#F00"});
                        $(".label-confirmeNomeCadastroNaoSouDonoSP").css("color", "red");
                    }
                });
   
               $(".confirmeNomeCadastroNaoSouDonoSP").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#3A94FB"});
                        $(".confirmeNomeCadastroNaoSouDonoSP").css("color", "#3A94FB");
                        $(".label-confirmeNomeCadastroNaoSouDonoSP").css("color", "#3A94FB");
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
                        $(this).css({"border-color" : "#F00"});
                        $(".label-confirmeCelularCadastroNaoSouDonoSP").css("color", "red");
                    }
                });
   
               $(".confirmeCelularCadastroNaoSouDonoSP").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#3A94FB"});
                        $(".confirmeCelularCadastroNaoSouDonoSP").css("color", "#3A94FB");
                        $(".label-confirmeCelularCadastroNaoSouDonoSP").css("color", "#3A94FB");
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
                        $(this).css({"border-color" : "#F00"});
                        $(".label-confirmeEmailCadastroNaoSouDonoSP").css("color", "red");
                    }
                });
   
               $(".confirmeEmailCadastroNaoSouDonoSP").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#3A94FB"});
                        $(".confirmeEmailCadastroNaoSouDonoSP").css("color", "#3A94FB");
                        $(".label-confirmeEmailCadastroNaoSouDonoSP").css("color", "#3A94FB");
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
                        $(this).css({"border-color" : "#F00"});
                        $(".label-cnjpCadastroNaoSouDonoSP").css("color", "red");
                    }
                });
   
               $(".cnjpCadastroNaoSouDonoSP").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#3A94FB"});
                        $(".cnjpCadastroNaoSouDonoSP").css("color", "#3A94FB");
                        $(".label-cnjpCadastroNaoSouDonoSP").css("color", "#3A94FB");
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
                        $(this).css({"border-color" : "#F00"});
                        $(".label-nomeCorretoraCadastroNaoSouDonoSP").css("color", "red");
                    }
                });
   
               $(".nomeCorretoraCadastroNaoSouDonoSP").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#3A94FB"});
                        $(".nomeCorretoraCadastroNaoSouDonoSP").css("color", "#3A94FB");
                        $(".label-nomeCorretoraCadastroNaoSouDonoSP").css("color", "#3A94FB");
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
                        $(this).css({"border-color" : "#F00"});
                        $(".label-senhaCadastroNaoSouDonoSP").css("color", "red");
                    }
                });
   
               $(".senhaCadastroNaoSouDonoSP").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#3A94FB"});
                        $(".senhaCadastroNaoSouDonoSP").css("color", "#3A94FB");
                        $(".label-senhaCadastroNaoSouDonoSP").css("color", "#ACAAA3A94FB5");
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
                        $(this).css({"border-color" : "#F00"});
                        $(".label-validade").css("color", "red");
                    }
                });
   
               $(".validade").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#3A94FB"});
                        $(".validade").css("color", "#3A94FB");
                        $(".label-validade").css("color", "#3A94FB");
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
                        $(this).css({"border-color" : "#F00"});
                        $(".label-cvv").css("color", "red");
                    }
                });
   
               $(".cvv").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#3A94FB"});
                        $(".cvv").css("color", "#3A94FB");
                        $(".label-cvv").css("color", "#3A94FB");
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
                        $(this).css({"border-color" : "#F00"});
                        $(".label-numeroCartaoCredito").css("color", "red");
                    }
                });
   
               $(".numeroCartaoCredito").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#3A94FB"});
                        $(".numeroCartaoCredito").css("color", "#3A94FB");
                        $(".label-numeroCartaoCredito").css("color", "#3A94FB");
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
            $(this).css({"border-color" : "#F00"});
            $(".label-nomeModalPF").css("color", "red");
        }
    });

    $(".nomeModalPF").keyup(function(){
    if($(this).val() != "")
        {
            $(this).css({"border-color" : "#3A94FB"});
            $(".nomeModalPF").css("color", "#3A94FB");
            $(".label-nomeModalPF").css("color", "#3A94FB");
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
                    $(this).css({"border-color" : "red"});
                    $(".cpfModalPF").css("color", "red");
                    $(".label-cpfModalPF").css("color", "red");
                }
            });

        $(".cpfModalPF").keyup(function(){
            if($(this).val() != "")
                {
                    $(this).css({"border-color" : "#3A94FB"});
                    $(".cpfModalPF").css("color", "#3A94FB");
                    $(".label-cpfModalPF").css("color", "#3A94FB");
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
                    $(this).css({"border-color" : "#F00"});
                    $(".label-dataNascimentoModalPF").css("color", "red");
                }
          });

          $(".dataNascimentoModalPF").keyup(function(){
          if($(this).val() != "")
              {
                  $(this).css({"border-color" : "#3A94FB"});
                  $(".dataNascimentoModalPF").css("color", "#3A94FB");
                  $(".label-dataNascimentoModalPF").css("color", "#3A94FB");
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
            $(this).css({"border-color" : "#F00"});
            $(".label-nomeMaeModalPF").css("color", "red");
        }
    });

    $(".nomeMaeModalPF").keyup(function(){
    if($(this).val() != "")
        {
            $(this).css({"border-color" : "#3A94FB"});
            $(".nomeMaeModalPF").css("color", "#3A94FB");
            $(".label-nomeMaeModalPF").css("color", "#3A94FB");
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
                $(this).css({"border-color" : "#F00"});
                $(".label-celularModalPF").css("color", "red");
            }
        });


        $(".celularModalPF").keyup(function(){
        if($(this).val() != "")
            {
                $(this).css({"border-color" : "#3A94FB"});
                $(".celularModalPF").css("color", "#3A94FB");
                $(".label-celularModalPF").css("color", "#3A94FB");
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
         
         $(".label-emailModalPF").css("color", "red");
         $(".emailModalPF").css("color", "red");
         $(".emailModalPF").css("border-color", "red");
       }

       else{

         $(".label-emailModalPF").css("color", "#3A94FB");
         $(".emailModalPF").css("color", "#3A94FB");
         $(".emailModalPF").css("border-color", "#3A94FB");

       }

     });

        $(".emailModalPF").keyup(function(){
        if($(this).val() != "")
            {
                $(this).css({"border-color" : "#3A94FB"});
                $(".emailModalPF").css("color", "#3A94FB");
                $(".label-emailModalPF").css("color", "#3A94FB");
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
         
         $(".label-emailModalPF").css("color", "red");
         $(".emailModalPF").css("color", "red");
         $(".label-emailModalPF").css("color", "red");
       }


       else{

         $(".label-emailModalPF").css("color", "#3A94FB");
         $(".emailModalPF").css("color", "#3A94FB");
         $(".label-emailModalPF").css("color", "#3A94FB");

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
                 
                 $(".label-emailCadastroNaoSouDono").css("color", "red");
                 $(".emailCadastroNaoSouDono").css("color", "red");
                 $(".emailCadastroNaoSouDono").css("border-color", "red");
               }
   
               else{
   
                 $(".label-emailCadastroNaoSouDono").css("color", "#3A94FB");
                 $(".emailCadastroNaoSouDono").css("color", "#3A94FB");
                 $(".emailCadastroNaoSouDono").css("border-color", "#3A94FB");
   
               }
   
             });
   
                $(".emailCadastroNaoSouDono").keyup(function(){
                if($(this).val() != "")
                    {
                        $(this).css({"border-color" : "#3A94FB"});
                        $(".emailCadastroNaoSouDono").css("color", "#3A94FB");
                        $(".label-emailCadastroNaoSouDono").css("color", "#3A94FB");
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
                 
                 $(".label-emailCadastroNaoSouDono").css("color", "red");
                 $(".emailCadastroNaoSouDono").css("color", "red");
                 $(".label-emailCadastroNaoSouDono").css("color", "red");
               }
   
   
               else{
   
                 $(".label-emailCadastroNaoSouDono").css("color", "#3A94FB");
                 $(".emailCadastroNaoSouDono").css("color", "#3A94FB");
                 $(".label-emailCadastroNaoSouDono").css("color", "#3A94FB");
   
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
                    
                    $(".label-emailCadastroSouDono").css("color", "red");
                    $(".emailCadastroSouDono").css("color", "red");
                    $(".emailCadastroSouDono").css("border-color", "red");
                }

                else{

                    $(".label-emailCadastroSouDono").css("color", "#3A94FB");
                    $(".emailCadastroSouDono").css("color", "#3A94FB");
                    $(".emailCadastroSouDono").css("border-color", "#3A94FB");

                }

                });

                    $(".emailCadastroSouDono").keyup(function(){
                    if($(this).val() != "")
                        {
                            $(this).css({"border-color" : "#3A94FB"});
                            $(".emailCadastroSouDono").css("color", "#3A94FB");
                            $(".label-emailCadastroSouDono").css("color", "#3A94FB");
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
                    
                    $(".label-emailCadastroSouDono").css("color", "red");
                    $(".emailCadastroSouDono").css("color", "red");
                    $(".label-emailCadastroSouDono").css("color", "red");
                }


                else{

                    $(".label-emailCadastroSouDono").css("color", "#3A94FB");
                    $(".emailCadastroSouDono").css("color", "#3A94FB");
                    $(".label-emailCadastroSouDono").css("color", "#3A94FB");

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



                $(this).css({"border-color" : "#F00"});

                $(".label-cpfcnpj").css("color", "red");

                $(".cpfcnpj").css("color", "red");

                }

                });



                $(".cpfcnpj").keyup(function(){

                if($(this).val() != "")

                {

                $(this).css({"border-color" : "#3A94FB"});

                $(".cpfcnpj").css("color", "#3A94FB");

                $(".label-cpfcnpj").css("color", "#3A94FB");

                $("input:-webkit-autofill").css("color", "#3A94FB");

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
           
          
      