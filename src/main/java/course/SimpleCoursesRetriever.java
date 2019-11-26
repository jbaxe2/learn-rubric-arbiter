package course;

import java.util.ArrayList;
import java.util.List;

import blackboard.persist.PersistenceException;
import blackboard.persist.course.CourseDbLoader;
import blackboard.persist.course.CourseMembershipDbLoader;

import membership.MembershipsLoader;
import membership.SimpleMembership;

/**
 * The [SimpleCoursesRetriever] class...
 */
public class SimpleCoursesRetriever {
  final private String userId;

  final private String evaluatorId;

  /**
   * The [SimpleCoursesRetriever] constructor...
   */
  public SimpleCoursesRetriever (String userId, String evaluatorId) {
    this.userId = userId;
    this.evaluatorId = evaluatorId;
  }

  /**
   * The [retrieveSimpleCourses] method...
   */
  public List<SimpleCourse> retrieveSimpleCourses (
    CourseMembershipDbLoader membershipsLoader, CourseDbLoader courseLoader
  ) throws PersistenceException {
    List<SimpleMembership> memberships = _loadMemberships (membershipsLoader);
    List<String> courseIds = _extractRubricCourseIds (memberships);

    return _loadCourses (courseLoader, courseIds);
  }

  /**
   * The [_loadMemberships] method...
   */
  private List<SimpleMembership> _loadMemberships (
    CourseMembershipDbLoader membershipsLoader
  ) throws PersistenceException {
    MembershipsLoader loader =
      new MembershipsLoader (membershipsLoader, evaluatorId);

    return loader.loadRubricEvaluatorMemberships (userId);
  }

  /**
   * The [_extractRubricCourseIds] method...
   */
  private List<String> _extractRubricCourseIds (List<SimpleMembership> memberships) {
    List<String> courseIds = new ArrayList<>();

    for (SimpleMembership membership : memberships) {
      courseIds.add (membership.getPrimaryKey());
    }

    return courseIds;
  }

  /**
   * The [_loadCourses] method...
   */
  private List<SimpleCourse> _loadCourses (CourseDbLoader courseLoader, List<String> courseIds)
      throws PersistenceException {
    List<SimpleCourse> courses = new ArrayList<>();
    CoursesLoader loader = new CoursesLoader (courseLoader);

    for (String courseId : courseIds) {
      courses.add (loader.loadByCourseId (courseId));
    }

    return courses;
  }
}
