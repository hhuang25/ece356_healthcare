/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ece356;

import bean.Doctor;
import composite.ViewProfileResult;
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
import util.DbConnectionUtil;
import util.Factory;

/**
 *
 * @author Tommy
 */
@WebServlet(name = "WriteReviewServlet", urlPatterns = {"/WriteReview"})
public class WriteReviewServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        String url = "";

        String docId = request.getParameter("docId");
        if (docId != null) {
            int id = -1;
            try {
                id = Integer.parseInt(docId);
            }
            catch (Exception e) {
                throw(e);
            }
            
            if (id == -1) {
                getServletContext().getRequestDispatcher("/main.jsp")
                                   .forward(request, response);
                return;
            }
            
            Connection con = null;
            try {
                con = DbConnectionUtil.getConnection();
                String userString = "SELECT * FROM Doctor WHERE id = ?;"; 
                try (PreparedStatement ps = con.prepareStatement(userString)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        Doctor doctor = Factory.CreateDoctor(rs);
                        request.setAttribute("doctor", doctor);
                        url = "/writereview.jsp";
                    }
                    else {
                        url = "/404Page.jsp";
                    }
                }
                
            } catch (NamingException | SQLException | RuntimeException ex) {
                request.setAttribute("exception", ex);
                url = "/error.jsp";
            }
            finally {
                DbConnectionUtil.closeConnection(con);
            }
        }
        else {
            url = "/main.jsp";
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
        response.setContentType("text/html;charset=UTF-8");
        String url = "/writereview.jsp";
        String errorMessage = "";
        Boolean success = false;

        String rating = request.getParameter("rating").trim();
        String review = request.getParameter("review").trim();
        
        request.setAttribute("sentResponse", true);
        
        if (review.length() > 1000) {
            errorMessage = "Your review exceeds 1000 characters.";
            request.setAttribute("errorMessage", errorMessage);
        }
        else if (review.length() == 0) {
            errorMessage = "Your review cannot be empty.";
            request.setAttribute("errorMessage", errorMessage);
        }
        
        if (errorMessage.length() == 0) {
            Connection con = null;
            PreparedStatement cs = null;
            
            double ratingValue = Double.parseDouble(rating);
            int doctorId = Integer.parseInt(request.getParameter("doctorId"));
            int patientId = Integer.parseInt(request.getParameter("patientId"));
            
            try {
                con = DbConnectionUtil.getConnection();
                cs = con.prepareCall("{call AddReview(?, ?, ?, ?)}");
                cs.setInt(1, patientId);
                cs.setInt(2, doctorId);
                cs.setDouble(3, ratingValue);
                cs.setString(4, review);
                cs.executeQuery();
                url = "/Profile?docId=" + doctorId;
                success = true;
            } catch (SQLException | NamingException ex) {
                request.setAttribute("exception", ex);
                url = "/error.jsp";
            }
            finally {
                if (cs != null) {
                    try {
                        cs.close();
                    } catch (SQLException ex) {
                        request.setAttribute("exception", ex);
                        url = "/error.jsp";
                    }
                }
                
                DbConnectionUtil.closeConnection(con);
            }
        }
        
        if (success) {
            response.sendRedirect(request.getContextPath() + url);
        }
        else {
            getServletContext().getRequestDispatcher(url).forward(request, response);
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
