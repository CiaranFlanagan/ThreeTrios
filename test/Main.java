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
import model.RefComposeBeats;
import model.Referee;
import model.RefereeDefault;
import model.RefereeFallenAce;
import model.RefereeNegate;
import provider.BluePlayerView;
import provider.RedPlayerView;
import utils.ConfigCard;
import utils.ConfigGrid;
import utils.TestFiles;
import view.DrawGrid;
import view.DrawHand;
import view.GUIGridBase;
import view.GUIHandBase;
import view.GUIPlayerAssn9;
import view.GUIPlayerDelegate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

/**
 * To represent the entry point into our program.
 */
public class Main {

  protected static Map<String, Function<CoachColor, GameListener>> controlMap;
  protected static Map<String, Referee> refMap;

  static {
    controlMap = new HashMap<>();
    controlMap.put("0", Main :: makeHumanPlayer);
    controlMap.put("1", Main :: makeMostFlipsAIPlayer);
    controlMap.put("2", Main :: makeCornerPlayer);
    controlMap.put("3", Main :: makeDefensePlayer);
    controlMap.put("4", Main :: makeAdaptedPlayer);
    refMap = new HashMap<>();
    refMap.put("10", new RefereeDefault());
    refMap.put("11", new RefereeNegate());
    refMap.put("12", new RefereeFallenAce());
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
    if (args.length < 2) {
      needsHelp();
      return;
    }

    String fst = args[0];
    String snd = args[1];

    if (!controlMap.containsKey(fst) || !controlMap.containsKey(snd)) {
      System.out.println("invalid player options: " + fst + " " + snd);
      needsHelp();
      System.exit(-1);
      return;
    }

    GameListener red = controlMap.get(fst).apply(CoachColor.RED);
    GameListener blue = controlMap.get(snd).apply(CoachColor.BLUE);
    List<Referee> composedBeats = new ArrayList<>();
    for (int arg = 2; arg < args.length; arg ++) {
      if (refMap.containsKey(args[arg])) {
        composedBeats.add(refMap.get(args[arg]));
      } else {
        System.out.println("invalid ref arg: " + args[arg]);
        needsHelp();
        System.exit(-1);
        return;
      }
    }
    Referee ref = new RefComposeBeats(composedBeats);
    new PlayableGameImpl().start(() -> makeModel(ref), red, blue);

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
    System.out.println("or enter three numbers where the third is the referee");
    System.out.println("Player selections");
    System.out.println("0: human player");
    System.out.println("1: most-flips AI player");
    System.out.println("2: corner AI player");
    System.out.println("3: defense AI player");
    System.out.println("4: adapted human player");
    System.out.println("Referee Selections");
    System.out.println("10: default");
    System.out.println("11: negate");
    System.out.println("12: fallen ace");
    System.out.println("example two player game: 'java -jar ThreeTrios.jar 0 4'");

  }

  private static AbstractControlPlayer makeHumanPlayer(CoachColor color) {
    GUIPlayerAssn9 playerView = new GUIPlayerAssn9(color);
    return new ControlPlayer(color, playerView, playerView);
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

  private static Model makeModel(Referee ref) {
    Model model = new ModelBase();
    model.startGame(makeGrid(), makeCards(), ref);
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
