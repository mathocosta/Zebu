/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compra.servlets;

import compra.dao.CompraDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import loja.dao.Produto;
import loja.dao.ProdutoDAO;
import utils.GerenciadorCkCarrinho;

/**
 * Servlet que faz a compra. Se baseia nos produtos que estão no carrinho, e
 * depois da compra remove-os. Obs: se não tiver a quantidade do produto da
 * compra no estoque ele não é comprado. TODO: Ajeitar isso.
 *
 * @author Matheus
 */
@WebServlet(name = "FazerCompra", urlPatterns = {"/fazercompra"})
public class FazerCompra extends HttpServlet {

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    Cookie[] ck = request.getCookies();
    int count = 0;
    while (count < ck.length) {
      if (ck[count].getName().equals("carrinho")) {
        break;
      }
      count++;
    }
    Cookie ckCarrinho = ck[count];
    List<Produto> compra = new GerenciadorCkCarrinho(ckCarrinho).getProdsList();

    ProdutoDAO prodDAO = new ProdutoDAO();
    for (Produto p : compra) {
      if (prodDAO.getQuantidade(p) < p.getQuantidade()) {
        compra.remove(p);
      }
      // é necessário verificar caso 'compra' fique vazio 
      // porque pode acontecer um erro.
      if (compra.isEmpty()) {
        break;
      }
    }

    CompraDAO compraDAO = new CompraDAO();

    String email = (String) request.getSession().getAttribute("emailUsuario");

    if (email != null) {
      if (compra.size() > 0) {
        compraDAO.fazCompra(compra, email);
        ckCarrinho.setValue("");
        response.addCookie(ckCarrinho);
      }
      response.sendRedirect("vercompras");
    } else {
      // NOTE: não sei se isso eh realmente necessário, 
      //   já que o usuario tem que tá logado quando chegar nessa página.
      request.getRequestDispatcher("loginUsuario.jsp").forward(request, response);
    }

    compraDAO.close();
  }
  
}
