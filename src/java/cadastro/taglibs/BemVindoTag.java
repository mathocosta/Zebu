/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastro.taglibs;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * Tag para printar uma saudação de bem vindo ao usuário logado. Sim.
 *
 * @author Matheus
 */
public class BemVindoTag extends SimpleTagSupport {

  @Override
  public void doTag() throws JspException {
    JspWriter out = getJspContext().getOut();

    try {
      String nomeUsuario = (String) getJspContext()
              .getAttribute("nomeUsuario", PageContext.SESSION_SCOPE);

      out.write("<h3 class=\"title\"> Bem Vindo " + nomeUsuario + "</h3>");
      
      JspFragment f = getJspBody();
      if (f != null) {
        f.invoke(out);
      }
      
    } catch (java.io.IOException ex) {
      throw new JspException(ex);
    }
  }

}
