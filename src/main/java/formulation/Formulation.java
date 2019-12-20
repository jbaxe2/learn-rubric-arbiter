package formulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import _error.InvalidFormulation;

import action.CoursesRetrieverAction;
import action.RubricsLoaderAction;
import action.RubricRowsLoaderAction;

import course.SimpleCourse;

import rubric.Rubric;
import rubric.RubricRow;

/**
 * The [Formulation] abstract class...
 */
public abstract class Formulation implements Formulatable {
  protected List<SimpleCourse> courses;

  protected List<Rubric> rubrics;

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
  public abstract Resultable formulate() throws InvalidFormulation;

  /**
   * The [getCourses] method...
   */
  public List<SimpleCourse> getCourses() {
    return new ArrayList<>(courses);
  }

  /**
   * The [getRubrics] method...
   */
  public List<Rubric> getRubrics() {
    return new ArrayList<>(rubrics);
  }

  /**
   * The [getCoursesRubrics] method...
   */
  public Map<SimpleCourse, List<Rubric>> getCoursesRubrics() {
    return new HashMap<>(coursesRubrics);
  }

  /**
   * The [getRubricsCriteria] method...
   */
  public Map<Rubric, List<RubricRow>> getRubricsCriteria() {
    return new HashMap<>(rubricsCriteria);
  }

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

    _flattenRubricsFromCourses();
  }

  /**
   * The [_performRetrieveCriteria] method...
   */
  private void _performRetrieveCriteria (String[] criteriaIds) throws Exception {
    RubricRowsLoaderAction criteriaLoader = new RubricRowsLoaderAction (rubrics);
    criteriaLoader.perform();

    rubricsCriteria = criteriaLoader.filterByIds (criteriaIds);
  }

  /**
   * The [_flattenRubricsFromCourses] method...
   */
  private void _flattenRubricsFromCourses() {
    rubrics = new ArrayList<>();

    for (List<Rubric> courseRubrics : coursesRubrics.values()) {
      rubrics.addAll (courseRubrics);
    }
  }
}
