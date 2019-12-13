package _action;

import _persistence.PersistenceManager;
import _persistence.rubric.RubricsLoader;

import rubric.Rubric;
import rubric.RubricRow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The [RubricRowsLoaderAction] class...
 */
public class RubricRowsLoaderAction implements Actionable {
  private Map<Rubric, List<RubricRow>> rubricRows;

  final private List<Rubric> rubrics;

  /**
   * The [RubricRowsLoaderAction] constructor...
   */
  public RubricRowsLoaderAction (List<Rubric> rubrics) {
    this.rubrics = rubrics;

    rubricRows = new HashMap<>();
  }

  /**
   * The [perform] method...
   */
  public void perform() throws Exception {
    PersistenceManager manager = PersistenceManager.getInstance();
    manager.establishConnection();

    RubricsLoader loader = new RubricsLoader (manager.getConnection());

    for (Rubric rubric : rubrics) {
      rubricRows.put (
        rubric, loader.loadRubricRowsByRubricId (rubric.getPrimaryKey())
      );
    }
  }

  /**
   * The [filterByIds] method...
   */
  public Map<Rubric, List<RubricRow>> filterByIds (String[] rowIds) {
    Map<Rubric, List<RubricRow>> filteredRows = new HashMap<>();

    return filteredRows;
  }

  /**
   * The [getRubricRows] method...
   */
  public Map<Rubric, List<RubricRow>> getRubricRows() {
    return rubricRows;
  }
}
