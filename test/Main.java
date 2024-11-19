import model.Model;
import model.ModelBase;
import model.RefereeDefault;
import utils.ConfigCard;
import utils.ConfigGrid;
import utils.TestFiles;
import view.View;
import view.ViewGUI;

import javax.swing.JFrame;

/**
 * To represent the entry point into our program.
 */
public class Main {

  /**
   * To run the program by just starting a game.
   *
   * @param args - command line args, which don't get processed currently.
   */
  public static void main(String[] args) {
    Model model = new ModelBase();
    model.startGame(ConfigGrid.scannerToGrid(TestFiles.GRID_ASSN_HARD),
                    ConfigCard.scannerToCardList(TestFiles.CC_LARGE),
                    new RefereeDefault());

    View<JFrame> view = new ViewGUI(model);
    JFrame frame = new JFrame("three trios hwk 6");
    view.renderTo(frame);
  }

}
