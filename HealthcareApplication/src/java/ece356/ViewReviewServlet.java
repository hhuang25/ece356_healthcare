/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ece356;

import bean.Review;
import composite.ReviewInfo;
import java.sql.*;
import util.DbConnectionUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ajanthan Asogamoorth
 */
@WebServlet(name = "ViewReviewServlet", urlPatterns = {"/ViewReview"})
public class ViewReviewServlet extends HttpServlet {

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
        String review_id = request.getParameter("review_id");
        int id = 0;
        if (review_id != null) {
            id = -1;
            try {
                id = Integer.parseInt(review_id);
            }
            catch (Exception e) {
                throw(e);
            }
            if (id == -1) {
                getServletContext().getRequestDispatcher("/404Page.jsp").forward(request, response);
                return;
            }
        }
        String url;
        Connection con;
        int next_id = 0;
        int previous_id = 0;
        try {
                con = DbConnectionUtil.getConnection();
                PreparedStatement call_statement = con.prepareCall(
                        "{call GetReview(?)}"
                );
                call_statement.setInt(1, id);
                
                ResultSet result_set = call_statement.executeQuery();
                if (result_set.next()) {
                    ReviewInfo review_info = new ReviewInfo();
                    Review r = new Review();
                    r.setRating(result_set.getFloat("rating"));
                    r.setReview(result_set.getString("review"));
                    r.setReviewDate(result_set.getTimestamp("review_date"));
                    r.setDoctorId(result_set.getInt("doctor_id"));
                    r.setId(result_set.getInt("id"));
                    review_info.setReview(r);
                    review_info.setDoctorName(result_set.getString("doctor_name"));

                    call_statement = con.prepareCall(
                            "{call NextReview(?, ?, ?)}"
                    );
                    call_statement.setInt(1, r.getId());
                    call_statement.setInt(2, r.getDoctorId());
                    call_statement.setInt(3, 1);
                    result_set = call_statement.executeQuery();

                    if (result_set.isBeforeFirst()) {
                        result_set.next();
                        next_id = result_set.getInt("id");
                    }
                    call_statement.setInt(3, 0);
                    result_set = call_statement.executeQuery();
                    if (result_set.isBeforeFirst()) {
                        result_set.next();
                        previous_id = result_set.getInt("id");
                    }
                    request.setAttribute("next_id", next_id);
                    request.setAttribute("previous_id", previous_id);
                    con.close();
                    request.setAttribute("review_info", review_info);
                    url = "/view_review.jsp";
                }
                else {
                    url = "/404Page.jsp";
                }
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
