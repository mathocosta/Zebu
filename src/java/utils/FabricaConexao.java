/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Cada classe que mexe com o banco de dados deve chamar a conexão aqui.
 *
 * @author Matheus
 */
public class FabricaConexao {

  private final String dbURL = "jdbc:mysql://localhost/zebu2";
  private final String dbUser = "matho";
  private final String dbPass = "bin11";

  public Connection getConnection() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      return DriverManager
              .getConnection(dbURL, dbUser, dbPass);
    } catch (ClassNotFoundException | SQLException ex) {
      throw new RuntimeException(ex.getMessage());
    }
  }

}
