package rubric;

/**
 *  The [RubricType] enum.
 */
public enum RubricType {
  N ("Numeric"),

  T ("Non-numeric(text)"),

  R ("Numeric Range"),

  P ("Percentage"),

  Q ("Percentage Range");

  public final String label;

  /**
   * The [RubricType] constructor...
   */
  RubricType (String label) {
    this.label = label;
  }
}
