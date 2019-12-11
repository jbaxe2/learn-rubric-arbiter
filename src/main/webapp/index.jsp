<!DOCTYPE html>

<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="_persistence.PersistenceManager" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bbNG" uri="/bbNG" %>

<%
  String selector = request.getParameter ("select");

  if ((null == selector) || selector.isEmpty()) {
    selector = "courses";
  }

  switch (selector) {
    case "criteria":
    case "rubrics":
    case "courses": break;
    default: selector = "courses";
  }
%>

<bbNG:learningSystemPage
    title="Learn Rubric Arbiter"
    authentication="Y"
    entitlement="course.control_panel.VIEW">

  <bbNG:pageHeader>
    <bbNG:pageTitleBar title="Learn Rubric Arbiter" />
  </bbNG:pageHeader>

  <%
    if ("rubrics".equals (selector)) {
      %><%@ include file="rubrics_selector.jsp" %><%
    } else if ("criteria".equals (selector)) {
      %><%@ include file="criteria_selector.jsp" %><%
    } else {
      %><%@ include file="courses_selector.jsp" %><%
    }
  %>

</bbNG:learningSystemPage>

<% PersistenceManager.getInstance().releaseConnection(); %>
