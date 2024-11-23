import controller.player.Player;
import controller.strategy.CornerStrategy;
import controller.strategy.DefenseStrategy;
import controller.strategy.MostFlips;
import controller.strategy.StrategyAbstract;
import model.Card;
import model.CoachColor;
import model.Grid;
import model.Model;
import model.ModelBase;
import model.PlayableGame;
import model.PlayableGameListener;
import model.RefereeDefault;
import utils.ConfigCard;
import utils.ConfigGrid;
import utils.TestFiles;
import view.DrawGrid;
import view.DrawHand;
import view.GUIGridBase;
import view.GUIGridInteractive;
import view.GUIHandBase;
import view.GUIHandInteractive;
import view.GUIPlayerDelegate;
import view.GUIPlayerInteractive;

import java.util.List;

/**
 * To represent the entry point into our program.
 */
public class Main {

  /**
   * To run the program by just starting a game.
   * @param args - command line args, which don't get processed currently.
   */
  public static void main1(String[] args) {
    PlayableGame gc = new PlayableGame(Main :: makeModel, makeHumanPlayer(CoachColor.RED),
                                       makeHumanPlayer(CoachColor.BLUE));
  }

  private static Model makeModel() {
    Model model = new ModelBase();
    model.startGame(makeGrid(), makeCards(), new RefereeDefault());
    return model;
  }

  private static PlayableGameListener makeHumanPlayer(CoachColor color) {
    GUIGridInteractive grid = new GUIGridInteractive(new DrawGrid());
    if (color == CoachColor.RED) {
      return new Player(color,
                        new GUIPlayerInteractive(new GUIHandInteractive(new DrawHand()),
                                                 new GUIHandBase(new DrawHand()), grid));
    } else {
      return new Player(color, new GUIPlayerInteractive(new GUIHandBase(new DrawHand()),
                                                        new GUIHandInteractive(
                                                            new DrawHand()), grid));
    }
  }

  private static Grid makeGrid() {
    return ConfigGrid.scannerToGrid(TestFiles.GRID_ASSN_HARD);
  }

  private static List<Card> makeCards() {
    return ConfigCard.scannerToCardList(TestFiles.CC_LARGE);
  }

  public static void main(String[] args) {
    PlayableGame gc = new PlayableGame(Main :: makeModel, makeHumanPlayer(CoachColor.RED),
                                       new Player(CoachColor.BLUE, new MostFlips()));
  }

  public static void main2(String[] args) {
    PlayableGame gc = new PlayableGame(Main :: makeModel,
                                       makeDelegatePlayer(CoachColor.RED,
                                                          new CornerStrategy()),
                                       makeAIPlayer(CoachColor.BLUE,
                                                    new DefenseStrategy()));
  }

  private static PlayableGameListener makeDelegatePlayer(CoachColor color,
                                                         StrategyAbstract strategy) {
    return new Player(color, new GUIPlayerDelegate(new GUIHandBase(new DrawHand()),
                                                   new GUIHandBase(new DrawHand()),
                                                   new GUIGridBase(new DrawGrid()), color,
                                                   strategy));
  }

  private static PlayableGameListener makeAIPlayer(CoachColor color,
                                                   StrategyAbstract strategy) {
    return new Player(color, strategy);
  }

}
