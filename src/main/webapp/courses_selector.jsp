<%@ page import="
  java.util.ArrayList,
  java.util.List,
  blackboard.persist.course.CourseDbLoader,
  blackboard.persist.course.CourseMembershipDbLoader,
  _context.BlackboardContext,
  _persistence.PersistenceManager,
  course.SimpleCourse,
  course.SimpleCoursesRetriever" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bbNG" uri="/bbNG" %>

<bbNG:includedPage authentication="Y" entitlement="course.control_panel.VIEW">

<%
  BlackboardContext context = new BlackboardContext (request);
  String userId = context.getContextUserId();

  SimpleCoursesRetriever retriever = new SimpleCoursesRetriever (userId);
  PersistenceManager manager = PersistenceManager.getInstance();

  List<SimpleCourse> courses = new ArrayList<>();

  try {
    manager.establishConnection();

    CourseDbLoader courseLoader = manager.getCourseDbLoader();
    CourseMembershipDbLoader membershipLoader = manager.getMembershipDbLoader();

    courses = retriever.retrieveSimpleCourses (
      membershipLoader, courseLoader, "rubric_evaluator"
    );
  } catch (Exception e) {
    %><bbNG:error exception="<%= e %>" /><br><%
  }

  pageContext.setAttribute ("courses", courses);
%>

  <c:choose>
    <c:when test="${courses.isEmpty()}">
      <p>There are no courses to select from.</p>
    </c:when>

    <c:otherwise>
      <c:forEach var="simpleCourse" items="<%= courses %>">
        <bbNG:dataElement>
          <bbNG:checkboxElement
              value="${simpleCourse.primaryKey}"
              name="simple-courses-${simpleCourse.primaryKey}"
              optionLabel="${simpleCourse.name}"
              isVertical="true" />
        </bbNG:dataElement>
      </c:forEach>
    </c:otherwise>
  </c:choose>

</bbNG:includedPage>
