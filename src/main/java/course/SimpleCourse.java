package course;

import blackboard.data.course.Course;

/**
 * The [SimpleCourse] class...
 */
public class SimpleCourse {
  final private String primaryKey;

  final private String courseId;

  final private String batchUid;

  final private String name;

  final private boolean availableInd;

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
  SimpleCourse (Course course) {
    this.primaryKey = course.getId().getExternalString();
    this.courseId = course.getCourseId();
    this.batchUid = course.getBatchUid();
    this.name = course.getDisplayTitle();
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
