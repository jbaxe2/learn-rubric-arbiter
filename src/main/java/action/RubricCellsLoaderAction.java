package action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import _error.ImproperRubricInfo;

import rubric.Rubric;
import rubric.RubricCell;
import rubric.RubricColumn;
import rubric.RubricRow;

/**
 * The [RubricCellsLoaderAction] class...
 */
public class RubricCellsLoaderAction extends RubricAction {
  private Map<Rubric, List<RubricCell>> rubricCells;

  final private List<Rubric> rubrics;

  /**
   * The [RubricCellsLoaderAction] constructor...
   */
  public RubricCellsLoaderAction (List<Rubric> rubrics) {
    this.rubrics = rubrics;

    rubricCells = new HashMap<>();
  }

  /**
   * The [perform] method...
   */
  public void perform() throws Exception {
    createLoader();

    for (Rubric rubric : rubrics) {
      List<RubricColumn> columns =
        loader.loadRubricColumnsByRubricId (rubric.getPrimaryKey());

      List<RubricRow> rows =
        loader.loadRubricRowsByRubricId (rubric.getPrimaryKey());

      rubricCells.putIfAbsent (rubric, new ArrayList<>());

      for (RubricColumn column : columns) {
        for (RubricRow row : rows) {
          RubricCell cell = loader.loadRubricCellByColumnRowIds (
            column.getPrimaryKey(), row.getPrimaryKey()
          );

          if (null == cell) {
            throw new ImproperRubricInfo ("Missing rubric cell information.");
          }

          rubricCells.get (rubric).add (cell);
        }
      }
    }
  }

  /**
   * The [filterByIds] method...
   */
  public Map<Rubric, List<RubricCell>> filterByIds (String[] cellIds) {
    Map<Rubric, List<RubricCell>> filteredCells = new HashMap<>();

    for (String cellId : cellIds) {
      for (Rubric courseRubric : rubrics) {
        for (RubricCell cell : rubricCells.get (courseRubric)) {
          if (cellId.equals (cell.getPrimaryKey())) {
            filteredCells.putIfAbsent (courseRubric, new ArrayList<>());
            filteredCells.get (courseRubric).add (cell);
          }
        }
      }
    }

    return filteredCells;
  }

  /**
   * The [getRubricCells] method...
   */
  public Map<Rubric, List<RubricCell>> getRubricCells() {
    return rubricCells;
  }
}
