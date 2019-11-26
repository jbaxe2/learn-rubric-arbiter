package rubric;

import _formatting.FormatType;

/**
 * The [RubricCellEval] class...
 */
public class RubricCellEval {
  final private String primaryKey;

  final private String rubricEvalPk;

  final private String rowPk;

  final private String cellPk;

  final private String feedback;

  final private FormatType feedbackType;

  final private float selectedPercent;

  final private boolean overrideInd;

  /**
   * The [RubricCellEval] constructor...
   */
  RubricCellEval (
    String primaryKey, String rubricEvalPk, String rowPk, String cellPk,
    String feedback, FormatType feedbackType, float selectedPercent,
    boolean overrideInd
  ) {
    this.primaryKey = primaryKey;
    this.rubricEvalPk = rubricEvalPk;
    this.rowPk = rowPk;
    this.cellPk = cellPk;
    this.feedback = feedback;
    this.feedbackType = feedbackType;
    this.selectedPercent = selectedPercent;
    this.overrideInd = overrideInd;
  }

  public String getPrimaryKey() {
    return primaryKey;
  }

  public String getRubricEvalPk() {
    return rubricEvalPk;
  }

  public String getRowPk() {
    return rowPk;
  }

  public String getCellPk() {
    return cellPk;
  }

  public String getFeedback() {
    return feedback;
  }

  public FormatType getFeedbackType() {
    return feedbackType;
  }

  public float getSelectedPercent() {
    return selectedPercent;
  }

  public boolean isOverrideInd() {
    return overrideInd;
  }
}
