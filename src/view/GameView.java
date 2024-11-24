package view;

import model.ModelReadOnly;

public interface GameView {
  void renderMessage(String message);

  void renderModel(ModelReadOnly model);
}
