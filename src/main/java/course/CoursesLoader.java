package course;

import blackboard.data.course.Course;

import blackboard.persist.Id;
import blackboard.persist.PersistenceException;

import blackboard.persist.course.CourseDbLoader;

/**
 * The [CoursesLoader] class...
 */
class CoursesLoader {
  final private CourseDbLoader coursesLoader;

  /**
   * The [CoursesLoader] constructor...
   */
  CoursesLoader (CourseDbLoader coursesLoader) {
    this.coursesLoader = coursesLoader;
  }

  /**
   * The [loadByCourseId] method...
   */
  SimpleCourse loadByCourseId (String courseId) throws PersistenceException {
    Id bbCourseId = Id.toId (Course.DATA_TYPE, courseId);

    return _loadCourseById (bbCourseId);
  }

  /**
   * The [_loadCourseById] method...
   */
  private SimpleCourse _loadCourseById (Id courseId) throws PersistenceException {
    return new SimpleCourse (coursesLoader.loadById (courseId));
  }
}
