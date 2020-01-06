package action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rubric.Rubric;
import rubric.RubricEval;

/**
 * The [RubricEvalsLoaderAction] class...
 */
public class RubricEvalsLoaderAction extends RubricAction {
  private Map<Rubric, List<RubricEval>> rubricsEvals;

  final private List<Rubric> rubrics;

  /**
   * The [RubricEvalsLoaderAction] constructor...
   */
  public RubricEvalsLoaderAction (List<Rubric> rubrics) {
    this.rubrics = rubrics;

    rubricsEvals = new HashMap<>();
  }

  /**
   * The [perform] method...
   */
  public void perform() throws Exception {
    createLoader();

    for (Rubric rubric : rubrics) {
      List<RubricEval> rubricEvals =
        loader.loadRubricEvalsByRubricId (rubric.getPrimaryKey());

      rubricsEvals.putIfAbsent (rubric, new ArrayList<>());
      rubricsEvals.get (rubric).addAll (rubricEvals);
    }
  }

  /**
   * The [filterByIds] method...
   */
  public Map<Rubric, List<RubricEval>> filterByIds (String[] rubricEvalIds) {
    return rubricsEvals;
  }

  /**
   * The [getRubricsEvals] method...
   */
  public Map<Rubric, List<RubricEval>> getRubricsEvals() {
    return rubricsEvals;
  }
}
