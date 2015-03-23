/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ece356;

import bean.Patient;
import bean.Region;
import bean.User;
import composite.PatientResult;
import java.io.IOException;
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
@WebServlet(name = "ViewPatientSearchResultServlet", urlPatterns = {"/ViewPatientSearchResult"})
public class ViewPatientSearchResultServlet extends HttpServlet {


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
        String alias_keyword = request.getParameter("alias");
        String province = request.getParameter("province");
        String city = request.getParameter("city");
        Patient logged_in_patient = (Patient)request.getSession().getAttribute(
            "PatientSession"
        );
        
        Connection con;
        String url;
        try {
            con = DbConnectionUtil.getConnection();
            PreparedStatement call_statement = con.prepareCall(
                    "{call PatientSearch(?, ?, ?, ?)}"
            );
            call_statement.setString(1, alias_keyword);
            call_statement.setString(2, province);
            call_statement.setString(3, city);
            call_statement.setInt(4, logged_in_patient.getId());

            ResultSet result_set = call_statement.executeQuery();

            ArrayList<PatientResult> patient_info = new ArrayList<PatientResult>();

            while (result_set.next()) {
                Patient p = new Patient();
                p.setId(result_set.getInt("ID"));
                User u = new User();
                u.setAlias(result_set.getString("alias"));
                Region r = new Region();
                r.setCity(result_set.getString("city"));
                r.setProvince(result_set.getString("province"));
                PatientResult pr = new PatientResult();
                pr.setUser(u);
                pr.setPatient(p);
                pr.setRegion(r);
                pr.setNumReview(result_set.getInt("num_of_reviews"));
                pr.setTimeReview(result_set.getTimestamp("last_review_date"));
                pr.setStatus(
                        result_set.getInt("incoming_status"),
                        result_set.getInt("outgoing_status")
                );
                patient_info.add(pr);
            }

            con.close();
            request.setAttribute("patient_info", patient_info);
            url = "/patient_search_results.jsp";

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
