package cs3500.threetrios.model;

/**
 * Represents the color of a player.
 */
public enum Coach {
  RED,
  BLUE;


  @Override
  public String toString() {
    switch (this) {
      case RED:
        return "Red";
      case BLUE:
        return "Blue";
      default:
        throw new IllegalArgumentException("Invalid color");
    }
  }

  /**
   * Returns the opposing coach.
   *
   * @return the opponent Coach of the current coach (RED's opponent is BLUE and vice versa)
   * @throws IllegalStateException if the current state is invalid
   */
  public Coach opponent() {
    switch (this) {
      case RED:
        return BLUE;
      case BLUE:
        return RED;
      default:
        throw new IllegalStateException();
    }
  }
}
