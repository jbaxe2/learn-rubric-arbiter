package _factory;

import formulation.BreakdownFormulation;
import formulation.Formulatable;

import javax.servlet.http.*;

/**
 * The [FormulationFactory] class...
 */
public class FormulationFactory {
  /**
   * The [createFormulatable] method...
   */
  public static Formulatable createFormulatable (
    HttpServletRequest request, String roleId,
    String[] courseIds, String[] rubricIds, String[] criteriaIds,
    String formulationType
  ) throws Exception {
    Formulatable formulatable = null;

    if ("breakdown".equals (formulationType)) {
      formulatable = new BreakdownFormulation (
        request, roleId, courseIds, rubricIds, criteriaIds
      );
    }

    return formulatable;
  }
}
