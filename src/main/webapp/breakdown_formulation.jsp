<%@ page import="
  formulation.BreakdownFormulation,
  formulation.Resultable,
  formulation.breakdown.CellEvalsBreakdown" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bbNG" uri="/bbNG" %>

<bbNG:includedPage authentication="Y">

  <%
    BreakdownFormulation breakForm = (BreakdownFormulation)formulatable;
    pageContext.setAttribute ("breakForm", breakForm);

    CellEvalsBreakdown cellEvalsResults = (CellEvalsBreakdown)resultable;
    pageContext.setAttribute ("results", cellEvalsResults);
  %>

  <bbNG:tabbedPanels>
    <c:forEach var="course" items="${breakForm.courses}">
      <bbNG:tabbedPanel title="${course.name} (${course.batchUid})">
        <c:forEach
            var="courseRubric"
            items="${breakForm.coursesRubrics.get (course)}">
          <div>
            <h3>${courseRubric.title}</h3>
            <p>
              <c:forEach
                  var="criteria"
                  items="${breakForm.rubricsCriteria.get (courseRubric)}">
                <strong>${criteria.header}:</strong><br>
                <c:forEach
                    var="expectation"
                    items="${breakForm.rubricsExpectations.get (courseRubric)}">
                  <strong>${expectation.header}:&nbsp;</strong>
                  <c:forEach
                      var="result"
                      items="${results.obtainResults()}">
                    <c:if test="${result.key.rowPk == criteria.primaryKey &&
                                  result.key.columnPk == expectation.primaryKey}">
                      ${result.value.size} total, ${result.value.average} average
                    </c:if>
                  </c:forEach><br>
                </c:forEach><br>
              </c:forEach><br>
            </p>
          </div><br>
        </c:forEach>
      </bbNG:tabbedPanel>
    </c:forEach>
  </bbNG:tabbedPanels>
</bbNG:includedPage>
