package _factory;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

/**
 * The [ResultSettingFactory] interface...
 */
public interface ResultSettingFactory<T> {
  /**
   * The [create] method...
   */
  T create (ResultSet rawResult) throws SQLException;

  /**
   * The [createAll] method...
   */
  List<T> createAll (ResultSet rawResults) throws SQLException;
}
