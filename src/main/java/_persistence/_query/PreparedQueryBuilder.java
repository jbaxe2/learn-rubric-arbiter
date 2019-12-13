package _persistence._query;

import java.sql.Connection;

/**
 * The [PreparedQueryBuilder] abstract class...
 */
public abstract class PreparedQueryBuilder implements QueryBuilder {
  final protected Connection connection;

  /**
   * The [PreparedQueryBuilder] constructor...
   */
  protected PreparedQueryBuilder (Connection connection) {
    this.connection = connection;
  }
}
