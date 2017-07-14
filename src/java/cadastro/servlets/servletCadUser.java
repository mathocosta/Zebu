/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastro.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.FabricaConexao;

/**
 *
 * @author lord
 */
@WebServlet("/servletCadUser")
public class servletCadUser extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    String table = "CREATE TABLE usuario (email varchar(255) not null, "
            + "nome varchar(255) not null,"
            + "login varchar(255) not null,"
            + "senha varchar(255) not null,"
            + "telefone varchar(255) not null,"
            + "cpf varchar(255) not null,"
            + "endereco varchar(255) not null,"
            + "primary key (email))";
    String insert = "INSERT INTO usuario (email, nome, login, senha, telefone, cpf, endereco) VALUES (?, ?, ?, ?, ?, ?, ?)";


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String email = request.getParameter("email");
            String nome = request.getParameter("nome");
            String login = request.getParameter("login");
            String senha = request.getParameter("senha");
            String telefone = request.getParameter("telefone");
            String cpf = request.getParameter("cpf");
            String endereco = request.getParameter("endereco");
            
            int resultado = criaBD();
            
            if (resultado != 0)
                out.println("<p>deu ruim");
            else
                out.println("<p>deu bom");
            
            int eae = insere(email, nome, login, senha, telefone, cpf, endereco);

            if (eae != 0)
                out.println("<p>deu ruim inserir");
            else
                out.println("<p>deu bom inserir");
            
            response.sendRedirect("index.jsp");
        }
    }

    public int criaBD() {
        try {
            Connection conn2 = new FabricaConexao().getConnection();
            PreparedStatement stmt2 = conn2.prepareStatement(table);
            stmt2.execute();
            
            return 0;
            
        } catch (SQLException sqlExceptionT) {
            return 1;
        }
    }
    
    public int insere(String email, String nome, String login, String senha, String telefone, String cpf, String endereco) {
        
        try {

            Connection con = new FabricaConexao().getConnection();

            PreparedStatement ps = con.prepareStatement(insert);
            ps.setString(1, email);
            ps.setString(2, nome);
            ps.setString(3, login);
            ps.setString(4, senha);
            ps.setString(5, telefone);
            ps.setString(6, cpf);
            ps.setString(7, endereco);

            ps.executeUpdate();
            ps.clearParameters();

            con.close();
            
            return 0;

        } catch (Exception ex) {
            Logger.getLogger(servletCadUser.class.getName()).log(
                    Level.SEVERE, null, ex);
            return 1;
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
