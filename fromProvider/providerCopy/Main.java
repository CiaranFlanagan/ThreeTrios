package providerCopy;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import cs3500.threetrios.ai.Flip;
import cs3500.threetrios.ai.GoForCorner;
import cs3500.threetrios.ai.HybridStrategy;
import cs3500.threetrios.ai.MinMaxStrategy;
import provider.ai.PosnStrategy;
import cs3500.threetrios.controller.ThreeTriosControllerImpl;
import provider.game.Game;
import cs3500.threetrios.game.GameModel;
import provider.game.ReadOnlyGameModel;
import provider.gui.BluePlayerView;
import provider.gui.RedPlayerView;
import provider.gui.ThreeTriosGameView;
import provider.gui.ThreeTriosViewImpl;

/**
 * The main entry point for the Three Trios game, setting up the model, view,
 * and controller, and starting the game.
 */
public class Main {

  /**
   * Running the game.
   * @param args for the game set up.
   */
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      try {
        if (args == null || args.length < 2) {
          throw new IllegalArgumentException("\"human\" \"human\" or \"human\" \"strategy1");
        }

        GameModel model = new GameModel("docs/boardNoHoles.config");

        if ("human".equalsIgnoreCase(args[1])) {
          // Human vs Human
          System.out.println("PLAYING HUMAN");
          createHumanVsHumanGame(model);
        } else {
          // Human vs AI
          System.out.println("PLAYING AI");
          createHumanVsAIGame(model, args);
        }
      } catch (Exception e) {
        throw new IllegalArgumentException("Need more arguments!", e);
      }
    });
  }

  /**
   * Sets up a Human vs Human game.
   */
  private static void createHumanVsHumanGame(GameModel model) {
    ThreeTriosGameView redView = new RedPlayerView(model);
    ThreeTriosGameView blueView = new BluePlayerView(model);

    new ThreeTriosControllerImpl(model, redView);
    new ThreeTriosControllerImpl(model, blueView);
  }

  /**
   * Sets up a Human vs AI game.
   */
  private static void createHumanVsAIGame(Game model, String[] args) {
    ThreeTriosGameView unifiedView = new ThreeTriosViewImpl((ReadOnlyGameModel) model);

    List<PosnStrategy> strategies = new ArrayList<>();
    for (int i = 1; i < args.length; i++) {
      PosnStrategy strategy = pickStrategy(args[i]);
      if (strategy == null) {
        throw new IllegalArgumentException("Invalid strategy: " + args[i]);
      }
      strategies.add(strategy);
    }

    PosnStrategy combinedStrategy = strategies.size() > 1
            ? new HybridStrategy(strategies)
            : strategies.get(0);

    new ThreeTriosControllerImpl(model, unifiedView, combinedStrategy);
  }

  /**
   * Maps a strategy name to its corresponding PosnStrategy implementation.
   */
  private static PosnStrategy pickStrategy(String strategyName) {
    switch (strategyName.toLowerCase()) {
      case "strategy1":
        return new Flip();
      case "strategy2":
        return new MinMaxStrategy();
      case "strategy3":
        return new GoForCorner();
      default:
        System.err.println("Unknown strategy: " + strategyName);
        return null;
    }
  }
}
