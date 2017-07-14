/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastro.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import utils.FabricaConexao;



/**
 *
 * @author lord
 */
@WebServlet("/servletAlteraUser")
public class servletAlteraUser extends HttpServlet {
    
    public static class Dado {

        public String nome;
        public String login;
        public String endereco;
        public String senha;
        public String cpf;
        public String email;
        public String telefone;

    }
    
    static String select = "SELECT * FROM usuario WHERE login=\"{0}\" AND email=\"{1}\"";
    static String updatee = "UPDATE usuario SET nome=\"{2}\",senha=\"{3}\",telefone=\"{4}\",cpf=\"{5}\",endereco=\"{6}\", "
            + "WHERE email=\"{0}\" AND usuario=\"{1}\"";
    static String updateee = "UPDATE usuario SET nome=\"?\",senha=\"?\",telefone=\"?\",cpf=\"?\",endereco=\"?\", "
            + "WHERE email=\"?\" AND login=\"?\"";
    static String update = "UPDATE usuario SET nome=?,senha=?,telefone=?,cpf=?,endereco=? WHERE email=? AND login=?";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession(false);
            
            String login = session.getAttribute("loginUsuario").toString();
            String email = session.getAttribute("emailUsuario").toString();
            try{
                String nomeNovo = request.getParameter("nomeNovo");
                String senhaNovo = request.getParameter("senhaNovo");
                String telefoneNovo = request.getParameter("telefoneNovo");
                String cpfNovo = request.getParameter("cpfNovo");
                String enderecoNovo = request.getParameter("enderecoNovo");
                if (!nomeNovo.equals("") && !senhaNovo.equals("") && !telefoneNovo.equals("") && !cpfNovo.equals("") && !enderecoNovo.equals("")){
                    try {
                        Connection con = new FabricaConexao().getConnection();
                        PreparedStatement ps = con.prepareStatement(update);

                        ps.setString(1, nomeNovo );
                        ps.setString(2, senhaNovo);
                        ps.setString(3, telefoneNovo);
                        ps.setString(4, cpfNovo);
                        ps.setString(5, enderecoNovo);
                        ps.setString(6, email);
                        ps.setString(7, login);
                        ps.executeUpdate();
                        con.close();
                    
                    } catch (Exception ex) {
                        Logger.getLogger(servletExcluiUser.class.getName()).log(
                                Level.SEVERE, null, ex);
                    }
                }
                
                response.sendRedirect("sobreUsuario.jsp");
            }
            catch (IOException e){
                
            }  
        }
    }
    
    public static Dado seleciona(String login, String email){
        Dado retorno = new Dado();

        try {
            //cria a conexão (endereço do bd, usuario, senha)
            Connection con = new FabricaConexao().getConnection();

            Statement stmt = con.createStatement();

            ResultSet result = stmt.executeQuery(MessageFormat.format(select, login, email));

            while (result.next()) {
                    retorno.nome = result.getString("nome");
                    retorno.login = result.getString("login");
                    retorno.endereco = result.getString("endereco");
                    retorno.senha = result.getString("senha");
                    retorno.cpf = result.getString("cpf");
                    retorno.email = result.getString("email");            
                    retorno.telefone = result.getString("telefone");  
            }

            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(servletAlteraUser.class.getName()).log(
                    Level.SEVERE, null, ex);
        }

        return retorno;
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
