package rubric;

/**
 * The [RubricRow] class...
 */
public class RubricRow {
  final private String primaryKey;

  final private String rubricPk;

  final private String header;

  final private int position;

  final private float percentage;

  /**
   * The [RubricRow] constructor...
   */
  public RubricRow (
    String primaryKey, String rubricPk, String header, int position,
    float percentage
  ) {
    this.primaryKey = primaryKey;
    this.rubricPk = rubricPk;
    this.header = header;
    this.position = position;
    this.percentage = percentage;
  }

  public String getPrimaryKey() {
    return primaryKey;
  }

  public String getRubricPk() {
    return rubricPk;
  }

  public String getHeader() {
    return header;
  }

  public int getPosition() {
    return position;
  }

  public float getPercentage() {
    return percentage;
  }
}
