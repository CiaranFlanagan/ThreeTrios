package cs3500.threetrios.test.view.unit;

import cs3500.threetrios.controller.ConfigCard;
import cs3500.threetrios.controller.ConfigGrid;
import cs3500.threetrios.controller.TestFiles;
import cs3500.threetrios.model.Model;
import cs3500.threetrios.model.Referee;
import cs3500.threetrios.model.RefereeDefault;
import cs3500.threetrios.model.ModelBase;
import cs3500.threetrios.view.View;
import org.junit.Before;
import org.junit.Test;
import static cs3500.threetrios.controller.TestFiles.CC_LARGE;
import static cs3500.threetrios.controller.TestFiles.CC_SMALL;
import static cs3500.threetrios.controller.TestFiles.GRID_CONNECTED_HOLES;
import static cs3500.threetrios.controller.TestFiles.GRID_DISC_HOLES;
import static cs3500.threetrios.controller.TestFiles.GRID_NO_HOLES;

/**
 * Tests for the view.
 */
public class TestView<OD> {
  protected View<OD> view;
  protected Model model;
  private final Referee ref = new RefereeDefault();

  @Before
  public void setup() {
    model = new ModelBase();
  }


  @Test
  public void testGridNoHolesView() {
    try {
      model.startGame(ConfigGrid.scannerToGrid(TestFiles.safeFileToScanner(GRID_NO_HOLES)),
                      ConfigCard.scannerToCardList(TestFiles.safeFileToScanner(CC_SMALL)), ref);
    } catch (Exception e) {
      Assert.fail();
    }
  }

  @Test
  public void testGridConnectedView() {
    try {

      model.startGame(ConfigGrid.scannerToGrid(TestFiles.safeFileToScanner(GRID_CONNECTED_HOLES)),
                      ConfigCard.scannerToCardList(TestFiles.safeFileToScanner(CC_LARGE)), ref);
    } catch (Exception e) {
      Assert.fail();
    }
  }

  @Test
  public void testGridDisconnectedView() {
    try {

      model.startGame(ConfigGrid.scannerToGrid(TestFiles.safeFileToScanner(GRID_DISC_HOLES)),
                      ConfigCard.scannerToCardList(TestFiles.safeFileToScanner(CC_LARGE)), ref);
    } catch (Exception e) {
      Assert.fail();
    }
  }
}
