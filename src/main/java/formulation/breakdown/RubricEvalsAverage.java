package formulation.breakdown;

import java.util.List;

import rubric.Rubric;
import rubric.RubricEval;

/**
 * The [RubricEvalsAverage] class...
 */
public class RubricEvalsAverage {
  final private Rubric rubric;

  final private List<RubricEval> rubricEvals;

  private int size;

  private float average;

  /**
   * The [RubricEvalsAverage] constructor...
   */
  public RubricEvalsAverage (Rubric rubric, List<RubricEval> rubricEvals) {
    this.rubric = rubric;
    this.rubricEvals = rubricEvals;

    _determineSize();
    _determineAverage();
  }

  /**
   * The [getRubric] method...
   */
  public Rubric getRubric() {
    return rubric;
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
    size = rubricEvals.size();
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

    for (RubricEval rubricEval : rubricEvals) {
      sum += rubricEval.getCalculatedPercent();
    }

    average = sum / size * 100;
  }
}
