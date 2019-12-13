<%@ page import="
  _action.*,
  rubric.*" %>
<%@ page import="java.util.*" %>

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
    Map<Rubric, List<RubricColumn>> rubricsCriteria;

    List<Rubric> courseRubrics = new ArrayList<>();

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
    pageContext.setAttribute ("courseRubrics", courseRubrics);

    %>
      <c:choose>
        <c:when test="${0 == coursesRubrics.size()}">
          <p>No rubrics were available to select the criteria from.</p>
        </c:when>

        <c:otherwise>
          <bbNG:form method="POST" action="?select=formulation">
            <bbNG:dataCollection>
              <c:forEach var="courseRubrics" items="<%= coursesRubrics %>">
                <bbNG:step
                    id="rubric-for-${courseRubrics.key.primaryKey}"
                    title="${courseRubrics.key.batchUid}"
                    enableExpandCollapse="true">
                  <%
                    RubricColumnsLoaderAction columnsLoaderAction =
                      new RubricColumnsLoaderAction (courseRubrics);

                    try {
                      columnsLoaderAction.perform();
                    } catch (Exception e) {
                      %><bbNG:error exception="<%= e %>" /><br><%
                    }

                    rubricsCriteria = columnsLoaderAction.getRubricColumns();

                    pageContext.setAttribute ("rubricsCriteria", rubricsCriteria);
                  %>

                  <c:choose>
                    <c:when test="${0 == rubricsCriteria.size()}">
                      <p>The criteria for the rubrics did not load properly.</p>
                    </c:when>

                    <c:otherwise>
                      <c:forEach var="rubricCriteria" items="${rubricsCriteria}">
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
                                  optionLabel="${criteria.header}"/>
                            </bbNG:dataElement>
                          </c:forEach>
                        </bbNG:step>
                      </c:forEach>
                    </c:otherwise>
                  </c:choose>
                </bbNG:step>
              </c:forEach>

              <bbNG:stepSubmit
                  title="Select Formulation of Criteria"
                  instructions="Submit to select the formulation you would like to
                      perform for the above selected rubric criteria."
                  cancelUrl="?select=courses">
                <bbNG:stepSubmitButton label="Select Formulation of Criteria" />
              </bbNG:stepSubmit>
            </bbNG:dataCollection>
          </bbNG:form>
        </c:otherwise>
      </c:choose>
    <%
  }
%>

</bbNG:includedPage>
