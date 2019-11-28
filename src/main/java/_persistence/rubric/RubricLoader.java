package _persistence.rubric;

import java.sql.Connection;

/**
 * The [RubricLoader] class...
 */
public class RubricLoader {
  final private Connection connection;

  /**
   * The [RubricLoader] constructor...
   */
  RubricLoader (Connection connection) {
    this.connection = connection;
  }
}
