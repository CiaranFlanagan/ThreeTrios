package cs3500.threetrios.utils;

public class IntPoint2D {
  public final int x;
  public final int y;

  public IntPoint2D(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof IntPoint2D) {
      IntPoint2D that = (IntPoint2D) obj;
      return this.x == that.x && this.y == that.y;
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return x * y;
  }

  @Override
  public String toString() {
    return "(" + x + ", " + y + ")";
  }
}
