package utils;

import javax.swing.SwingWorker;


public class SwingRunnable extends SwingWorker<Void, Void> {

  private Runnable todo;

  private SwingRunnable(Runnable todo) {
    this.todo = todo;
  }

  public static void execute(Runnable todo) {
    try {
      new SwingRunnable(todo).doInBackground();
    } catch (Exception ignored) {
      // do nothing
    }
  }

  @Override
  protected Void doInBackground() {
    todo.run();
    return null;
  }

}
