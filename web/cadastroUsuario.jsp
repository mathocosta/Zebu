<%--
    Document   : cadastroUsuario
    Created on : 11/06/2017, 17:13:10
    Author     : lord
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="./css/style.css">
    <title>Tropical Heat</title>
</head>

<body>
    <%@include file="./includes/cabecalho.jsp" %>
    
    <main>
        <section class="section">
            <div class="container">
                <h3 class="title">Faça aqui seu cadastro</h3>
                <form action="servletCadUser" method="post">
                    <div class="columns">
                        <div class="column">
                            <div class="field">
                                <label class="label">Seu nome:</label>
                                <input class="input" type="text" name="nome" required/><br/>
                            </div>
                            <div class="field">
                                <label class="label">Seu cpf: </label>
                                <input class="input" type="text" name="cpf" required/>
                            </div>
                            <div class="field">
                                <label class="label">Seu endereço: </label>
                                <input class="input" type="text" name="endereco" />
                            </div>
                            <div class="field">
                                <label class="label">Seu(s) telefone(s): </label>
                                <input class="input" type="text" name="telefone" />
                            </div>
                        </div>
                        <div class="column">
                            <div class="field">
                                <label class="label">Seu login:</label>
                                <input class="input" type="text" name="login" required/>
                            </div>
                            <div class="field">
                                <label class="label">Seu email:</label>
                                <input class="input" type="email" name="email" required/>
                            </div>
                            <div class="field">
                                <label class="label">Sua senha:</label>
                                <input class="input" type="password" name="senha" required/>
                            </div>
                            <div class="field">
                                <br>
                                <p class="control">
                                    <button class="button is-medium is-primary" type="submit">Cadastrar</button>
                                </p>
                            </div>
                        </div>
                    </div>
                </form>

            </div>
        </section>
    </main>
    
    <%@include file="./includes/footer.jsp" %>
</body>

</html>