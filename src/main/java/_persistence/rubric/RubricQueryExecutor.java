package _persistence.rubric;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import _error.ImproperRubricInfo;
import _factory.RubricFactory;
import _persistence.query.PreparedQueryExecutor;

import rubric.Rubric;

/**
 * The [RubricsQueryExecutor] class...
 */
class RubricQueryExecutor extends PreparedQueryExecutor {
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
  public List<Rubric> retrieveRubricsForCourse() throws ImproperRubricInfo {
    try {
      return rubrics = rubricFactory.createAll (statement.executeQuery());
    } catch (SQLException e) {
      throw new ImproperRubricInfo (e.getMessage());
    }
  }

  /**
   * The [retrieveRubric] method...
   */
  public Rubric retrieveRubric() throws ImproperRubricInfo {
    Rubric rubric;

    try {
      rubric = rubricFactory.create (statement.executeQuery());
    } catch (SQLException e) {
      throw new ImproperRubricInfo (e.getMessage());
    }

    rubrics = new ArrayList<>();
    rubrics.add (rubric);

    return rubric;
  }

  /**
   * The [getRubrics] method...
   */
  public List<Rubric> getRubrics() {
    return rubrics;
  }
}
