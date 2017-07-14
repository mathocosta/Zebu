/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loja.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import loja.dao.Produto;
import loja.dao.ProdutoDAO;

/**
 * Servlet para pegar o produto a ser mostrado na página de produto.
 *
 * @author Matheus
 */
@WebServlet("/mostraproduto")
public class MostraProduto extends HttpServlet {

  @Override
  protected void service (HttpServletRequest req, HttpServletResponse resp) 
    throws ServletException, IOException {

    Produto produto = new ProdutoDAO().getProduto(Integer.parseInt(req.getParameter("id")));

    req.setAttribute("produto", produto);
    req.getRequestDispatcher("produto.jsp").forward(req, resp);
  }

}
