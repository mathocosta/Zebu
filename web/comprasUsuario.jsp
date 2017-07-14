<%--
    Document   : comprasUsuario
    Created on : 07/07/2017, 21:45:56
    Author     : Matheus
--%>
<%@page import="compra.dao.Venda"%>
<%@page import="loja.dao.Produto"%>
<%@page import="java.util.List"%>
<%@page import="java.text.DecimalFormat" %>
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
    <%
      List<Venda> compras = (List<Venda>) request.getAttribute("compras");
      DecimalFormat df = new DecimalFormat("0.##");
    %>

    <%@include file="./includes/cabecalho.jsp" %>

    <main>
      <section class="section">
        <div class="container">
          <h3 class="title">Suas compras realizadas:</h3>

          <%
            for (Venda v : compras) {
          %>

          <div class="card">
            <div class="card-content">
              <article class="media">
                <div class="media-content">
                  <div class="content">
                    <p>
                      <strong><%=v.getVendaID() %></strong>
                      - $<%=df.format(v.getPrecoTotal())%>
                    </p>
                  </div>
                  <%
                    for (Produto p : v.getProdutos()) {
                  %>
                  <article class="media">
                    <p>
                      <strong><%=p.getNome() %></strong>
                      <br>
                      x <%=p.getQuantidade() %>
                    </p>
                  </article>
                  <%
                    }
                  %>
                </div>
              </article>
            </div>
          </div>
          <br>
          <%
            }
          %>
        </div>
      </section>
    </main>    

    <%@include file="./includes/footer.jsp" %>
  </body>
</html>
