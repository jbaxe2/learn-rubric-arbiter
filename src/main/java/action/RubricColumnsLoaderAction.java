package action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rubric.Rubric;
import rubric.RubricColumn;

/**
 * The [RubricColumnsLoaderAction] class...
 */
public class RubricColumnsLoaderAction extends RubricAction {
  private Map<Rubric, List<RubricColumn>> rubricColumns;

  final private List<Rubric> rubrics;

  /**
   * The [RubricColumnsLoaderAction] constructor...
   */
  public RubricColumnsLoaderAction (List<Rubric> rubrics) {
    this.rubrics = rubrics;

    rubricColumns = new HashMap<>();
  }

  /**
   * The [perform] method...
   */
  public void perform() throws Exception {
    createLoader();

    for (Rubric rubric : rubrics) {
      rubricColumns.put (
        rubric, loader.loadRubricColumnsByRubricId (rubric.getPrimaryKey())
      );
    }
  }

  /**
   * The [filterByIds] method...
   */
  public Map<Rubric, List<RubricColumn>> filterByIds (String[] columnIds) {
    Map<Rubric, List<RubricColumn>> filteredColumns = new HashMap<>();

    for (String columnId : columnIds) {
      for (Rubric courseRubric : rubrics) {
        for (RubricColumn rubricColumn : rubricColumns.get (courseRubric)) {
          if (columnId.equals (rubricColumn.getPrimaryKey())) {
            filteredColumns.putIfAbsent (courseRubric, new ArrayList<>());
            filteredColumns.get (courseRubric).add (rubricColumn);
          }
        }
      }
    }

    return filteredColumns;
  }

  /**
   * The [getRubricColumns] method...
   */
  public Map<Rubric, List<RubricColumn>> getRubricColumns() {
    return rubricColumns;
  }
}
