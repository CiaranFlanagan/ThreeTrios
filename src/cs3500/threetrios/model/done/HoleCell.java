package cs3500.threetrios.model.done;


import cs3500.threetrios.model.IPlayer;

public class HoleCell extends ABoardCell {

  public HoleCell() {
    super();
  }


  public void battleAllPossible() {
    throw new IllegalStateException("hole cell should not be called into battle");
  }


  public void battleAndCascadeOnLose(TTCard card, CardinalDirection direction, IPlayer maybeNewOwner) {
    // do nothing
  }

  @Override
  public String toString() {
    return "X";
  }
}
