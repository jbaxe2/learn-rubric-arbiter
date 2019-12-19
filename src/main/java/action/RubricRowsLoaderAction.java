package action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rubric.Rubric;
import rubric.RubricRow;

/**
 * The [RubricRowsLoaderAction] class...
 */
public class RubricRowsLoaderAction extends RubricAction {
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
    createLoader();

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

    for (String rowId : rowIds) {
      for (Rubric courseRubric : rubrics) {
        for (RubricRow rubricRow : rubricRows.get (courseRubric)) {
          if (rowId.equals (rubricRow.getPrimaryKey())) {
            filteredRows.putIfAbsent (courseRubric, new ArrayList<>());
            filteredRows.get (courseRubric).add (rubricRow);
          }
        }
      }
    }

    return filteredRows;
  }

  /**
   * The [getRubricRows] method...
   */
  public Map<Rubric, List<RubricRow>> getRubricRows() {
    return rubricRows;
  }
}
