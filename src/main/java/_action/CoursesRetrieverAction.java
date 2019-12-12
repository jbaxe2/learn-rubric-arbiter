package _action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import blackboard.persist.course.CourseDbLoader;
import blackboard.persist.course.CourseMembershipDbLoader;

import _context.BlackboardContext;
import _persistence.PersistenceManager;

import course.SimpleCourse;
import course.SimpleCoursesRetriever;

/**
 * The [CoursesRetrieverAction] class...
 */
public class CoursesRetrieverAction implements Actionable {
  private List<SimpleCourse> courses;

  final private HttpServletRequest request;

  final private String roleId;

  /**
   * The [CoursesRetrieverAction] constructor...
   */
  public CoursesRetrieverAction (HttpServletRequest request, String roleId) {
    this.request = request;
    this.roleId = roleId;

    courses = new ArrayList<>();
  }

  /**
   * The [perform] method...
   */
  public void perform() throws Exception {
    BlackboardContext context = new BlackboardContext (request);
    String userId = context.getContextUserId();
    SimpleCoursesRetriever retriever = new SimpleCoursesRetriever (userId);

    PersistenceManager manager = PersistenceManager.getInstance();
    manager.establishConnection();

    CourseDbLoader courseLoader = manager.getCourseDbLoader();
    CourseMembershipDbLoader membershipLoader = manager.getMembershipDbLoader();

    courses = retriever.retrieveSimpleCourses (
      membershipLoader, courseLoader, roleId
    );
  }

  /**
   * The [filterByIds] method...
   */
  public List<SimpleCourse> filterByIds (String[] courseIds) {
    List<SimpleCourse> filteredCourses = new ArrayList<>();

    for (String courseId : courseIds) {
      for (SimpleCourse course : courses) {
        if (courseId.equals (course.getPrimaryKey())) {
          filteredCourses.add (course);

          continue;
        }
      }
    }

    return filteredCourses;
  }

  /**
   * The [getCourses] method...
   */
  public List<SimpleCourse> getCourses() {
    return courses;
  }
}
