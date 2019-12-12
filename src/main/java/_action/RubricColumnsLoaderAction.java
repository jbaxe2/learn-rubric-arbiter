package _action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import _persistence.PersistenceManager;
import _persistence.rubric.RubricsLoader;

import rubric.Rubric;
import rubric.RubricColumn;

/**
 * The [RubricColumnsLoaderAction] class...
 */
public class RubricColumnsLoaderAction implements Actionable {
  private Map<Rubric, List<RubricColumn>> rubricColumns;

  final private List<Rubric> rubrics;

  /**
   * The [RubricColumnsLoaderAction] constructor...
   */
  public RubricColumnsLoaderAction (List<Rubric> rubrics) {
    this.rubrics = rubrics;

    rubricColumns = new HashMap<>();
  }

  /**
   * The [perform] method...
   */
  public void perform() throws Exception {
    PersistenceManager manager = PersistenceManager.getInstance();
    manager.establishConnection();

    RubricsLoader loader = new RubricsLoader (manager.getConnection());

    for (Rubric rubric : rubrics) {
      rubricColumns.put (
        rubric, loader.loadRubricColumnsByRubricId (rubric.getPrimaryKey())
      );
    }
  }

  /**
   * The [filterByIds] method...
   */
  public Map<Rubric, List<RubricColumn>> filterByIds (String[] columnIds) {
    Map<Rubric, List<RubricColumn>> filteredColumns = new HashMap<>();

    return filteredColumns;
  }

  /**
   * The [getRubricColumns] method...
   */
  public Map<Rubric, List<RubricColumn>> getRubricColumns() {
    return rubricColumns;
  }
}
