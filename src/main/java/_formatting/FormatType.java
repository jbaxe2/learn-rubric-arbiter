package _formatting;

/**
 * The [FormatType] enum...
 */
public enum FormatType {
  H ("html"),

  P ("plain"),

  S ("smart");

  final public String label;

  /**
   * The [FormatType] constructor...
   */
  FormatType (String label) {
    this.label = label;
  }
}
