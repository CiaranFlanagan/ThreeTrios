package cs3500.threetrios.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class HoleCell implements BoardCell{
  private final Position position;
  private final Map<Direction, BoardCell> neighbors;
  public HoleCell(Position position) {
    if (position == null) {
      throw new IllegalArgumentException("Position cannot be null");
    }
    this.position = position;
    this.neighbors = new HashMap<>();
  }


  /**
   * @return
   */
  @Override
  public CellType getType() {
    return CellType.HOLE;
  }

  /**
   * @return
   */
  @Override
  public Map<Direction, BoardCell> getNeighbors() {
    return Collections.unmodifiableMap(neighbors);
  }

  /**
   * @param direction
   * @param neighbor
   */
  @Override
  public void setNeighbor(Direction direction, BoardCell neighbor) {

  }

  /**
   * @param direction
   * @param neighbor
   */

  /**
   * @return
   */
  @Override
  public Position getPosition() {
    return position;
  }
}
