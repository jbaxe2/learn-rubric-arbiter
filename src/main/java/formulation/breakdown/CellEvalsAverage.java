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

  private float average;

  /**
   * The [CellEvalsAverage] constructor...
   */
  public CellEvalsAverage (RubricCell cell, List<RubricCellEval> cellEvals) {
    this.cell = cell;
    this.cellEvals = cellEvals;

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

    average = sum / size * 100;
  }
}
