package _factory;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import rubric.Rubric;
import rubric.RubricType;

/**
 * The [RubricFactory] class...
 */
public class RubricFactory implements ResultSettingFactory<Rubric> {
  /**
   * The [create] method...
   */
  public Rubric create (ResultSet rawResult) throws SQLException {
    return new Rubric (
      rawResult.getString ("pk1"),
      rawResult.getString ("course_pk1"),
      rawResult.getString ("title"),
      rawResult.getString ("rubric_desc"),
      rawResult.getString ("creator_user_pk1"),
      _determineRubricType (rawResult.getString ("type").charAt (0)),
      rawResult.getFloat ("max_value"),
      rawResult.getDate ("dtcreated"),
      rawResult.getDate ("dtmodified")
    );
  }

  /**
   * The [createAll] method...
   */
  public Iterable<Rubric> createAll (ResultSet rawResults) {
    return new ArrayList<>();
  }

  /**
   * The [_determineRubricType] method...
   */
  private RubricType _determineRubricType (char rawType) {
    switch (rawType) {
      case 'T': return RubricType.T;
      case 'R': return RubricType.R;
      case 'P': return RubricType.P;
      case 'Q': return RubricType.Q;
      case 'N':
      default: return RubricType.N;
    }
  }
}
