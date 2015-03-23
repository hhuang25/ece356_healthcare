/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ece356;

import bean.Doctor;
import bean.Patient;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bean.User;
import com.sun.org.apache.bcel.internal.generic.Type;
import composite.DoctorReview;
import java.util.ArrayList;
import util.DbConnectionUtil;
import java.sql.*;
import util.Factory;
import util.NumberUtil;

/**
 *
 * @author Ajanthan Asogamoorthy
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
        Patient logged_in_patient = (Patient)request.getSession().getAttribute(
                "PatientSession");
        String url;
        Connection con = null;
        try{
            String firstName = request.getParameter("firstname").trim();
            String middleName = request.getParameter("middlename").trim();
            String lastName = request.getParameter("lastname").trim();
            String gender = request.getParameter("gender");
            String city = request.getParameter("city").trim();
            String province = request.getParameter("province").trim();
            String streetNumber = request.getParameter("street_number").trim();
            String street = request.getParameter("street").trim();
            String postalCode = request.getParameter("postal_code").trim();
            String yearLicenseObtained = request.getParameter("year_license_obtained").trim();
            String specializationName = request.getParameter("specialization");
            String ratingThreshold = request.getParameter("rating_threshold").trim();
            boolean friendsReviewed = (request.getParameter("friend_reviewed") != null);
            String wordsInReview = request.getParameter("keyword").trim();
            
            con = DbConnectionUtil.getConnection();
            int pIndex = 2;
            try(PreparedStatement psmt = con.prepareCall(
                    "{call FlexibleDoctorSearch(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}")){
                
                psmt.setInt(1, logged_in_patient.getId());
                psmt.setString(pIndex++, firstName);
                psmt.setString(pIndex++, lastName);
                psmt.setString(pIndex++, middleName);
                psmt.setString(pIndex++, gender);
                psmt.setString(pIndex++, street);
                if(NumberUtil.isInteger(streetNumber)){
                    psmt.setInt(pIndex++, Integer.parseInt(streetNumber));
                }else{
                    psmt.setNull(pIndex++, Types.NULL);
                }
                psmt.setString(pIndex++, city);
                psmt.setString(pIndex++, province);
                psmt.setString(pIndex++, postalCode);
                if(NumberUtil.isInteger(yearLicenseObtained)){
                    psmt.setInt(pIndex++, Integer.parseInt(yearLicenseObtained));
                }else{
                    psmt.setNull(pIndex++, Types.NULL);
                }
                psmt.setString(pIndex++, specializationName);
                if(NumberUtil.isDouble(ratingThreshold)){
                    psmt.setDouble(pIndex++, Double.parseDouble(ratingThreshold));
                }else{
                    psmt.setNull(pIndex++, Types.NULL);
                }
                psmt.setBoolean(pIndex++, friendsReviewed);
                psmt.setString(pIndex++, wordsInReview);
                try(ResultSet rs = psmt.executeQuery()){
                    ArrayList<DoctorReview> doctorReviews = new ArrayList<DoctorReview>();
                    while (rs.next()) {
                        DoctorReview docRev = Factory.CreateDoctorReview(rs);
                        doctorReviews.add(docRev);
                    }
                    con.close();
                    request.setAttribute("doctor_reviews", doctorReviews);
                    url = "/search_doctor_results.jsp";
                }
            }
        } catch (Exception e) {
            request.setAttribute("exception", e);
            url = "/error.jsp";
        }finally{
            DbConnectionUtil.closeConnection(con);
        }
        getServletContext().getRequestDispatcher(url).forward(request, response);
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
