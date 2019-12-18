package action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import _persistence.PersistenceManager;
import _persistence.rubric.RubricsLoader;

import course.SimpleCourse;
import rubric.Rubric;

/**
 * The [RubricsLoaderAction] class...
 */
public class RubricsLoaderAction implements Actionable {
  private Map<SimpleCourse, List<Rubric>> coursesRubrics;

  final private List<SimpleCourse> courses;

  /**
   * The [RubricsLoaderAction] constructor...
   */
  public RubricsLoaderAction (List<SimpleCourse> courses) {
    this.courses = courses;

    coursesRubrics = new HashMap<>();
  }

  /**
   * The [perform] method...
   */
  public void perform() throws Exception {
    PersistenceManager manager = PersistenceManager.getInstance();
    manager.establishConnection();

    RubricsLoader loader = new RubricsLoader (manager.getConnection());

    coursesRubrics = loader.loadRubricsForCourses (courses);
  }

  /**
   * The [filterByIds] method...
   */
  public Map<SimpleCourse, List<Rubric>> filterByIds (String[] rubricIds) {
    Map<SimpleCourse, List<Rubric>> filteredRubrics = new HashMap<>();

    for (String rubricId : rubricIds) {
      for (SimpleCourse simpleCourse : courses) {
        for (Rubric rubric : coursesRubrics.get (simpleCourse)) {
          if (rubricId.equals (rubric.getPrimaryKey())) {
            filteredRubrics.putIfAbsent (simpleCourse, new ArrayList<>());
            filteredRubrics.get (simpleCourse).add (rubric);
          }
        }
      }
    }

    return filteredRubrics;
  }

  /**
   * The [getCourseRubrics] method...
   */
  public Map<SimpleCourse, List<Rubric>> getCoursesRubrics() {
    return coursesRubrics;
  }
}
