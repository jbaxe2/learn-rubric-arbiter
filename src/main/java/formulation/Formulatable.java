package formulation;

import _error.InvalidFormulation;

/**
 * The [Formulatable] interface...
 */
public interface Formulatable {
  /**
   * The [formulate] method...
   */
  Resultable formulate() throws InvalidFormulation;
}
