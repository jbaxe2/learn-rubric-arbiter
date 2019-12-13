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
}
