package _persistence.rubric;

import java.sql.Connection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import _error.ImproperRubricInfo;

import course.SimpleCourse;

import rubric.*;

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
   * The [loadRubricsForCourses] method...
   */
  public Map<SimpleCourse, List<Rubric>> loadRubricsForCourses (
    List<SimpleCourse> courses
  ) throws ImproperRubricInfo {
    Map<SimpleCourse, List<Rubric>> courseRubrics = new HashMap<>();

    for (SimpleCourse course : courses) {
      courseRubrics.put (course, loadRubricsByCourseId (course.getPrimaryKey()));
    }

    return courseRubrics;
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

  /**
   * The [loadRubricsByCourseId] method...
   */
  public List<Rubric> loadRubricsByCourseId (String courseId)
      throws ImproperRubricInfo {
    RubricsForCourseQueryExecutor rubricExecutor;

    try {
      rubricExecutor = new RubricsForCourseQueryExecutor (
        queryBuilder.buildRetrieveRubricsByCourseIdQuery (courseId)
      );
    } catch (Exception e) {
      throw new ImproperRubricInfo (e.getMessage());
    }

    return rubricExecutor.retrieveRubricsForCourse();
  }

  /**
   * The [loadRubricColumnsByRubricId] method...
   */
  public List<RubricColumn> loadRubricColumnsByRubricId (String rubricId)
      throws ImproperRubricInfo {
    RubricColumnsQueryExecutor rubricExecutor;

    try {
      rubricExecutor = new RubricColumnsQueryExecutor (
        queryBuilder.buildRetrieveColumnsByRubricIdQuery (rubricId)
      );
    } catch (Exception e) {
      throw new ImproperRubricInfo (e.getMessage());
    }

    return rubricExecutor.retrieveRubricColumns();
  }

  /**
   * The [loadRubricRowsByRubricId] method...
   */
  public List<RubricRow> loadRubricRowsByRubricId (String rubricId)
      throws ImproperRubricInfo {
    RubricRowsQueryExecutor rubricExecutor;

    try {
      rubricExecutor = new RubricRowsQueryExecutor (
        queryBuilder.buildRetrieveRowsByRubricIdQuery (rubricId)
      );
    } catch (Exception e) {
      throw new ImproperRubricInfo (e.getMessage());
    }

    return rubricExecutor.retrieveRubricRows();
  }

  /**
   * The [loadRubricCellByColumnRowIds] method...
   */
  public List<RubricCell> loadRubricCellByColumnRowIds (
    String columnId, String rowId
  ) throws ImproperRubricInfo {
    RubricCellQueryExecutor rubricExecutor;

    try {
      rubricExecutor = new RubricCellQueryExecutor (
        queryBuilder.buildRetrieveRubricCellByColumnRowIds (columnId, rowId)
      );
    } catch (Exception e) {
      throw new ImproperRubricInfo (e.getMessage());
    }

    return rubricExecutor.retrieveRubricCell();
  }
}
