package view;

import model.ModelReadOnly;

/**
 * Represents the view for a game. This interface had methods for rendering
 * messages and the game model in a read-only version.
 */
public interface GameView {
  void renderMessage(String message);

  void renderModel(ModelReadOnly model);
}
