<!DOCTYPE html>

<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<%@ taglib prefix="bbNG" uri="/bbNG" %>

<bbNG:learningSystemPage
    title="Learn Rubric Arbiter"
    authentication="Y"
    entitlement="course.control_panel.VIEW">
  <bbNG:pageHeader>
    <bbNG:pageTitleBar title="Learn Rubric Arbiter" />
  </bbNG:pageHeader>

  <bbNG:step title="Course(s) Selection">
    <%@include file="courses_selector.jsp"%>
  </bbNG:step>

</bbNG:learningSystemPage>
