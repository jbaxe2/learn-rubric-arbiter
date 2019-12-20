package formulation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import _error.InvalidFormulation;

import action.RubricCellEvalsLoaderAction;
import action.RubricCellsLoaderAction;
import action.RubricColumnsLoaderAction;

import formulation.breakdown.CellEvalsBreakdown;

import rubric.Rubric;
import rubric.RubricCell;
import rubric.RubricCellEval;
import rubric.RubricColumn;

/**
 * The [BreakdownFormulation] class...
 */
public class BreakdownFormulation extends Formulation {
  private Map<Rubric, List<RubricColumn>> rubricsExpectations;

  private Map<Rubric, List<RubricCell>> rubricsCells;

  private Map<RubricCell, List<RubricCellEval>> rubricCellsEvals;

  public BreakdownFormulation (
    HttpServletRequest request, String roleId,
    String[] courseIds, String[] rubricIds, String[] criteriaIds
  ) throws InvalidFormulation {
    super (request, roleId, courseIds, rubricIds, criteriaIds);

    rubricsExpectations = new HashMap<>();
    rubricsCells = new HashMap<>();
    rubricCellsEvals = new HashMap<>();
  }

  /**
   * The [formulate] method...
   */
  public Resultable formulate() throws InvalidFormulation {
    try {
      _handlePerformances();

      return _provideResults();
    } catch (Exception e) {
      throw new InvalidFormulation (e.getMessage());
    }
  }

  /**
   * The [_handlePerformances] method...
   */
  private void _handlePerformances() throws Exception {
    _performRetrieveExpectations();
    _performRetrieveCells();
    _performRetrieveCellsEvals();
  }

  /**
   * The [_provideResults] method...
   */
  private Resultable _provideResults() {
    return new CellEvalsBreakdown (rubricCellsEvals);
  }

  /**
   * The [_performRetrieveExpectations] method...
   */
  private void _performRetrieveExpectations() throws Exception {
    RubricColumnsLoaderAction expectationsLoader =
      new RubricColumnsLoaderAction (rubrics);

    expectationsLoader.perform();

    rubricsExpectations = expectationsLoader.getRubricColumns();
  }

  /**
   * The [_performRetrieveCells] method...
   */
  private void _performRetrieveCells() throws Exception {
    RubricCellsLoaderAction cellsLoader = new RubricCellsLoaderAction (rubrics);
    cellsLoader.perform();

    rubricsCells = cellsLoader.getRubricCells();
  }

  /**
   * The [_performRetrieveCellsEvals] method...
   */
  private void _performRetrieveCellsEvals() throws Exception {
    RubricCellEvalsLoaderAction cellsEvalsLoader;

    for (List<RubricCell> rubricCells : rubricsCells.values()) {
      cellsEvalsLoader = new RubricCellEvalsLoaderAction (rubricCells);
      cellsEvalsLoader.perform();

      rubricCellsEvals.putAll (cellsEvalsLoader.getRubricCellEvals());
    }
  }
}
