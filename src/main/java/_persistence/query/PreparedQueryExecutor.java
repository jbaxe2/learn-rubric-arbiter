package _persistence.query;

import java.sql.PreparedStatement;

/**
 * The [PreparedQueryExecutor] abstract class...
 */
public abstract class PreparedQueryExecutor {
  final protected PreparedStatement statement;

  /**
   * The [PreparedQueryExecutor] constructor...
   */
  protected PreparedQueryExecutor (PreparedStatement statement) {
    this.statement = statement;
  }
}
