package cs3500.threetrios.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * To abstract and bundle grid and coach mutation from models.
 */
public abstract class ModelAbstract implements Model {
  protected Grid grid;
  protected Map<Coach, List<Card>> coachesHands;
  protected Coach currentCoach;
  protected List<Consumer<ModelAbstract>> moves;

  protected ModelAbstract() {
    Map<Coach, List<Card>> temp = new EnumMap<>(Coach.class);
    temp.put(Coach.RED, new ArrayList<>());
    temp.put(Coach.BLUE, new ArrayList<>());
    // only red and blue, so just get and then update the hand accordingly
    coachesHands = Collections.unmodifiableMap(temp);
    currentCoach = Coach.RED;
  }

  @Override
  public Map<Coach, List<Card>> curCoachesHands() {
    Map<Coach, List<Card>> temp = new EnumMap<>(coachesHands);
    for (Coach coach : temp.keySet()) {
      temp.put(coach, Collections.unmodifiableList(
              coachesHands.get(coach).stream().map(Card::copy).collect(Collectors.toList())));
    }
    return temp;
  }

  protected GridCellAbstract setGridCardAt(int row, int col, Card card) {
    return grid.placeCardOn(row, col, card);
  }

  @Override
  public Grid curGrid() {
    return grid;
  }

}
