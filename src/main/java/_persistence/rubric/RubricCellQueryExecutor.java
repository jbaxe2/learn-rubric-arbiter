package _persistence.rubric;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import _error.ImproperRubricInfo;

import _factory.RubricCellFactory;

import rubric.RubricCell;

/**
 * The [RubricCellQueryExecutor] class...
 */
class RubricCellQueryExecutor extends RubricQueryExecutor {
  /**
   * The [RubricCellQueryExecutor] constructor...
   */
  RubricCellQueryExecutor (PreparedStatement statement) {
    super (statement);
  }

  /**
   * The [retrieveRubricCell] method...
   */
  RubricCell retrieveRubricCell() throws ImproperRubricInfo {
    try {
      return (new RubricCellFactory()).create (statement.executeQuery());
    } catch (SQLException e) {
      throw new ImproperRubricInfo (e.getMessage());
    }
  }
}
