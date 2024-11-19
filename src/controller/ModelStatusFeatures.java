package controller;

import model.Coach;

public interface ModelStatusFeatures {
  void onTurnChanged(Coach currentCoach);
  void onGameOver(Coach winner, int redScore, int blueScore);
}
