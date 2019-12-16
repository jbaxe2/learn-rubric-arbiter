package _factory;

import formulation.BreakdownFormulation;
import formulation.Formulatable;

/**
 * The [FormulationFactory] class...
 */
public class FormulationFactory {
  /**
   * The [createFormulatable] method...
   */
  public static Formulatable createFormulatable (String formulationType) {
    Formulatable formulatable = null;

    if ("breakdown".equals (formulationType)) {
      formulatable = new BreakdownFormulation();
    }

    return formulatable;
  }
}
