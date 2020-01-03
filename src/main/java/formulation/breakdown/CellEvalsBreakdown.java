package formulation.breakdown;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import formulation.Resultable;

import rubric.RubricCell;
import rubric.RubricCellEval;

/**
 * The [CellEvalBreakdown] class...
 */
public class CellEvalsBreakdown implements Resultable {
  final private Map<RubricCell, List<RubricCellEval>> cellsEvals;

  private Map<RubricCell, CellEvalAverage> cellsEvalAverages;

  /**
   * The [CellEvalBreakdown] constructor...
   */
  public CellEvalsBreakdown (Map<RubricCell, List<RubricCellEval>> cellsEvals) {
    this.cellsEvals = cellsEvals;

    _determineBreakdown();
  }

  /**
   * The [obtainResults] method...
   */
  public Map<RubricCell, CellEvalAverage> obtainResults() {
    return cellsEvalAverages;
  }

  /**
   * The [_determineBreakdown] method...
   */
  private void _determineBreakdown() {
    cellsEvalAverages = new HashMap<>();

    for (RubricCell cell : cellsEvals.keySet()) {
      CellEvalAverage evalAverage =
        new CellEvalAverage (cell, cellsEvals.get (cell));

      cellsEvalAverages.put (cell, evalAverage);
    }
  }
}
