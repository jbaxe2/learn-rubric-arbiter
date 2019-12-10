<%@ page import="
  java.util.*,
  _persistence.PersistenceManager,
  _persistence.rubric.RubricsLoader,
  rubric.Rubric" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bbNG" uri="/bbNG" %>

<bbNG:includedPage authentication="Y" entitlement="course.control_panel.VIEW">

<%
  PersistenceManager manager = PersistenceManager.getInstance();
  Map<SimpleCourse, List<Rubric>> coursesRubrics = new HashMap<>();

  try {
    manager.establishConnection();

    RubricsLoader rubricsLoader = new RubricsLoader (manager.getConnection());

    coursesRubrics = rubricsLoader.loadRubricsForCourses (
      (List<SimpleCourse>)pageContext.getAttribute ("courses")
    );
  } catch (Exception e) {
    %><bbNG:error exception="<%= e %>" /><br><%
  }

  pageContext.setAttribute ("coursesRubrics", coursesRubrics);
%>

  <c:choose>
    <c:when test="${coursesRubrics.isEmpty()}">
      <p>There are no courses rubrics to select from.</p>
    </c:when>

    <c:otherwise>
      <c:forEach var="courseRubrics" items="${coursesRubrics.values()}">
        <c:forEach var="courseRubric" items="${courseRubrics}">
          <bbNG:dataElement>
            <bbNG:checkboxElement
               value="course-rubrics-${courseRubric.primaryKey}"
               name="course-rubrics"
               optionLabel="${courseRubric.title}"
               isVertical="true" />
          </bbNG:dataElement>
        </c:forEach>
      </c:forEach>
    </c:otherwise>
  </c:choose>

</bbNG:includedPage>
