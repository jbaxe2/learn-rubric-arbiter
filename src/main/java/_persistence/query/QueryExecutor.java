package _persistence.query;

import java.util.List;

/**
 * The [QueryExecutor] interface...
 */
public interface QueryExecutor<T> {
  /**
   * The [getLatestResult] method...
   */
  T getLatestResult();

  /**
   * The [getLatestGroupedResult] method...
   */
  List<T> getLatestGroupedResult();
}
