/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ece356;

import bean.Patient;
import bean.User;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.DbConnectionUtil;
import util.Factory;
import java.sql.*;

/**
 *
 * @author Ajanthan Asogamoorth
 */
@WebServlet(name = "AddFriendServlet", urlPatterns = {"/AddFriend"})
public class AddFriendServlet extends HttpServlet {


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
        getServletContext().getRequestDispatcher(
                "/patient_search_results"
        ).forward(request, response);
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
        String friend_request = request.getParameter("friend_request");
        Patient logged_in_patient = (Patient)request.getSession().getAttribute(
            "PatientSession"
        );
        if (friend_request == null) { response.sendRedirect("/HealthcareApplication"); }
        else {
            try {
                Connection con = DbConnectionUtil.getConnection();
                PreparedStatement call_statement;
                if (friend_request.equals("Send Friend Request")) {
                    call_statement = con.prepareCall(
                        "{call RequestFriend(?, ?)}"
                    );
                    call_statement.setInt(1, logged_in_patient.getId());
                    call_statement.setInt(
                            2,
                            Integer.parseInt(
                                    request.getParameter("patient_id")
                            )
                    );
                    call_statement.executeQuery();
                    
                } else if (friend_request.equals("Confirm Friend Request")) {
                    call_statement = con.prepareCall(
                        "{call ConfirmFriendsPatient(?, ?)}"
                    );
                    call_statement.setInt(1, logged_in_patient.getId());
                    call_statement.setInt(
                            2,
                            Integer.parseInt(
                                    request.getParameter("patient_id")
                            )
                    );
                    call_statement.executeQuery();
                }
                con.close();
            } catch (Exception e) {
                request.setAttribute("exception", e);
            }
            response.sendRedirect(request.getHeader("Referer"));
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
