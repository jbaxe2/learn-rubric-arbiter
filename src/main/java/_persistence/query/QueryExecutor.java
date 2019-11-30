package _persistence.query;

import java.util.List;

/**
 * The [QueryExecutor] interface...
 */
public interface QueryExecutor<T> {
  /**
   * The [getLastResult] method...
   */
  T getLastResult();

  /**
   * The [getLastGroupedResults] method...
   */
  List<T> getLastGroupedResults();
}
