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

  private Map<RubricCell, CellEvalAverage> cellEvalAverage;

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
    return cellEvalAverage;
  }

  /**
   * The [_determineBreakdown] method...
   */
  private void _determineBreakdown() {
    cellEvalAverage = new HashMap<>();

    for (RubricCell cell : cellsEvals.keySet()) {
      cellEvalAverage.put (cell, new CellEvalAverage (cellsEvals.get (cell)));
    }
  }
}
