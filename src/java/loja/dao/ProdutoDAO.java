
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loja.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.FabricaConexao;

/**
 * Classe que acessa e manipula tudo relacionado ao estoque de produtos no
 * banco.
 *
 * @author Matheus
 */
public class ProdutoDAO {

  private final Connection connection;

  public ProdutoDAO() {
    this.connection = new FabricaConexao().getConnection();
  }

  public List<Produto> getAll() {
    try {
      List<Produto> produtos = new ArrayList<>();

      PreparedStatement statement = this.connection
              .prepareStatement("SELECT * FROM produtos");
      ResultSet rs = statement.executeQuery();

      while (rs.next()) {
        if (rs.getInt("quantidade") > 0) {
          Produto p = new Produto();
          p.setCodigo(rs.getInt("codigo"));
          p.setNome(rs.getString("nome"));
          p.setFabricante(rs.getString("fabricante"));
          p.setDescricao(rs.getString("descricao"));
          p.setPreco(rs.getString("preco"));
          p.setQuantidade(rs.getInt("quantidade"));

          produtos.add(p);
        }
      }

      rs.close();
      statement.close();

      return produtos;

    } catch (SQLException ex) {
      throw new RuntimeException(ex.getMessage());
    }
  }

  public Produto getProduto(int id) {
    try {
      String sql = "SELECT * FROM produtos WHERE codigo=?";

      PreparedStatement statement = this.connection.prepareStatement(sql);
      statement.setInt(1, id);
      ResultSet rs = statement.executeQuery();
      rs.next();

      Produto p = new Produto();
      p.setCodigo(rs.getInt("codigo"));
      p.setNome(rs.getString("nome"));
      p.setFabricante(rs.getString("fabricante"));
      p.setDescricao(rs.getString("descricao"));
      p.setPreco(rs.getString("preco"));
      p.setQuantidade(rs.getInt("quantidade"));

      rs.close();
      statement.close();

      return p;

    } catch (SQLException ex) {
      throw new RuntimeException(ex.getMessage());
    }
  }

  public int getQuantidade(Produto produto) {
    String sql = "SELECT quantidade FROM produtos WHERE codigo=?";
    try {
      PreparedStatement statement = this.connection.prepareStatement(sql);
      statement.setInt(1, produto.getCodigo());
      ResultSet rs = statement.executeQuery();
      rs.next();

      return rs.getInt("quantidade");

    } catch (SQLException ex) {
      throw new RuntimeException(ex.getMessage());
    }
  }

  public void add(Produto produto) {
    String sql = "INSERT INTO produtos"
            + "(codigo, nome, fabricante, descricao, preco, quantidade)"
            + "VALUES (?, ?, ?, ?)";
    try {
      PreparedStatement statement = this.connection.prepareStatement(sql);

      statement.setInt(1, produto.getCodigo());
      statement.setString(2, produto.getNome());
      statement.setString(3, produto.getFabricante());
      statement.setString(4, produto.getDescricao());
      statement.setString(5, produto.getPreco());
      statement.setInt(6, produto.getQuantidade());

      statement.execute();
      statement.close();
    } catch (SQLException ex) {
      throw new RuntimeException(ex.getMessage());
    }

  }

  public void updateQuantidade(Produto produto, int novaQtd) {
    String sql = "UPDATE produtos SET quantidade=? WHERE codigo=?";
    try {
      PreparedStatement statement = this.connection.prepareStatement(sql);

      statement.setInt(1, novaQtd);
      statement.setInt(2, produto.getCodigo());

      statement.execute();
    } catch (SQLException ex) {
      throw new RuntimeException(ex.getMessage());
    }
  }

  public void remove(Produto produto) {
    String sql = "DELETE FROM produtos WHERE codigo=?";
    try {
      PreparedStatement statement = this.connection.prepareStatement(sql);

      statement.setInt(1, produto.getCodigo());

      statement.execute();
      statement.close();
    } catch (SQLException ex) {
      throw new RuntimeException(ex.getMessage());
    }
  }

  public void close() {
    try {
      this.connection.close();
    } catch (SQLException ex) {
      throw new RuntimeException(ex.getMessage());
    }
  }

  @Override
  protected void finalize() throws Throwable {
    try {
      close();
    } finally {
      super.finalize();
    }
  }
  
  

}
