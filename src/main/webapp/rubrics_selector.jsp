<%@ page import="
  java.util.ArrayList,
  java.util.List,
  _context.BlackboardContext,
  _persistence.PersistenceManager,
  rubric.Rubric" %>
<%@ page import="_persistence.rubric.*" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bbNG" uri="/bbNG" %>

<bbNG:includedPage authentication="Y" entitlement="course.control_panel.VIEW">

  <%
    BlackboardContext context = new BlackboardContext (request);
    PersistenceManager manager = PersistenceManager.getInstance();
    List<Rubric> rubrics = new ArrayList<>();

    try {
      manager.establishConnection();
      RubricsLoader rubricsLoader = new RubricsLoader (manager.getConnection());

      rubrics =
        rubricsLoader.loadRubricsByCourseId (context.getContextCourseId());
    } catch (Exception e) {
  %><bbNG:error exception="<%= e %>" /><br><%
  }

  pageContext.setAttribute ("rubrics", rubrics);
%>

  <c:choose>
    <c:when test="${rubrics.isEmpty()}">
      <p>There are no courses to select from.</p>
    </c:when>

    <c:otherwise>
      <c:forEach var="rubric" items="<%= rubrics %>">
        <bbNG:dataElement>
          <bbNG:checkboxElement
             value="${rubric.primaryKey}"
             name="rubrics-${rubric.primaryKey}"
             optionLabel="${rubric.title}"
             isVertical="true"/>
        </bbNG:dataElement>
      </c:forEach>
    </c:otherwise>
  </c:choose>

</bbNG:includedPage>
