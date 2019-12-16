package _persistence.rubric;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.List;

import _error.ImproperRubricInfo;
import _factory.RubricCellFactory;

import rubric.*;

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
  List<RubricCell> retrieveRubricCell() throws ImproperRubricInfo {
    try {
      return (new RubricCellFactory()).createAll (statement.executeQuery());
    } catch (SQLException e) {
      throw new ImproperRubricInfo (e.getMessage());
    }
  }
}
