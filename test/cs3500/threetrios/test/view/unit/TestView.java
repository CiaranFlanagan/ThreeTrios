package cs3500.threetrios.test.view.unit;

import cs3500.threetrios.controller.ConfigCard;
import cs3500.threetrios.controller.ConfigGrid;
import cs3500.threetrios.controller.TestFiles;
import cs3500.threetrios.model.Model;
import cs3500.threetrios.model.ModelReadOnly;
import cs3500.threetrios.model.Referee;
import cs3500.threetrios.model.RefereeDefault;
import cs3500.threetrios.model.ModelBase;
import cs3500.threetrios.utils.LineWriter;
import cs3500.threetrios.view.ViewTextBase;
import cs3500.threetrios.view.View;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.swing.JFrame;

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
    model.startGame(ConfigGrid.scannerToGrid(TestFiles.safeFileToScanner(GRID_NO_HOLES)),
            ConfigCard.scannerToCardList(TestFiles.safeFileToScanner(CC_SMALL)), ref);
  }

  @Test
  public void testGridConnectedView() {
    model.startGame(ConfigGrid.scannerToGrid(TestFiles.safeFileToScanner(GRID_CONNECTED_HOLES)),
            ConfigCard.scannerToCardList(TestFiles.safeFileToScanner(CC_LARGE)), ref);
  }

  @Test
  public void testGridDisconnectedView() {
    model.startGame(ConfigGrid.scannerToGrid(TestFiles.safeFileToScanner(GRID_DISC_HOLES)),
            ConfigCard.scannerToCardList(TestFiles.safeFileToScanner(CC_LARGE)), ref);
  }
}
