package _persistence.rubric;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.List;

import _error.ImproperRubricInfo;
import _factory.RubricEvalFactory;

import rubric.RubricEval;

/**
 * The [RubricEvalQueryExecutor] class...
 */
public class RubricEvalQueryExecutor extends RubricQueryExecutor {
  /**
   * The [RubricEvalQueryExecutor] constructor...
   */
  RubricEvalQueryExecutor (PreparedStatement statement) {
    super (statement);
  }

  /**
   * The [retrieveRubricEvals] method...
   */
  List<RubricEval> retrieveRubricEvals() throws ImproperRubricInfo {
    try {
      return (new RubricEvalFactory()).createAll (statement.executeQuery());
    } catch (SQLException e) {
      throw new ImproperRubricInfo (e.getMessage());
    }
  }
}
