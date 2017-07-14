/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compra.servlets;

import compra.dao.CompraDAO;
import compra.dao.Venda;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import loja.dao.Produto;
import loja.dao.ProdutoDAO;

/**
 * Acessa o bd para pegar as vendas realizadas para aquele cliente e repassa os
 * dados para serem exibidos na view.
 *
 * @author Matheus
 */
@WebServlet(name = "VerCompras", urlPatterns = {"/vercompras"})
public class VerCompras extends HttpServlet {

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    String email = (String) request.getSession().getAttribute("emailUsuario");
    List<Venda> compras = new CompraDAO().getCompras(email);
    
    // Quando vem do bd não tem o nome do produto.
    for (Venda v : compras) {
      for (Produto p : v.getProdutos()) {
        p.setNome(
                new ProdutoDAO()
                  .getProduto(p.getCodigo())
                  .getNome()
        );
      }
    }
    
    // Para aparecer em ordem decresente.
    Collections.reverse(compras);
    
    request.setAttribute("compras", compras);
    request.getRequestDispatcher("comprasUsuario.jsp").forward(request, response);

  }

}
