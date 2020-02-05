package action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rubric.*;

/**
 * The [RubricCellEvalsLoaderAction] class...
 */
public class RubricCellEvalsLoaderAction extends RubricAction {
  private Map<RubricCell, List<RubricCellEval>> cellsEvals;

  final private Map<Rubric, List<RubricCell>> rubricsCells;

  final private Map<Rubric, List<RubricEval>> rubricsEvals;

  //final private List<RubricCell> rubricCells;

  /**
   * The [RubricCellEvalsLoaderAction] constructor...
   */
  /*public RubricCellEvalsLoaderAction (List<RubricCell> rubricCells) {
    this.rubricCells = rubricCells;

    cellsEvals = new HashMap<>();
  }*/

  public RubricCellEvalsLoaderAction (
    Map<Rubric, List<RubricCell>> rubricsCells,
    Map<Rubric, List<RubricEval>> rubricsEvals
  ) {
    this.rubricsCells = rubricsCells;
    this.rubricsEvals = rubricsEvals;

    cellsEvals = new HashMap<>();
  }

  /**
   * The [perform] method...
   */
  public void perform() throws Exception {
    createLoader();

    for (Rubric rubric : rubricsCells.keySet()) {
      List<RubricCell> rubricCells = rubricsCells.get (rubric);
      List<RubricEval> rubricEvals = rubricsEvals.get (rubric);

      for (RubricCell rubricCell : rubricCells) {
        cellsEvals.putIfAbsent (rubricCell, new ArrayList<>());

        for (RubricEval rubricEval : rubricEvals) {
          List<RubricCellEval> cellEvals = loader.loadRubricCellEvalsByEvalCellIds (
            rubricEval.getPrimaryKey(), rubricCell.getPrimaryKey()
          );

          cellsEvals.get (rubricCell).addAll (cellEvals);
        }
      }
    }

    /*
    for (RubricCell rubricCell : rubricCells) {
      List<RubricCellEval> cellEvals = loader.loadRubricCellEvalsByRowCellIds (
        rubricCell.getRowPk(), rubricCell.getPrimaryKey()
      );

      cellsEvals.putIfAbsent (rubricCell, new ArrayList<>());
      cellsEvals.get (rubricCell).addAll (cellEvals);
    }
    */
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
