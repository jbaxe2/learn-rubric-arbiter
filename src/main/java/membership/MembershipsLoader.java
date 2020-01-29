package membership;

import java.util.ArrayList;
import java.util.List;

import blackboard.data.course.CourseMembership;

import blackboard.data.user.User;

import blackboard.persist.Id;
import blackboard.persist.PersistenceException;

import blackboard.persist.course.CourseMembershipDbLoader;

/**
 * The [MembershipsLoader] class...
 */
public class MembershipsLoader {
  final private CourseMembershipDbLoader membershipsLoader;

  /**
   * The [MembershipsLoader] constructor...
   */
  public MembershipsLoader (CourseMembershipDbLoader membershipsLoader) {
    this.membershipsLoader = membershipsLoader;
  }

  /**
   * The [loadInstructorAndRubricEvaluatorMemberships] method...
   */
  public List<SimpleMembership> loadInstructorAndRubricEvaluatorMemberships (
    String userId, String evaluatorRoleId
  ) throws PersistenceException {
    List<SimpleMembership> memberships = new ArrayList<>();

    memberships.addAll (loadInstructorMemberships (userId));
    memberships.addAll (loadRubricEvaluatorMemberships (userId, evaluatorRoleId));

    return memberships;
  }

  /**
   * The [loadInstructorMemberships] method...
   */
  public List<SimpleMembership> loadInstructorMemberships (String userId)
      throws PersistenceException {
    List<SimpleMembership> memberships = new ArrayList<>();
    Id userBbId = Id.toId (User.DATA_TYPE, userId);

    List<CourseMembership> bbMemberships =
      membershipsLoader.loadByUserId (userBbId);

    for (CourseMembership bbMembership : bbMemberships) {
      if (bbMembership.getRole().equals (CourseMembership.Role.INSTRUCTOR)) {
        memberships.add (new SimpleMembership (bbMembership));
      }
    }

    return memberships;
  }

  /**
   * The [loadRubricEvaluatorMemberships] method...
   */
  public List<SimpleMembership> loadRubricEvaluatorMemberships (
    String userId, String evaluatorRoleId
  ) throws PersistenceException {
    Id userBbId = Id.toId (User.DATA_TYPE, userId);

    return _loadRubricEvaluatorMemberships (userBbId, evaluatorRoleId);
  }

  /**
   * The [_loadRubricEvaluatorMemberships] method...
   */
  private List<SimpleMembership> _loadRubricEvaluatorMemberships (
    Id userId, String evaluatorRoleId
  ) throws PersistenceException {
    List<SimpleMembership> memberships = new ArrayList<>();

    List<CourseMembership> bbMemberships = membershipsLoader.loadByUserId (userId);

    for (CourseMembership bbMembership : bbMemberships) {
      if (_isRubricEvaluatorRole (bbMembership, evaluatorRoleId)) {
        memberships.add (new SimpleMembership (bbMembership));
      }
    }

    return memberships;
  }

  /**
   * The [_isRubricEvaluatorRole] method...
   */
  private boolean _isRubricEvaluatorRole (
    CourseMembership bbMembership, String evaluatorRoleId
  ) {
    return evaluatorRoleId.equals (bbMembership.getRole().getIdentifier());
  }
}
