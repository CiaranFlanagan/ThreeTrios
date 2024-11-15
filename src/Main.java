import cs3500.threetrios.controller.ConfigCard;
import cs3500.threetrios.controller.ConfigGrid;
import cs3500.threetrios.model.Model;
import cs3500.threetrios.model.ModelBase;
import cs3500.threetrios.model.Referee;
import cs3500.threetrios.model.RefereeDefault;
import cs3500.threetrios.view.View;
import cs3500.threetrios.view.ViewGUI;


import javax.swing.JFrame;

/**
 * to represent the entry point into our program.
 */
public class Main {
  public static void main(String[] args) {
    Model model = new ModelBase();
    model.startGame(ConfigGrid.scannerToGrid(TestFiles.safeFileToScanner(GRID_DISC_HOLES)),
                    ConfigCard.scannerToCardList(TestFiles.safeFileToScanner(CC_LARGE)), ref);
    View<JFrame> view = new ViewGUI();
    final Referee ref = new RefereeDefault();
    JFrame frame = new JFrame("three trios hwk 6");

  }
}
