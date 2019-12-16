package _persistence.rubric;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.List;

import _error.ImproperRubricInfo;
import _factory.RubricColumnFactory;

import rubric.RubricColumn;

/**
 * The [RubricColumnsQueryExecutor] class...
 */
class RubricColumnsQueryExecutor extends RubricQueryExecutor {
  /**
   * The [RubricColumnsQueryExecutor] constructor...
   */
  RubricColumnsQueryExecutor (PreparedStatement statement) {
    super (statement);
  }

  /**
   * The [retrieveRubricColumns] method...
   */
  List<RubricColumn> retrieveRubricColumns() throws ImproperRubricInfo {
    try {
      return (new RubricColumnFactory()).createAll (statement.executeQuery());
    } catch (SQLException e) {
      throw new ImproperRubricInfo (e.getMessage());
    }
  }
}
