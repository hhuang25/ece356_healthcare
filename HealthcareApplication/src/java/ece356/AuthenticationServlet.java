/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ece356;

import bean.Doctor;
import bean.Patient;
import bean.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.DbConnectionUtil;
import util.Factory;

/**
 *
 * @author Tommy
 */
@WebServlet(name = "AuthenticationServlet", urlPatterns = {"/Authentication"})
public class AuthenticationServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        response.setContentType("text/html;charset=UTF-8");

        String url = "";
        Connection con = null;        
        PreparedStatement authenticate = null;
        PreparedStatement userType = null;

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try
        {
            con = DbConnectionUtil.getConnection();
            String authenticateString = "Select EXISTS (SELECT 1 FROM User WHERE alias= ? AND password = SHA2(CONCAT(salt, ?), 256) LIMIT 1);"; 
            authenticate = con.prepareStatement(authenticateString);
            authenticate.setString(1, username);
            authenticate.setString(2, password);
            
            request.setAttribute("username", username);
            
            ResultSet rs = authenticate.executeQuery();
            rs.next();
            if (rs.getInt(1) == 0) {
                String message = "User not found or incorrect password.";
                request.setAttribute("errorMessage", message);
                url = "/login.jsp";
            }
            else {
                HttpSession session = request.getSession(true);
                authenticateString = "SELECT * FROM User WHERE alias = ?";
                authenticate = con.prepareStatement(authenticateString);
                authenticate.setString(1, username);
                rs = authenticate.executeQuery();
                rs.next();

                User user = Factory.CreateUser(rs);
                session.setAttribute("UserSession", user);
                
                int user_id = rs.getInt("id");
                String userTypeString = "SELECT * FROM Doctor WHERE user_id = ?";
                userType = con.prepareStatement(userTypeString);
                userType.setInt(1, user_id);
                rs = userType.executeQuery();

                if (!rs.next()) {
                    userTypeString = "SELECT * FROM Patient WHERE user_id = ?";
                    userType = con.prepareStatement(userTypeString);
                    userType.setInt(1, user_id);
                    rs = userType.executeQuery();

                    if (rs.next()) {
                        Patient patient = Factory.CreatePatient(rs);
                        session.setAttribute("PatientSession", patient);  
                    }
                }
                else {
                    Doctor doctor = Factory.CreateDoctor(rs);
                    session.setAttribute("DoctorSession", doctor);
                }
                
                url = "/main.jsp";
            }
        }
        catch (SQLException | NamingException e)
        {
            System.out.println(e);
            String message = "Oops! Something went wrong.";
            request.setAttribute("errorMessage", message);
            url = "/login.jsp";
        }
        finally {
            if (authenticate != null) {
                authenticate.close();
            }
                        
            if (userType != null) {
                userType.close();
            }

            if (con != null) {
                con.close();
            }

            getServletContext().getRequestDispatcher(url)
                    .forward(request, response);
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
        try {
            processRequest(request, response);
        }
        catch(SQLException e) {
            System.out.println(e);
        }
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
        try {
            processRequest(request, response);
        }
        catch(SQLException e) {
            System.out.println(e);
        }
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
