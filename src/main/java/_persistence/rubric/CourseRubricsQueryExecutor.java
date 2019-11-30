package _persistence.rubric;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import _error.ImproperRubricInfo;
import _factory.RubricFactory;
import _persistence.query.QueryExecutor;

import rubric.Rubric;

/**
 * The [CourseRubricsQueryExecutor] class...
 */
public class CourseRubricsQueryExecutor
    extends RubricQueryExecutor implements QueryExecutor<Rubric> {
  private List<Rubric> rubrics;

  private RubricFactory rubricFactory;

  /**
   * The [CourseRubricsQueryExecutor] constructor...
   */
  CourseRubricsQueryExecutor (PreparedStatement statement) {
    super (statement);

    rubrics = new ArrayList<>();
    rubricFactory = new RubricFactory();
  }

  /**
   * The [retrieveCourseRubrics] method...
   */
  public List<Rubric> retrieveCourseRubrics() throws ImproperRubricInfo {
    try {
      return rubrics = rubricFactory.createAll (statement.executeQuery());
    } catch (SQLException e) {
      throw new ImproperRubricInfo (e.getMessage());
    }
  }

  /**
   * The [getLatestResult] method...
   */
  public Rubric getLatestResult() {
    return rubrics.get (0);
  }

  /**
   * The [getLatestGroupedResults] method...
   */
  public List<Rubric> getLatestGroupedResult() {
    return rubrics;
  }
}
