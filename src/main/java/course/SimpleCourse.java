package course;

import blackboard.data.course.Course;

/**
 * The [SimpleCourse] class...
 */
public class SimpleCourse {
  private final String primaryKey;

  private final String courseId;

  private final String batchUid;

  private final String name;

  private final boolean availableInd;

  /**
   * The [SimpleCourse] field constructor...
   */
  public SimpleCourse (
    String primaryKey, String courseId, String batchUid, String name,
    boolean availableInd
  ) {
    this.primaryKey = primaryKey;
    this.courseId = courseId;
    this.batchUid = batchUid;
    this.name = name;
    this.availableInd = availableInd;
  }

  /**
   * The [SimpleCourse] course constructor...
   */
  public SimpleCourse (Course course) {
    this.primaryKey = course.getId().getExternalString();
    this.courseId = course.getCourseId();
    this.batchUid = course.getBatchUid();
    this.name = course.getTitle();
    this.availableInd = course.getIsAvailable();
  }

  public String getPrimaryKey() {
    return primaryKey;
  }

  public String getCourseId() {
    return courseId;
  }

  public String getBatchUid() {
    return batchUid;
  }

  public String getName() {
    return name;
  }

  public boolean isAvailableInd() {
    return availableInd;
  }
}
