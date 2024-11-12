package cs3500.threetrios.model;

import java.util.List;
import java.util.function.Consumer;

/**
 * To abstract and bundle grid and coach mutation from models.
 */
public abstract class ModelAbstract implements Model {
  protected Grid grid;
  protected Coach coachRed;
  protected Coach coachBlue;
  protected Coach currentCoach;
  protected List<Consumer<ModelAbstract>> moves;

  protected ModelAbstract() {
    this.coachRed = new Coach(Coach.Color.Red);
    this.coachBlue = new Coach(Coach.Color.Blue);
    currentCoach = coachRed;
  }

  protected void addCardTo(Coach coach, Card card) {
    coach.addCard(card);
  }

  protected void updateGrid(Grid grid) {
    this.grid = grid;
  }

  protected GridCellAbstract setGridCardAt(int row, int col, Card card) {
    return grid.placeCardOn(row, col, card);
  }

  @Override
  public Grid getGrid() {
    return grid;
  }
}
