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

  private static PersistenceManager _instance;

  /**
   * The [PersistenceManager] constructor...
   */
  public static PersistenceManager getInstance() {
    if (null == _instance) {
      _instance = new PersistenceManager();
    }

    return _instance;
  }

  /**
   * The [PersistenceManager] private constructor...
   */
  private PersistenceManager() {}

  /**
   * The [establishConnection] method...
   */
  public void establishConnection() throws ConnectionNotAvailableException {
    if (null != connection) {
      return;
    }

    bbPersistenceManager =
      PersistenceServiceFactory.getInstance().getDbPersistenceManager();

    connectionManager = BbDatabase.getDefaultInstance().getConnectionManager();
    connection = connectionManager.getConnection();
  }

  /**
   * The [getConnection] method...
   */
  public Connection getConnection() {
    return connection;
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
    if (null != connection) {
      connectionManager.releaseConnection (connection);

      connection = null;
    }
  }
}
