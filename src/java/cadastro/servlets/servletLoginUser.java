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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.FabricaConexao;

/**
 *
 * @author lord
 */
@WebServlet("/servletLoginUser")
public class servletLoginUser extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    String select = "SELECT * FROM usuario";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String login = request.getParameter("login");
            String senha = request.getParameter("senha");
            
            try {
                //cria a conexão (endereço do bd, usuario, senha)
                Connection con = new FabricaConexao().getConnection();

                Statement stmt = con.createStatement();

                ResultSet result = stmt.executeQuery(select);
                
                int contador = 1;
                
                String nome = "";
                String email = "";
                String endereco = "";
                String cpf = "";
                
                while (result.next()) {
                    
                    if (result.getString("login").equals(login)) {
                        if (result.getString("senha").equals(senha)){
                            login = result.getString("login");
                            nome = result.getString("nome");
                            email = result.getString("email");
                            cpf = result.getString("cpf");
                            endereco = result.getString("endereco");
                            contador = 0;
                            break;
                        } else
                            contador++;
                    } else
                            contador++;
                }
                
                if (contador == 0){
                    HttpSession session = request.getSession(true);
                    session.setAttribute("loginUsuario", login);
                    session.setAttribute("nomeUsuario", nome);
                    session.setAttribute("emailUsuario", email);
                    session.setAttribute("enderecoUsuario", endereco);
                    session.setAttribute("cpfUsuario", cpf);
                    response.sendRedirect("sobreUsuario.jsp");
                }
                else
                    out.println("oh shit niBBa");

                con.close();

            } 
            catch (Exception ex) {
                Logger.getLogger(servletLoginUser.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
            
            
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
