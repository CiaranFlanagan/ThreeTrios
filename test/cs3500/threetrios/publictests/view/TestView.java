package cs3500.threetrios.publictests.view;

import cs3500.threetrios.controller.CardConfig;
import cs3500.threetrios.controller.GridConfig;
import cs3500.threetrios.model.BattlePhaseReferee;
import cs3500.threetrios.model.DefaultReferee;
import cs3500.threetrios.model.ThreeTriosModel;
import cs3500.threetrios.view.TextView;
import cs3500.threetrios.view.ThreeTriosView;
import org.junit.Test;

import static cs3500.threetrios.TestFiles.CC_SMALL;
import static cs3500.threetrios.TestFiles.GRID_NO_HOLES;

public class TestView {
  private ThreeTriosView view;
  private ThreeTriosModel model;
  private final BattlePhaseReferee ref = new DefaultReferee();

  @Test
  public void test1() {
    model = new ThreeTriosModel();
    model.startGame(GridConfig.fileToGridBoard(GRID_NO_HOLES),
            CardConfig.fileToTTCardList(CC_SMALL), ref);
    view = new TextView(model);
    view.render();
  }

  @Test
  public void test2() {
    model = new ThreeTriosModel();
    model.startGame(GridConfig.fileToGridBoard(GRID_NO_HOLES),
            CardConfig.fileToTTCardList(CC_SMALL), ref);
    view = new TextView(model);
    view.render();
  }
}
