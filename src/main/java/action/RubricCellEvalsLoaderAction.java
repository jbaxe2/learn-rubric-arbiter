package action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rubric.RubricCell;
import rubric.RubricCellEval;

/**
 * The [RubricCellEvalsLoaderAction] class...
 */
public class RubricCellEvalsLoaderAction extends RubricAction {
  private Map<RubricCell, List<RubricCellEval>> cellsEvals;

  final private Collection<List<RubricCell>> rubricsCells;

  /**
   * The [RubricCellEvalsLoaderAction] constructor...
   */
  public RubricCellEvalsLoaderAction (Collection<List<RubricCell>> rubricsCells) {
    this.rubricsCells = rubricsCells;

    cellsEvals = new HashMap<>();
  }

  /**
   * The [perform] method...
   */
  public void perform() throws Exception {
    createLoader();

    for (List<RubricCell> rubricCells : rubricsCells) {
      for (RubricCell rubricCell : rubricCells) {
        List<RubricCellEval> cellEvals = loader.loadRubricCellEvalsByRowCellIds (
          rubricCell.getRowPk(), rubricCell.getPrimaryKey()
        );

        cellsEvals.putIfAbsent (rubricCell, new ArrayList<>());
        cellsEvals.get (rubricCell).addAll (cellEvals);
      }
    }
  }

  /**
   * The [filterByIds] method...
   */
  public Map<RubricCell, List<RubricCellEval>> filterByIds (String[] cellEvalIds) {
    return cellsEvals;
  }

  /**
   * The [getRubricCellEvals] method...
   */
  public Map<RubricCell, List<RubricCellEval>> getRubricCellEvals() {
    return cellsEvals;
  }
}
