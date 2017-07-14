/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loja.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet para adicionar o código do produto para ser guardado no carrinho, 
 * que é armazenado nos cookies.
 *
 * @author Matheus
 */
@WebServlet("/adicionacarrinho")
public class AdicionaCarrinho extends HttpServlet {

  @Override
  protected void service (HttpServletRequest req, HttpServletResponse resp) 
    throws ServletException, IOException {
    
    String codigo = (String) req.getParameter("codigo");

    Cookie[] ck = req.getCookies();
    if (ck == null) {
      Cookie cookie = new Cookie("carrinho", codigo);
      resp.addCookie(cookie);
    } else {
      Cookie c = null;
      for (Cookie temp : ck) {
        if (temp.getName().equals("carrinho")) {
          c = temp;
        }
      }

      if (c == null) {
        c = new Cookie("carrinho", codigo);
      } else {
        c.setValue(c.getValue() + "@" + codigo);
      }
      resp.addCookie(c);
    }
    
  }
  
}
