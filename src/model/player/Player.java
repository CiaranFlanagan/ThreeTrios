package model.player;

import model.Model;

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

  Difficulty difficulty();

  Consumer<Model> nextMove();

}

