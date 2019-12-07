package rubric;

import java.util.Date;

/**
 *  The [Rubric] class...
 */
public class Rubric {
  final private String primaryKey;

  final private String coursePk;

  final private String title;

  final private String description;

  final private String creatorPk;

  final private RubricType type;

  final private float maxValue;

  final private Date created;

  final private Date modified;

  /**
   *  The [Rubric] constructor...
   */
  public Rubric (
    String primaryKey, String coursePk, String title, String description,
    String creatorPk, RubricType type, float maxValue,
    Date created, Date modified
  ) {
    this.primaryKey = primaryKey;
    this.coursePk = coursePk;
    this.title = title;
    this.description = description;
    this.creatorPk = creatorPk;
    this.type = type;
    this.maxValue = maxValue;
    this.created = created;
    this.modified = modified;
  }

  public String getPrimaryKey() {
    return primaryKey;
  }

  public String getCoursePk() {
    return coursePk;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public String getCreatorPk() {
    return creatorPk;
  }

  public RubricType getType() {
    return type;
  }

  public float getMaxValue() {
    return maxValue;
  }

  public Date getCreated() {
    return created;
  }

  public Date getModified() {
    return modified;
  }
}
