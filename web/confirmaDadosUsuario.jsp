<%-- 
    Document   : confirmaDados
    Created on : 08/07/2017, 21:25:32
    Author     : Matheus
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
          <h3 class="title">Confirme seus dados</h3>
          <h4 class="subtitle">*caso não sejam esses, altere em seu perfil e inicie a compra novamente.</h4>
          <div class="content">
            <p><strong>Nome:</strong> <%=request.getAttribute("nome") %> </p>
            <p><strong>CPF:</strong> <%=request.getAttribute("cpf") %></p>
            <p><strong>Email:</strong> <%=request.getAttribute("email") %> </p>
            <p><strong>Endereço:</strong> <%=request.getAttribute("endereco") %> </p>
          </div>
          <a href="fazercompra" class="button is-success is-large">Finalizar Compra</a>
        </div>
      </section>
    </main>

    <%@include file="./includes/footer.jsp" %>
  </body>
</html>
