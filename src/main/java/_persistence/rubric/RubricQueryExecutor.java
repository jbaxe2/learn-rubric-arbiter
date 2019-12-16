package _persistence.rubric;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import _error.ImproperRubricInfo;
import _factory.RubricFactory;

import _persistence._query.PreparedQueryExecutor;

import rubric.Rubric;

/**
 * The [RubricsQueryExecutor] class...
 */
class RubricQueryExecutor extends PreparedQueryExecutor {
  /**
   * The [RubricsQueryExecutor] constructor...
   */
  RubricQueryExecutor (PreparedStatement statement) {
    super (statement);
  }

  /**
   * The [retrieveRubric] method...
   */
  Rubric retrieveRubric() throws ImproperRubricInfo {
    try {
      return (new RubricFactory()).create (statement.executeQuery());
    } catch (SQLException e) {
      throw new ImproperRubricInfo (e.getMessage());
    }
  }
}
