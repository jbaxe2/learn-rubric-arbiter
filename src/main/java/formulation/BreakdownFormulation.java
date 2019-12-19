package formulation;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import _error.InvalidFormulation;

import action.RubricColumnsLoaderAction;

import rubric.Rubric;
import rubric.RubricColumn;

/**
 * The [BreakdownFormulation] class...
 */
public class BreakdownFormulation extends Formulation {
  private Map<Rubric, List<RubricColumn>> rubricsExpectations;

  public BreakdownFormulation (
    HttpServletRequest request, String roleId,
    String[] courseIds, String[] rubricIds, String[] criteriaIds
  ) throws InvalidFormulation {
    super (request, roleId, courseIds, rubricIds, criteriaIds);
  }

  /**
   * The [formulate] method...
   */
  public void formulate() throws InvalidFormulation {
    try {
      _performRetrieveExpectations();
      _performRetrieveCells();
    } catch (Exception e) {
      throw new InvalidFormulation (e.getMessage());
    }
  }

  /**
   * The [_performRetrieveExpectations] method...
   */
  private void _performRetrieveExpectations() throws Exception {
    RubricColumnsLoaderAction expectationsLoader =
      new RubricColumnsLoaderAction (rubrics);

    expectationsLoader.perform();

    rubricsExpectations = expectationsLoader.getRubricColumns();
  }

  /**
   * The [_performRetrieveCells] method...
   */
  private void _performRetrieveCells() throws Exception {
  }
}
