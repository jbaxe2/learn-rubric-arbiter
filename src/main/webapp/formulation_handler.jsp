<%@ page import="_factory.*" %>
<%@ page import="formulation.*" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bbNG" uri="/bbNG" %>

<bbNG:includedPage authentication="Y" entitlement="course.control_panel.VIEW">
  <%
    String[] selectedCourses = request.getParameterValues ("simple-courses");
    String[] selectedRubrics = request.getParameterValues ("course-rubrics");
    String[] selectedCriteria = request.getParameterValues ("rubric-criteria");

    String selectedFormulation = request.getParameter ("criteria-formulation");

    if ((null == selectedCourses) || (0 == selectedCourses.length)) {
      %><p>No courses were selected for which to review rubrics.</p><%
    } else if ((null == selectedRubrics) || (0 == selectedRubrics.length)) {
      %><p>No rubrics were selected for which to review criteria.</p><%
    } else if ((null == selectedCriteria) || (0 == selectedCriteria.length)) {
      %><p>No criteria were selected for which to review formulations.</p><%
    } else if (null == selectedFormulation) {
      %><p>No formulation was selected for the course rubric criteria.</p><%
    } else {
      try {
        Formulatable formulatable = FormulationFactory.createFormulatable (
          selectedFormulation, request, "rubric_evaluator",
          selectedCourses, selectedRubrics, selectedCriteria
        );

        if (null != formulatable) {
          formulatable.formulate();
        }
      } catch (Exception e) {
        %><bbNG:error exception="<%= e %>" /><br><%
      }
    }
  %>

  <p>Here will be the results of formulating course rubric criteria.</p><br>

  <p>Start over: <a href="?select=courses">select courses</a>.</p>
</bbNG:includedPage>
