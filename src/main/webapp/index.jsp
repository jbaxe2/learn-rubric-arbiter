<!DOCTYPE html>

<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="_persistence.PersistenceManager" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bbNG" uri="/bbNG" %>

<bbNG:learningSystemPage
    title="Learn Rubric Arbiter"
    authentication="Y"
    entitlement="course.control_panel.VIEW">

  <bbNG:pageHeader>
    <bbNG:pageTitleBar title="Learn Rubric Arbiter" />
  </bbNG:pageHeader>

  <%
    String selector = request.getParameter ("select");

    if (null == selector) {
      selector = "courses";
    }

    try {
      switch (selector) {
        case "criteria":
          %><%@ include file="criteria_selector.jsp" %><%
          break;
        case "rubrics":
          %><%@ include file="rubrics_selector.jsp" %><%
          break;
        default:
          %><%@ include file="courses_selector.jsp" %><%
      }
    } catch (Exception e) {
      %><bbNG:error exception="<%= e %>" /><br><%
    }
  %>

</bbNG:learningSystemPage>

<% PersistenceManager.getInstance().releaseConnection(); %>
