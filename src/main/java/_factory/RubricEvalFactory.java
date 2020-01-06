package _factory;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import _formatting.FormatType;

import rubric.RubricEval;

/**
 * The [RubricEvalFactory] class...
 */
public class RubricEvalFactory implements ResultSettingFactory<RubricEval> {
  /**
   * The [create] method...
   */
  public RubricEval create (ResultSet rawResult) throws SQLException {
    return new RubricEval (
      rawResult.getString ("pk1"),
      rawResult.getString ("rubric_link_pk1"),
      rawResult.getString ("rubric_association_pk1"),
      rawResult.getString ("reviewer_user_pk1"),
      rawResult.getString ("reviewer_user_name"),
      rawResult.getString ("respondent_user_pk1"),
      rawResult.getString ("respondent_user_name"),
      rawResult.getBoolean ("completed_ind"),
      rawResult.getDate ("submission_date"),
      rawResult.getString ("comments"),
      _determineFormatType (rawResult.getString ("comments_format_type")),
      rawResult.getFloat ("max_value"),
      rawResult.getFloat ("total_value"),
      rawResult.getFloat ("override_value"),
      rawResult.getFloat ("calculated_percent")
    );
  }

  /**
   * The [createAll] method...
   */
  public List<RubricEval> createAll (ResultSet rawResults) throws SQLException {
    List<RubricEval> rubricEvals = new ArrayList<>();

    while (rawResults.next()) {
      rubricEvals.add (create (rawResults));
    }

    return rubricEvals;
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
}
