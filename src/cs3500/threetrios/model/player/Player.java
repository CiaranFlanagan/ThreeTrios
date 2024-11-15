package cs3500.threetrios.model.player;

import cs3500.threetrios.model.Model;

import java.util.function.Consumer;

/**
 * Represents a player in the game.
 */
public interface Player {
  /**
   * returns the name of the player.
   *
   * @return the player's name
   * @throws IllegalStateException if not initialized
   */
  String getName();

  /**
   * gives an integer measurement that describes skill, more is better.
   *
   * @return - an integer measure of skill.
   */
  Difficulty difficulty();

  Consumer<Model> nextMove();

}

