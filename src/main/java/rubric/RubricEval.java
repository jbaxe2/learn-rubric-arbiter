package rubric;

import java.util.Date;

import _formatting.FormatType;

/**
 * The [RubricEval] class...
 */
public class RubricEval {
  final private String primaryKey;

  final private String linkPk;

  final private String associationPk;

  final private String reviewerUserPk;

  final private String reviewerUsername;

  final private String respondentUserPk;

  final private String respondentUsername;

  final private boolean completedInd;

  final private Date submissionDate;

  final private String comments;

  final private FormatType commentsType;

  final private float maxValue;

  final private float totalValue;

  final private float overrideValue;

  final private float calculatedPercent;

  /**
   * The [RubricEval] constructor...
   */
  public RubricEval (
    String primaryKey, String linkPk, String associationPk, String reviewerUserPk,
    String reviewerUsername, String respondentUserPk, String respondentUsername,
    boolean completedInd, Date submissionDate, String comments,
    FormatType commentsType, float maxValue, float totalValue, float overrideValue,
    float calculatedPercent
  ) {
    this.primaryKey = primaryKey;
    this.linkPk = linkPk;
    this.associationPk = associationPk;
    this.reviewerUserPk = reviewerUserPk;
    this.reviewerUsername = reviewerUsername;
    this.respondentUserPk = respondentUserPk;
    this.respondentUsername = respondentUsername;
    this.completedInd = completedInd;
    this.submissionDate = submissionDate;
    this.comments = comments;
    this.commentsType = commentsType;
    this.maxValue = maxValue;
    this.totalValue = totalValue;
    this.overrideValue = overrideValue;
    this.calculatedPercent = calculatedPercent;
  }

  public String getPrimaryKey() {
    return primaryKey;
  }

  public String getLinkPk() {
    return linkPk;
  }

  public String getAssociationPk() {
    return associationPk;
  }

  public String getReviewerUserPk() {
    return reviewerUserPk;
  }

  public String getReviewerUsername() {
    return reviewerUsername;
  }

  public String getRespondentUserPk() {
    return respondentUserPk;
  }

  public String getRespondentUsername() {
    return respondentUsername;
  }

  public boolean isCompletedInd() {
    return completedInd;
  }

  public Date getSubmissionDate() {
    return submissionDate;
  }

  public String getComments() {
    return comments;
  }

  public FormatType getCommentsType() {
    return commentsType;
  }

  public float getMaxValue() {
    return maxValue;
  }

  public float getTotalValue() {
    return totalValue;
  }

  public float getOverrideValue() {
    return overrideValue;
  }

  public float getCalculatedPercent() {
    return calculatedPercent;
  }
}
