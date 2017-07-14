/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.Cookie;
import loja.dao.Produto;
import loja.dao.ProdutoDAO;

/**
 * Serve para gerenciar o cookie carrinho, e o deixar da melhor forma para
 * manipulá-lo.
 *
 * @author Matheus
 */
public class GerenciadorCkCarrinho {

  private Cookie cookieObj = null;
  private Map<String, Integer> prodsMap = null;
  private List<Produto> prodsList = null;

  public GerenciadorCkCarrinho(Cookie cookie) {
    this.cookieObj = cookie;
    this.prodsMap = organizeCookies();
    this.prodsList = getProductsInDB(this.prodsMap);
  }

  private Map<String, Integer> organizeCookies() {
    String[] codProdutos = cookieObj.getValue().split("@");
    Map<String, Integer> resultHash = new HashMap<>();

    for (String cod : codProdutos) {
      if (!cod.equals("")) {
        if (resultHash.containsKey(cod)) {
          resultHash.put(cod, resultHash.get(cod) + 1);
        } else {
          resultHash.put(cod, 1);
        }
      }
    }

    return resultHash;
  }

  private List<Produto> getProductsInDB(Map<String, Integer> codProdMap) {
    ProdutoDAO dao = new ProdutoDAO();
    List<Produto> produtos = new ArrayList<>();

    for (String cod : codProdMap.keySet()) {
      Produto p = dao.getProduto(Integer.parseInt(cod));
      p.setQuantidade(codProdMap.get(cod));
      produtos.add(p);
    }

    return produtos;
  }

  public List<Produto> getProdsList() {
    return this.prodsList;
  }

}
