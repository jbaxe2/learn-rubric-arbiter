package _factory;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import _formatting.FormatType;

import rubric.RubricCellEval;

/**
 * The [RubricCellEvalFactory] class...
 */
public class RubricCellEvalFactory
    implements ResultSettingFactory<RubricCellEval> {
  /**
   * The [create] method...
   */
  public RubricCellEval create (ResultSet rawResult) throws SQLException {
    return new RubricCellEval (
      rawResult.getString ("pk1"),
      rawResult.getString ("rubric_row_pk1"),
      rawResult.getString ("rubric_column_pk1"),
      rawResult.getString ("rubric_cell_pk1"),
      rawResult.getString ("feedback"),
      _determineFormatType (
        rawResult.getString ("feedback_format_type").charAt (0)
      ),
      rawResult.getFloat ("selected_percent"),
      _determineOverrideInd (
        rawResult.getString ("override_type").charAt (0)
      )
    );
  }

  /**
   * The [createAll] method...
   */
  public List<RubricCellEval> createAll (ResultSet rawResults) throws SQLException {
    List<RubricCellEval> cellEvals = new ArrayList<>();

    while (rawResults.next()) {
      cellEvals.add (create (rawResults));
    }

    return cellEvals;
  }

  /**
   * The [_determineFormatType] method...
   */
  private FormatType _determineFormatType (char rawType) {
    switch (rawType) {
      case 'H': return FormatType.H;
      case 'S': return FormatType.S;
      case 'P':
      default: return FormatType.P;
    }
  }

  /**
   * The [_determineOverrideInd] method...
   */
  private boolean _determineOverrideInd (char rawInd) {
    return 'Y' == rawInd;
  }
}
