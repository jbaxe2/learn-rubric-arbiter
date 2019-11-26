package _context;

import blackboard.platform.context.Context;
import blackboard.platform.context.ContextManager;
import blackboard.platform.context.ContextManagerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * The [BlackboardContext] class...
 */
public class BlackboardContext {
  private Context context;

  /**
   * The [BlackboardContext] constructor...
   */
  public BlackboardContext (HttpServletRequest request) {
    ContextManager contextManager = ContextManagerFactory.getInstance();

    context = contextManager.setContext (request);
  }

  /**
   * The [getContextCourseId] method...
   */
  public String getContextCourseId() {
    return context.getCourseId().getExternalString();
  }

  /**
   * The [getContextUserId] method...
   */
  public String getContextUserId() {
    return context.getUserId().getExternalString();
  }
}
