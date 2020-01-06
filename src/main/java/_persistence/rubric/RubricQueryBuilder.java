package _persistence.rubric;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import _persistence._query.PreparedQueryBuilder;

/**
 * The [RubricQueryBuilder] class...
 */
class RubricQueryBuilder extends PreparedQueryBuilder {
  /**
   * The [RubricQueryBuilder] constructor...
   */
  RubricQueryBuilder (Connection connection) {
    super (connection);
  }

  /**
   * The [buildRetrieveRubricByIdQuery] method...
   */
  PreparedStatement buildRetrieveRubricByIdQuery (String rubricId)
      throws SQLException {
    String query = "SELECT * FROM rubric WHERE pk1 = ?";

    PreparedStatement statement = connection.prepareStatement (query);
    statement.setString (1, rubricId);

    return statement;
  }

  /**
   * The [buildRetrieveRubricsByCourseIdQuery] method...
   */
  PreparedStatement buildRetrieveRubricsByCourseIdQuery (String courseId)
      throws SQLException {
    String query = "SELECT * FROM rubric WHERE course_pk1 = ?";

    PreparedStatement statement = connection.prepareStatement (query);
    statement.setString (1, courseId.split ("_")[1]);

    return statement;
  }

  /**
   * The [buildRetrieveRubricEvalsByRubricIdQuery] method...
   */
  PreparedStatement buildRetrieveRubricEvalsByRubricIdQuery (String rubricId)
      throws SQLException {
    String query = "SELECT * FROM rubric_eval WHERE " +
      "rubric_association_pk1 = rubric_association.pk1 AND " +
      "rubric_association.rubric_pk1 = ?";

    PreparedStatement statement = connection.prepareStatement (query);
    statement.setString (1, rubricId);

    return statement;
  }

  /**
   * The [buildRetrieveColumnsByRubricIdQuery] method...
   */
  PreparedStatement buildRetrieveColumnsByRubricIdQuery (String rubricId)
      throws SQLException {
    String query =
      "SELECT * FROM rubric_column WHERE rubric_pk1 = ?";

    PreparedStatement statement = connection.prepareStatement (query);
    statement.setString (1, rubricId);

    return statement;
  }

  /**
   * The [buildRetrieveRowsByRubricIdQuery] method...
   */
  PreparedStatement buildRetrieveRowsByRubricIdQuery (String rubricId)
      throws SQLException {
    String query =
      "SELECT * FROM rubric_row WHERE rubric_pk1 = ?";

    PreparedStatement statement = connection.prepareStatement (query);
    statement.setString (1, rubricId);

    return statement;
  }

  /**
   * The [buildRetrieveRubricCellByColumnRowIds] method...
   */
  PreparedStatement buildRetrieveRubricCellByColumnRowIds (
    String columnId, String rowId
  ) throws SQLException {
    String query = "SELECT * FROM rubric_cell WHERE " +
      "rubric_column_pk1 = ? AND rubric_row_pk1 = ?";

    PreparedStatement statement = connection.prepareStatement (query);
    statement.setString (1, columnId);
    statement.setString (2, rowId);

    return statement;
  }

  /**
   * The [buildRetrieveRubricCellEvalsByRowAndCellIds] method...
   */
  PreparedStatement buildRetrieveRubricCellEvalsByRowAndCellIds (
    String rowId, String cellId
  ) throws SQLException {
    String query = "SELECT * FROM rubric_cell_eval WHERE " +
      "rubric_row_pk1 = ? AND rubric_cell_pk1 = ?";

    PreparedStatement statement = connection.prepareStatement (query);
    statement.setString (1, rowId);
    statement.setString (2, cellId);

    return statement;
  }
}
