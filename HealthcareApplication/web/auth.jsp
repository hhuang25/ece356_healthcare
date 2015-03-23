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
<% bean.Doctor _doctor = (bean.Doctor)session.getAttribute("DoctorSession");
   if (_doctor != null) {
       _docId = _doctor.getId();
       _firstName = _doctor.getFirstName();
       _middleName = _doctor.getMiddleName();
       _lastName = _doctor.getLastName();
       _gender = _doctor.getGender();
       _yearsLicensed = _doctor.getYearLicenseObtained();
   }
   else {
        bean.Patient _patient = (bean.Patient)session.getAttribute("PatientSession");
        if (_patient != null) {
            _patientId = _patient.getId();
            _firstName = _patient.getFirstName();
            _middleName = _patient.getMiddleName();
            _lastName = _patient.getLastName();
            _gender = _patient.getGender();
            _regionId = _patient.getRegionId();
        }
   }
%>
<% String errorMessage = (String)request.getAttribute("errorMessage"); %>
