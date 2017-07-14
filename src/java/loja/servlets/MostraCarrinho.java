/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loja.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import loja.dao.Produto;
import utils.GerenciadorCkCarrinho;

/**
 * Servlet que pega, baseado nos códigos salvos no cookie do carrinho, os
 * produtos correspondentes no banco para serem exibidos na página do carrinho.
 *
 * @author Matheus
 */
@WebServlet("/mostracarrinho")
public class MostraCarrinho extends HttpServlet {

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {

    Cookie[] ck = req.getCookies();
    int count = 0;
    while (count < ck.length) {
      if (ck[count].getName().equals("carrinho")) {
        break;
      }
      count++;
    }

    // HACK: É necessário verificar se o cookie foi encontrado, senão for,
    //  é retornado um objeto vazio para não dar erro. #GAMBI 
    if (!(count >= ck.length)) {
      List<Produto> produtosCarrinho = new GerenciadorCkCarrinho(ck[count])
              .getProdsList();

      req.setAttribute("produtos", produtosCarrinho);
    } else {
      req.setAttribute("produtos", new ArrayList<>());
    }

    req.getRequestDispatcher("carrinho.jsp").forward(req, resp);
  }

}
