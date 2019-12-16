package formulation;

import javax.servlet.http.HttpServletRequest;

import _error.InvalidFormulation;

/**
 * The [BreakdownFormulation] class...
 */
public class BreakdownFormulation extends Formulation {
  public BreakdownFormulation (
    HttpServletRequest request, String roleId,
    String[] courseIds, String[] rubricIds, String[] criteriaIds
  ) throws InvalidFormulation {
    super (request, roleId, courseIds, rubricIds, criteriaIds);
  }

  /**
   * The [formulate] method...
   */
  public void formulate() throws InvalidFormulation {}
}
