package cs3500.threetrios.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CardCell implements BoardCell{
  private final Position position;
  private final Map<Direction, BoardCell> neighbors;
  private Card card;
  private GamePlayer owner;

  public CardCell(Position position) {
    if (position ==  null) {
      throw new IllegalArgumentException("Position Cannot Be Null");
    }
    this.position = position;
    this.neighbors = new HashMap<>();
  }

  public CellType getType() {
    return CellType.CARD_CELL;
  }

  public Map<Direction, BoardCell> getNeighbors() {
    return Collections.unmodifiableMap(neighbors);
  }

  @Override
  public void setNeighbor(Direction direction, BoardCell neighbor) {
    if (direction == null || neighbor == null) {
      throw new IllegalArgumentException("Direction and neighbor cannot be null");
    }
    neighbors.put(direction, neighbor);
  }

  @Override
  public Position getPosition() {
    return position;
  }

  // Specific methods for CardCell
  public void placeCard(Card card, GamePlayer owner) {
    if (this.card != null) {
      throw new IllegalStateException("Cell already has a card");
    }
    if (card == null || owner == null) {
      throw new IllegalArgumentException("Card and owner cannot be null");
    }
    this.card = card;
    this.owner = owner;
  }

  public Card getCard() {
    return card;
  }

  public GamePlayer getOwner() {
    return owner;
  }

  public void setOwner(GamePlayer owner) {
    if (card == null) {
      throw new IllegalStateException("Cell is empty");
    }
    if (owner == null) {
      throw new IllegalArgumentException("Owner cannot be null");
    }
    this.owner = owner;
  }

  public boolean hasCard() {
    return card != null;
  }

}
