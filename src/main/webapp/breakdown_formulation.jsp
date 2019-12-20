<%@ page import="formulation.*" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bbNG" uri="/bbNG" %>

<bbNG:includedPage authentication="Y" entitlement="course.control_panel.VIEW">

<%
  BreakdownFormulation breakForm = (BreakdownFormulation)formulatable;
%>

  <bbNG:tabbedPanels>
    <c:forEach var="course" items="<%= breakForm.getCourses() %>">
      <bbNG:tabbedPanel title="${course.name} (${course.batchUid})">
        <c:forEach var="courseRubric" items="<%= breakForm.getRubrics() %>">
          <div>
            <h3>${courseRubric.title}</h3><br>
            <p>
              <c:forEach
                  var="criteria"
                  items="<%= breakForm.getRubricsCriteria().get (null) %>">
                <strong>${criteria.header}:</strong> ${criteria.percentage}
              </c:forEach>
            </p>
          </div><br>
        </c:forEach>
      </bbNG:tabbedPanel>
    </c:forEach>
  </bbNG:tabbedPanels>
</bbNG:includedPage>
