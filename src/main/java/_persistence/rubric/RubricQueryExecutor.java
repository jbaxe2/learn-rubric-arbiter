package _persistence.rubric;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.List;

import _error.ImproperRubricInfo;

import _factory.RubricColumnFactory;
import _factory.RubricFactory;
import _factory.RubricRowFactory;

import _persistence.query.PreparedQueryExecutor;

import rubric.Rubric;
import rubric.RubricColumn;
import rubric.RubricRow;

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
   * The [retrieveRubrics] method...
   */
  List<Rubric> retrieveRubricsForCourse() throws ImproperRubricInfo {
    try {
      return (new RubricFactory()).createAll (statement.executeQuery());
    } catch (SQLException e) {
      throw new ImproperRubricInfo (e.getMessage());
    }
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
