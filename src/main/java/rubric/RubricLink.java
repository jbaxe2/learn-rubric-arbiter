package rubric;

/**
 * The [RubricLink] class...
 */
public class RubricLink {
  final private String primaryKey;

  final private String rubricPk;

  final private int version;

  final private String evalEntityPk;

  final private String subRubricAssocPk;

  final private boolean visibleInd;

  final private boolean usedForGradingInd;

  /**
   * The [RubricLink] constructor...
   */
  public RubricLink (
    String primaryKey, String rubricPk, int version, String evalEntityPk,
    String subRubricAssocPk, boolean visibleInd, boolean usedForGradingInd
  ) {
    this.primaryKey = primaryKey;
    this.rubricPk = rubricPk;
    this.version = version;
    this.evalEntityPk = evalEntityPk;
    this.subRubricAssocPk = subRubricAssocPk;
    this.visibleInd = visibleInd;
    this.usedForGradingInd = usedForGradingInd;
  }

  public String getPrimaryKey() {
    return primaryKey;
  }

  public String getRubricPk() {
    return rubricPk;
  }

  public int getVersion() {
    return version;
  }

  public String getEvalEntityPk() {
    return evalEntityPk;
  }

  public String getSubRubricAssocPk() {
    return subRubricAssocPk;
  }

  public boolean isVisibleInd() {
    return visibleInd;
  }

  public boolean isUsedForGradingInd() {
    return usedForGradingInd;
  }
}
