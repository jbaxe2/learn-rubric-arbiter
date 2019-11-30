package _persistence.rubric;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import _error.ImproperRubricInfo;
import _factory.AspectedRubricFactory;
import _persistence.query.QueryExecutor;

import rubric.Rubric;

/**
 * The [CourseRubricsQueryExecutor] class...
 */
public class CourseRubricsQueryExecutor
    extends RubricQueryExecutor implements QueryExecutor<Rubric> {
  private List<Rubric> rubrics;

  private AspectedRubricFactory rubricFactory;

  /**
   * The [CourseRubricsQueryExecutor] constructor...
   */
  CourseRubricsQueryExecutor (PreparedStatement statement) {
    super (statement);

    rubrics = new ArrayList<>();
    rubricFactory = new AspectedRubricFactory();
  }

  /**
   * The [retrieveCourseRubrics] method...
   */
  public List<Rubric> retrieveCourseRubrics() throws ImproperRubricInfo {
    try {
      rubrics = rubricFactory.createAll (statement.executeQuery());
    } catch (SQLException e) {
      throw new ImproperRubricInfo (e.getMessage());
    }

    return rubrics;
  }

  /**
   * The [getLastResult] method...
   */
  public Rubric getLastResult() {
    return rubrics.get (0);
  }

  /**
   * The [getLastGroupedResults] method...
   */
  public List<Rubric> getLastGroupedResults() {
    return rubrics;
  }
}
