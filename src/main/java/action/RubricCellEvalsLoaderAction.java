package action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rubric.RubricCell;
import rubric.RubricCellEval;

/**
 * The [RubricCellEvalsLoaderAction] class...
 */
public class RubricCellEvalsLoaderAction extends RubricAction {
  private Map<RubricCell, List<RubricCellEval>> cellEvals;

  private List<RubricCell> rubricCells;

  /**
   * The [RubricCellEvalsLoaderAction] constructor...
   */
  public RubricCellEvalsLoaderAction (List<RubricCell> rubricCells) {
    this.rubricCells = rubricCells;

    cellEvals = new HashMap<>();
  }

  /**
   * The [perform] method...
   */
  public void perform() throws Exception {}

  /**
   * The [filterByIds] method...
   */
  public Map<RubricCell, List<RubricCellEval>> filterByIds (String[] cellEvalIds) {
    return cellEvals;
  }

  /**
   * The [getRubricCellEvals] method...
   */
  public Map<RubricCell, List<RubricCellEval>> getRubricCellEvals() {
    return cellEvals;
  }
}
