package _action;

/**
 * The [Actionable] interface...
 */
public interface Actionable {
  /**
   * The [perform] method...
   */
  void perform() throws Exception;

  /**
   * The [filterByIds] method...
   */
  Object filterByIds (String[] filterIds);
}
