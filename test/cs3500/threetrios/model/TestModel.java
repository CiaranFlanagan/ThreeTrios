package cs3500.threetrios.model;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import cs3500.threetrios.controller.CardConfig;
import cs3500.threetrios.controller.GridConfig;

public class TestModel {
  private Grid gridNoHoles;
  private Grid gridConnectedHoles;
  private Grid gridDisconnectedHoles;
  private List<Card> cardsSmall;
  private List<Card> cardsLarge;
  private ThreeTriosModel model;
  private BattlePhaseReferee referee;
  @Before
  public void setUp() {
    model = new ThreeTriosModel();
    referee = new DefaultReferee();

    gridNoHoles = GridConfig.fileToGridBoard(new File("./test/grid_no_holes.txt"));
    gridConnectedHoles = GridConfig.fileToGridBoard(new File("./test/grid_connected_holes.txt"));
    gridDisconnectedHoles = GridConfig.fileToGridBoard(new File("./test/grid_disconnected_holes.txt"));

    cardsSmall = CardConfig.fileToTTCardList(new File("./test/cards_small.txt"));
    cardsLarge = CardConfig.fileToTTCardList(new File("./test/cards_large.txt"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGameAlreadyStarted() {
    model.startGame(gridNoHoles, cardsSmall, referee);
    model.startGame(gridNoHoles, cardsSmall, referee);
  }

}

