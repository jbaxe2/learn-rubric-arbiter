<%@ page import="
  _action.*,
  rubric.*" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bbNG" uri="/bbNG" %>

<bbNG:includedPage authentication="Y" entitlement="course.control_panel.VIEW">

<%
  String[] selectedCourses = request.getParameterValues ("simple-courses");
  String[] selectedRubrics = request.getParameterValues ("course-rubrics");

  if ((null == selectedCourses) || (0 == selectedCourses.length)) {
    %><p>No courses were selected to review rubrics for.</p><%
  } else if ((null == selectedRubrics) || (0 == selectedRubrics.length)) {
    %><p>No rubrics were selected to review criteria for.</p><%
  } else {
    Map<SimpleCourse, List<Rubric>> coursesRubrics = new HashMap<>();
    Map<Rubric, List<RubricColumn>> rubricsCriteria = new HashMap<>();

    try {
      CoursesRetrieverAction coursesRetrieverAction =
        new CoursesRetrieverAction (request, "rubric_evaluator");

      coursesRetrieverAction.perform();

      List<SimpleCourse> courses =
        coursesRetrieverAction.filterByIds (selectedCourses);

      RubricsLoaderAction rubricsLoaderAction = new RubricsLoaderAction (courses);
      rubricsLoaderAction.perform();

      coursesRubrics = rubricsLoaderAction.filterByIds (selectedRubrics);
    } catch (Exception e) {
      %><bbNG:error exception="<%= e %>" /><br><%
    }

    pageContext.setAttribute ("coursesRubrics", coursesRubrics);
    pageContext.setAttribute ("rubricsCriteria", rubricsCriteria);

    %>
      <c:choose>
        <c:when test="${0 == coursesRubrics.size()}">
          <p>No rubrics were available to select the criteria from.</p>
        </c:when>

        <c:otherwise>
          <bbNG:form method="POST" action="?select=">
            <bbNG:dataCollection>
              <c:forEach var="courseRubrics" items="<%= coursesRubrics %>">
                <p>Here will be course rubrics.</p>
              </c:forEach>
            </bbNG:dataCollection>
          </bbNG:form>
        </c:otherwise>
      </c:choose>
    <%
  }
%>

</bbNG:includedPage>
