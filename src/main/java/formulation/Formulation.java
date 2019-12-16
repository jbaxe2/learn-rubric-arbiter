package formulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import _action.CoursesRetrieverAction;
import _action.RubricsLoaderAction;
import _action.RubricRowsLoaderAction;

import _error.InvalidFormulation;

import course.SimpleCourse;

import rubric.Rubric;
import rubric.RubricRow;

/**
 * The [Formulation] abstract class...
 */
public abstract class Formulation implements Formulatable {
  protected List<SimpleCourse> courses;

  protected Map<SimpleCourse, List<Rubric>> coursesRubrics;

  protected Map<Rubric, List<RubricRow>> rubricsCriteria;

  /**
   * The [Formulation] constructor...
   */
  protected Formulation (
    HttpServletRequest request, String roleId,
    String[] courseIds, String[] rubricIds, String[] criteriaIds
  ) throws InvalidFormulation {
    try {
      _performRetrieveCourses (request, roleId, courseIds);
      _performRetrieveRubrics (rubricIds);
      _performRetrieveCriteria (criteriaIds);
    } catch (Exception e) {
      throw new InvalidFormulation (e.getMessage());
    }
  }

  /**
   * The [formulate] abstract method...
   */
  public abstract void formulate() throws InvalidFormulation;

  /**
   * The [_performRetrieveCourses] method...
   */
  private void _performRetrieveCourses (
    HttpServletRequest request, String roleId, String[] courseIds
  ) throws Exception {
    CoursesRetrieverAction coursesRetriever =
      new CoursesRetrieverAction (request, roleId);

    coursesRetriever.perform();

    courses = coursesRetriever.filterByIds (courseIds);
  }

  /**
   * The [_performRetrieveRubrics] method...
   */
  private void _performRetrieveRubrics (String[] rubricIds) throws Exception {
    RubricsLoaderAction rubricsLoader = new RubricsLoaderAction (courses);
    rubricsLoader.perform();

    coursesRubrics = rubricsLoader.filterByIds (rubricIds);
  }

  /**
   * The [_performRetrieveCriteria] method...
   */
  private void _performRetrieveCriteria (String[] criteriaIds) throws Exception {
    RubricRowsLoaderAction rubricsRowsLoader;
    List<Rubric> courseRubrics = new ArrayList<>();

    for (List<Rubric> rubrics : coursesRubrics.values()) {
      courseRubrics.addAll (rubrics);
    }

    rubricsRowsLoader = new RubricRowsLoaderAction (courseRubrics);
    rubricsRowsLoader.perform();

    rubricsCriteria = rubricsRowsLoader.filterByIds (criteriaIds);
  }
}
