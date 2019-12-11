<%@ page import="
    java.util.HashMap,
    java.util.List,
    java.util.Map,
    _action.CoursesSelectorAction,
    _action.RubricsSelectorAction,
    rubric.Rubric" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bbNG" uri="/bbNG" %>

<bbNG:includedPage authentication="Y" entitlement="course.control_panel.VIEW">

<%
  String[] selectedCourses = request.getParameterValues ("simple-courses");

  List<SimpleCourse> courses;
  Map<SimpleCourse, List<Rubric>> coursesRubrics = new HashMap<>();

  if (0 == selectedCourses.length) {
    %><p>No courses were selected to review rubrics for.</p><%
  } else {
    CoursesSelectorAction coursesSelector;
    RubricsSelectorAction rubricsSelector;

    try {
      coursesSelector = new CoursesSelectorAction (request, "rubric_evaluator");
      coursesSelector.perform();

      courses = coursesSelector.filterByIds (selectedCourses);

      rubricsSelector = new RubricsSelectorAction (courses);
      rubricsSelector.perform();

      coursesRubrics = rubricsSelector.getCoursesRubrics();
    } catch (Exception e) {
      %><bbNG:error exception="<%= e %>" /><br><%
    }

    if (0 == coursesRubrics.size()) {
      %><p>No rubrics are available to select from.</p><%
    } else {
      pageContext.setAttribute ("coursesRubrics", coursesRubrics);

      %><bbNG:dataCollection>
        <c:forEach var="courseRubrics" items="<%= coursesRubrics %>">
          <bbNG:stepGroup title="${courseRubrics.key.courseId}">
            <c:forEach var="rubrics" items="${courseRubrics.value}">
              <bbNG:step
                  id="course-rubrics-${courseRubrics.key.primaryKey}"
                  title="Rubrics for ${courseRubrics.key.batchUid}"
                  enableExpandCollapse="true">
                <bbNG:dataElement>
                  <bbNG:checkboxElement
                      value="${rubrics.primaryKey}"
                      name="rubrics-for-${courseRubrics.key.primaryKey}"
                      optionLabel="${rubrics.title}"/>
                </bbNG:dataElement>
              </bbNG:step>
            </c:forEach>
          </bbNG:stepGroup>
        </c:forEach>

        <bbNG:stepSubmit
            title="Select Criteria for Rubrics"
            instructions="Submit to select criteria from the above selected
                rubrics.  Cancel to return to courses selection."
            cancelUrl="?select=courses">

          <bbNG:stepSubmitButton
              label="Select Criteria for Rubrics"
              url="?select=criteria" />
        </bbNG:stepSubmit>
      </bbNG:dataCollection><%
    }
  }
%>

</bbNG:includedPage>
