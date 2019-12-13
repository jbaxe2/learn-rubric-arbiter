package _factory;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import rubric.RubricRow;

/**
 * The [RubricRowFactory] class...
 */
public class RubricRowFactory implements ResultSettingFactory<RubricRow> {
  /**
   * The [create] method...
   */
  public RubricRow create (ResultSet rawResult) throws SQLException {
    return new RubricRow (
      rawResult.getString ("pk1"), rawResult.getString ("rubric_pk1"),
      rawResult.getString ("header"), rawResult.getInt ("position"),
      rawResult.getFloat ("percentage")
    );
  }

  /**
   * The [createAll] method...
   */
  public List<RubricRow> createAll (ResultSet rawResults) throws SQLException {
    List<RubricRow> rubricRows = new ArrayList<>();

    while (rawResults.next()) {
      rubricRows.add (create (rawResults));
    }

    return rubricRows;
  }
}
