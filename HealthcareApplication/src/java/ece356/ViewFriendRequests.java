/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ece356;

import java.io.IOException;
import java.io.PrintWriter;
import bean.User;
import bean.Patient;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import util.Factory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.DbConnectionUtil;
import java.sql.PreparedStatement;

/**
 *
 * @author Ajanthan Asogamoorth
 */
@WebServlet(name = "ViewFriendRequests", urlPatterns = {"/ViewFriendRequests"})
public class ViewFriendRequests extends HttpServlet {

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
        String url;
        Connection con;  
        Patient logged_in_patient = (Patient)request.getSession().getAttribute(
                "PatientSession"
        );
        try {
            con = DbConnectionUtil.getConnection();
            PreparedStatement call_statement = con.prepareCall(
                    "{call GetFriendRequestUsers(?)}"
            );
            call_statement.setInt(1, logged_in_patient.getId());
            ResultSet result_set = call_statement.executeQuery();
            
            ArrayList<User> users_request_friend = new ArrayList<User>();
            while (result_set.next()) {
                User u = Factory.CreateUser(result_set);
                users_request_friend.add(u);
            }
            con.close();
            request.setAttribute("users_request_friend", users_request_friend);
            url = "/view_friend_requests.jsp";
            
        } catch (Exception e) {
            request.setAttribute("exception", e);
            url = "/error.jsp";
        }
        getServletContext().getRequestDispatcher(url).forward(request, response);
    
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
        Patient logged_in_patient = (Patient)request.getSession().getAttribute(
                "PatientSession"
        );
        String url;
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        try {
            Connection con = DbConnectionUtil.getConnection();
            PreparedStatement call_statement = con.prepareCall(
                    "{call ConfirmFriends(?, ?)}"
            );
            call_statement.setInt(1, logged_in_patient.getId());
            call_statement.setInt(2, user_id);
            call_statement.executeQuery();
                     
            con.close();
            url = "/view_friend_requests.jsp";
            
        } catch (Exception e) {
            request.setAttribute("exception", e);
            url = "/error.jsp";
        }
        response.sendRedirect(url);
        
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
