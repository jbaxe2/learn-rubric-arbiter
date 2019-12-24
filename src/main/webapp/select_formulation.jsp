<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bbNG" uri="/bbNG" %>

<bbNG:includedPage authentication="Y">

<%
  String[] selectedCourses = request.getParameterValues ("simple-courses");
  String[] selectedRubrics = request.getParameterValues ("course-rubrics");
  String[] selectedCriteria = request.getParameterValues ("rubric-criteria");

  if ((null == selectedCourses) || (0 == selectedCourses.length)) {
    %><p>No courses were selected for which to review rubrics.</p><%
  } else if ((null == selectedRubrics) || (0 == selectedRubrics.length)) {
    %><p>No rubrics were selected for which to review criteria.</p><%
  } else if ((null == selectedCriteria) || (0 == selectedCriteria.length)) {
    %><p>No criteria were selected for which to review formulations.</p><%
  } else {
    %>
      <h3 style="margin-left: 25px;">
        Please select one option to use in formulating the previously selected
        rubric criteria.
      </h3>

      <bbNG:form method="POST" action="?formulate=true">
        <bbNG:dataCollection>
          <bbNG:step
              id="formulation-selector"
              title="Formulation Selection">
            <bbNG:dataElement>
              <bbNG:radioElement
                  id="criteria-formulation-breakdown"
                  name="criteria-formulation"
                  value="breakdown"
                  optionLabel="Breakdown of Rubric Criteria"
                  isVertical="true" />
            </bbNG:dataElement>
          </bbNG:step>

          <bbNG:stepSubmit
             title="Formulate the Course Rubric Criteria"
             instructions="Submit to formulate the previously selected criteria
                 for the course rubrics."
             cancelUrl="?select=courses">

            <bbNG:stepSubmitButton label="Formulate Course Rubric Criteria" />
          </bbNG:stepSubmit>
        </bbNG:dataCollection>

        <c:forEach var="selectedCourse" items="<%= selectedCourses %>">
          <bbNG:hiddenElement name="simple-courses" value="${selectedCourse}" />
        </c:forEach>

        <c:forEach var="courseRubric" items="<%= selectedRubrics %>">
          <bbNG:hiddenElement name="course-rubrics" value="${courseRubric}" />
        </c:forEach>

        <c:forEach var="rubricCriteria" items="<%= selectedCriteria %>">
          <bbNG:hiddenElement name="rubric-criteria" value="${rubricCriteria}" />
        </c:forEach>
      </bbNG:form>
    <%
  }
%>

</bbNG:includedPage>
