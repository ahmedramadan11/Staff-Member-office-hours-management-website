/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

/**
 *
 * @author LENOVO
 */
@WebServlet(urlPatterns = {"/deletenotfiaction"})
public class deletenotfiaction extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/internetproject";
                String user = "root";
                String password = "root";
                Connection Con = null;
                Statement Stmt = null;
                ResultSet RS = null;
                Con = DriverManager.getConnection(url, user, password);
                Stmt = Con.createStatement();
                String line = null;
                String check = request.getParameter("check");
                if (check.equals("cancelall")) {
                    HttpSession session = request.getSession(true);
                    String userName = session.getAttribute("session_username").toString();
                    line = "DELETE  FROM notification WHERE userName=" + "'" + userName + "'";
                    int Rows = Stmt.executeUpdate(line);
                    out.print("<script type=text/javascript>"
                            + "alert('Deleted successfully');"
                            + "location='notification.jsp';"
                            + "</script>");
                } else {
                    String not_id = request.getParameter("not_id");

                    line = "DELETE  FROM notification WHERE id=" + "'" + not_id + "'";
                    int Rows = Stmt.executeUpdate(line);
                    out.print("<script type=text/javascript>"
                            + "alert('Deleted successfully');"
                            + "location='notification.jsp';"
                            + "</script>");
                }

                Stmt.close();
                Con.close();

            } catch (Exception ex) {
                ex.printStackTrace();

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
