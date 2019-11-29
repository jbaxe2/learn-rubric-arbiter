package rubric;

/**
 * The [RubricCell] class...
 */
public class RubricCell {
  final private String primaryKey;

  final private String rowPk;

  final private String columnPk;

  final private String description;

  final private float numericPoints;

  final private float startPointRange;

  final private float endPointRange;

  final private float percentage;

  final private float percentageMin;

  final private float percentageMax;

  /**
   * The [RubricCell] constructor...
   */
  public RubricCell (
    String primaryKey, String rowPk, String columnPk, String description,
    float numericPoints, float startPointRange, float endPointRange,
    float percentage, float percentageMin, float percentageMax
  ) {
    this.primaryKey = primaryKey;
    this.rowPk = rowPk;
    this.columnPk = columnPk;
    this.description = description;
    this.numericPoints = numericPoints;
    this.startPointRange = startPointRange;
    this.endPointRange = endPointRange;
    this.percentage = percentage;
    this.percentageMin = percentageMin;
    this.percentageMax = percentageMax;
  }

  public String getPrimaryKey() {
    return primaryKey;
  }

  public String getRowPk() {
    return rowPk;
  }

  public String getColumnPk() {
    return columnPk;
  }

  public String getDescription() {
    return description;
  }

  public float getNumericPoints() {
    return numericPoints;
  }

  public float getStartPointRange() {
    return startPointRange;
  }

  public float getEndPointRange() {
    return endPointRange;
  }

  public float getPercentage() {
    return percentage;
  }

  public float getPercentageMin() {
    return percentageMin;
  }

  public float getPercentageMax() {
    return percentageMax;
  }
}
