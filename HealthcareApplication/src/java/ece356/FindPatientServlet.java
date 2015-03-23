/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ece356;
import bean.*;
import java.io.IOException;
import composite.PatientResult;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.DbConnectionUtil;
import java.sql.*;

/**
 *
 * @author Ajanthan Asogamoorth
 */
@WebServlet(name = "FindPatientServlet", urlPatterns = {"/FindPatient"})
public class FindPatientServlet extends HttpServlet {

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
        String url = null;
        Connection con = null;
        
        try {
            con = DbConnectionUtil.getConnection();
            try(Statement stmt = con.createStatement()){
                ArrayList<String> cities = new ArrayList<String>();

                // get cities
                try(ResultSet rs = stmt.executeQuery("Select city from Region;")){
                    while (rs.next()) {
                        cities.add(
                            rs.getString("city")
                        );
                    request.setAttribute("cities", cities);
                    url = "/search_patient_form.jsp";
                    }                    
                }
            }
        } catch (Exception e) {
             request.setAttribute("exception", e);
            url = "/error.jsp";
        } finally {
            DbConnectionUtil.closeConnection(con);
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
