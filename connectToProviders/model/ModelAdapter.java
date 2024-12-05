package model;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ModelAdapter implements
    Function<ModelReadOnly, provider.game.ReadOnlyGameModel>,
    provider.game.ReadOnlyGameModel {

  protected ModelReadOnly model;

  @Override
  public provider.game.ReadOnlyGameModel apply(ModelReadOnly modelReadOnly) {
    model = modelReadOnly;
    return this;
  }

  @Override
  public int getGridSize() {
    return Math.min(model.numCols(), model.numRows());
  }

  @Override
  public provider.card.Card getCardAt(int row, int col) {
    return model.cardAt(row, col).map(new CardAdapter() :: forward).orElse(null);
  }

  @Override
  public boolean isGameOver() {
    return this.model.isGameOver();
  }

  @Override
  public String getWinner() {
    switch (model.winner()) {
      case RED:
        return "Red wins";
      case BLUE:
        return "Blue wins";
      default:
        return "tie";
    }
  }

  @Override
  public provider.player.Player getCurrentPlayer() {
    return makePlayer(model.curCoach());
  }

  private provider.player.Player makePlayer(CoachColor color) {
    PlayerForProvider player = new PlayerForProvider();
    player.setColor(new CoachLens().forward(color));
    player.setHand(model.curCoachesHands()
                        .get(color)
                        .stream()
                        .map(new CardAdapter() :: forward)
                        .collect(
                            Collectors.toList()));
    return player;
  }

  @Override
  public boolean isMoveLegal(int row, int col) {
    return model.canPlayAt(row, col);
  }

  @Override
  public provider.player.Player[] getPlayers() {
    return Stream.of(CoachColor.RED, CoachColor.BLUE)
                 .map(this :: makePlayer)
                 .collect(
                   Collectors.toList())
                 .toArray(new provider.player.Player[2]);
  }

  @Override
  public provider.game.Grid getGrid() {
    return new model.GridAdapter().forward(model.curGrid());
  }

}
