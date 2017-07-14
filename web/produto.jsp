<%-- 
    Document   : produto
    Author     : Matheus
--%>
<jsp:useBean id="produto" class="loja.dao.Produto"/>
<%@page import="loja.dao.Produto"%>
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

  <%
    produto = (Produto) request.getAttribute("produto");
  %>
  <main>
    <section class="section">
      <div class="container">
        <div class="columns is-desktop">
          <div class="column">
            <figure class="image">
              <img src="" alt="">
            </figure>
          </div>
          <div class="column">
            <div class="content">
              <h3><%=produto.getNome() %></h3>
              <h4><%=produto.getFabricante() %></h4>
              <p><%=produto.getDescricao() %></p>
              <h2><strong><%=produto.getPreco()%></strong></h2>
              <button class="button is-white is-medium add-carrinho-btn" data-prod-cod=<%=produto.getCodigo()%> >
                <spam class="icon is-medium">
                  <i class="fa fa-cart-plus" aria-hidden="true"></i>
                </spam>
                <spam> Adicionar ao Carrinho </spam>
              </button>
            </div>
          </div>
        </div>
      </div>
    </section>
  </main>

  <%@include file="./includes/footer.jsp" %>
  <script src="./js/adiciona_carrinho.js"></script>
</body>

</html>