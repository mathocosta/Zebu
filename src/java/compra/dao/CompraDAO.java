/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compra.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import loja.dao.Produto;
import loja.dao.ProdutoDAO;
import utils.FabricaConexao;

/**
 *
 * @author Matheus
 */
public class CompraDAO {

  private final Connection connection;

  public CompraDAO() {
    this.connection = new FabricaConexao().getConnection();
  }

  private int calcQuantidadeVendas() {
    int qtdVendas = 0;
    try {
      Statement stmt = this.connection.createStatement();
      ResultSet rs = stmt.executeQuery(
              "SELECT MAX(venda_id) AS total FROM vendas"
      );

      rs.next();
      qtdVendas = rs.getInt("total");

      rs.close();
      stmt.close();
    } catch (SQLException ex) {
      throw new RuntimeException(ex.getMessage());
    }

    return qtdVendas;
  }

  /**
   * Faz o procedimento de compra.
   *
   * 1. Consulta o banco de vendas para saber o id da última venda e salva o
   * valor.
   *
   * 2. Cria uma venda(venda_id, usuario_email, preco), o venda_id é baseado na
   * consulta anterior.
   *
   * 3. Cria venda_item(venda_id, produto_id, quantidade) para cada produto da
   * venda.
   *
   * 4. Atualizar o estoque de produtos.
   *
   * @param prodsList
   * @param usuario_email
   */
  public void fazCompra(List<Produto> prodsList, String usuario_email) {
    int qtdVendas = calcQuantidadeVendas();

    String sql_venda = "INSERT INTO vendas"
            + "(venda_id, usuario_email, preco)"
            + "VALUES (?, ?, ?)";
    double preco_total = 0;
    for (Produto p : prodsList) {
      preco_total += Float.parseFloat(p.getPreco().replace("$", "")) * p.getQuantidade();
    }

    try {
      PreparedStatement statement = this.connection
              .prepareStatement(sql_venda);

      statement.setInt(1, qtdVendas + 1);
      statement.setString(2, usuario_email);
      statement.setDouble(3, preco_total);

      statement.execute();
      statement.close();
    } catch (SQLException ex) {
      throw new RuntimeException(ex.getMessage());
    }

    String sql_venda_item = "INSERT INTO venda_item"
            + "(venda_id, produto_id, quantidade)"
            + "VALUES (?, ?, ?)";
    for (Produto p : prodsList) {
      try {
        PreparedStatement statement = this.connection
                .prepareStatement(sql_venda_item);

        statement.setInt(1, qtdVendas + 1);
        statement.setInt(2, p.getCodigo());
        statement.setInt(3, p.getQuantidade());

        statement.execute();
        statement.close();
      } catch (SQLException ex) {
        throw new RuntimeException(ex.getMessage());
      }
    }

    atualizaEstoque(prodsList);
  }

  private void atualizaEstoque(List<Produto> prodsList) {
    ProdutoDAO dao = new ProdutoDAO();
    for (Produto produto : prodsList) {
      dao.updateQuantidade(
              produto,
              (dao.getQuantidade(produto) - produto.getQuantidade())
      );
    }
  }

  /**
   * Para pegar todas as compras feitas por um usuário.
   *
   * 1. Obtem todas as compras feitas pelo usuário.
   *
   * 2. Obtem os itens de cada compra.
   *
   * 3. Agrupa-os em uma venda.
   *
   * 4. Repete-se isso até retornar um Array com todas as compras.
   *
   * @param userEmail
   * @return
   */
  public List<Venda> getCompras(String userEmail) {
    List<Venda> compras = new ArrayList<>();

    String sql_venda = "SELECT venda_id,preco FROM vendas WHERE usuario_email=?";
    String sql_venda_itens = "SELECT produto_id, quantidade FROM venda_item WHERE venda_id=?";

    try {
      PreparedStatement ps = this.connection.prepareStatement(sql_venda);
      ps.setString(1, userEmail);
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        Venda venda = new Venda();
        venda.setVendaID(rs.getInt("venda_id"));
        venda.setPrecoTotal(rs.getDouble("preco"));

        PreparedStatement ps2 = this.connection.prepareStatement(sql_venda_itens);
        ps2.setInt(1, rs.getInt("venda_id"));
        ResultSet rs2 = ps2.executeQuery();
        while (rs2.next()) {
          Produto p = new Produto();
          p.setCodigo(rs2.getInt("produto_id"));
          p.setQuantidade(rs2.getInt("quantidade"));

          venda.addProduto(p);
        }

        compras.add(venda);

        rs2.close();
        ps2.close();
      }

      rs.close();
      ps.close();
    } catch (SQLException ex) {
      throw new RuntimeException(ex.getMessage());
    }

    return compras;
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
