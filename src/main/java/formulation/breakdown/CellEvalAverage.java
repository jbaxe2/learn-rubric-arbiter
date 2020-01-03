package formulation.breakdown;

import java.util.List;

import rubric.RubricCell;
import rubric.RubricCellEval;

/**
 * The [CellEvalAverage] class...
 */
public class CellEvalAverage {
  final private List<RubricCellEval> cellEvals;

  private float min;

  private float max;

  private float average;

  /**
   * The [CellEvalAverage] constructor...
   */
  public CellEvalAverage (RubricCell cell, List<RubricCellEval> cellEvals) {
    this.min = cell.getStartPointRange();
    this.max = cell.getEndPointRange();

    this.cellEvals = cellEvals;

    _determineAverage();
  }

  /**
   * The [getSize] method...
   */
  public int getSize() {
    return cellEvals.size();
  }

  /**
   * The [getMin] method...
   */
  public float getMin() {
    return min;
  }

  /**
   * The [getMax] method...
   */
  public float getMax() {
    return max;
  }

  /**
   * The [getAverage] method...
   */
  public float getAverage() {
    return (float) (Math.round (average) / 1.00);
  }

  /**
   * The [_determineAverage] method...
   */
  private void _determineAverage() {
    if (0 == cellEvals.size()) {
      average = 0;

      return;
    }

    float sum = 0;

    for (RubricCellEval cellEval : cellEvals) {
      sum += cellEval.getSelectedPercent();
    }

    average = sum / cellEvals.size() * 100;
  }
}
