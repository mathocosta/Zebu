/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compra.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Matheus
 */
@WebServlet(name = "ConfirmaDados", urlPatterns = {"/confirmadados"})
public class ConfirmaDados extends HttpServlet {

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    
    HttpSession session = request.getSession();
    
    request.setAttribute("email", session.getAttribute("emailUsuario"));
    request.setAttribute("nome", session.getAttribute("nomeUsuario"));
    request.setAttribute("cpf", session.getAttribute("cpfUsuario"));
    request.setAttribute("login", session.getAttribute("loginUsuario"));
    request.setAttribute("endereco", session.getAttribute("enderecoUsuario"));
    
    request.getRequestDispatcher("confirmaDadosUsuario.jsp").forward(request, response);
  }

}
