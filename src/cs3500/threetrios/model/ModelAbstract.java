package cs3500.threetrios.model;

/*
INVARIANT: getCurrentCoach().toString() will always be one of: "Red" or "Blue".
 */
public abstract class ModelAbstract implements Model {
  protected Grid grid;
  protected Coach coachRed;
  protected Coach coachBlue;
  protected Coach currentCoach;

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
