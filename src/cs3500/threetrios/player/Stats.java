package cs3500.threetrios.player;

import java.util.HashMap;
import java.util.Map;

/**
 * To represent the statistics of players.
 */
public class Stats {
  private int wins;
  private int losses;

  // constructor
  private Stats() {

  }

  /**
   * To represent a database that tracks players and their statistics.
   */
  public static class DB {
    private static final Map<Player, Stats> db = new HashMap<>();

    public static void add(Player player) {
      db.put(player, new Stats());
    }
  }

  /**
   * number of wins of a player.
   * @return - number of wins of a player
   */
  public int numWins() {
    return this.wins;
  }

  /**
   * number of losses of a player.
   * @return - number of losses of a player
   */
  public int numLosses() {
    return this.losses;
  }

  /**
   * to increment the number of wins of a player.
   */
  public void incWins() {
    this.wins++;
  }

  /**
   * to increment the number of losses of a player.
   */
  public void incLosses() {
    this.losses++;
  }


}
