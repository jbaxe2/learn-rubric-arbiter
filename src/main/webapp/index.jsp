<!DOCTYPE html>

<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="_persistence.PersistenceManager" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bbNG" uri="/bbNG" %>

<bbNG:learningSystemPage title="Learn Rubric Arbiter" authentication="Y">

  <bbNG:pageHeader>
    <bbNG:pageTitleBar title="Learn Rubric Arbiter" />
  </bbNG:pageHeader>

  <%
    String selector = request.getParameter ("select");

    try {
      if (null == selector) {
        String formulate = request.getParameter ("formulate");

        if ("true".equals (formulate)) {
          %><%@ include file="formulation_handler.jsp" %><%
        } else {
          %><%@ include file="select_courses.jsp" %><%
        }
      } else {
        switch (selector) {
          case "formulation":
            %><%@ include file="select_formulation.jsp" %><%
            break;
          case "criteria":
            %><%@ include file="select_criteria.jsp" %><%
            break;
          case "rubrics":
            %><%@ include file="select_rubrics.jsp" %><%
            break;
          default:
            %><%@ include file="select_courses.jsp" %><%
        }
      }
    } catch (Exception e) {
      %><bbNG:error exception="<%= e %>" /><br><%
    } finally {
      PersistenceManager.getInstance().releaseConnection();
    }
  %>

</bbNG:learningSystemPage>
