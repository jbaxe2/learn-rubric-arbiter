package _factory;

import javax.servlet.http.HttpServletRequest;

import _error.InvalidFormulation;

import formulation.BreakdownFormulation;
import formulation.Formulatable;

/**
 * The [FormulationFactory] class...
 */
public class FormulationFactory {
  /**
   * The [createFormulatable] method...
   */
  public static Formulatable createFormulatable (
    String formulationType, HttpServletRequest request, String roleId,
    String[] courseIds, String[] rubricIds, String[] criteriaIds
  ) throws InvalidFormulation {
    Formulatable formulatable = null;

    if ("breakdown".equals (formulationType)) {
      formulatable = new BreakdownFormulation (
        request, roleId, courseIds, rubricIds, criteriaIds
      );
    }

    return formulatable;
  }
}
