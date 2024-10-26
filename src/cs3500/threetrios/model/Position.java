package cs3500.threetrios.model;

public class Position {
  private final int row;
  private final int col;

  public Position(int row, int col) {
    this.row = row;
    this.col = col;
  }

  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }


  //need to override equals and hashcode for all classes eventually!!!!
}

