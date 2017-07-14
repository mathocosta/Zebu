/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compra.dao;

import java.util.ArrayList;
import java.util.List;
import loja.dao.Produto;

/**
 * Entidade de Venda para guardar os dados de uma venda para exibir na página do
 * usuario.
 *
 * @author Matheus
 */
public class Venda {

  private int vendaID;
  private List<Produto> produtos;
  private double precoTotal;

  public Venda() {
    this.produtos = new ArrayList<>();
  }

  public Venda(int vendaID, List<Produto> produtos, double precoTotal) {
    this.vendaID = vendaID;
    this.produtos = produtos;
    this.precoTotal = precoTotal;
  }

  public void addProduto(Produto p) {
    this.produtos.add(p);
  }

  public int getVendaID() {
    return vendaID;
  }

  public void setVendaID(int vendaID) {
    this.vendaID = vendaID;
  }

  public List<Produto> getProdutos() {
    return produtos;
  }

  public void setProdutos(List<Produto> produtos) {
    this.produtos = produtos;
  }

  public double getPrecoTotal() {
    return precoTotal;
  }

  public void setPrecoTotal(double precoTotal) {
    this.precoTotal = precoTotal;
  }

}
