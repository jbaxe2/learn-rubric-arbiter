package _persistence.rubric;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.List;

import _error.ImproperRubricInfo;
import _factory.RubricFactory;

import _persistence._query.PreparedQueryExecutor;

import rubric.Rubric;

/**
 * The [RubricsForCourseQueryExecutor] class...
 */
class RubricsForCourseQueryExecutor extends PreparedQueryExecutor {
  /**
   * The [RubricsForCourseQueryExecutor] constructor...
   */
  RubricsForCourseQueryExecutor (PreparedStatement statement) {
    super (statement);
  }

  /**
   * The [retrieveRubrics] method...
   */
  List<Rubric> retrieveRubricsForCourse() throws ImproperRubricInfo {
    try {
      return (new RubricFactory()).createAll (statement.executeQuery());
    } catch (SQLException e) {
      throw new ImproperRubricInfo (e.getMessage());
    }
  }
}
