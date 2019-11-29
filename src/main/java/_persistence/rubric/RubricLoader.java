package _persistence.rubric;

import rubric.Rubric;
import rubric.RubricType;

import java.sql.Connection;

import java.util.ArrayList;
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
  Rubric loadRubricById (String rubricPk) {
    return new Rubric (
      "", "", "", "", "",
      RubricType.N, (float) 0.0, new Date(), new Date()
    );
  }

  /**
   * The [loadRubricsByCourseId] method...
   */
  List<Rubric> loadRubricsByCourseId (String courseId) {
    return new ArrayList<>();
  }
}
