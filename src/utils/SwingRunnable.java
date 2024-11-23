package utils;

import javax.swing.SwingWorker;

/**
 * A utility class for running tasks in the background using {@link SwingWorker}.
 * <p>
 * SwingWorker is a utility in the Swing framework for performing background tasks
 * without blocking the Event Dispatch Thread (EDT). This allows long-running
 * operations to be executed while keeping the GUI responsive.
 * </p>
 */
public class SwingRunnable extends SwingWorker<Void, Void> {

  private Runnable todo;

  private SwingRunnable(Runnable todo) {
    this.todo = todo;
  }

  /**
   * Executes the given task in a background thread.
   * <p>
   * This method initializes a new {@link SwingRunnable} to perform the provided
   * task without blocking the GUI thread. It suppresses any exceptions encountered
   * during execution.
   * </p>
   *
   * @param todo the task to execute
   */
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
