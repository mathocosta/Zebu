<%--
    Document   : index
    Created on : 08/06/2017, 21:04:38
    Author     : Matheus
--%>

<%@page import="java.text.MessageFormat"%>
<%@page import="java.util.List"%>
<%@page import="loja.dao.Produto"%>
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
        <%
          List<Produto> produtos = (List<Produto>) request.getAttribute("produtos");

          int counter = 0;
          while (counter < produtos.size()) {
        %>
        <div class="columns">
          <%
            for (int i = 0; i < 4; i++) {
              if (counter < produtos.size()) {
                Produto p = produtos.get(counter);
                String link = MessageFormat.format(
                  "/Zebu/mostraproduto?id={0}",
                  p.getCodigo());
          %>
                <div class="column is-one-quarter">
                    <div class="card">

                      <a href=<%=link%>>
                        <div class="card-image">
                          <figure class="image is-square">
                            <img src="http://www.wolterskluwercdi.com/images/sections/capsule.png" alt="">
                          </figure>
                        </div>
                        <div class="card-content">
                          <div class="content">
                            <p><%=p.getNome() %></p>
                            <h4><%=p.getPreco()%></h4>
                          </div>
                        </div>
                      </a>

                      <div class="card-footer">
                        <button class="button card-footer-item add-carrinho-btn" data-prod-cod=<%=p.getCodigo()%>>
                          <span class="icon">
                            <i class="fa fa-cart-arrow-down" aria-hidden="true"></i>
                          </span>
                          <span>
                            Adicionar ao carrinho
                          </span>
                        </button>
                      </div>
                    </div>
                </div>
            <%
                counter++;
              }
            %>
          <% } %>
        </div> <!--/.columns-->
        <% } %>
        <% if ((boolean) request.getAttribute("temProxima")) {%>
            <div class="has-text-centered">
              <button class="button">Próxima Página</button>
            </div>
        <% } %>
      </div> <!--/.container-->
    </section>
  </main>

  <%@include file="./includes/footer.jsp" %>
  <script src="./js/adiciona_carrinho.js"></script>
</body>

</html>