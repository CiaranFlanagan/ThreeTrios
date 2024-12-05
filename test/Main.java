import controller.AbstractControlPlayer;
import controller.ControlPlayer;
import controller.CornerStrategy;
import controller.DefenseStrategy;
import controller.MostFlips;
import controller.StrategyAbstract;
import model.Card;
import model.CoachColor;
import model.GameListener;
import model.Grid;
import model.Model;
import model.ModelBase;
import model.PlayableGameImpl;
import model.RefereeDefault;
import provider.BluePlayerView;
import provider.RedPlayerView;
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
import java.util.Scanner;
import java.util.function.Function;

/**
 * To represent the entry point into our program.
 */
public class Main {

  protected static Map<String, Function<CoachColor, GameListener>> dispatchMap;

  static {
    dispatchMap = new HashMap<>();
    dispatchMap.put("0", Main :: makeHumanPlayer);
    dispatchMap.put("1", Main :: makeMostFlipsAIPlayer);
    dispatchMap.put("2", Main :: makeCornerPlayer);
    dispatchMap.put("3", Main :: makeDefensePlayer);
    dispatchMap.put("4", Main :: makeAdaptedPlayer);
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

    GameListener red = dispatchMap.get(fst).apply(CoachColor.RED);
    GameListener blue = dispatchMap.get(snd).apply(CoachColor.BLUE);
    new PlayableGameImpl().start(Main :: makeModel, red, blue);

    Scanner input = new Scanner(System.in);
    while (input.hasNext()) {
      if (input.nextLine().strip().contains("q")) {
        System.exit(0);
      }
    }

  }

  private static void needsHelp() {
    System.out.println(
        "enter two numbers with a space in between to select types of " + "players");
    System.out.println("0: human player");
    System.out.println("1: most-flips AI player");
    System.out.println("2: corner AI player");
    System.out.println("3: defense AI player");
    System.out.println("4: adapted human player");
    System.out.println("example two player game: 'java -jar ThreeTrios.jar 0 4'");

  }

  private static AbstractControlPlayer makeHumanPlayer(CoachColor color) {
    GUIGridInteractive grid = new GUIGridInteractive(new DrawGrid());
    GUIPlayerInteractive guiAndPlayer;
    if (color == CoachColor.RED) {
      guiAndPlayer = new GUIPlayerInteractive(new GUIHandInteractive(new DrawHand()),
                                              new GUIHandBase(new DrawHand()), grid);
      return new ControlPlayer(color, guiAndPlayer, guiAndPlayer);
    } else {
      guiAndPlayer = new GUIPlayerInteractive(new GUIHandBase(new DrawHand()),
                                              new GUIHandInteractive(new DrawHand()),
                                              grid);
      return new ControlPlayer(color, guiAndPlayer, guiAndPlayer);
    }
  }

  private static AbstractControlPlayer makeAdaptedPlayer(CoachColor color) {
    view.InteractiveViewAdapter view =
        new view.InteractiveViewAdapter(m ->
                                            color == CoachColor.RED ?
                                                new RedPlayerView(m) :
                                                new BluePlayerView(m));
    return new controller.ControlPlayer(color, view, view);
  }

  private static AbstractControlPlayer makeMostFlipsAIPlayer(CoachColor color) {
    return makeDelegatePlayer(color, new MostFlips());
  }

  private static AbstractControlPlayer makeCornerPlayer(CoachColor color) {
    return makeDelegatePlayer(color, new CornerStrategy());
  }

  private static AbstractControlPlayer makeDefensePlayer(CoachColor color) {
    return makeDelegatePlayer(color, new DefenseStrategy());
  }

  private static Model makeModel() {
    Model model = new ModelBase();
    model.startGame(makeGrid(), makeCards(), new RefereeDefault());
    return model;
  }

  private static Grid makeGrid() {
    return ConfigGrid.scannerToGrid(TestFiles.GRID_NO_HOLES_THREE_BY_THREE);
  }

  private static List<Card> makeCards() {
    return ConfigCard.scannerToCardList(TestFiles.CC_LARGE);
  }

  private static AbstractControlPlayer makeDelegatePlayer(CoachColor color,
                                                          StrategyAbstract strategy) {
    return new ControlPlayer(color, new GUIPlayerDelegate(new GUIHandBase(new DrawHand()),
                                                          new GUIHandBase(new DrawHand()),
                                                          new GUIGridBase(new DrawGrid()),
                                                          color), strategy);
  }

}
