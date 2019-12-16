package formulation;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;

import _action.CoursesRetrieverAction;
import _action.RubricsLoaderAction;

import _error.ImproperRubricInfo;

import course.SimpleCourse;

import rubric.Rubric;
import rubric.RubricRow;

/**
 * The [Formulation] abstract class...
 */
public abstract class Formulation implements Formulatable {
  protected List<SimpleCourse> courses;

  protected Map<SimpleCourse, List<Rubric>> coursesRubrics;

  protected Map<Rubric, List<RubricRow>> rubricsCritera;

  /**
   * The [Formulation] constructor...
   */
  protected Formulation (
    HttpServletRequest request, String roleId,
    String[] courseIds, String[] rubricIds, String[] criteriaIds
  ) throws Exception {
    _performRetrieveCourses (request, roleId, courseIds);
    _performRetrieveRubrics (rubricIds);
    _performRetrieveCriteria (criteriaIds);
  }

  /**
   * The [formulate] abstract method...
   */
  public abstract void formulate() throws ImproperRubricInfo;

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
  private void _performRetrieveCriteria (String[] criteriaIds) {
    ;
  }
}
