package cs3500.threetrios.player;

import java.io.Serializable;

/**
 * Represents a player in the game.
 */
public interface Player extends Serializable {
  /**
   * returns the name of the player.
   * @return the player's name
   * @throws IllegalStateException if not initialized
   */
  String getName();

  /**
   * gives an integer measurement that describes skill, more is better.
   * @return - an integer measure of skill.
   */
  Difficulty difficulty();

}

