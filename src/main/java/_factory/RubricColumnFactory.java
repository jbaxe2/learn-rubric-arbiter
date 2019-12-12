package _factory;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import rubric.RubricColumn;

/**
 * The [RubricColumnFactory] class...
 */
public class RubricColumnFactory implements ResultSettingFactory<RubricColumn> {
  /**
   * The [create] method...
   */
  public RubricColumn create (ResultSet rawResult) throws SQLException {
    return new RubricColumn (
      rawResult.getString ("pk1"), rawResult.getString ("rubric_pk1"),
      rawResult.getString ("header"), rawResult.getInt ("position")
    );
  }

  /**
   * The [createAll] method...
   */
  public List<RubricColumn> createAll (ResultSet rawResults) throws SQLException {
    List<RubricColumn> rubricColumns = new ArrayList<>();

    while (rawResults.next()) {
      rubricColumns.add (create (rawResults));
    }

    return rubricColumns;
  }
}
