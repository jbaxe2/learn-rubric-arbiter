package formulation.breakdown;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import formulation.Resultable;

import rubric.Rubric;
import rubric.RubricCell;
import rubric.RubricCellEval;
import rubric.RubricEval;

/**
 * The [RubricsEvalsBreakdown] class...
 */
public class RubricsEvalsBreakdown implements Resultable {
  final private Map<Rubric, List<RubricEval>> rubricsEvals;

  final private Map<RubricCell, List<RubricCellEval>> cellsEvals;

  private Map<RubricCell, CellEvalsAverage> cellsEvalsAverages;

  private Map<Rubric, RubricEvalsAverage> rubricsEvalsAverages;

  /**
   * The [RubricsEvalsBreakdown] constructor...
   */
  public RubricsEvalsBreakdown (
    Map<Rubric, List<RubricEval>> rubricsEvals,
    Map<RubricCell, List<RubricCellEval>> cellsEvals
  ) {
    this.rubricsEvals = rubricsEvals;
    this.cellsEvals = cellsEvals;

    _determineRubricsEvalAverages();
    _determineCellsEvalAverages();
  }

  /**
   * The [obtainResults] method...
   */
  public Map<RubricCell, CellEvalsAverage> obtainResults() {
    return cellsEvalsAverages;
  }

  /**
   * The [getRubricsEvalsAverages] method...
   */
  public Map<Rubric, RubricEvalsAverage> getRubricsEvalsAverages() {
    return rubricsEvalsAverages;
  }

  /**
   * The [getCellsEvalsAverages] method...
   */
  public Map<RubricCell, CellEvalsAverage> getCellsEvalsAverages() {
    return cellsEvalsAverages;
  }

  /**
   * The [_determineRubricsEvalAverages] method...
   */
  private void _determineRubricsEvalAverages() {
    rubricsEvalsAverages = new HashMap<>();

    for (Rubric rubric : rubricsEvals.keySet()) {
      RubricEvalsAverage evalAverage =
        new RubricEvalsAverage (rubric, rubricsEvals.get (rubric));

      rubricsEvalsAverages.put (rubric, evalAverage);
    }
  }

  /**
   * The [_determineCellsEvalAverages] method...
   */
  private void _determineCellsEvalAverages() {
    cellsEvalsAverages = new HashMap<>();

    for (RubricCell cell : cellsEvals.keySet()) {
      CellEvalsAverage evalAverage =
        new CellEvalsAverage (cell, cellsEvals.get (cell));

      cellsEvalsAverages.put (cell, evalAverage);
    }
  }
}
