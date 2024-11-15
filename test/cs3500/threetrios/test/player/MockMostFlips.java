package cs3500.threetrios.test.player;

import cs3500.threetrios.model.Card;
import cs3500.threetrios.model.Coach;
import cs3500.threetrios.model.Grid;
import cs3500.threetrios.model.Model;
import cs3500.threetrios.model.ModelBase;
import cs3500.threetrios.model.Referee;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MockMostFlips extends ModelBase {
  List<List<Integer>> log;

  public MockMostFlips(List<List<Integer>> log) {
    this.log = log;
  }

  @Override
  public void placeCard(int idx, int row, int col) throws IllegalStateException,
          IllegalArgumentException {
    log.add(List.of(row, col));
  }

}
