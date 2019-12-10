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

  <bbNG:dataCollection>
    <bbNG:step
        id="courses-selection-step"
        title="Courses Selection"
        enableExpandCollapse="true"
        instructions="Please select one or more courses for which
            you would like to review the rubrics.">
      <%@ include file="courses_selector.jsp" %>
    </bbNG:step>

    <bbNG:step
        id="rubrics-selection-step"
        title="Rubrics Selection"
        enableExpandCollapse="true"
        instructions="Please select one or more rubrics from the above
            selected courses.">
      <%@ include file="rubrics_selector.jsp" %>
    </bbNG:step>

    <bbNG:stepSubmit
        title="Review Selected Rubric Information"
        instructions="Submit to review the selected rubric information." />
  </bbNG:dataCollection>
</bbNG:learningSystemPage>

<% PersistenceManager.getInstance().releaseConnection(); %>
