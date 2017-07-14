/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loja.filtros;

import java.io.IOException;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import loja.dao.Produto;
import loja.dao.ProdutoDAO;

/**
 * Isso é para montar a página principal(index) a cada requisição para ela. Os
 * produtos que são enviados são escolhidos de acordo com o número da página que
 * vem na requisição.
 *
 * TODO: Fazer funcionar o botão de proxima página.
 *
 * @author Matheus
 */
@WebFilter("/index.jsp")
public class IndexFilter implements Filter {

  @Override
  public void doFilter (ServletRequest request, ServletResponse response, FilterChain chain)
    throws IOException, ServletException {

    String pagNum = request.getParameter("page");
    if (pagNum == null) {
      pagNum = "1";
    }

    List<Produto> produtos = new ProdutoDAO().getAll();

    request.setAttribute("produtos", getProdsOfPage(produtos, 
      Integer.parseInt(pagNum)));
    request.setAttribute("temProxima", false);

    chain.doFilter(request, response);
  }

  private List<Produto> getProdsOfPage (List<Produto> produtos, int pageNum) {
    int endPos = 8 * pageNum;
    return produtos.subList(
      8 * (pageNum - 1),
      (endPos > produtos.size() ? produtos.size() : endPos)
    );
  }

  @Override
  public void init (FilterConfig filterConfig) throws ServletException {
  }

  @Override
  public void destroy () {
  }

}
