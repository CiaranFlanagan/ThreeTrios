package controller;

public interface PlayerActionsFeatures {
  public interface PlayerActionFeatures {
    void selectCard(int cardIndex);
    void selectGridCell(int row, int col);
    void playCard();
    void endTurn();
  }
}
