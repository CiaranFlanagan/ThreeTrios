package model;

import java.util.concurrent.Callable;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public interface PlayableGameListener extends
    BiConsumer<Consumer<Move>, Callable<Model>> {

  @Override
  void accept(Consumer<Move> moveConsumer, Callable<Model> modelCallable);

}
