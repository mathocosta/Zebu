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
 * Servlet "invocado" para remover item do carrinho. Para isso é retirado o
 * primeiro codigo igual ao recebido como parametro na string salva como valor
 * do cookie, e depois salvo uma nova string sem o codigo.
 *
 * @author Matheus
 */
@WebServlet("/removecarrinho")
public class RemoveCarrinho extends HttpServlet {

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    String idToRemove = request.getParameter("codigo");

    Cookie[] ck = request.getCookies();
    int count = 0;
    Cookie carrinhoCk = null;
    while (count < ck.length) {
      if (ck[count].getName().equals("carrinho")) {
        carrinhoCk = ck[count];
        break;
      } else {
        count++;
      }
    }
    
    if (idToRemove.equals("todos")) {
      carrinhoCk.setValue("");
      response.addCookie(carrinhoCk);
    } else {
      String carrinhoStr = carrinhoCk.getValue();
      int initialPos = carrinhoStr.indexOf(idToRemove);
      int aux = initialPos;
      while ((aux < carrinhoStr.length()) && (carrinhoStr.charAt(aux) != '@')) {
        aux++;
      }

      String primeiraParte = carrinhoStr.substring(0, initialPos);
      String segundaParte = carrinhoStr.substring(aux);
      String novoCookie = primeiraParte.concat(segundaParte);

      carrinhoCk.setValue(novoCookie);
      response.addCookie(carrinhoCk);
    }
  }

  /**
   * Caso precise para concatenar arrays no java. JS Wins :P
   */
//  public Foo[] concat (Foo[] a, Foo[] b) {
//    int aLen = a.length;
//    int bLen = b.length;
//    Foo[] c = new Foo[aLen + bLen];
//    System.arraycopy(a, 0, c, 0, aLen);
//    System.arraycopy(b, 0, c, aLen, bLen);
//    return c;
//  }
}
