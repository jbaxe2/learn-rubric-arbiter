package rubric;

/**
 * The [RubricColumn] class...
 */
public class RubricColumn {
  final private String primaryKey;

  final private String rubricPk;

  final private String header;

  final private int position;

  /**
   * The [RubricColumn] constructor...
   */
  RubricColumn (
    String primaryKey, String rubricPk, String header, int position
  ) {
    this.primaryKey = primaryKey;
    this.rubricPk = rubricPk;
    this.header = header;
    this.position = position;
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
}
