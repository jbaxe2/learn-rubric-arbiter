package _persistence.rubric;

import _error.ImproperRubricInfo;

import rubric.Rubric;
import rubric.RubricType;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.Date;
import java.util.List;

/**
 * The [RubricLoader] class...
 */
public class RubricLoader {
  private RubricQueryBuilder queryBuilder;

  /**
   * The [RubricLoader] constructor...
   */
  RubricLoader (Connection connection) {
    queryBuilder = new RubricQueryBuilder (connection);
  }

  /**
   * The [loadRubricById] method...
   */
  Rubric loadRubricById (String rubricId) {
    return new Rubric (
      "", "", "", "", "",
      RubricType.N, (float) 0.0, new Date(), new Date()
    );
  }

  /**
   * The [loadRubricsByCourseId] method...
   */
  List<Rubric> loadRubricsByCourseId (String courseId) throws ImproperRubricInfo {
    CourseRubricsQueryExecutor rubricExecutor;

    try {
      rubricExecutor = new CourseRubricsQueryExecutor (
        queryBuilder.buildRetrieveRubricsByCourseIdQuery (courseId)
      );
    } catch (SQLException e) {
      throw new ImproperRubricInfo (e.getMessage());
    }

    return rubricExecutor.retrieveCourseRubrics();
  }
}
