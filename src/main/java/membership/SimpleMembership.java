package membership;

import java.util.Date;

import blackboard.data.course.CourseMembership;

/**
 * The [SimpleMembership] class...
 */
public class SimpleMembership {
  final private String primaryKey;

  final private String coursePk;

  final private String userPk;

  final private String role;

  final private boolean availableInd;

  final private Date enrollmentDate;

  /**
   * The [SimpleMembership] field constructor...
   */
  public SimpleMembership (
    String primaryKey, String coursePk, String userPk, String role,
    boolean availableInd, Date enrollmentDate//, Date lastAccess
  ) {
    this.primaryKey = primaryKey;
    this.coursePk = coursePk;
    this.userPk = userPk;
    this.role = role;
    this.availableInd = availableInd;
    this.enrollmentDate = enrollmentDate;
  }

  /**
   * The [SimpleMembership] course membership constructor...
   */
  SimpleMembership (CourseMembership membership) {
    this.primaryKey = membership.getId().getExternalString();
    this.coursePk = membership.getCourseId().getExternalString();
    this.userPk = membership.getUserId().getExternalString();
    this.role = membership.getRoleAsString();
    this.availableInd = membership.getIsAvailable();
    this.enrollmentDate = membership.getEnrollmentDate().getTime();
  }

  public String getPrimaryKey() {
    return primaryKey;
  }

  public String getCoursePk() {
    return coursePk;
  }

  public String getUserPk() {
    return userPk;
  }

  public String getRole() {
    return role;
  }

  public boolean isAvailableInd() {
    return availableInd;
  }

  public Date getEnrollmentDate() {
    return enrollmentDate;
  }
}
