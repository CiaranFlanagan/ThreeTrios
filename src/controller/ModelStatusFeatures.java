package controller;

import model.CoachColor;

public interface ModelStatusFeatures {
  void onTurnChanged(CoachColor currentCoach);
  void onGameOver(CoachColor winner, int redScore, int blueScore);
}
