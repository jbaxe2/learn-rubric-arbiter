package _persistence.rubric;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.List;

import _error.ImproperRubricInfo;

import _factory.RubricRowFactory;

import rubric.RubricRow;

/**
 * The [RubricRowsQueryExecutor] class...
 */
class RubricRowsQueryExecutor extends RubricQueryExecutor {
  /**
   * The [RubricRowsQueryExecutor] constructor...
   */
  RubricRowsQueryExecutor (PreparedStatement statement) {
    super (statement);
  }

  /**
   * The [retrieveRubricRows] method...
   */
  List<RubricRow> retrieveRubricRows() throws ImproperRubricInfo {
    try {
      return (new RubricRowFactory()).createAll (statement.executeQuery());
    } catch (SQLException e) {
      throw new ImproperRubricInfo (e.getMessage());
    }
  }
}
