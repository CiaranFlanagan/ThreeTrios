package cs3500.threetrios.model;

import java.util.List;

public interface BoardGrid {
  int getRows();
  int getColumns();
  BoardCell getCell(int row, int col);
  List<BoardCell> getAllCells();
}
