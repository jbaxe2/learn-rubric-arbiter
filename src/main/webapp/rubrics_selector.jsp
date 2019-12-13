<%@ page import="
    java.util.HashMap,
    java.util.List,
    java.util.Map,
    _action.CoursesRetrieverAction,
    _action.RubricsLoaderAction,
    rubric.Rubric" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bbNG" uri="/bbNG" %>

<bbNG:includedPage authentication="Y" entitlement="course.control_panel.VIEW">

<%
  String[] selectedCourses = request.getParameterValues ("simple-courses");

  if ((null == selectedCourses) || (0 == selectedCourses.length)) {
    %><p>No courses were selected to review rubrics for.</p><%
  } else {
    Map<SimpleCourse, List<Rubric>> coursesRubrics = new HashMap<>();

    try {
      CoursesRetrieverAction coursesRetrieverAction =
        new CoursesRetrieverAction (request, "rubric_evaluator");

      coursesRetrieverAction.perform();

      List<SimpleCourse> courses =
        coursesRetrieverAction.filterByIds (selectedCourses);

      RubricsLoaderAction rubricsLoaderAction = new RubricsLoaderAction (courses);
      rubricsLoaderAction.perform();

      coursesRubrics = rubricsLoaderAction.getCoursesRubrics();
    } catch (Exception e) {
      %><bbNG:error exception="<%= e %>" /><br><%
    }

    pageContext.setAttribute ("coursesRubrics", coursesRubrics);

    %>
      <c:choose>
        <c:when test="${0 == coursesRubrics.size()}">
          <p>No rubrics are available to select from.</p>
        </c:when>

        <c:otherwise>
          <bbNG:form method="POST" action="?select=criteria">
            <bbNG:dataCollection>
              <c:forEach var="courseRubrics" items="<%= coursesRubrics %>">
                <c:choose>
                  <c:when test="${0 == courseRubrics.value.size()}">
                    <p>
                      No course rubrics available for
                      ${courseRubrics.key.batchUid}.
                    </p>
                  </c:when>

                  <c:otherwise>
                    <c:forEach var="rubrics" items="${courseRubrics.value}">
                      <bbNG:step
                          id="course-rubrics-${courseRubrics.key.primaryKey}"
                          title="Rubrics for: ${courseRubrics.key.name}
                              (${courseRubrics.key.batchUid})"
                          enableExpandCollapse="true">
                        <bbNG:dataElement>
                          <bbNG:checkboxElement
                              id="rubrics-for-${courseRubrics.key.primaryKey}"
                              value="${rubrics.primaryKey}"
                              name="course-rubrics"
                              optionLabel="${rubrics.title}"/>
                        </bbNG:dataElement>
                      </bbNG:step>
                    </c:forEach>
                  </c:otherwise>
                </c:choose>
              </c:forEach>

              <bbNG:stepSubmit
                  title="Select Criteria for Rubrics"
                  instructions="Submit to select criteria from the above selected
                      rubrics.  Cancel to return to courses selection."
                  cancelUrl="?select=courses">
                <bbNG:stepSubmitButton label="Select Criteria for Rubrics" />
              </bbNG:stepSubmit>
            </bbNG:dataCollection>

            <c:forEach var="selectedCourse" items="<%= selectedCourses %>">
              <bbNG:hiddenElement name="simple-courses" value="${selectedCourse}" />
            </c:forEach>
          </bbNG:form>
        </c:otherwise>
      </c:choose>
    <%
  }
%>

</bbNG:includedPage>
