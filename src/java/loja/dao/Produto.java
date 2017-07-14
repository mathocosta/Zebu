/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loja.dao;

import java.io.Serializable;

/**
 *
 * @author Matheus
 */
public class Produto implements Serializable {
  
  private int codigo;
  private String nome;
  private String fabricante;
  private String descricao;
  private String preco;
  private int quantidade;

  public Produto () {
  }

  public Produto (int codigo, String nome, String descricao, int quantidade) {
    this.codigo = codigo;
    this.nome = nome;
    this.descricao = descricao;
    this.quantidade = quantidade;
  }

  public int getCodigo () {
    return codigo;
  }

  public void setCodigo (int codigo) {
    this.codigo = codigo;
  }

  public String getNome () {
    return nome;
  }

  public void setNome (String nome) {
    this.nome = nome;
  }

  public String getDescricao () {
    return descricao;
  }

  public void setDescricao (String descricao) {
    this.descricao = descricao;
  }

  public int getQuantidade () {
    return quantidade;
  }

  public void setQuantidade (int quantidade) {
    this.quantidade = quantidade;
  }

  public String getPreco () {
    return preco;
  }

  public void setPreco (String preco) {
    this.preco = preco;
  }

  public String getFabricante () {
    return fabricante;
  }

  public void setFabricante (String fabricante) {
    this.fabricante = fabricante;
  }
  
}
