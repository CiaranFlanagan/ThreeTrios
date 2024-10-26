package cs3500.threetrios.model;

import java.util.Map;

public interface BoardCell {
  CellType getType();
  Map<Direction, BoardCell> getNeighbors();
  void setNeighbor(Direction direction, BoardCell neighbor);
  Position getPosition();
}
