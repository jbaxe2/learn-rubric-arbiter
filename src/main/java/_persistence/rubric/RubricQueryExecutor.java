package _persistence.rubric;

import java.sql.PreparedStatement;

/**
 * The [RubricQueryExecutor] abstract class...
 */
public abstract class RubricQueryExecutor {
  final protected PreparedStatement statement;

  /**
   * The [RubricQueryExecutor] constructor...
   */
  protected RubricQueryExecutor (PreparedStatement statement) {
    this.statement = statement;
  }
}
