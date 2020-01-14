<%@ page import="
  formulation.BreakdownFormulation,
  formulation.Resultable,
  formulation.breakdown.RubricsEvalsBreakdown" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bbNG" uri="/bbNG" %>

<bbNG:includedPage authentication="Y">

  <%
    BreakdownFormulation breakForm = (BreakdownFormulation)formulatable;
    pageContext.setAttribute ("breakForm", breakForm);

    RubricsEvalsBreakdown evalsResults = (RubricsEvalsBreakdown)resultable;
    pageContext.setAttribute ("results", evalsResults);
  %>

  <bbNG:tabbedPanels>
    <c:forEach var="course" items="${breakForm.courses}">
      <bbNG:tabbedPanel title="${course.name} (${course.batchUid})">
        <c:forEach
            var="courseRubric"
            items="${breakForm.coursesRubrics.get (course)}">
          <div>
            <h3 style="text-decoration: underline;">${courseRubric.title}</h3>
            <p>
              <c:forEach
                  var="criteria"
                  items="${breakForm.rubricsCriteria.get (courseRubric)}">
                <strong>Criteria <em>(${criteria.header})</em>:</strong><br>
                <c:forEach
                    var="expectation"
                    items="${breakForm.rubricsExpectations.get (courseRubric)}">
                  <strong>${expectation.header}:&nbsp;</strong>
                  <c:forEach
                      var="result"
                      items="${results.getCellsEvalsAverages()}">
                    <c:if test="${result.key.rowPk == criteria.primaryKey &&
                                  result.key.columnPk == expectation.primaryKey}">
                      ${result.value.size} scores,
                      ${result.value.average / 100 * courseRubric.maxValue} in-range average
                      <em>(${result.value.min} min - ${result.value.max} max)</em>
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
