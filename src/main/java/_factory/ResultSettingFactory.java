package _factory;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The [ResultSettingFactory] interface...
 */
public interface ResultSettingFactory<T> {
  /**
   * The [create] method...
   */
  public T create (ResultSet rawResult) throws SQLException;

  /**
   * The [createAll] method...
   */
  public Iterable<T> createAll (ResultSet rawResults) throws SQLException;
}
