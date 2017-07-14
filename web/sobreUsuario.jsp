<%-- 
    Document   : sobreUsuario
    Created on : 11/06/2017, 23:04:07
    Author     : lord
--%>
<%@taglib uri="tags" prefix="tag" %>
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
          <div class="columns">
            <div class="column">
              <tag:BemVindoTag />
            </div>
            <div>
              <a href="servletLogoutUser" class="button is-danger">
                <spam> Fazer LogOut </spam>
                <spam class="icon"><i class="fa fa-sign-out" aria-hidden="true"></i></spam>
              </a>
            </div>
          </div>
          <a href="vercompras">Ver histórico de compras</a>
          <hr>
          <h4 class="title is-4">Altere seus dados:</h4>
          <form action="servletAlteraUser" method="post">
            <div class="columns">
              <div class="column">
                <div class="field">
                  <label class="label">Seu nome:</label>
                  <input class="input" type="text" name="nomeNovo" required/><br/>
                </div>
                <div class="field">
                  <label class="label">Seu cpf: </label>
                  <input class="input" type="text" name="cpfNovo" required/>
                </div>
                <div class="field">
                  <label class="label">Seu endereço: </label>
                  <input class="input" type="text" name="enderecoNovo" />
                </div>
              </div>
              <div class="column">
                <div class="field">
                  <label class="label">Sua senha:</label>
                  <input class="input" type="password" name="senhaNovo" required/>
                </div>
                <div class="field">
                  <label class="label">Seu(s) telefone(s): </label>
                  <input class="input" type="text" name="telefoneNovo" />
                </div>
                <div class="field">
                  <br>
                  <p class="control">
                    <button class="button is-medium is-primary" type="submit">Salvar</button>
                  </p>
                </div>
              </div>
            </div>
          </form>
          <hr>
          <a href="servletExcluiUser">Exclua sua conta</a><br>
          <small>*isso não poderá ser desfeito</small>
        </div>

      </section>
    </main> 

    <%@include file="./includes/footer.jsp" %>
  </body>
</html>
