package formulation.breakdown;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rubric.Rubric;
import rubric.RubricCell;
import rubric.RubricEval;
import rubric.RubricCellEval;

/**
 * The [EvalsBreakdownHomologue] class...
 */
public class EvalsBreakdownHomologue {
  final private Map<Rubric, List<RubricEval>> rubricsEvals;

  final private Map<RubricCell, List<RubricCellEval>> rubricCellsEvals;

  private Map<Rubric, List<RubricEval>> filteredRubricsEvals;

  private Map<RubricCell, List<RubricCellEval>> filteredRubricCellsEvals;

  /**
   * The [EvalsBreakdownHomologue] constructor...
   */
  public EvalsBreakdownHomologue (
    Map<Rubric, List<RubricEval>> rubricsEvals,
    Map<RubricCell, List<RubricCellEval>> cellsEvals
  ) {
    this.rubricsEvals = rubricsEvals;
    this.rubricCellsEvals = cellsEvals;

    _establishBreakdownHomologue();
  }

  /**
   * The [getHomologueRubricEvals] method...
   */
  public Map<Rubric, List<RubricEval>> getHomologueRubricEvals() {
    return filteredRubricsEvals;
  }

  /**
   * The [getHomologueCellEvals] method...
   */
  public Map<RubricCell, List<RubricCellEval>> getHomologueCellEvals() {
    return filteredRubricCellsEvals;
  }

  /**
   * The [_establishBreakdownHomologue] method...
   */
  private void _establishBreakdownHomologue() {
    _establishRubricEvalsHomologue();
    _establishCellEvalsHomologue();
  }

  /**
   * The [_establishRubricEvalsHomologue] method...
   */
  private void _establishRubricEvalsHomologue() {
    _filterRubricsEvals (_deduplicateStudentsEvals());
  }

  /**
   * The [_establishCellEvalsHomologue] method...
   */
  private void _establishCellEvalsHomologue() {
    _filterRubricCellsEvals (_deduplicateCellsEvals());
  }

  /**
   * The [_deduplicateStudentsEvals] method...
   */
  private Map<String, RubricEval> _deduplicateStudentsEvals() {
    Map<String, RubricEval> studentsEvals = new HashMap<>();

    for (Rubric rubric : rubricsEvals.keySet()) {
      List<RubricEval> evals = rubricsEvals.get (rubric);

      for (RubricEval eval : evals) {
        String evalKey = rubric.getPrimaryKey() + ":" + eval.getAssociationPk() +
          ":" + eval.getRespondentUserPk();

        if (!studentsEvals.containsKey (evalKey)) {
          if (eval.isCompletedInd()) {
            studentsEvals.put (evalKey, eval);
          }
        } else {
          if (0f < eval.getOverrideValue()) {
            studentsEvals.put (evalKey, eval);
          }
        }
      }
    }

    return studentsEvals;
  }

  /**
   * The [_filterRubricsEvals] method...
   */
  private void _filterRubricsEvals (Map<String, RubricEval> studentsEvals) {
    filteredRubricsEvals = new HashMap<>();

    for (String evalKey : studentsEvals.keySet()) {
      final String rubricPK = evalKey.split (":")[0];

      for (Rubric rubric : rubricsEvals.keySet()) {
        if (rubricPK.equals (rubric.getPrimaryKey())) {
          filteredRubricsEvals.putIfAbsent (rubric, new ArrayList<>());
          filteredRubricsEvals.get (rubric).add (studentsEvals.get (evalKey));
        }
      }
    }
  }

  /**
   * The [_deduplicateCellsEvals] method...
   */
  private Map<String, RubricCellEval> _deduplicateCellsEvals() {
    Map<String, RubricCellEval> cellsEvals = new HashMap<>();

    for (RubricCell cell : rubricCellsEvals.keySet()) {
      List<RubricCellEval> cellEvals = rubricCellsEvals.get (cell);

      for (RubricCellEval cellEval : cellEvals) {
        String cellEvalKey = cell.getPrimaryKey() + ":" + cellEval.getRowPk() +
          ":" + cellEval.getRubricEvalPk();

        if (!cellsEvals.containsKey (cellEvalKey) || cellEval.isOverrideInd()) {
          cellsEvals.put (cellEvalKey, cellEval);
        }
      }
    }

    return cellsEvals;
  }

  /**
   * The [_filterRubricCellsEvals] method...
   */
  private void _filterRubricCellsEvals (Map<String, RubricCellEval> cellsEvals) {
    filteredRubricCellsEvals = new HashMap<>();

    for (String cellEvalKey : cellsEvals.keySet()) {
      final String cellPk = cellEvalKey.split (":")[0];

      for (RubricCell cell : rubricCellsEvals.keySet()) {
        if (cellPk.equals (cell.getPrimaryKey())) {
          filteredRubricCellsEvals.putIfAbsent (cell, new ArrayList<>());
          filteredRubricCellsEvals.get (cell).add (cellsEvals.get (cellEvalKey));
        }
      }
    }
  }
}
