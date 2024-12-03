package model;

import java.util.function.Supplier;

/**
 * Represents a playable game in three trios, uses suppliers and listeners to facilitate.
 */
public interface PlayableGame {

  /**
   * Stars a playable game with a model supplier and listeners for each player.
   * @param modelSupplier supplier to create a new instance of the game model
   * @param red           listener for the red player
   * @param blue          listener for the blue player
   */
  void start(Supplier<Model> modelSupplier, GameListener red, GameListener blue);
}
