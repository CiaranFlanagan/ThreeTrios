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

import java.awt.Color;
import java.util.List;

/**
 * To represent the entry point into our program.
 */
public class Main {



  public static void main(String[] args) {
    if (args.length == 0) {
      System.err.println("run jar again with 'help'");
      return;
    }
    if (!needsHelp(args[0])) {
      int fst = Integer.valueOf(args[0]);
      int snd = Integer.valueOf(args[1]);
      ControlPlayer red = makePlayer(fst, CoachColor.RED);
      ControlPlayer blue = makePlayer(snd, CoachColor.BLUE);
      new PlayableGame(Main :: makeModel, red, blue);
    }
    
  }
  
  private static boolean needsHelp(String s) {
    if (s.equals("help")) {
      System.out.println("enter two numbers with a space in between to select types of "
                             + "players");
      System.out.println("0: human player");
      System.out.println("1: most-flips AI player");
      System.out.println("2: corner AI player");
      System.out.println("3: defense AI player");
      System.out.println("ex: java -jar ThreeTrios.jar 0 0");
      return true;
    } else {
      return false;
    }
  }

  private static ControlPlayer makePlayer(int i, CoachColor color) {
    switch (i) {
      case 0: return makeHumanPlayer(color);
      case 1: return makeMostFlipsAIPlayer(color);
      case 2: return makeCornerPlayer(color);
      case 3: return makeDefensePlayer(color);
    }
    throw new IllegalArgumentException("bad int; restart");
  }

  private static ControlPlayer makeHumanPlayer(CoachColor color) {
    GUIGridInteractive grid = new GUIGridInteractive(new DrawGrid());
    if (color == CoachColor.RED) {
      return new ControlPlayer(color,
                               new GUIPlayerInteractive(new GUIHandInteractive(new DrawHand()),
                                                 new GUIHandBase(new DrawHand()), grid));
    } else {
      return new ControlPlayer(color, new GUIPlayerInteractive(new GUIHandBase(new DrawHand()),
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
                                                          new GUIGridBase(new DrawGrid()), color,
                                                          strategy));
  }

}
