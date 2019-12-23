<%@ page import="
  java.util.HashMap,
  action.CoursesRetrieverAction,
  action.RubricsLoaderAction,
  action.RubricRowsLoaderAction,
  rubric.Rubric,
  rubric.RubricRow" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bbNG" uri="/bbNG" %>

<bbNG:includedPage authentication="Y">

<%
  String[] selectedCourses = request.getParameterValues ("simple-courses");
  String[] selectedRubrics = request.getParameterValues ("course-rubrics");

  if ((null == selectedCourses) || (0 == selectedCourses.length)) {
    %><p>No courses were selected for which to review rubrics.</p><%
  } else if ((null == selectedRubrics) || (0 == selectedRubrics.length)) {
    %><p>No rubrics were selected for which to review criteria.</p><%
  } else {
    Map<SimpleCourse, List<Rubric>> coursesRubrics = new HashMap<>();
    Map<Rubric, List<RubricRow>> rubricsCriteria = new HashMap<>();

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

    %>
      <c:choose>
        <c:when test="${0 == coursesRubrics.size()}">
          <p>No rubrics were available to select the criteria from.</p>
        </c:when>

        <c:otherwise>
          <h3 style="margin-left: 25px;">
            Please select one or more criteria from one or more of the previously
            selected course rubrics.
          </h3>

          <bbNG:form method="POST" action="?select=formulation">
            <bbNG:dataCollection>
              <c:forEach var="courseWithRubrics" items="<%= coursesRubrics %>">
                <c:set var="courseRubrics" scope="page" value="${courseWithRubrics.value}" />

                <bbNG:step
                    id="rubric-for-${courseWithRubrics.key.primaryKey}"
                    title="${courseWithRubrics.key.batchUid}"
                    enableExpandCollapse="true">
                  <%
                    List<Rubric> courseRubrics;

                    try {
                      courseRubrics =
                        (List<Rubric>)pageContext.getAttribute ("courseRubrics");

                      RubricRowsLoaderAction rowsLoaderAction =
                        new RubricRowsLoaderAction (courseRubrics);

                      rowsLoaderAction.perform();
                      rubricsCriteria = rowsLoaderAction.getRubricRows();
                    } catch (Exception e) {
                      %><bbNG:error exception="<%= e %>" /><br><%
                    }

                    pageContext.setAttribute ("rubricsCriteria", rubricsCriteria);
                  %>

                  <c:choose>
                    <c:when test="${0 == rubricsCriteria.size()}">
                      <p>The criteria for the rubrics did not load properly.</p>
                    </c:when>

                    <c:otherwise>
                      <c:forEach var="rubricCriteria" items="${rubricsCriteria}">
                        <br>
                        <bbNG:step
                            id="course-rubric-${rubricCriteria.key.primaryKey}"
                            title="Rubric ${rubricCriteria.key.title}"
                            enableExpandCollapse="true"
                            subStep="true">
                          <c:forEach var="criteria" items="${rubricCriteria.value}">
                            <bbNG:dataElement>
                              <bbNG:checkboxElement
                                  id="rubric-criteria-${criteria.primaryKey}"
                                  value="${criteria.primaryKey}"
                                  name="rubric-criteria"
                                  optionLabel="${criteria.header}"
                                  isVertical="true"/>
                            </bbNG:dataElement>
                          </c:forEach>
                        </bbNG:step>
                      </c:forEach>
                    </c:otherwise>
                  </c:choose>
                </bbNG:step>
              </c:forEach>

              <bbNG:stepSubmit
                  title="Use the Selected Criteria"
                  instructions="Submit to select the formulation you would like to
                      perform for the above selected rubric criteria."
                  cancelUrl="?select=courses">
                <bbNG:stepSubmitButton label="Use the Selected Criteria" />
              </bbNG:stepSubmit>
            </bbNG:dataCollection>

            <c:forEach var="selectedCourse" items="<%= selectedCourses %>">
              <bbNG:hiddenElement name="simple-courses" value="${selectedCourse}" />
            </c:forEach>

            <c:forEach var="courseRubric" items="<%= selectedRubrics %>">
              <bbNG:hiddenElement name="course-rubrics" value="${courseRubric}" />
            </c:forEach>
          </bbNG:form>
        </c:otherwise>
      </c:choose>
    <%
  }
%>

</bbNG:includedPage>
