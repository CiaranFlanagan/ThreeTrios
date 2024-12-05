package model;

import provider.COLOR;

public class CoachLens implements Lens<CoachColor, COLOR> {

  @Override
  public COLOR forward(CoachColor coachColor) {
    switch (coachColor) {
      case BLUE:
        return COLOR.BLUE;
      case RED:
        return COLOR.RED;
      default:
        return null;
    }
  }

  @Override
  public CoachColor backward(COLOR color) {
    switch (color) {
      case BLUE:
        return CoachColor.BLUE;
      case RED:
        return CoachColor.RED;
      default:
        return null;
    }
  }

}
