/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ece356;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bean.User;
import java.util.ArrayList;
import util.DbConnectionUtil;
import java.sql.*;

/**
 *
 * @author Ajanthan Asogamoorth
 */
@WebServlet(name = "FindDoctorServlet", urlPatterns = {"/FindDoctorServlet"})
public class FindDoctorServlet extends HttpServlet {

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
        Statement stmt;
        
        
        try {
            con = DbConnectionUtil.getConnection();
            stmt = con.createStatement();

            ResultSet specializations = stmt.executeQuery("Select specialization from Specialization;");
            ArrayList<String> specialization_list = new ArrayList<String>();
            while (specializations.next()) {
                specialization_list.add(
                    specializations.getString("specialization")
                );
            }
            request.setAttribute("specializations", specialization_list);
            url = "/search_doctor_form.jsp";
            con.close();
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
