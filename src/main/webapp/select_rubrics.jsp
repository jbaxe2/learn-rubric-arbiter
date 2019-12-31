<%@ page import="
  java.util.HashMap,
  java.util.List,
  java.util.Map,
  action.CoursesRetrieverAction,
  action.RubricsLoaderAction,
  rubric.Rubric" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bbNG" uri="/bbNG" %>

<bbNG:includedPage authentication="Y">

  <%
    String[] selectedCourses = request.getParameterValues ("simple-courses");

    if ((null == selectedCourses) || (0 == selectedCourses.length)) {
      %><p>No courses were selected for which to review rubrics.</p><%
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
            <h3 style="margin-left: 25px;">
              Please select one or more rubrics from the previously selected courses,
              from which you would like to review criteria.
            </h3>

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
                      <bbNG:step
                          id="course-rubrics-${courseRubrics.key.primaryKey}"
                          title="Rubrics for: ${courseRubrics.key.name}
                              (${courseRubrics.key.batchUid})">
                        <bbNG:dataElement>
                          <c:forEach var="rubrics" items="${courseRubrics.value}">
                            <bbNG:checkboxElement
                                id="rubrics-for-${rubrics.primaryKey}"
                                value="${rubrics.primaryKey}"
                                name="course-rubrics"
                                optionLabel="${rubrics.title}"
                                isVertical="true" />
                          </c:forEach>
                        </bbNG:dataElement>
                      </bbNG:step>
                    </c:otherwise>
                  </c:choose>
                </c:forEach>

                <bbNG:stepSubmit
                    title="Use the Selected Rubrics"
                    instructions="Submit to select criteria from the above selected
                        rubrics.  Cancel to return to courses selection."
                    cancelUrl="?select=courses">
                  <bbNG:stepSubmitButton label="Use the Selected Rubrics" />
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
