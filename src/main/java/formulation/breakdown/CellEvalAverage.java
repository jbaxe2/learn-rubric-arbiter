package formulation.breakdown;

import java.util.List;

import rubric.RubricCellEval;

/**
 * The [CellEvalAverage] class...
 */
public class CellEvalAverage {
  final private List<RubricCellEval> cellEvals;

  private float average;

  /**
   * The [CellEvalAverage] constructor...
   */
  public CellEvalAverage (List<RubricCellEval> cellEvals) {
    this.cellEvals = cellEvals;

    _determineAverage();
  }

  /**
   * The [getAverage] method...
   */
  public float getAverage() {
    return average;
  }

  /**
   * The [_determineAverage] method...
   */
  private void _determineAverage() {
    float sum = 0;

    for (RubricCellEval cellEval : cellEvals) {
      sum += cellEval.getSelectedPercent();
    }

    average = sum / cellEvals.size();
  }
}
