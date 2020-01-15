package formulation.breakdown;

import java.util.List;

import rubric.RubricCell;
import rubric.RubricCellEval;

/**
 * The [CellEvalsAverage] class...
 */
public class CellEvalsAverage {
  final private RubricCell cell;

  final private List<RubricCellEval> cellEvals;

  private int size;

  private float min;

  private float max;

  private float average;

  /**
   * The [CellEvalsAverage] constructor...
   */
  public CellEvalsAverage (RubricCell cell, List<RubricCellEval> cellEvals) {
    this.cell = cell;
    this.cellEvals = cellEvals;

    this.min = cell.getStartPointRange();
    this.max = cell.getEndPointRange();

    _determineSize();
    _determineAverage();
  }

  /**
   * The [getStartPoint] method...
   */
  public float getStartPoint() {
    return cell.getStartPointRange();
  }

  /**
   * The [getEndPoint] method...
   */
  public float getEndPoint() {
    return cell.getEndPointRange();
  }

  /**
   * The [getSize] method...
   */
  public int getSize() {
    return size;
  }

  /**
   * The [getMin] method...
   */
  public float getMin() {
    return (float) (Math.round (min) / 1.00);
  }

  /**
   * The [getMax] method...
   */
  public float getMax() {
    return (float) (Math.round (max) / 1.00);
  }

  /**
   * The [getAverage] method...
   */
  public float getAverage() {
    return average;
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
      average = 0f;

      return;
    }

    float sum = 0;

    for (RubricCellEval cellEval : cellEvals) {
      sum += cellEval.getSelectedPercent();
    }

    average = (float) (Math.round (sum / size * 100) / 1.00);
  }
}
