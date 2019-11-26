package _persistence;

import java.sql.Connection;

import blackboard.db.BbDatabase;
import blackboard.db.ConnectionManager;
import blackboard.db.ConnectionNotAvailableException;

import blackboard.persist.BbPersistenceManager;
import blackboard.persist.PersistenceException;
import blackboard.persist.course.CourseDbLoader;
import blackboard.persist.course.CourseMembershipDbLoader;
import blackboard.persist.user.UserDbLoader;

import blackboard.platform.persistence.PersistenceServiceFactory;

/**
 * The [PersistenceManager] class...
 */
public class PersistenceManager {
  private ConnectionManager connectionManager;

  private BbPersistenceManager bbPersistenceManager;

  private Connection connection;

  /**
   * The [PersistenceManager] constructor...
   */
  public PersistenceManager() {}

  /**
   * The [obtainConnection] method...
   */
  public void obtainConnection() throws ConnectionNotAvailableException {
    bbPersistenceManager =
      PersistenceServiceFactory.getInstance().getDbPersistenceManager();

    connectionManager = BbDatabase.getDefaultInstance().getConnectionManager();
    connection = connectionManager.getConnection();
  }

  /**
   * The [getCourseDbLoader] method...
   */
  public CourseDbLoader getCourseDbLoader() throws PersistenceException {
    return (CourseDbLoader) bbPersistenceManager.getLoader (CourseDbLoader.TYPE);
  }

  /**
   * The [getUserDbLoader] method...
   */
  public UserDbLoader getUserDbLoader() throws PersistenceException {
    return (UserDbLoader) bbPersistenceManager.getLoader (UserDbLoader.TYPE);
  }

  /**
   * The [getMembershipDbLoader] method...
   */
  public CourseMembershipDbLoader getMembershipDbLoader()
      throws PersistenceException {
    return (CourseMembershipDbLoader) bbPersistenceManager.getLoader (
      CourseMembershipDbLoader.TYPE
    );
  }

  /**
   * The [releaseConnection] method...
   */
  public void releaseConnection() {
    connectionManager.releaseConnection (connection);
  }
}