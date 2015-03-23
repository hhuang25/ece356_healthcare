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
import util.StringUtil;

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
        getServletContext().getRequestDispatcher("/search_patient_form.jsp").forward(request, response);
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
<<<<<<< Updated upstream
=======
            String alias_keyword = request.getParameter("alias");
            String province = request.getParameter("province");
            String city = request.getParameter("city");
            Patient logged_in_patient = (Patient)request.getSession().getAttribute(
                "PatientSession"
            );
            String url;
            boolean invalidInput = false;
            if(!StringUtil.IsValidString(30, alias_keyword, province, city)){
                url = "/search_patient_form.jsp";
                String message = "Search value(s) incorrect type or exceeded maximum length.";
                request.setAttribute("errorMessage", message);
                invalidInput = true;
            }else{
                request.setAttribute("errorMessage", "");
                Connection con = null;
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
                    request.setAttribute("patient_info", patient_info);
                    url = "/patient_search_results.jsp";

                } catch (Exception e) {
                    request.setAttribute("exception", e);
                    url = "/error.jsp";
                } finally{
                    DbConnectionUtil.closeConnection(con);
                }
            }
            if(invalidInput){
                doGet(request, response);
            }else{
                getServletContext().getRequestDispatcher(url).forward(request, response);
            }
>>>>>>> Stashed changes
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
