<%@ page import="
  java.util.List,
  _action.CoursesSelectorAction,
  course.SimpleCourse" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bbNG" uri="/bbNG" %>

<bbNG:includedPage authentication="Y" entitlement="course.control_panel.VIEW">

<%
  CoursesSelectorAction coursesSelector =
    new CoursesSelectorAction (request, "rubric_evaluator");

  try {
    coursesSelector.perform();
  } catch (Exception e) {
    %><bbNG:error exception="<%= e %>" /><br><%
  }

  List<SimpleCourse> courses = coursesSelector.getCourses();

  pageContext.setAttribute ("courses", courses);
%>

  <c:choose>
    <c:when test="${courses.isEmpty()}">
      <p>There are no courses to select from.</p>
    </c:when>

    <c:otherwise>
      <bbNG:dataCollection>
        <bbNG:step
            id="courses-selection-step"
            title="Courses Selection"
            enableExpandCollapse="true"
            instructions="Please select one or more courses for which
                you would like to review rubric information.">

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

          <bbNG:stepSubmitButton
              label="Select Rubrics for Courses"
              url="?select=rubrics" />
        </bbNG:stepSubmit>
      </bbNG:dataCollection>
    </c:otherwise>
  </c:choose>

</bbNG:includedPage>
