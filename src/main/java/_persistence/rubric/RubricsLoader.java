package _persistence.rubric;

import java.sql.Connection;
import java.util.List;

import _error.ImproperRubricInfo;
import rubric.Rubric;

/**
 * The [RubricsLoader] class...
 */
public class RubricsLoader {
  private RubricQueryBuilder queryBuilder;

  /**
   * The [RubricsLoader] constructor...
   */
  public RubricsLoader (Connection connection) {
    queryBuilder = new RubricQueryBuilder (connection);
  }

  /**
   * The [loadRubricsByCourseId] method...
   */
  public List<Rubric> loadRubricsByCourseId (String courseId)
      throws ImproperRubricInfo {
    RubricQueryExecutor rubricExecutor;

    try {
      rubricExecutor = new RubricQueryExecutor (
        queryBuilder.buildRetrieveRubricsByCourseIdQuery (courseId)
      );
    } catch (Exception e) {
      throw new ImproperRubricInfo (e.getMessage());
    }

    return rubricExecutor.retrieveRubricsForCourse();
  }

  /**
   * The [loadRubricById] method...
   */
  public Rubric loadRubricById (String rubricId) throws ImproperRubricInfo {
    RubricQueryExecutor rubricExecutor;

    try {
      rubricExecutor = new RubricQueryExecutor (
          queryBuilder.buildRetrieveRubricByIdQuery (rubricId)
      );
    } catch (Exception e) {
      throw new ImproperRubricInfo (e.getMessage());
    }

    return rubricExecutor.retrieveRubric();
  }
}
