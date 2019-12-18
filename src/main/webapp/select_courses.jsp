<%@ page import="
  java.util.List,
  action.CoursesRetrieverAction,
  course.SimpleCourse" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bbNG" uri="/bbNG" %>

<bbNG:includedPage authentication="Y" entitlement="course.control_panel.VIEW">

<%
  CoursesRetrieverAction coursesRetrieverAction =
    new CoursesRetrieverAction (request, "rubric_evaluator");

  try {
    coursesRetrieverAction.perform();
  } catch (Exception e) {
    %><bbNG:error exception="<%= e %>" /><br><%
  }

  List<SimpleCourse> courses = coursesRetrieverAction.getCourses();

  pageContext.setAttribute ("courses", courses);
%>

  <c:choose>
    <c:when test="${courses.isEmpty()}">
      <p>Your are not enrolled in any courses as a rubrics evaluator.</p>
    </c:when>

    <c:otherwise>
      <h3 style="margin-left: 25px;">
        Please select one or more courses for which you would like to review
        rubric information.
      </h3>

      <bbNG:form method="POST" action="?select=rubrics">
        <bbNG:dataCollection>
          <bbNG:step
              id="courses-selection-step"
              title="Courses Selection"
              enableExpandCollapse="true">

            <c:forEach var="simpleCourse" items="<%= courses %>">
              <bbNG:dataElement>
                <bbNG:checkboxElement
                    id="simple-courses-${simpleCourse.primaryKey}"
                    name="simple-courses"
                    value="${simpleCourse.primaryKey}"
                    optionLabel="${simpleCourse.name}"
                    isVertical="true" />
              </bbNG:dataElement>
            </c:forEach><br>
          </bbNG:step>

          <bbNG:stepSubmit
              title="Select Rubrics for Courses"
              instructions="Submit to select rubrics from the above
                  selected courses."
              cancelUrl="?select=courses">

            <bbNG:stepSubmitButton label="Select Rubrics for Courses" />
          </bbNG:stepSubmit>
        </bbNG:dataCollection>
      </bbNG:form>
    </c:otherwise>
  </c:choose>

</bbNG:includedPage>
