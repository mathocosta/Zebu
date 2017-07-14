<%--
    Document   : carrinho
    Author     : Matheus
--%>

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
    <%
      List<Produto> produtos = (List<Produto>) request.getAttribute("produtos");
    %>

    <%@include file="./includes/cabecalho.jsp" %>

    <main>
      <section class="section">
        <div class="container">
          <h3 class="title">Carrinho</h3>
          <table class="table">
            <thead>
              <tr>
                <th>Produto</th>
                <th>Quantidade</th>
                <th>Preço</th>
                <th></th>
              </tr>
            </thead>
            <tbody>
              <%
                for (Produto p : produtos) {
              %>
              <tr>
                <td>
                  <%= p.getNome() %><br> <%=p.getFabricante()%>
                </td>
                <td><%=p.getQuantidade()%></td>
                <td class="preco-label"><%=p.getPreco()%></td>
                <td>
                  <button class="delete remove-btn" data-prod-cod=<%=p.getCodigo()%>></button>
                </td>
              </tr>
              <%
               }
              %>
            </tbody>
            <tfoot>
              <tr>
                <th colspan="2">Preço total:</th>
                <th id="preco-final-label"></th>
                <th>
                  <button class="button is-small is-danger remove-btn" data-prod-cod="todos">Remover todos</button>
                </th>
              </tr>
              <tr>
                <td colspan="4">
                  <a href="confirmadados" class="button is-success is-medium">Comprar</a>
                </td>
              </tr>
            </tfoot>
          </table>
        </div>
      </section>
    </main>

    <%@include file="./includes/footer.jsp" %>

    <script src="./js/preco_total.js"></script>
    <script src="./js/remove_carrinho.js"></script>
  </body>

</html>