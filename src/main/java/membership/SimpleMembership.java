package membership;

import java.util.Date;

import blackboard.data.course.CourseMembership;

/**
 * The [SimpleMembership] class...
 */
public class SimpleMembership {
  private final String primaryKey;

  private final String coursePk1;

  private final String userPk1;

  private final String role;

  private final boolean availableInd;

  private final Date enrollmentDate;

  //private final Date lastAccess;

  /**
   * The [SimpleMembership] field constructor...
   */
  public SimpleMembership (
    String primaryKey, String coursePk1, String userPk1, String role,
    boolean availableInd, Date enrollmentDate//, Date lastAccess
  ) {
    this.primaryKey = primaryKey;
    this.coursePk1 = coursePk1;
    this.userPk1 = userPk1;
    this.role = role;
    this.availableInd = availableInd;
    this.enrollmentDate = enrollmentDate;
    //this.lastAccess = lastAccess;
  }

  /**
   * The [SimpleMembership] course membership constructor...
   */
  SimpleMembership (CourseMembership membership) {
    this.primaryKey = membership.getId().getExternalString();
    this.coursePk1 = membership.getCourseId().getExternalString();
    this.userPk1 = membership.getUserId().getExternalString();
    this.role = membership.getRoleAsString();
    this.availableInd = membership.getIsAvailable();
    this.enrollmentDate = membership.getEnrollmentDate().getTime();
    //this.lastAccess = membership.getLastAccessDate().getTime();
  }

  public String getPrimaryKey() {
    return primaryKey;
  }

  public String getCoursePk1() {
    return coursePk1;
  }

  public String getUserPk1() {
    return userPk1;
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

  /*public Date getLastAccess() {
    return lastAccess;
  }*/
}
