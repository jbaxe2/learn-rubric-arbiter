package _persistence.rubric;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.List;

import _error.ImproperRubricInfo;
import _factory.RubricCellEvalFactory;

import rubric.RubricCellEval;

/**
 * The [RubricCellEvalQueryExecutor] class...
 */
class RubricCellEvalQueryExecutor extends RubricQueryExecutor {
  /**
   * The [RubricCellEvalQueryExecutor] constructor...
   */
  RubricCellEvalQueryExecutor (PreparedStatement statement) {
    super (statement);
  }

  /**
   * The [retrieveRubricCellEvals] method...
   */
  List<RubricCellEval> retrieveRubricCellEvals() throws ImproperRubricInfo {
    try {
      return (new RubricCellEvalFactory()).createAll (statement.executeQuery());
    } catch (SQLException e) {
      throw new ImproperRubricInfo (e.getMessage());
    }
  }
}
