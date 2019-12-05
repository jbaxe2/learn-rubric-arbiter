package _persistence.rubric;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import _error.ImproperRubricInfo;
import _factory.RubricFactory;

import _persistence.query.QueryExecutor;
import _persistence.query.PreparedQueryExecutor;

import rubric.Rubric;

/**
 * The [RubricsQueryExecutor] class...
 */
public class RubricQueryExecutor
    extends PreparedQueryExecutor implements QueryExecutor {
  private List<Rubric> rubrics;

  private RubricFactory rubricFactory;

  /**
   * The [RubricsQueryExecutor] constructor...
   */
  RubricQueryExecutor (PreparedStatement statement) {
    super (statement);

    rubrics = new ArrayList<>();
    rubricFactory = new RubricFactory();
  }

  /**
   * The [retrieveRubrics] method...
   */
  public List<Rubric> retrieveRubrics() throws ImproperRubricInfo {
    try {
      return rubrics = rubricFactory.createAll (statement.executeQuery());
    } catch (SQLException e) {
      throw new ImproperRubricInfo (e.getMessage());
    }
  }

  /**
   * The [getRubrics] method...
   */
  public List<Rubric> getRubrics() {
    return rubrics;
  }
}
