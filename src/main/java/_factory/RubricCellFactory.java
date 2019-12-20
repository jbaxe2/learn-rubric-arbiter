package _factory;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import rubric.RubricCell;

/**
 * The [RubricCellFactory] class...
 */
public class RubricCellFactory implements ResultSettingFactory<RubricCell> {
  /**
   * The [create] method...
   */
  public RubricCell create (ResultSet rawResult) throws SQLException {
    if (!rawResult.next()) {
      return null;
    }

    return new RubricCell (
      rawResult.getString ("pk1"),
      rawResult.getString ("rubric_row_pk1"),
      rawResult.getString ("rubric_column_pk1"),
      rawResult.getString ("description"),
      rawResult.getFloat ("numeric_points"),
      rawResult.getFloat ("start_point_range"),
      rawResult.getFloat ("end_point_range"),
      rawResult.getFloat ("percentage"),
      rawResult.getFloat ("percentage_min"),
      rawResult.getFloat ("percentage_max")
    );
  }

  /**
   * The [createAll] method...
   */
  public List<RubricCell> createAll (ResultSet rawResults) throws SQLException {
    List<RubricCell> rubricCells = new ArrayList<>();

    while (rawResults.next()) {
      rubricCells.add (create (rawResults));
    }

    return rubricCells;
  }
}
