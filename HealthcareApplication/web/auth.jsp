<% 
    if (session.getAttribute("UserSession") == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
%>

<% String _firstName = ""; %>
<% String _middleName = ""; %>
<% String _lastName = ""; %>
<% String _gender = ""; %>
<% int _yearsLicensed = -1; %>
<% int _regionId = -1; %>
<% int _docId = -1; %>
<% int _patientId = -1; %>
<% bean.Doctor doctor = (bean.Doctor)session.getAttribute("DoctorSession");
   if (doctor != null) {
       _docId = doctor.getId();
       _firstName = doctor.getFirstName();
       _middleName = doctor.getMiddleName();
       _lastName = doctor.getLastName();
       _gender = doctor.getGender();
       _yearsLicensed = doctor.getYearLicenseObtained();
   }
   else {
        bean.Patient patient = (bean.Patient)session.getAttribute("PatientSession");
        if (patient != null) {
            _patientId = patient.getId();
            _firstName = patient.getFirstName();
            _middleName = patient.getMiddleName();
            _lastName = patient.getLastName();
            _gender = patient.getGender();
            _regionId = patient.getRegionId();
        }
   }
%>
