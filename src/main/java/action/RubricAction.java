package action;

import blackboard.db.ConnectionNotAvailableException;

import _persistence.PersistenceManager;

import _persistence.rubric.RubricsLoader;

/**
 * The [RubricAction] abstract class...
 */
abstract class RubricAction implements Actionable {
  protected RubricsLoader loader;

  /**
   * The [perform] method...
   */
  public abstract void perform() throws Exception;

  /**
   * The [filterByIds] method...
   */
  public abstract Object filterByIds (String[] filterIds);

  /**
   * The [createLoader] method...
   */
  protected void createLoader() throws ConnectionNotAvailableException {
    PersistenceManager manager = PersistenceManager.getInstance();
    manager.establishConnection();

    loader = new RubricsLoader (manager.getConnection());
  }
}
