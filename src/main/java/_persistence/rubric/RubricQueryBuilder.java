package _persistence.rubric;

import _persistence.query.PreparedQueryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The [RubricQueryBuilder] class...
 */
public class RubricQueryBuilder extends PreparedQueryBuilder {
  /**
   * The [RubricQueryBuilder] constructor...
   */
  public RubricQueryBuilder (Connection connection) {
    super (connection);
  }

  /**
   * The [buildRetrieveRubricsByCourseIdQuery] method...
   */
  public PreparedStatement buildRetrieveRubricsByCourseIdQuery (
    String courseId
  ) throws SQLException {
    String query = "SELECT * FROM rubric WHERE course_pk1 = ?";

    PreparedStatement statement = connection.prepareStatement (query);
    statement.setString (1, courseId.split ("_")[1]);

    return statement;
  }

  /**
   * The [buildRetrieveRubricByIdQuery] method...
   */
  public PreparedStatement buildRetrieveRubricByIdQuery (
    String rubricId
  ) throws SQLException {
    String query = "SELECT * FROM rubric WHERE pk1 = ?";

    PreparedStatement statement = connection.prepareStatement (query);
    statement.setString (1, rubricId.split ("_")[1]);

    return statement;
  }

  /**
   * The [buildRetrieveColumnsByRubricIdQuery] method...
   */
  public PreparedStatement buildRetrieveColumnsByRubricIdQuery (
    String rubricId
  ) throws SQLException {
    String query =
      "SELECT * FROM rubric_column WHERE rubric_pk1 = ? GROUP BY rubric_pk1";

    PreparedStatement statement = connection.prepareStatement (query);
    statement.setString (1, rubricId.split ("_")[1]);

    return statement;
  }
}
