package membership;

import java.util.ArrayList;
import java.util.List;

import blackboard.data.course.CourseMembership;

import blackboard.persist.Id;
import blackboard.persist.PersistenceException;
import blackboard.persist.course.CourseMembershipDbLoader;

/**
 * The [MembershipsLoader] class...
 */
public class MembershipsLoader {
  final private CourseMembershipDbLoader membershipsLoader;

  final private String evaluatorRoleId;

  /**
   * The [MembershipsLoader] constructor...
   */
  public MembershipsLoader (
    CourseMembershipDbLoader membershipsLoader, String evaluatorRoleId
  ) {
    this.membershipsLoader = membershipsLoader;
    this.evaluatorRoleId = evaluatorRoleId;
  }

  /**
   * The [loadRubricEvaluatorMemberships] method...
   */
  public List<SimpleMembership> loadRubricEvaluatorMemberships (String userId)
      throws PersistenceException {
    Id userBbId = Id.toId (CourseMembership.DATA_TYPE, userId);

    return _loadRubricEvaluatorMemberships (userBbId);
  }

  /**
   * The [_loadRubricEvaluatorMemberships] method...
   */
  private List<SimpleMembership> _loadRubricEvaluatorMemberships (Id userId)
      throws PersistenceException {
    List<SimpleMembership> memberships = new ArrayList<>();

    List<CourseMembership> bbMemberships = membershipsLoader.loadByUserId (userId);

    for (CourseMembership bbMembership : bbMemberships) {
      if (_isRubricEvaluatorRole (bbMembership)) {
        memberships.add (new SimpleMembership (bbMembership));
      }
    }

    return memberships;
  }

  /**
   * The [_isRubricEvaluatorRole] method...
   */
  private boolean _isRubricEvaluatorRole (CourseMembership bbMembership) {
    return evaluatorRoleId.equals (bbMembership.getRole().getIdentifier());
  }
}
