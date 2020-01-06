package formulation.breakdown;

import java.util.List;

import rubric.RubricCell;
import rubric.RubricCellEval;

/**
 * The [CellEvalAverage] class...
 */
public class CellEvalAverage {
  final private RubricCell cell;

  final private List<RubricCellEval> cellEvals;

  private float min;

  private float max;

  private int size;

  private float average;

  /**
   * The [CellEvalAverage] constructor...
   */
  public CellEvalAverage (RubricCell cell, List<RubricCellEval> cellEvals) {
    this.cell = cell;
    this.cellEvals = cellEvals;

    this.min = cell.getStartPointRange();
    this.max = cell.getEndPointRange();

    _determineSize();
    _determineAverage();
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
   * The [getSize] method...
   */
  public int getSize() {
    return size;
  }

  /**
   * The [getAverage] method...
   */
  public float getAverage() {
    return (float) (Math.round (average) / 1.00);
  }

  /**
   * The [_determineSize] method...
   */
  private void _determineSize() {
    size = 0;

    for (RubricCellEval cellEval : cellEvals) {
      if (cell.getPrimaryKey().equals (cellEval.getCellPk())) {
        size++;
      }
    }
  }

  /**
   * The [_determineAverage] method...
   */
  private void _determineAverage() {
    if (0 == size) {
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
