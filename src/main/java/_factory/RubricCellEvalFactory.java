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
      rawResult.getString ("rubric_eval_pk1"),
      rawResult.getString ("rubric_row_pk1"),
      rawResult.getString ("rubric_cell_pk1"),
      _determineFeedback (rawResult.getString ("feedback")),
      _determineFormatType (rawResult.getString ("feedback_format_type")),
      rawResult.getFloat ("selected_percent"),
      _determineOverrideInd (rawResult.getString ("override_ind"))
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
   * The [_determineFeedback] method...
   */
  private String _determineFeedback (String feedback) {
    if (null == feedback) {
      return "N/A";
    }

    return feedback;
  }

  /**
   * The [_determineFormatType] method...
   */
  private FormatType _determineFormatType (String rawTypeStr) {
    char rawType = 'P';

    if ((null != rawTypeStr) && (0 < rawTypeStr.length())) {
      rawType = rawTypeStr.charAt (0);
    }

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
  private boolean _determineOverrideInd (String rawIndStr) {
    char rawInd = 'Y';

    if ((null != rawIndStr) && (0 < rawIndStr.length())) {
      rawInd = rawIndStr.charAt (0);
    }

    return 'Y' == rawInd;
  }
}
