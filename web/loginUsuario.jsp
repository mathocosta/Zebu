<%--
    Document   : loginUsuario
    Created on : 11/06/2017, 17:04:02
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
                <h3 class="title">Faça seu login</h3>
                <div class="columns">
                    <div class="column">
                        <form action="servletLoginUser" method="post">
                            <div class="field">
                                <label class="label">Usuário:</label>
                                <p class="control">
                                    <input class="input" type="text" name="login" required/>
                                </p>
                            </div>
                            <div class="field">
                                <label class="label">Senha:</label>
                                <p class="control">
                                    <input class="input" type="password" name="senha" required/>
                                </p>
                            </div>
                            <div class="field">
                                <p class="control">
                                    <button class="button is-primary" type="submit">Login</button>
                                </p>
                            </div>
                        </form>
                    </div>
                    <div class="column">
                        <label class="label">Ainda não tem conta?</label>
                        <a href="cadastroUsuario.jsp">
                          <button class="button is-medium">
                              Faça seu cadastro
                          </button>
                        </a>
                    </div>
                </div>
        </section>
    </main>

    <%@include file="./includes/footer.jsp" %>
</body>

</html>