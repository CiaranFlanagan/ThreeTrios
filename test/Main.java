import controller.player.ControlPlayer;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * To represent the entry point into our program.
 */
public class Main {

  protected static Map<String, Function<CoachColor, ControlPlayer>> dispatchMap;

  static {
    dispatchMap = new HashMap<>();
    dispatchMap.put("0", Main :: makeHumanPlayer);
    dispatchMap.put("1", Main :: makeMostFlipsAIPlayer);
    dispatchMap.put("2", Main :: makeCornerPlayer);
    dispatchMap.put("3", Main :: makeDefensePlayer);
  }

  /**
   * The main method to launch the game.
   * <p>
   * Parses command-line arguments to configure player types and starts the game. If no
   * arguments or invalid arguments are provided, displays a usage message.
   * </p>
   * @param args command-line arguments, where the first two numbers specify player types
   */
  public static void main(String[] args) {
    if (args.length != 2) {
      needsHelp();
      return;
    }

    String fst = args[0];
    String snd = args[1];

    if (!dispatchMap.containsKey(fst) || !dispatchMap.containsKey(snd)) {
      needsHelp();
      return;
    }

    ControlPlayer red = dispatchMap.get(fst).apply(CoachColor.RED);
    ControlPlayer blue = dispatchMap.get(fst).apply(CoachColor.BLUE);
    new PlayableGame(Main :: makeModel, red, blue);


  }

  private static void needsHelp() {
    System.out.println(
        "enter two numbers with a space in between to select types of " + "players");
    System.out.println("0: human player");
    System.out.println("1: most-flips AI player");
    System.out.println("2: corner AI player");
    System.out.println("3: defense AI player");
    System.out.println("ex: java -jar ThreeTrios.jar 0 0");

  }

  private static ControlPlayer makeHumanPlayer(CoachColor color) {
    GUIGridInteractive grid = new GUIGridInteractive(new DrawGrid());
    if (color == CoachColor.RED) {
      return new ControlPlayer(color, new GUIPlayerInteractive(
          new GUIHandInteractive(new DrawHand()), new GUIHandBase(new DrawHand()), grid));
    } else {
      return new ControlPlayer(color,
                               new GUIPlayerInteractive(new GUIHandBase(new DrawHand()),
                                                        new GUIHandInteractive(
                                                            new DrawHand()), grid));
    }
  }

  private static ControlPlayer makeMostFlipsAIPlayer(CoachColor color) {
    return makeDelegatePlayer(color, new MostFlips());
  }

  private static ControlPlayer makeCornerPlayer(CoachColor color) {
    return makeDelegatePlayer(color, new CornerStrategy());
  }

  private static ControlPlayer makeDefensePlayer(CoachColor color) {
    return makeDelegatePlayer(color, new DefenseStrategy());
  }


  private static Model makeModel() {
    Model model = new ModelBase();
    model.startGame(makeGrid(), makeCards(), new RefereeDefault());
    return model;
  }

  private static Grid makeGrid() {
    return ConfigGrid.scannerToGrid(TestFiles.GRID_ASSN_HARD);
  }

  private static List<Card> makeCards() {
    return ConfigCard.scannerToCardList(TestFiles.CC_LARGE);
  }

  private static ControlPlayer makeDelegatePlayer(CoachColor color,
                                                  StrategyAbstract strategy) {
    return new ControlPlayer(color, new GUIPlayerDelegate(new GUIHandBase(new DrawHand()),
                                                          new GUIHandBase(new DrawHand()),
                                                          new GUIGridBase(new DrawGrid()),
                                                          color, strategy));
  }

}
