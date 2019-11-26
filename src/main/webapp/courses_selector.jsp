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
  String evaluatorId = "rubric_evaluator";

  SimpleCoursesRetriever retriever = new SimpleCoursesRetriever (userId, evaluatorId);
  PersistenceManager manager = new PersistenceManager();

  List<SimpleCourse> courses = new ArrayList<>();
  pageContext.setAttribute ("courses", courses);

  try {
    manager.obtainConnection();

    CourseDbLoader courseLoader = manager.getCourseDbLoader();
    CourseMembershipDbLoader membershipLoader = manager.getMembershipDbLoader();

    courses = retriever.retrieveSimpleCourses (membershipLoader, courseLoader);
  } catch (Exception e) {
    %><bbNG:error exception="<%= e %>" /><%
  }
%>

  <c:choose>
    <c:when test="${courses.isEmpty()}">
      <p>There are no courses to select from.</p>
    </c:when>
    <c:otherwise>
      <c:forEach var="simpleCourse" items="courses">
        <bbNG:checkboxElement
           value="${simpleCourse.primaryKey}"
           name="simple-courses"
           optionLabel="${simpleCourse.name}"/>
      </c:forEach>
    </c:otherwise>
  </c:choose>

<% manager.releaseConnection(); %>

</bbNG:includedPage>
