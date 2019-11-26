package user;

import blackboard.data.user.User;

/**
 * The [SimpleUser] class...
 */
public class SimpleUser {
  private final String primaryKey;

  private final String userId;

  private final String batchUid;

  private final String studentId;

  private final String systemRole;

  private final String firstName;

  private final String lastName;

  private final String email;

  private final boolean availableInd;

  /**
   * The [SimpleUser] field constructor...
   */
  public SimpleUser (
    String primaryKey, String userId, String batchUid, String studentId,
    String systemRole, String firstName, String lastName, String email,
    boolean availableInd
  ) {
    this.primaryKey = primaryKey;
    this.userId = userId;
    this.batchUid = batchUid;
    this.studentId = studentId;
    this.systemRole = systemRole;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.availableInd = availableInd;
  }

  /**
   * The [SimpleUser] user constructor...
   */
  public SimpleUser (User user) {
    this.primaryKey = user.getId().getExternalString();
    this.userId = user.getUserName();
    this.batchUid = user.getBatchUid();
    this.studentId = user.getStudentId();
    this.systemRole = user.getSystemRoleIdentifier();
    this.firstName = user.getGivenName();
    this.lastName = user.getFamilyName();
    this.email = user.getEmailAddress();
    this.availableInd = user.getIsAvailable();
  }

  public String getPrimaryKey() {
    return primaryKey;
  }

  public String getUserId() {
    return userId;
  }

  public String getBatchUid() {
    return batchUid;
  }

  public String getStudentId() {
    return studentId;
  }

  public String getSystemRole() {
    return systemRole;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmail() {
    return email;
  }

  public boolean isAvailableInd() {
    return availableInd;
  }
}
