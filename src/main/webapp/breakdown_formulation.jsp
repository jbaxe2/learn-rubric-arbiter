<%@ page import="formulation.Resultable" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bbNG" uri="/bbNG" %>

<bbNG:includedPage authentication="Y" entitlement="course.control_panel.VIEW">

<%
  Resultable resultable = (Resultable)pageContext.getAttribute ("resultable");
%>

  <p>Got here with a resultable.</p>
</bbNG:includedPage>
